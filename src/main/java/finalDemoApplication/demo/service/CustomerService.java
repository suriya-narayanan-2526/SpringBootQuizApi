package finalDemoApplication.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import finalDemoApplication.demo.entity.CustomerEntity;
import finalDemoApplication.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository cusRepo;
	@Autowired
	private BCryptPasswordEncoder encoder;
	public CustomerEntity createCustomer(CustomerEntity cus)
	{
		cus.setPassword(encoder.encode(cus.getPassword()));
		return cusRepo.save(cus);
	}

}
