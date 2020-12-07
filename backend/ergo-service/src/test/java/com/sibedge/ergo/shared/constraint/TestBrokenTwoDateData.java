package com.sibedge.ergo.shared.constraint;

import java.time.LocalDate;

import lombok.Value;

/**
 * Wrong configured annotation on class definition
 * example (for test purposes).
 */
@Value(staticConstructor = "of")
@ClosedDatePeriod(start = "one", end = "two")
public class TestBrokenTwoDateData {

    LocalDate first;
    LocalDate second;

}
