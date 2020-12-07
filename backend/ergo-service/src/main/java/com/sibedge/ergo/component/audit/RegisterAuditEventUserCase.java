package com.sibedge.ergo.component.audit;

import com.sibedge.ergo.shared.event.AuditEvent;

/**
 * Registers a new audit event.
 */
public interface RegisterAuditEventUserCase {

    /**
     * Saves a new event.
     *
     * @param event target event to be stored
     */
    void execute(AuditEvent event);

}
