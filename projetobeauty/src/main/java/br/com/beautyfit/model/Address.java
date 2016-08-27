package br.com.beautyfit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 333444551L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="address_id")
	private Long id;

	private String address;
	private String neighborhood;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "idCity")
	private City city;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "address_clients", joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id"), inverseJoinColumns = @JoinColumn(name = "clients_id", referencedColumnName = "clients_id"))
	private List<Clients> clients;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "address_providers", joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id"), inverseJoinColumns = @JoinColumn(name = "providers_id", referencedColumnName = "providers_id"))
	private List<Providers> providers;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "address_employees", joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id"), inverseJoinColumns = @JoinColumn(name = "employees_id", referencedColumnName = "employees_id"))
	private List<Employees> employees;
	
	public List<Providers> getProviders() {
		return providers;
	}
	public void setProviders(List<Providers> providers) {
		this.providers = providers;
	}
	private String cep;

	public List<Clients> getClients() {
		return clients;
	}
	public void setClients(List<Clients> clients) {
		this.clients = clients;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public List<Employees> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
}
