package com.sibedge.ergo.event;

import com.sibedge.ergo.shared.event.AuditEvent;
import com.sibedge.ergo.shared.event.EventSender;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AuditEventSender implements EventSender<AuditEvent> {

    private final ApplicationEventPublisher publisher;

    @Override
    public void send(AuditEvent event) {
        publisher.publishEvent(event);
    }
}
