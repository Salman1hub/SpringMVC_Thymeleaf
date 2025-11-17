package com.luv.springboot.thymeleafdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv.springboot.thymeleafdemo.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    // هنا ممكن تضيف custom query methods لو محتاج
    Optional<Student> findByUsername(String username);

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    List<Student> findByLastNameContainingIgnoreCase(String lastName);
}
