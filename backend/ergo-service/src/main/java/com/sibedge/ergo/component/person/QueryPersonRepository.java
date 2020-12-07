package com.sibedge.ergo.component.person;

import com.sibedge.ergo.component.person.domain.Person;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@org.springframework.stereotype.Repository
interface QueryPersonRepository extends Repository<Person, Long>, QuerydslPredicateExecutor<Person> {
}
