package net.employee.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "emailId")
	private String emailId;
	
	public Employee(){
		
	}
	
	public Employee(String first_name, String last_name, String emailId) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.emailId = emailId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return first_name;
	}
	public void setFname(String first_name) {
		this.first_name = first_name;
	}
	public String getLname() {
		return last_name;
	}
	public void setLname(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return emailId;
	}
	public void setEmail(String emailId) {
		this.emailId = emailId;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", emailId=" + emailId + "]";
	}
}
