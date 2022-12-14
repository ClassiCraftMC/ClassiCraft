package org.bedracket.eventbus;

import java.lang.reflect.Type;

public interface IGenericEvent<T>
{
    Type getGenericType();
}
