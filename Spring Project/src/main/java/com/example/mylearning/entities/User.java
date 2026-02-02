package com.example.mylearning.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rollno;

    private String firstname;
    private String lastname;
    private String city;

    //No Argument Field Constructor is mandatory
    public User() {}

    // Getters & Setters
    public long getRollno() { return rollno; }
    public void setRollno(long rollno) { this.rollno = rollno; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}

