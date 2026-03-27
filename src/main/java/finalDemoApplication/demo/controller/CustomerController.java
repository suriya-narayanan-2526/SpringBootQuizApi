package finalDemoApplication.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finalDemoApplication.demo.entity.CustomerEntity;
import finalDemoApplication.demo.security.Jwtutil;
import finalDemoApplication.demo.service.CustomerService;

@RestController
@RequestMapping("/api/auth")
public class CustomerController {
	@Autowired
	private CustomerService service;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private Jwtutil jwt;
	@PostMapping("/register")
	public ResponseEntity<CustomerEntity> createSCustomer(@RequestBody CustomerEntity cus)
	{
		return ResponseEntity.ok().body(service.createCustomer(cus));
	}
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> loginCustomer(@RequestBody CustomerEntity cus)
	{
		try {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(cus.getName(),cus.getPassword()));
		UserDetails user = (UserDetails)auth.getPrincipal();
		String Token = jwt.generateToken(user);
		return ResponseEntity.ok().body(Map.of("Token :",Token));
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Error","Invalid UserName and Password"));
		}
	}

}
