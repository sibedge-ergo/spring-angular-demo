package com.sibedge.ergo.shared.transport;

import java.util.List;

import lombok.Data;

/**
 * Describes the standard transport list wrapper
 * for other data.
 *
 * @param <T> type of nested data
 */
@Data(staticConstructor = "of")
public class ListData<T> {
    private final List<T> content;
    private final long total;
}
