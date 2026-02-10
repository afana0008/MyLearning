package com.example.mylearning.dto;

import java.util.List;

import com.example.mylearning.entities.Department;



public class UserMmDto {
	
	private Long rollno;
	private String firstname;
	private String lsttname;
	private List<Department> Department;
	public Long getRollno() {
		return rollno;
	}
	public void setRollno(Long rollno) {
		this.rollno = rollno;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLsttname() {
		return lsttname;
	}
	public void setLsttname(String lsttname) {
		this.lsttname = lsttname;
	}
	public List<Department> getDepartment() {
		return Department;
	}
	public void setDepartment(List<Department> department) {
		Department = department;
	}
	
}
