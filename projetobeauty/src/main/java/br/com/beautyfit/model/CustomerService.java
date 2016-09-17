package br.com.beautyfit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name = "customer_service")
public class CustomerService implements Serializable{
	
	private static final long serialVersionUID = -2375372180919316210L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_service_id")
	private Integer id;
	private String name;
	private LocalDate serviceDate;
	private String serviceTime;
	private String description;
	private String serviceStatus;
	private Double price;
	
	@ManyToMany(mappedBy = "customerServices")
	private List<Schedules> schedules;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "schedules_employees", joinColumns = @JoinColumn(name = "customer_service_id", referencedColumnName = "customer_service_id"), inverseJoinColumns = @JoinColumn(name = "employees_id", referencedColumnName = "employees_id"))
	private List<Employees> employees;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "schedules_clients", joinColumns = @JoinColumn(name = "customer_service_id", referencedColumnName = "customer_service_id"), inverseJoinColumns = @JoinColumn(name = "clients_id", referencedColumnName = "clients_id"))
	private List<Clients> clients;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public List<Schedules> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedules> schedules) {
		this.schedules = schedules;
	}
	public List<Employees> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
	public List<Clients> getClients() {
		return clients;
	}
	public void setClients(List<Clients> clients) {
		this.clients = clients;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDate getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(LocalDate serviceDate) {
		this.serviceDate = serviceDate;
	}
}
