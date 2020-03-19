package com.atcud.icecreamapp.DTO;

import java.sql.Date;

public class CustomerDTO {
	
	private Long id;
	
	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String phoneNumber;
	
	private String email;
	
	private Short gender;
	
	private Date birthday;
	
	private String avatar;
	
	private Date expiredDate;
	
	private Short status;

	private Short numOfLoginFailed;

	public CustomerDTO(Long id, String userName, String password, String firstName, String lastName, String address,
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
	
}
