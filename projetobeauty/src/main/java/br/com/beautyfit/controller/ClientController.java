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
import br.com.beautyfit.model.Clients;
import br.com.beautyfit.repository.ClientsRepository;

@RequestMapping(value = Path.urls.CLIENTS)
@RestController
public class ClientController {
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Clients>> listALL() {
		
		List<Clients> clients = clientsRepository.findAll();
		
		if(clients.isEmpty()){
			
			return new ResponseEntity<List<Clients>>(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<Clients>>(clients, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{clientsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Clients> getClientesById(@PathVariable Long clientsId) {

		Clients clients = clientsRepository.findOne(clientsId);
		
		if (clients != null) {
		
			return new ResponseEntity<Clients>(clients, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(clientsId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Clients> create(@RequestBody Clients clients) {
		
		clientsRepository.save(clients);
		
		return new ResponseEntity<Clients>(clients, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{clientsId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Clients> update(@PathVariable Long clientsId, @RequestBody Clients clients) {

		Clients clientsUpdated = clientsRepository.findOne(clientsId);

		if (clientsUpdated != null) {
			
			clientsUpdated.setName(clients.getName());
			clientsUpdated.setCpf(clients.getCpf());
			clientsUpdated.setGender(clients.getGender());
			clientsUpdated.setPhoneNumber(clients.getPhoneNumber());
			clientsUpdated.setEmail(clients.getEmail());
									
			clientsRepository.save(clientsUpdated);
			
			return new ResponseEntity<Clients>(clients, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(clientsId, Messages.MESSAGE_UPDATE_FAIL);
			
		}
	}
	
	@RequestMapping(value = "/{clientsId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Long clientsId) {

		Clients clients = clientsRepository.findOne(clientsId);

		if (clients != null) {

			clientsRepository.delete(clients);

		} else {
			
			throw new KeywordNotFoundException(clientsId, Messages.MESSAGE_DELETE_FAIL);
			
		}
	}

}
