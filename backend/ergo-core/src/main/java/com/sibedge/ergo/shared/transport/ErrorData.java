package com.sibedge.ergo.shared.transport;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Describes the standard error message
 * for transport purposes.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ErrorData {
    List<String> errors;
}

