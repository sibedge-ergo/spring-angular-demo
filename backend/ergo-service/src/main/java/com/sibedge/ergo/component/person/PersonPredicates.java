package com.sibedge.ergo.component.person;

import java.time.LocalDate;

import com.sibedge.ergo.component.person.domain.QPerson;
import com.sibedge.ergo.shared.data.Gender;

import com.querydsl.core.types.Predicate;
import lombok.experimental.UtilityClass;

/**
 * Provides a number of convenient predicates that con be used
 * to compose search criteria for persons.
 */
@UtilityClass
class PersonPredicates {

    public Predicate hasPersonalId(String personalId) {
        return personalId != null ? QPerson.person.personalId.like(personalId) : null;
    }

    public Predicate hasFirstName(String firstName) {
        return firstName != null ? QPerson.person.firstName.like(firstName) : null;
    }

    public Predicate hasLastName(String lastName) {
        return lastName != null ? QPerson.person.lastName.like(lastName) : null;
    }

    public Predicate hasGender(Gender gender) {
        return gender != null ? QPerson.person.gender.eq(gender) : null;
    }

    public Predicate hasDateOfBirthAfrer(LocalDate from) {
        return from != null ? QPerson.person.dateOfBirth.goe(from) : null;
    }

    public Predicate hasDateOfBirthBefore(LocalDate to) {
        return to != null ? QPerson.person.dateOfBirth.loe(to) : null;
    }

}
