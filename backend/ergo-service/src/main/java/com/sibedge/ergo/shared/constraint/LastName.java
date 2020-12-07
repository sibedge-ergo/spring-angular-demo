package com.sibedge.ergo.shared.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;

/**
 * Composite constraint for the person last name.
 */
@Size(min = 2, max = 100)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@ReportAsSingleViolation
@Documented
public @interface LastName {
    String message() default "{com.sibedge.ergo.validator.constraints.LastName.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
