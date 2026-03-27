package finalDemoApplication.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import finalDemoApplication.demo.entity.CustomerEntity;
import finalDemoApplication.demo.repository.CustomerRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private CustomerRepository cusRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		CustomerEntity customer = cusRepo.findByName(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
		return new User(customer.getName(),customer.getPassword(),List.of(new SimpleGrantedAuthority("Role_ADMIN")));
	}

}
