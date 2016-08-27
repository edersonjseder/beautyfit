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
@Table(name="schedules")
public class Schedules implements Serializable{

	private static final long serialVersionUID = -4588376288311929667L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="schedules_id")
	private Integer id;
	
	private LocalDate schedulingDate;
	private String schedulingTime;
	private String schedulingStatus;
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "schedules_customer_services", joinColumns = @JoinColumn(name = "schedules_id", referencedColumnName = "schedules_id"), inverseJoinColumns = @JoinColumn(name = "customer_service_id", referencedColumnName = "customer_service_id"))
	private List<CustomerService> customerServices;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "schedules_employees", joinColumns = @JoinColumn(name = "schedules_id", referencedColumnName = "schedules_id"), inverseJoinColumns = @JoinColumn(name = "employees_id", referencedColumnName = "employees_id"))
	private List<Employees> employees;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "schedules_clients", joinColumns = @JoinColumn(name = "schedules_id", referencedColumnName = "schedules_id"), inverseJoinColumns = @JoinColumn(name = "clients_id", referencedColumnName = "clients_id"))
	private List<Clients> clients;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSchedulingTime() {
		return schedulingTime;
	}

	public void setSchedulingTime(String schedulingTime) {
		this.schedulingTime = schedulingTime;
	}

	public String getSchedulingStatus() {
		return schedulingStatus;
	}

	public void setSchedulingStatus(String schedulingStatus) {
		this.schedulingStatus = schedulingStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<CustomerService> getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(List<CustomerService> customerServices) {
		this.customerServices = customerServices;
	}

	public LocalDate getSchedulingDate() {
		return schedulingDate;
	}

	public void setSchedulingDate(LocalDate schedulingDate) {
		this.schedulingDate = schedulingDate;
	}

}
