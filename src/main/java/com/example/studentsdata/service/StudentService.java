package com.example.studentsdata.service;
import com.example.studentsdata.models.Student;
import com.example.studentsdata.repository.StudentsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentsdata.repository.StudentsRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentsRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentbyID(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student currentStudent = studentRepository.findById(id).orElse(null);
        if (currentStudent == null) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        currentStudent.setName(student.getName());
        currentStudent.setEmail(student.getEmail());
        return studentRepository.save(currentStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

