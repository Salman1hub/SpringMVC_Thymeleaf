package com.luv.springboot.thymeleafdemo.entity;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Student {
private String firstName;
private String lastName;
private String country;
private int age;
private String favoriteLanguage;
 private List<String> favoriteSystems;

public Student() {
}
public List<String> getFavoriteSystems() {
    return favoriteSystems;
}
public void setFavoriteSystems(List<String> favoriteSystem) {
    this.favoriteSystems = favoriteSystem;
}
public String getFavoriteLanguage() {
    return favoriteLanguage;
}
public void setFavoriteLanguage(String favoriteLanguage) {
    this.favoriteLanguage = favoriteLanguage;
}
public int getAge() {
    return age;
}
public void setAge(int age) {
    this.age = age;
}
public String getCountry() {
    return country;
}
public void setCountry(String country) {
    this.country = country;
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

}
