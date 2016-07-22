package com.pgs.soft.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String surname;

	private String city;

	private String street;

	@Column(name = "home_number")
	private String homeNumber;

	@Column(name = "post_code")
	private String postCode;

	@OneToOne(mappedBy = "userProfile")
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_hobbies", joinColumns = @JoinColumn(name = "user_profile_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "hobby_id", referencedColumnName = "id"))
	private Set<Hobby> hobbies;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id")
	private Set<Points> points;

	public UserProfile() {

	}

	public UserProfile(Integer id, String name, String surname, String city, String street, String homeNumber,
			String postCode) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.street = street;
		this.homeNumber = homeNumber;
		this.postCode = postCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public Set<Points> getPoints() {
		return points;
	}

	public void setPoints(Set<Points> points) {
		this.points = points;
	}
}
