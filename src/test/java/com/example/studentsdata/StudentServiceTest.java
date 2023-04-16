package com.example.studentsdata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.studentsdata.models.Student;
import com.example.studentsdata.repository.StudentsRepository;
import com.example.studentsdata.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentsRepository studentRepository;

    @Test
    public void testGetStudents() {
        Student student1 = new Student(1L, "John Doe", "john.doe@example.com");
        Student student2 = new Student(2L, "Jane Doe", "jane.doe@example.com");

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentService.getStudents();

        assertEquals(2, students.size());
        assertEquals(student1, students.get(0));
        assertEquals(student2, students.get(1));
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student(1L, "John Doe", "john.doe@example.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentbyID(1L);

        assertEquals(student, result);
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student(null, "John Doe", "john.doe@example.com");
        Student savedStudent = new Student(1L, "John Doe", "john.doe@example.com");

        when(studentRepository.save(student)).thenReturn(savedStudent);

        Student result = studentService.createStudent(student);

        assertEquals(savedStudent, result);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student(null, "John Doe", "john.doe@example.com");
        Student updatedStudent = new Student(1L, "John Doe", "john.doe@example.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(updatedStudent));
        when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(1L, student);

        assertEquals(updatedStudent, result);
    }

    @Test
    public void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById(1L);

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }
}
