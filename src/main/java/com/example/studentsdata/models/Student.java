package com.example.studentsdata.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String email;

    public Student(Long l, String string1, String string2) {
        this.id = l;
        this.name = string1;
        this.email = string2;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }

}
    

