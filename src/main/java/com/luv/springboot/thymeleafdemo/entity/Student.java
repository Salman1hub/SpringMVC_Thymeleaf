package com.luv.springboot.thymeleafdemo.entity;

import java.util.HashSet;
import java.util.Set;

import com.luv.springboot.thymeleafdemo.validation.courseCode;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    private String lastName;

    private String country;

    @NotNull(message = "is required")
    @Max(value = 20, message = "Age must be less than or equal to 20")
    @Min(value = 6, message = "Age must be at least 6")
    private int age;

    private String favoriteLanguage;

    @courseCode(value = "Ta", message = "must start with Ta and four digits followed")
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{4}$", message = "must match the pattern XX9999")
    private String courseCode;

    // LOGIN CREDENTIALS
    @NotNull(message = "is required")
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull(message = "is required")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "student_roles",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    // =======================
    // Constructors
    // =======================
    public Student() {
    }

    // =======================
    // Getters & Setters
    // =======================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
