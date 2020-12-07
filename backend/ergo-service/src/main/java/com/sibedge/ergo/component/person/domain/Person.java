package com.sibedge.ergo.component.person.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.sibedge.ergo.shared.data.BaseEntity;
import com.sibedge.ergo.shared.data.Gender;

import lombok.Getter;
import lombok.Setter;

/**
 * Describes a persistent person entity.
 */
@Getter
@Setter
@Entity
public class Person extends BaseEntity {
    @NotBlank
    @Size(max = 50)
    private String personalId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @PastOrPresent
    private LocalDate dateOfBirth;
}
