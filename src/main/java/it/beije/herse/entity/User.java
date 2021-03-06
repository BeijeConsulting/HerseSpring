package it.beije.herse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;

	
	public Integer getId() {
		return id;
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

	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonGetter("xxx")
	public String getFullName() {
		return name + " " + surname;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("{id: ").append(id)
				.append(", name: ").append(name)
				.append(", surname: ").append(surname)
				.append(", email: ").append(email)
				.append(", password: ").append(password)
				.append("}");
		
		return builder.toString();
	}

}
