package com.sibedge.ergo.util;

import java.time.LocalDate;

import com.sibedge.ergo.shared.data.Gender;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Describes a form filter representation with criteria for tests.
 */
@Getter
@Builder
@ToString
public class TestPersonFilter {
    private String personalId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate fromDateOfBirth;
    private LocalDate toDateOfBirth;
}
