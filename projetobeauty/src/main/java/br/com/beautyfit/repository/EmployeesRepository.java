package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.Employees;

@Transactional
public interface EmployeesRepository extends CrudRepository<Employees, Long> {
	
	public List<Employees> findAll();

}
