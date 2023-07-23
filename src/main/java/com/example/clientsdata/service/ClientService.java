package com.example.clientsdata.service;
import com.example.clientsdata.models.Client;
import com.example.clientsdata.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clientsdata.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getStudents() {
        return studentRepository.findAll();
    }

    public Client getClientbyID(Long id) {
        return clientRepository.findById(id).orElse(null);
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

