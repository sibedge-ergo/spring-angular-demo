package com.sibedge.ergo.event;

import com.sibedge.ergo.shared.event.AuditEvent;
import com.sibedge.ergo.shared.event.EventHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * Saves event data to a log file under INFO level, if enabled.
 * It uses the provided {@link org.slf4j.Logger} to write an event.
 *
 * <p>This handler adapts Spring based event environment via {@link org.springframework.context.ApplicationListener}
 */
@Slf4j
@Component
@RequiredArgsConstructor
class LogAuditEventHandler implements EventHandler<AuditEvent>,
        ApplicationListener<PayloadApplicationEvent<AuditEvent>> {
    @Override
    public void handle(AuditEvent event) {
        if (log.isInfoEnabled()) {
            log.info("Audit event: '{}'", event);
        }
    }

    @Override
    public void onApplicationEvent(PayloadApplicationEvent<AuditEvent> auditEvent) {
        handle(auditEvent.getPayload());
    }
}
