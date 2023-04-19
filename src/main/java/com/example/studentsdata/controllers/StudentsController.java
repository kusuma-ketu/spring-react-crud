package com.example.studentsdata.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.studentsdata.service.StudentService;
import com.example.studentsdata.models.Student;

@RestController
@RequestMapping("/students")
public class StudentsController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentbyID(@PathVariable Long id) {
        return studentService.getStudentbyID(id);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) throws URISyntaxException {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.created(new URI("/clients/" + savedStudent.getId())).body(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
