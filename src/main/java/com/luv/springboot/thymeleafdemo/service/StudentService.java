package com.luv.springboot.thymeleafdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luv.springboot.thymeleafdemo.entity.Student;
import com.luv.springboot.thymeleafdemo.repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // ===============================
    // للطالب
    // ===============================
    public Student findByUsername(String username) {
        return studentRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ===============================
    // للأدمن: عرض الكل
    // ===============================
    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    // ===============================
    // للأدمن: بحث بالـ ID
    // ===============================
    public Student findById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // ===============================
    // بحث بالاسم الأول
    // ===============================
    public List<Student> searchByFirstName(String firstName) {
        return studentRepo.findByFirstNameContainingIgnoreCase(firstName);
    }

    // ===============================
    // بحث بالاسم الأخير
    // ===============================
    public List<Student> searchByLastName(String lastName) {
        return studentRepo.findByLastNameContainingIgnoreCase(lastName);
    }

    // ===============================
    // إضافة طالب (Admin)
    // ===============================
    public Student addStudent(Student student) {
        // أولاً تحقق لو الـ username موجود
        if (studentRepo.findByUsername(student.getUsername()).isPresent()) {
            return null; // رجع null لو موجود مسبقًا
        }

        // تشفير كلمة السر
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Student updateStudent(Student student) {
        Student existing = studentRepo.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setCountry(student.getCountry());
        existing.setAge(student.getAge());
        existing.setCourseCode(student.getCourseCode());
        existing.setUsername(student.getUsername());

        if (student.getPassword() != null && !student.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        existing.setRoles(student.getRoles());

        return studentRepo.save(existing);
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}
