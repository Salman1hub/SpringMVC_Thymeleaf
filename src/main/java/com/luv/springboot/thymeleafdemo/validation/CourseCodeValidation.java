package com.luv.springboot.thymeleafdemo.validation;

public class CourseCodeValidation implements jakarta.validation.ConstraintValidator<courseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(courseCode theCourseCode) {
        coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode,
            jakarta.validation.ConstraintValidatorContext theConstraintValidatorContext) {

        if (theCode != null) {
            return theCode.startsWith(coursePrefix);
        } else {
            return true;
        }
    }

}
