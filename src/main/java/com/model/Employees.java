package com.model;
 
import java.sql.Date;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
 
@Entity
public class Employees {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
 
    @NotEmpty
    private String firstName;
 
    @NotEmpty
    private String lastName;
 
    @NotEmpty
    private String position;
 
    @NotEmpty
    private String phoneNumber;
 
    @NotEmpty
    private String email;
    @NotEmpty
    private Date hireDate;
 
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Addresses addresses;
 
    @Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", position=" + position + ", phoneNumber=" + phoneNumber + ", email=" + email + ", hireDate="
				+ hireDate + ", addresses=" + addresses + ", role=" + role + "]";
	}
 
	public Date getHireDate() {
		return hireDate;
	}
 
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
 
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;
 
    // Getters and Setters
    public Long getEmployeeId() {
        return employeeId;
    }
 
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getPosition() {
        return position;
    }
 
    public void setPosition(String position) {
        this.position = position;
    }
 
    public String getPhoneNumber() {
        return phoneNumber;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public Addresses getAddresses() {
        return addresses;
    }
 
    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }
 
    public RoleType getRole() {
        return role;
    }
 
    public void setRole(RoleType role) {
        this.role = role;
    }
 
   
}