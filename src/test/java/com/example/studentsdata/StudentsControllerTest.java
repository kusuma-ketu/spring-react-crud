package com.example.studentsdata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.studentsdata.controllers.StudentsController;
import com.example.studentsdata.models.Student;
import com.example.studentsdata.service.StudentService;

import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentsControllerTest {

    @InjectMocks
    private StudentsController studentsController;

    @Mock
    private ClientService studentService;

    @Test
    public void testGetStudents() {
        Student student1 = new Student(1L, "John Doe", "john.doe@example.com");
        Student student2 = new Student(2L, "Jane Doe", "jane.doe@example.com");

        when(studentService.getStudents()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentsController.getStudents();

        assertEquals(2, students.size());
        assertEquals(student1, students.get(0));
        assertEquals(student2, students.get(1));
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student(1L, "John Doe", "john.doe@example.com");

        when(studentService.getStudentbyID(1L)).thenReturn(student);

        Student result = studentsController.getStudentbyID(1L);

        assertEquals(student, result);
    }

    @Test
    public void testCreateStudent() throws URISyntaxException {
        Student student = new Student(null, "John Doe", "john.doe@example.com");
        Student savedStudent = new Student(1L, "John Doe", "john.doe@example.com");

        when(studentService.createStudent(student)).thenReturn(savedStudent);

        ResponseEntity<Student> responseEntity = studentsController.createStudent(student);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(new URI("/clients/" + savedStudent.getId()), responseEntity.getHeaders().getLocation());
        assertEquals(savedStudent, responseEntity.getBody());
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student(null, "John Doe", "john.doe@example.com");
        Student updatedStudent = new Student(1L, "John Doe", "john.doe@example.com");

        when(studentService.updateStudent(1L, student)).thenReturn(updatedStudent);

        ResponseEntity<Student> responseEntity = studentsController.updateStudent(1L, student);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedStudent, responseEntity.getBody());
    }

    @Test
    public void testDeleteStudent() {
        doNothing().when(studentService).deleteStudent(1L);

        ResponseEntity<Void> responseEntity = studentsController.deleteStudent(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(studentService, times(1)).deleteStudent(1L);
    }
}


