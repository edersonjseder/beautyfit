package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.CustomerService;

@Transactional
public interface CustomerServiceRepository  extends CrudRepository<CustomerService, Integer> {
	
	public List<CustomerService> findAll();

}
