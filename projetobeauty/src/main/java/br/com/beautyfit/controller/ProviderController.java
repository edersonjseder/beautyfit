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
import br.com.beautyfit.model.Providers;
import br.com.beautyfit.repository.ProviderRepository;

@RequestMapping(value = Path.urls.PROVIDERS)
@RestController
public class ProviderController {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Providers>> listALL() {
		
		List<Providers> providers = providerRepository.findAll();
		
		if(providers.isEmpty()){
			
			return new ResponseEntity<List<Providers>>(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<Providers>>(providers, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{providersId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Providers> getProvidersById(@PathVariable Integer providersId) {

		Providers providers = providerRepository.findOne(providersId);
		
		if (providers != null) {
		
			return new ResponseEntity<Providers>(providers, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(providersId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Providers> create(@RequestBody Providers providers) {
		
		providerRepository.save(providers);
		
		return new ResponseEntity<Providers>(providers, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{providersId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Providers> update(@PathVariable Integer providersId, @RequestBody Providers providers) {

		Providers providersUpdated = providerRepository.findOne(providersId);

		if (providersUpdated != null) {
			
			providersUpdated.setName(providers.getName());
			providersUpdated.setCpfOrCnpj(providers.getCpfOrCnpj());
			providersUpdated.setIeOrRg(providers.getIeOrRg());
			providersUpdated.setRegisterDate(providers.getRegisterDate());
			providersUpdated.setPhoneNumber(providers.getPhoneNumber());
			providersUpdated.setEmail(providers.getEmail());
									
			providerRepository.save(providersUpdated);
			
			return new ResponseEntity<Providers>(providers, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(providersId, Messages.MESSAGE_UPDATE_FAIL);
			
		}
	}
	
	@RequestMapping(value = "/{providersId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Integer providersId) {

		Providers providers = providerRepository.findOne(providersId);

		if (providers != null) {

			providerRepository.delete(providers);

		} else {
			
			throw new KeywordNotFoundException(providersId, Messages.MESSAGE_DELETE_FAIL);
			
		}
	}

}
