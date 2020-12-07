package com.sibedge.ergo.api;

import java.time.LocalDate;
import java.util.List;

import com.sibedge.ergo.shared.transport.ListData;
import com.sibedge.ergo.shared.transport.PersonData;
import com.sibedge.ergo.shared.data.Gender;
import com.sibedge.ergo.util.TestPersonFilter;
import com.sibedge.ergo.util.TestPersonFilterHelper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

/**
 * Full API tests for person search endpoint
 *
 * @see com.sibedge.ergo.api.PersonSearchEndpoint
 */
@DisplayName("a search person endpoint")
class PersonSearchEndpointTest extends BaseApiTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private TestPersonFilterHelper personFilterHelper;

    @BeforeEach
    public void setUp() {
        personFilterHelper = TestPersonFilterHelper.with(restTemplate);
    }

    @Test
    @DisplayName("returns all records when the filter is empty")
    @Sql(scripts = "/assets/person-set-1.sql")
    void testLoadAllPersonsWithEmptyFilter() {
        // given
        var allPersons = TestPersonFilter.builder().build();
        var expectedPersonsFound = 3L;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.filter(allPersons);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);
    }

    @Test
    @DisplayName("returns mo records satisfied by the filter")
    @Sql(scripts = "/assets/person-set-1.sql")
    void testLoadNoSatisfiedPersonsWith() {
        // given
        var allPersons = TestPersonFilter.builder()
                .firstName("non-existent name")
                .build();
        var expectedPersonsFound = 0L;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.filter(allPersons);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);
        Assertions.assertThat(foundPersons.getContent()).isEmpty();
    }

    @Test
    @DisplayName("returns one record satisfied to personal ID")
    @Sql(scripts = "/assets/person-set-1.sql")
    void testLoadPersonByExactPersonalId() {
        // given
        var personById = TestPersonFilter.builder()
                .personalId("U0001")
                .build();
        var expectedPersonsFound = 1L;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.filter(personById);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);
        Assertions.assertThat(foundPersons.getContent())
                .extracting(PersonData::getPersonalId)
                .containsExactly("U0001");
    }

    @Test
    @DisplayName("returns only male persons")
    @Sql(scripts = "/assets/person-set-1.sql")
    void testLoadMalePersons() {
        // given
        var onlyMaleFilter = TestPersonFilter.builder()
                .gender(Gender.MALE)
                .build();
        var expectedPersonsFound = 2L;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.filter(onlyMaleFilter);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);
        Assertions.assertThat(foundPersons.getContent())
                .extracting(PersonData::getGender)
                .containsOnly(Gender.MALE);
    }

    @Test
    @DisplayName("returns persons with names starting with H letter")
    @Sql(scripts = "/assets/person-set-2.sql")
    void testLoadPersonsStartingWithHLetter() {
        // given
        var onlyHNames = TestPersonFilter.builder()
                .firstName("H*")
                .build();

        var expectedPersonsFound = 2L;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.filter(onlyHNames);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);
        Assertions.assertThat(foundPersons.getContent())
                .extracting(PersonData::getFirstName)
                .allMatch(name -> name.startsWith("H"));
    }


    @Test
    @DisplayName("returns persons with last names having exactly two letters")
    @Sql(scripts = "/assets/person-set-3.sql")
    void testLoadPersonsHaveTwoLetterLengthLastName() {
        // given
        var onlyTwoLettersLastName = TestPersonFilter.builder()
                .lastName("??")
                .build();

        var expectedPersonsFound = 1L;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.filter(onlyTwoLettersLastName);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);
        Assertions.assertThat(foundPersons.getContent())
                .extracting(PersonData::getLastName)
                .allSatisfy(name -> Assertions.assertThat(name).hasSize(2));
    }

    @Test
    @DisplayName("returns persons who were born within the specific period")
    @Sql(scripts = "/assets/person-set-3.sql")
    void testLoadPersonsBornWithinPeriod() {
        // given
        var from = LocalDate.ofYearDay(1990, 1);
        var to = from.plusYears(5);
        var bornBetween90and95 = TestPersonFilter.builder()
                .fromDateOfBirth(from)
                .toDateOfBirth(to)
                .build();

        var expectedPersonsFound = 5L;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.filter(bornBetween90and95);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);
        Assertions.assertThat(foundPersons.getContent())
                .extracting(PersonData::getDateOfBirth)
                .allSatisfy(date -> Assertions.assertThat(date).isBetween(from, to));
    }

    @Test
    @DisplayName("returns first five persons from the list")
    @Sql(scripts = "/assets/person-set-3.sql")
    void testLoadFirstPageOfPersons() {
        // given
        var allPersons = TestPersonFilter.builder().build();
        var page = PageRequest.of(0, 5);

        var expectedPersonsFound = 20L;
        var expectedFirstPageSize = 5;
        var expectedStatus = HttpStatus.OK;

        // when
        var response = personFilterHelper.<ListData<PersonData>>filter(allPersons, page);

        // then
        var foundPersons = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
        Assertions.assertThat(foundPersons.getTotal()).isEqualTo(expectedPersonsFound);

        var content = foundPersons.getContent();
        Assertions.assertThat(content).hasSize(expectedFirstPageSize);
    }

}