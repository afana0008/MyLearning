package com.example.mylearning.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Department {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonView(Views.Internal.class)
	@Column(name = "DEPARTMENT_ID")
	private Integer department;
	
	@Column(name = "DEPARTMENT_DESC", nullable = false, length =100)
	//@JsonView(Views.Internal.class)
	@NotEmpty(message = "Department description is mandatory. Please provide description")
	private String deparment_desc;
	
	@Column(name="DEPARTMENT_FIELD", nullable=true)
	//@JsonView(Views.Internal.class)
	private String department_field;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ROLL_NO")
	@JsonIgnore
	private User user;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getDeparment_desc() {
		return deparment_desc;
	}

	public void setDeparment_desc(String deparment_desc) {
		this.deparment_desc = deparment_desc;
	}

	public String getDepartment_field() {
		return department_field;
	}

	public void setDepartment_field(String department_field) {
		this.department_field = department_field;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
