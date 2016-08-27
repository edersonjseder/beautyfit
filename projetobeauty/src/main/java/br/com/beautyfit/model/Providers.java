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

import org.joda.time.LocalDate;

@Entity
@Table(name="providers")
public class Providers implements Serializable{

	private static final long serialVersionUID = -5463332562754085747L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="providers_id")
	private Integer id;
	
	private String name;
	private String cpfOrCnpj;
	private String ieOrRg;
	private LocalDate registerDate;
	private String phoneNumber;
	private String email;
	
	@ManyToMany(mappedBy = "providers")
	private List<Address> addresses;

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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public String getIeOrRg() {
		return ieOrRg;
	}

	public void setIeOrRg(String ieOrRg) {
		this.ieOrRg = ieOrRg;
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

}
