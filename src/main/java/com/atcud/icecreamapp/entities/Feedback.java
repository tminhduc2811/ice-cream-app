package com.atcud.icecreamapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private Long id;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	public Feedback() {
		
	}

	public Feedback(Long id, String fullName, String title, String content) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.title = title;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", fullName=" + fullName + ", title=" + title + ", content=" + content + "]";
	}
	
}
