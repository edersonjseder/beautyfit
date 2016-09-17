package br.com.beautyfit.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, Integer> {
	
    public UserAccount findByUsername(String username);
    
    public UserAccount findByUsernameAndPassword(String username, String password);
}
