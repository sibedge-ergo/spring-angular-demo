package com.sibedge.ergo.shared.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Describes supported audit event types.
 */
@Getter
@RequiredArgsConstructor
public enum EventKey {
    FILTER_PERSON_LIST("Person data access through the filter");

    private final String description;
}
