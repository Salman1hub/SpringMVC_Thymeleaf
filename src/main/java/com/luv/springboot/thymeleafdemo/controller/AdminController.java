package com.luv.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv.springboot.thymeleafdemo.entity.Student;
import com.luv.springboot.thymeleafdemo.service.StudentService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("student", new Student());
        return "login-form";
    }

    @PostMapping("/processLogin")
    public String processLogin(@ModelAttribute("student") Student student, Model model) {
        Student dbStudent = studentService.findByUsername(student.getUsername());

        if (dbStudent == null || !dbStudent.getPassword().equals(student.getPassword())) {
            model.addAttribute("error", "Invalid username or password");
            return "login-form";
        }

        // ADMIN
        if (dbStudent.getRoles().contains("ADMIN")) {
            List<Student> students = studentService.findAllStudents();
            model.addAttribute("students", students);
            return "admin-students";
        }

        // USER
        if (dbStudent.getRoles().contains("USER")) {
            model.addAttribute("student", dbStudent);
            return "user-dashboard";
        }

        return "login-form";
    }

    @GetMapping("/add-student")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) {
        if (student.getId() != null) {
            studentService.updateStudent(student);
        } else {
            Student addedStudent = studentService.addStudent(student);
            if (addedStudent == null) {
                model.addAttribute("error", "Username already exists. Please choose another one.");
                return "add-student";
            }
        }

        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "admin-students";
    }

    @GetMapping("/update-student")
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "add-student";
    }

    @GetMapping("/delete-student")
    public String deleteStudent(@RequestParam("id") Long id, Model model) {
        studentService.deleteStudent(id);
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "admin-students";
    }

}
