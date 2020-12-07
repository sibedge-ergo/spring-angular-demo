package com.sibedge.ergo.shared.event;

/**
 * Independent event handler contract.
 * May be implemented for different event bus environments.
 *
 * @param <E> event type.
 */
public interface EventHandler<E> {

    /**
     * Handles an event.
     *
     * @param event target event
     */
    void handle(E event);

}
