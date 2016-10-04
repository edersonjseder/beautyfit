package br.com.beautyfit.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.beautyfit.model.UserAccount;
import br.com.beautyfit.repository.UserRepository;

@Service
public class UserDetailsFitService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAccount theUser = userRepository.findByUsername(username);
		
		if(theUser == null){
			throw new UsernameNotFoundException(username + " not found");
		}
		
		return new User(username, theUser.getPassword(), getGrantedAuthority(username));
	}
	
	
	/** Method that does a new query in the database to check which roles the user has **/
	private Collection<? extends GrantedAuthority> getGrantedAuthority(String username){
		
		Collection<? extends GrantedAuthority> authorities;
		
		if (username.equals("admin")) {
			
			authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
			
		} else {
			
			authorities = AuthorityUtils.createAuthorityList("ROLE_BASIC","ROLE_USER");
		}
		
		return authorities;
		
	}

}
