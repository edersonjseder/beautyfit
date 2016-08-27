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
import br.com.beautyfit.model.CustomerService;
import br.com.beautyfit.repository.CustomerServiceRepository;

@RequestMapping(value = Path.urls.SERVICES)
@RestController
public class CustomerServiceController {
	
	@Autowired
	private CustomerServiceRepository customerServiceRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CustomerService>> listALL() {
		
		List<CustomerService> customerService = customerServiceRepository.findAll();
		
		if(customerService.isEmpty()){
			
			return new ResponseEntity<List<CustomerService>>(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<CustomerService>>(customerService, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{customerServiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerService> getServicesById(@PathVariable Long customerServiceId) {

		CustomerService customerService = customerServiceRepository.findOne(customerServiceId);
		
		if (customerService != null) {
		
			return new ResponseEntity<CustomerService>(customerService, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(customerServiceId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CustomerService> create(@RequestBody CustomerService customerService) {
		
		customerServiceRepository.save(customerService);
		
		return new ResponseEntity<CustomerService>(customerService, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{customerServiceId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<CustomerService> update(@PathVariable Long customerServiceId, @RequestBody CustomerService customerService) {

		CustomerService customerServiceUpdated = customerServiceRepository.findOne(customerServiceId);

		if (customerServiceUpdated != null) {
			
			customerServiceUpdated.setName(customerService.getName());
			customerServiceUpdated.setDescription(customerService.getDescription());
			customerServiceUpdated.setPrice(customerService.getPrice());
									
			customerServiceRepository.save(customerServiceUpdated);
			
			return new ResponseEntity<CustomerService>(customerService, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(customerServiceId, Messages.MESSAGE_UPDATE_FAIL);
			
		}
	}
	
	@RequestMapping(value = "/{customerServiceId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Long customerServiceId) {

		CustomerService customerService = customerServiceRepository.findOne(customerServiceId);

		if (customerService != null) {

			customerServiceRepository.delete(customerService);

		} else {
			
			throw new KeywordNotFoundException(customerServiceId, Messages.MESSAGE_DELETE_FAIL);
			
		}
	}
}
