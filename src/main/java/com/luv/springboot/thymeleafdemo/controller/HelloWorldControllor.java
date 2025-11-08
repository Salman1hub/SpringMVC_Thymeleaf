package com.luv.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
// this second step to use spring mvc with thymeleaf template
@RequestMapping
@Controller
public class HelloWorldControllor {
// need a controller method to handle /hello-world request
@GetMapping("/showform")
public String showForm() {
    return "helloworld-form"; // return the name of the view
}
// need a controller method to return a view
@RequestMapping("/processform")
public String processPage() {
       
    return "helloworld"; // return the name of the view
}
// new controller method to read form data,put data to model and return a view 
@GetMapping("/processformVTwo")
public String processPageversiontwo(HttpServletRequest req,Model theModel) {
    //read the request parameter from the html form
    String theName=req.getParameter("studentname");
    //convert data to uppercase
    theName=theName.toUpperCase();
    //create the message
    String result="Hello from V2! "+theName;
    //add message to the model
        theModel.addAttribute( /* this name */"message",/*this value*/result);
    return "helloworld"; // return the name of the view
}
// new controller method to read form data,put data to model and return a view 
@PostMapping("/processformVThree")
public String processPageversionThree(@RequestParam("studentname") String name,Model theModel) {
 
 
    
    String result="Hello from V3! "+name.toUpperCase();
    //add message to the model
        theModel.addAttribute( /* this name */"message",/*this value*/result);
    return "helloworld"; // return the name of the view
}
}
