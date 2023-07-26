package com.incedo.sso.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Logs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long logId;
	
	private String name;
	private LocalDate loggedInAt;
	
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getLoggedInAt() {
		return loggedInAt;
	}
	public void setLoggedInAt(LocalDate loggedInAt) {
		this.loggedInAt = loggedInAt;
	}
	
	
}
