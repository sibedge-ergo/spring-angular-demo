package com.sibedge.ergo.shared.constraint;

import java.time.LocalDate;

import lombok.Value;

/**
 * Test validation time period type.
 */
@Value(staticConstructor = "of")
@ClosedDatePeriod(start = "first", end = "second")
public class TestTwoDateData {

    LocalDate first;
    LocalDate second;

}
