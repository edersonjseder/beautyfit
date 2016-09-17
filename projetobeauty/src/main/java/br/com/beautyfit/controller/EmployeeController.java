package br.com.beautyfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.beautyfit.endpoint.Path;
import br.com.beautyfit.exceptions.KeywordNotFoundException;
import br.com.beautyfit.messages.Messages;
import br.com.beautyfit.model.Employees;
import br.com.beautyfit.repository.EmployeesRepository;

@RequestMapping(value = Path.urls.EMPLOYEES)
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeesRepository employeesRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employees>> listALL() {
		
		List<Employees> employees = employeesRepository.findAll();
		
		if(employees != null){
			
			return new ResponseEntity<List<Employees>>(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<Employees>>(employees, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{employeesId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employees> getEmployeesById(@PathVariable Integer employeesId) {

		Employees employees = employeesRepository.findOne(employeesId);
		
		if (employees != null) {
		
			return new ResponseEntity<Employees>(employees, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(employeesId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Employees> create(@RequestBody Employees employees) {
		
		employeesRepository.save(employees);
		
		return new ResponseEntity<Employees>(employees, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{employeesId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Employees> update(@PathVariable Integer employeesId, @RequestBody Employees employees) {

		Employees employeesUpdated = employeesRepository.findOne(employeesId);

		if (employeesUpdated != null) {
			
			employeesUpdated.setName(employees.getName());
			employeesUpdated.setCpf(employees.getCpf());
			employeesUpdated.setRg(employees.getRg());
			employeesUpdated.setRole(employees.getRole());
			employeesUpdated.setBirthDate(employees.getBirthDate());
			employeesUpdated.setAdmissionDate(employees.getAdmissionDate());
			employeesUpdated.setDismissalDate(employees.getDismissalDate());
			employeesUpdated.setGender(employees.getGender());
			employeesUpdated.setPhoneNumber(employees.getPhoneNumber());
			employeesUpdated.setEmail(employees.getEmail());
			employeesUpdated.setSalary(employees.getSalary());
			employeesUpdated.setEmployeeStatus(employees.getEmployeeStatus());
									
			employeesRepository.save(employeesUpdated);
			
			return new ResponseEntity<Employees>(employees, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(employeesId, Messages.MESSAGE_UPDATE_FAIL);
			
		}
	}
	
	@RequestMapping(value = "/{employeesId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Integer employeesId) {

		Employees employees = employeesRepository.findOne(employeesId);

		if (employees != null) {

			employeesRepository.delete(employees);

		} else {
			
			throw new KeywordNotFoundException(employeesId, Messages.MESSAGE_DELETE_FAIL);
			
		}
	}

}
