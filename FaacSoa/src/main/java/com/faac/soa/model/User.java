package com.faac.soa.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "users")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    @Id
    @Column(name = "user_id")
    public int getId() {
        return id;
    }
    public void setId(int id){
    	this.id=id;
    }
    @Column(name = "name")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	  @Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 @Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
 
}