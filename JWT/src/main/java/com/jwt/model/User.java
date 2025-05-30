package com.jwt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String username;
private String password;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public String getusername() {
	return username;
}
public void setusername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public User(int id, String username, String password) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
}


}

