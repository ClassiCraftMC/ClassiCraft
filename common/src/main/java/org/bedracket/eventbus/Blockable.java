package org.bedracket.eventbus;

@SuppressWarnings("unused SpellCheckingInspection")
public interface Blockable {

    /**
     * Gets the blocked state of this event. A blocked event will not
     * be executed in the server
     *
     * @return true if this event is blocked
     */
    boolean isBlocked();

    void setBlocked(boolean block);
}