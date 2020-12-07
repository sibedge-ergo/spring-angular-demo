package com.sibedge.ergo.shared.event;

import java.time.LocalDateTime;

import com.sibedge.ergo.shared.data.EventKey;

/**
 * Service event marker interface.
 */
public interface Event {

    /**
     * Gets the key which describes a type of event.
     * @return event key
     */
    EventKey getKey();

    /**
     * Gets event details.
     *
     * @return event payload
     */
    String getPayload();

    /**
     * Gets event registration time.
     *
     * @return event time
     */
    LocalDateTime getTimestemp();

}
