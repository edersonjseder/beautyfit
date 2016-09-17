package br.com.beautyfit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Clients implements Serializable{

	private static final long serialVersionUID = 1286786L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="clients_id")
	private Integer id;
	
	private String name;
	private String cpf;
	private Character gender;
	private String phoneNumber;
	private String email;
	
	@ManyToMany(mappedBy = "clients")
	private List<Address> address;
	
	@ManyToMany(mappedBy = "clients")
	private List<Schedules> schedules;
	
	@ManyToMany(mappedBy = "clients")
	private List<CustomerService> customerServices;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
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

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Schedules> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedules> schedules) {
		this.schedules = schedules;
	}
	
}
