package com.sibedge.ergo.util;

import java.net.URI;
import java.util.Optional;

import com.sibedge.ergo.shared.transport.ErrorData;
import com.sibedge.ergo.shared.transport.ListData;
import com.sibedge.ergo.shared.transport.PersonData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Helper to perform search queries for persons.
 */
@RequiredArgsConstructor(staticName = "with")
public class TestPersonFilterHelper {

    private final TestRestTemplate testRestTemplate;

    public ResponseEntity<ListData<PersonData>> filter(TestPersonFilter personFilter) {
        return filter(personFilter, PageRequest.of(0, 10));
    }

    public ResponseEntity<ErrorData> filterForError(TestPersonFilter personFilter) {
        var headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return testRestTemplate.exchange(
                toQueryUri(personFilter, PageRequest.of(0, 10)),
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                ErrorData.class
        );
    }

    public ResponseEntity<ListData<PersonData>> filter(TestPersonFilter personFilter, Pageable pageable) {
        var headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return testRestTemplate.exchange(
                toQueryUri(personFilter, pageable),
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<>() { }
        );
    }

    private URI toQueryUri(TestPersonFilter filter, Pageable pageable) {
        return UriComponentsBuilder.fromPath("/persons")
                .queryParamIfPresent("personalId", Optional.ofNullable(filter.getPersonalId()))
                .queryParamIfPresent("firstName", Optional.ofNullable(filter.getFirstName()))
                .queryParamIfPresent("lastName", Optional.ofNullable(filter.getLastName()))
                .queryParamIfPresent("gender", Optional.ofNullable(filter.getGender()).map(Enum::name))
                .queryParamIfPresent("fromDateOfBirth", Optional.ofNullable(filter.getFromDateOfBirth()))
                .queryParamIfPresent("toDateOfBirth", Optional.ofNullable(filter.getToDateOfBirth()))
                .queryParamIfPresent("size", Optional.of(pageable.getPageSize()))
                .queryParamIfPresent("page", Optional.of(pageable.getPageNumber()))
                .build().toUri();
    }

}
