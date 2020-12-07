package com.sibedge.ergo.shared.event;

import java.time.LocalDateTime;

import com.sibedge.ergo.shared.data.EventKey;

import lombok.Value;

/**
 * Describes a system audit event.
 */
@Value(staticConstructor = "of")
public class AuditEvent implements Event {
    EventKey key;
    String payload;
    LocalDateTime timestemp;
}
