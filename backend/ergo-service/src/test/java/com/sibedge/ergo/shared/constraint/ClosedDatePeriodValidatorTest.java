package com.sibedge.ergo.shared.constraint;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ClosedDatePeriodValidator")
class ClosedDatePeriodValidatorTest {

    ClosedDatePeriodValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new ClosedDatePeriodValidator();
        validator.initialize(TestTwoDateData.class.getDeclaredAnnotation(ClosedDatePeriod.class));
    }

    @Test
    @DisplayName("successfully validates correct data when start is before than end")
    public void testFirstIsBeforeSecond() {
        // given
        var data = TestTwoDateData.of(LocalDate.MIN, LocalDate.MAX);

        // when
        validator.initialize(data.getClass().getDeclaredAnnotation(ClosedDatePeriod.class));
        var result = validator.isValid(data, null);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("successfully validates null")
    public void testNullObjectCorrect() {
        // given
        TestTwoDateData data = null;

        // when
        var result = validator.isValid(data, null);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("successfully validates missing start")
    public void testWhenStartIsNull() {
        // given
        var data = TestTwoDateData.of(null, LocalDate.ofYearDay(2020, 1));

        // when
        var result = validator.isValid(data, null);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("successfully validates missing end")
    public void testWhenEndIsNull() {
        // given
        var data = TestTwoDateData.of(LocalDate.ofYearDay(2020, 1), null);

        // when
        var result = validator.isValid(data, null);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("successfully validates when start and end equal")
    public void testWhenStartAndEndEqual() {
        // given
        var day = LocalDate.ofYearDay(2020, 1);
        var data = TestTwoDateData.of(day, day);

        // when
        var result = validator.isValid(data, null);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("fails when start is after end")
    public void testWhenStartIsAfterEnd() {
        // given
        var day = LocalDate.ofYearDay(2020, 1);
        var data = TestTwoDateData.of(day.plusDays(1), day);

        // when
        var result = validator.isValid(data, null);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    @DisplayName("fails when there are no bean attributes required")
    public void testWhenValidatorFailsToDiscoverFields() {
        // given
        var day = LocalDate.ofYearDay(2020, 1);
        var data = TestBrokenTwoDateData.of(day, day.plusDays(2));
        validator.initialize(TestBrokenTwoDateData.class.getDeclaredAnnotation(ClosedDatePeriod.class));

        // when
        var result = validator.isValid(data, null);

        // then
        Assertions.assertThat(result).isFalse();
    }
}