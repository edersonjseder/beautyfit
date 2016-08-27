package br.com.beautyfit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name="cash_register")
public class CashRegister implements Serializable{

	private static final long serialVersionUID = 902632130683212778L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cash_register_id")
	private Integer id;
	
	private Integer month;
	private LocalDate releaseDate;
	private String releaseType;
	private Double amount;
	private Double currentAmountRegister;
	private String history;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getReleaseType() {
		return releaseType;
	}
	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getCurrentAmountRegister() {
		return currentAmountRegister;
	}
	public void setCurrentAmountRegister(Double currentAmountRegister) {
		this.currentAmountRegister = currentAmountRegister;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

}
