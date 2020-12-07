package com.sibedge.ergo.api;

import java.time.LocalDate;

import com.sibedge.ergo.shared.transport.ErrorData;
import com.sibedge.ergo.util.TestPersonFilter;
import com.sibedge.ergo.util.TestPersonFilterHelper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

/**
 * Full API tests for person search endpoint
 *
 * @see com.sibedge.ergo.api.PersonSearchEndpoint
 */
@DisplayName("a search person endpoint")
class PersonInvalidSearchEndpointTest extends BaseApiTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private TestPersonFilterHelper personFilterHelper;

    @BeforeEach
    public void setUp() {
        personFilterHelper = TestPersonFilterHelper.with(restTemplate);
    }

    @Test
    @DisplayName("returns an error when a personal ID is too large")
    void testGetErrorWhenPersonalIdIsTooLarge() {
        // given
        var personFilter = TestPersonFilter.builder()
                .personalId("tooozzooooooooooooooooooooo laaaaaaarge teeeeeeeeeeeeeeeext")
                .build();
        var expectedStatus = HttpStatus.BAD_REQUEST;

        // when
        var response = personFilterHelper.filter(personFilter);

        // then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @Test
    @DisplayName("returns an error when a first name is too large")
    void testGetErrorWhenPersonFirstNameIsTooLarge() {
        // given
        var personFilter = TestPersonFilter.builder()
                .firstName("tooozzooooooooooooooooooooo laaaaaaarge naaaaaaammeeeeeeeeeeee")
                .build();
        var expectedStatus = HttpStatus.BAD_REQUEST;

        // when
        var response = personFilterHelper.filter(personFilter);

        // then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @Test
    @DisplayName("returns an error when a last name is too large")
    void testGetErrorWhenPersonLastNameIsTooLarge() {
        // given
        var personFilter = TestPersonFilter.builder()
                .lastName(
                        "tooooozzooooooooooooooooooooo laaaaaaaaaaarge laaaaaaaaast naaaaaaaaaaaaaammeeeeeeeeeet" +
                                "aaaaaandd!!!!!!!!!!!!!"
                )
                .build();
        var expectedStatus = HttpStatus.BAD_REQUEST;

        // when
        var response = personFilterHelper.filter(personFilter);

        // then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @Test
    @DisplayName("returns an error when period between from and to is negative")
    void testGetInvalidPeriod() {
        // given
        var from = LocalDate.ofYearDay(2010, 10);
        var to = from.minusDays(1);
        var personFilter = TestPersonFilter.builder()
                .fromDateOfBirth(from)
                .toDateOfBirth(to)
                .build();
        var expectedStatus = HttpStatus.BAD_REQUEST;

        // when
        var response = personFilterHelper.filter(personFilter);

        // then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @Test
    @DisplayName("returns an error when the period takes future time")
    void testGetFuturePeriod() {
        // given
        var from = LocalDate.now().minusMonths(1);
        var to = LocalDate.now().plusMonths(1);
        var personFilter = TestPersonFilter.builder()
                .fromDateOfBirth(from)
                .toDateOfBirth(to)
                .build();
        var expectedStatus = HttpStatus.BAD_REQUEST;

        // when
        var response = personFilterHelper.filterForError(personFilter);

        // then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        var body = response.getBody();
    }

    @Test
    @DisplayName("returns errors when several filter properties are invalid")
    void testGetMultipleFilterErrors() {
        // given
        var from = LocalDate.now().minusMonths(1);
        var to = LocalDate.now().plusMonths(1);
        var personFilter = TestPersonFilter.builder()
                .fromDateOfBirth(from)
                .toDateOfBirth(to)
                .firstName("w")
                .lastName("f")
                .build();
        var expectedStatus = HttpStatus.BAD_REQUEST;

        // when
        var response = personFilterHelper.filterForError(personFilter);

        // then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        var body = response.getBody();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getErrors()).hasSize(3);
    }

}