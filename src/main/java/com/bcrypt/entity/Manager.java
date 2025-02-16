package com.bcrypt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")    // @Column Annotation used to assign name to the column in the table in to the database.
	private Integer id;
	
	@Column(name = "m_name", nullable = false)
	private String name;
	
	@Column(name = "m_email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "m_username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "m_password", unique = true, nullable = false)
	private String password;
	
	@Column(name = "m_city", nullable = false)
	private String city;
	
	@Column(name = "m_role", nullable = false)
	private String role;
	
	@Column(name = "DOJ", nullable = false)
	private String date_of_joining;
	
	@Column(name = "Salary", nullable = false)
	private Double salary;
	
	@Column(name = "Status", nullable = false)
	private String status;

	public Integer getId() {
		return id;
	}

	public String getDate_of_joining() {
		return date_of_joining;
	}

	public void setDate_of_joining(String date_of_joining) {
		this.date_of_joining = date_of_joining;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
