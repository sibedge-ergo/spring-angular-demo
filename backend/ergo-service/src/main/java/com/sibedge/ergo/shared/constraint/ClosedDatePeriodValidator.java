package com.sibedge.ergo.shared.constraint;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

/**
 * A custom class validator that checks two temporal values describing a time range
 * will be correct: the lower bound is earlier than the upper one.
 *
 * <p>It works in combination with {@link com.sibedge.ergo.shared.constraint.ClosedDatePeriod}.
 */
@Slf4j
public class ClosedDatePeriodValidator implements ConstraintValidator<ClosedDatePeriod, Object> {
    private String startAttributeName;
    private String endAttributeName;

    @Override
    public void initialize(ClosedDatePeriod constraintAnnotation) {
        startAttributeName = constraintAnnotation.start();
        endAttributeName = constraintAnnotation.end();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
            LocalDate startDate = (LocalDate) beanWrapper.getPropertyValue(startAttributeName);
            LocalDate endDate = (LocalDate) beanWrapper.getPropertyValue(endAttributeName);
            return startDate == null || endDate == null || !startDate.isAfter(endDate);
        } catch (Exception cause) {
            log.warn("Cannot get bean properties: ", cause);
            return false;
        }
    }

}
