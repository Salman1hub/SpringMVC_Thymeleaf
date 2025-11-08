package com.luv.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.luv.springboot.thymeleafdemo.entity.Student;


@Controller
public class StudentController {
    @Value("${countries}")
    public List<String> countries;
    @Value("${favoriteLanguages}")
     public List<String> favoriteLanguages;
    @Value("${favoriteSystems}")
     public List<String> favoriteSystems;
// method to show the student form
    @GetMapping("/student-form")
    public String studentForm(Model theModel) { //Model is used to pass data between controller and view
        theModel.addAttribute("student", new Student());//passing empty student object in model to the form
        theModel.addAttribute("countries",countries);//pass countries list to the form
         theModel.addAttribute("favoriteLanguages",favoriteLanguages);//pass favorite languages list to the form
          theModel.addAttribute("favoriteSystems",favoriteSystems);//pass favorite systems list to the form
        return "student-form";//return the name of the html form
    }
    @PostMapping("/students/save")
    //method to process the form
    //@ModelAttribute is used to bind form data to the object
    public String studendprocess(@ModelAttribute("student") Student theStudent) {
        String fullName=theStudent.getFirstName()+" "+theStudent.getLastName();
        System.out.println("Student Name: "+fullName + " " + " Country: "+theStudent.getCountry() 
        + " | Age: "+theStudent.getAge() +"  Favorite Language: "+theStudent.getFavoriteLanguage());
        return "student-confirmation";
    }

}
