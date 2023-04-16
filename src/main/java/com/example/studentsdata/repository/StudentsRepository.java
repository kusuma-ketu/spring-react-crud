package com.example.studentsdata.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentsdata.models.Student;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    
}
