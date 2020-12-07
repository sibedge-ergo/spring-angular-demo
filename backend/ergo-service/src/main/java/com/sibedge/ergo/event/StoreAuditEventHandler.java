package com.sibedge.ergo.event;

import com.sibedge.ergo.component.audit.RegisterAuditEventUserCase;
import com.sibedge.ergo.shared.event.AuditEvent;
import com.sibedge.ergo.shared.event.EventHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * Saves event data to a persistent storage.
 * This handler adapts Spring based event environment via {@link org.springframework.context.ApplicationListener}
 */
@Component
@RequiredArgsConstructor
class StoreAuditEventHandler implements EventHandler<AuditEvent>,
        ApplicationListener<PayloadApplicationEvent<AuditEvent>> {
    private final RegisterAuditEventUserCase registerAuditEventUserCase;

    @Override
    public void handle(AuditEvent event) {
        registerAuditEventUserCase.execute(event);
    }

    @Override
    public void onApplicationEvent(PayloadApplicationEvent<AuditEvent> event) {
        handle(event.getPayload());
    }
}
