package com.luv.springboot.thymeleafdemo.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Constraint(validatedBy = CourseCodeValidation.class)
@Target({java.lang.annotation.ElementType.METHOD,
    java.lang.annotation.ElementType.FIELD,})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface courseCode {

    public String value() default "Ta";

    public String message() default "must start with Ta";

    public Class<?>[] groups() default {};

    public Class<? extends jakarta.validation.Payload>[] payload() default {};

}
