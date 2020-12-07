package com.sibedge.ergo.component.person;

import java.util.Optional;

import com.sibedge.ergo.shared.transport.PersonFilterData;
import com.sibedge.ergo.component.person.domain.Person;
import com.sibedge.ergo.shared.transport.ListData;
import com.sibedge.ergo.shared.transport.PersonData;
import com.sibedge.ergo.util.WildcardConverter;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FindPersonsByFilterUseCaseImpl implements FindPersonsByFilterUseCase {

    private final QueryPersonRepository queryPersonRepository;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public ListData<PersonData> execute(PersonFilterData personFilter, Pageable pageable) {

        var predicate = new BooleanBuilder()
                .and(translateWildcard(personFilter.getPersonalId()).map(PersonPredicates::hasPersonalId).orElse(null))
                .and(translateWildcard(personFilter.getFirstName()).map(PersonPredicates::hasFirstName).orElse(null))
                .and(translateWildcard(personFilter.getLastName()).map(PersonPredicates::hasLastName).orElse(null))
                .and(PersonPredicates.hasGender(personFilter.getGender()))
                .and(PersonPredicates.hasDateOfBirthAfrer(personFilter.getFromDateOfBirth()))
                .and(PersonPredicates.hasDateOfBirthBefore(personFilter.getToDateOfBirth()));

        var foundPersons = queryPersonRepository.findAll(predicate, pageable);
        return ListData.of(
                foundPersons.map(this::convert).getContent(),
                foundPersons.getTotalElements()
        );
    }

    private Optional<String> translateWildcard(String text) {
        return Optional.ofNullable(text).map(WildcardConverter::asSqlLikeWildcard);
    }

    private PersonData convert(Person person) {
        return PersonData.of(
                person.getPersonalId(),
                person.getFirstName(),
                person.getLastName(),
                person.getGender(),
                person.getDateOfBirth()
        );
    }

}
