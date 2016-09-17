package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.BillsToPay;

@Transactional
public interface BillsToPayRepository extends CrudRepository<BillsToPay, Integer> {
	
	public BillsToPay findByCreditor(String creditor);
	public List<BillsToPay> findAll();

}
