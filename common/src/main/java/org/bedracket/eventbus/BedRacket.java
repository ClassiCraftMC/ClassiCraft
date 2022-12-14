package org.bedracket.eventbus;

import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BedRacket {

    public static HashMap<Class<?>, List<RegisteredListener>> eventHandlers = new HashMap<>();
    public static Logger LOGGER = LogUtils.getLogger();

    public static final BedRacket EVENT_BUS = new BedRacket();

    public int addListener(Listener listener) {
        int registered = 0;
        for (Method method : listener.getClass().getMethods()) {
            Class<?>[] put = method.getParameterTypes();
            if (put.length == 0) continue;

            Class<?> clazz = put[0];
            Class<?> clazzo = put[0];

            while (clazz.getSuperclass() != null) {
                if (clazz.equals(Event.class)) break;

                clazz = clazz.getSuperclass();
            }

            if (clazz.equals(Event.class)) { // first argument of method is subclass of Event
                EventHandler handler = method.getAnnotation(EventHandler.class);
                List<RegisteredListener> list = eventHandlers.getOrDefault(clazzo, new ArrayList<>());
                EventExecutor executor = new EventExecutor() {
                    @Override
                    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException, InvocationTargetException, IllegalAccessException {
                        try {
                            if (!clazzo.isAssignableFrom(event.getClass())) {
                                return;
                            }
                            method.invoke(listener, event);
                        } catch (InvocationTargetException ex) {
                            throw new EventException(ex.getCause());
                        } catch (Throwable t) {
                            throw new EventException(t);
                        }
                    }
                };
                RegisteredListener registeredListener = new RegisteredListener(listener, handler, executor, method);
                list.add(registeredListener);
                eventHandlers.put(clazzo, list);
                registered++;
            }
        }
        return registered;
    }

    public Event post(Class<? extends Event> type, Event event) throws EventException {
        post(eventHandlers.getOrDefault(type, new ArrayList<>()), event);
        return event;
    }

    public void post(List<RegisteredListener> listeners, Event event) throws EventException {
        if (event.isAsynchronous()) {
            Multithreading.runAsync(() -> {
                try {
                    post0(listeners, event);
                } catch (EventException e) {
                    e.printStackTrace();
                    LOGGER.error( "Could not pass event " + event.getEventName() + " to Mod", e);
                }
            });
        } else {
            post0(listeners, event);
        }
    }

    public void post0(List<RegisteredListener> listeners, Event event) throws EventException {
        for (RegisteredListener registeredListener : listeners) {
            Method method = registeredListener.getMethod();
            try {
                if (event instanceof Blockable && ((Blockable) event).isBlocked()) {
                    return;
                }
                if (event instanceof Cancellable) {
                    if (((Cancellable) event).isCancelled() && registeredListener.isIgnoringCancelled()) {
                        return;
                    }
                }
                method.invoke(registeredListener.getListener(), event);
                registeredListener.getExecutor().execute(registeredListener.getListener(), event);
            } catch (EventException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void unPost(Event event) {
        eventHandlers.remove(event.getClass());
    }
}
