package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import br.com.beautyfit.model.UserAccount;

@Transactional
public interface UserRepository extends CrudRepository<UserAccount, Integer> {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UserAccount> findAll();
	
    public UserAccount findByUsername(String username);
    
    public UserAccount findByUsernameAndPassword(String username, String password);
}
