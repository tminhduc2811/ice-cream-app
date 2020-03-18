package com.atcud.icecreamapp.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullName;
	
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
	
	@Column(name = "enable_status")
	private Short enableStatus;
	
	public Customer() {
		
	}

	public Customer(Long id, String userName, String password, String fullName, String address, String phoneNumber,
			String email, Short gender, Date birthday, String avatar, Date expiredDate, Short enableStatus) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.avatar = avatar;
		this.expiredDate = expiredDate;
		this.enableStatus = enableStatus;
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
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
		return enableStatus;
	}

	public void setEnableStatus(Short enableStatus) {
		this.enableStatus = enableStatus;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userName=" + userName + ", password=" + password + ", fullName=" + fullName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", gender=" + gender
				+ ", birthday=" + birthday + ", avatar=" + avatar + ", expiredDate=" + expiredDate + ", enableStatus="
				+ enableStatus + "]";
	}
	
	
}
