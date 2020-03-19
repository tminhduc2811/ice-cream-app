package com.atcud.icecreamapp.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_name")
	private String userName;
	

	@Column(name = "password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private Short gender;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "expired_date")
	private Date expiredDate;
	
	@Column(name = "status")
	private Short status;
	
	@Column(name = "num_of_login_failed")
	private Short numOfLoginFailed;
	
	// Relation
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="customer",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	private List<Feedback> feedback;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="customer",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	private List<Order> orders;
	
	public Customer() {
		
	}

	public Customer(Long id, String userName, String password, String firstName, String lastName, String address,
			String phoneNumber, String email, Short gender, Date birthday, String avatar, Date expiredDate,
			Short status, Short numOfLoginFailed) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.avatar = avatar;
		this.expiredDate = expiredDate;
		this.status = status;
		this.numOfLoginFailed = numOfLoginFailed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return firstName;
	}

	public void setFullName(String fullName) {
		this.firstName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Short getEnableStatus() {
		return status;
	}

	public void setEnableStatus(Short enableStatus) {
		this.status = enableStatus;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getNumOfLoginFailed() {
		return numOfLoginFailed;
	}

	public void setNumOfLoginFailed(Short numOfLoginFailed) {
		this.numOfLoginFailed = numOfLoginFailed;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", gender=" + gender + ", birthday=" + birthday + ", avatar=" + avatar + ", expiredDate="
				+ expiredDate + ", status=" + status + ", numOfLoginFailed=" + numOfLoginFailed + "]";
	}


}
