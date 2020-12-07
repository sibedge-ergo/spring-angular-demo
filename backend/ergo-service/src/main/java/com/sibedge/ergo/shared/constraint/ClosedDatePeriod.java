package com.sibedge.ergo.shared.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * A custom constraint for the time period validation.
 *
 * @see com.sibedge.ergo.shared.constraint.ClosedDatePeriodValidator
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClosedDatePeriodValidator.class)
public @interface ClosedDatePeriod {
    /**
     * An attribute name of the lower end of period.
     *
     * @return attribute name
     */
    String start();

    /**
     * An attribute name of the upper end of period.
     *
     * @return attribute name
     */
    String end();

    String message() default "{com.sibedge.ergo.validator.constraints.ClosedDatePeriod.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
