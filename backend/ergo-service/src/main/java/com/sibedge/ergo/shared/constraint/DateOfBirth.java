package com.sibedge.ergo.shared.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.PastOrPresent;

/**
 * Composite constraint for the person birthday.
 */
@PastOrPresent
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@ReportAsSingleViolation
@Documented
public @interface DateOfBirth {
    String message() default "{com.sibedge.ergo.validator.constraints.DateOfBirth.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
