package com.example.mylearning.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROll_NO", nullable = false)
    private long rollno;

    @Size(min = 2, max = 50,  message = "FirstName should have atleast 2 characters")
    @NotEmpty(message = "Username is Mandatory field. Please provide username")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstname;
    
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastname;
    
    @Column(name = "CITY", length = 50)
    private String city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Department> departments;
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

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
    
    
}

