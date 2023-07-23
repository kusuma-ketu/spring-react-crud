package com.example.clientsdata.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clientsdata.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
