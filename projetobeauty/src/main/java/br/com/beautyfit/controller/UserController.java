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
import br.com.beautyfit.model.UserAccount;
import br.com.beautyfit.repository.UserRepository;

@RestController
@RequestMapping(value = Path.urls.USERS)
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserAccount> getAllUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> getOneUser(@PathVariable Integer userId){
		
		UserAccount userAccount = userRepository.findOne(userId);
		
		if(userAccount != null) {
		
			return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(userId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<UserAccount> createAccount(@RequestBody UserAccount userAccount){
		
		userRepository.save(userAccount);
		
		return new ResponseEntity<UserAccount>(userAccount, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<UserAccount> updateUser(@PathVariable Integer userId, @RequestBody UserAccount userAccount){
		
		UserAccount userAccountUpdated = userRepository.findOne(userId);

		if (userAccountUpdated != null) {
			
			userAccountUpdated.setUsername(userAccount.getUsername());
			userAccountUpdated.setPassword(userAccount.getPassword());
			userAccountUpdated.setEmail(userAccount.getEmail());
									
			userRepository.save(userAccountUpdated);
			
			return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<UserAccount>(userAccount, HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteAccount( @PathVariable Integer userId) {
		
		UserAccount userAccount = userRepository.findOne(userId);

		if (userAccount != null) {

			userRepository.delete(userAccount);

		} else {
			
			throw new KeywordNotFoundException(userId, Messages.MESSAGE_DELETE_FAIL);
			
		}
		
	}

}
