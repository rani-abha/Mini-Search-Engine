package com.seekster.WebCrawler.api.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SettingKeyValidator.class)
public @interface ValidateSettingKey {
    String message() default "Invalid setting key.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
