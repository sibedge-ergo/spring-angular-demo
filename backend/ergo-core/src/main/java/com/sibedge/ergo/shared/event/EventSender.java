package com.sibedge.ergo.shared.event;

/**
 * Independent event sender contract.
 * May be implemented for different event bus environments.
 *
 * @param <E> event type
 */
public interface EventSender<E> {

    /**
     * Sends an event.
     *
     * @param event target event
     */
    void send(E event);

}
