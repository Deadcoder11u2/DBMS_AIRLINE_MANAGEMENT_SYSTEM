package com.example.demo.Enquiry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.Employee.Employee;
import com.example.demo.User.User;

@Entity
public class Query {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "query_id")
	private Long queryId;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "is_open", nullable = false)
	private Boolean isOpen;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;


	public Long getQueryId() {
		return queryId;
	}


	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean getIsOpen() {
		return isOpen;
	}


	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
