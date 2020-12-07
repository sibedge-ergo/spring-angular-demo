package com.sibedge.ergo.shared.transport;

import java.time.LocalDate;

import com.sibedge.ergo.shared.data.Gender;

import lombok.Value;

/**
 * Describes a transpor view of a person record.
 */
@Value(staticConstructor = "of")
public class PersonData {
    private String personalId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
}
