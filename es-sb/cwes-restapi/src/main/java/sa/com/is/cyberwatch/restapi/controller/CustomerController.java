package sa.com.is.cyberwatch.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sa.com.is.cyberwatch.restapi.model.Customer;
import sa.com.is.cyberwatch.restapi.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository repository;

	@PostMapping("/saveCustomer")
	public int saveCustomer(@RequestBody List<Customer> customers) {
		repository.saveAll(customers);
		return customers.size();
	}

	@GetMapping("/findAll")
	public Iterable<Customer> findAllCustomers() {
		return repository.findAll();
	}
//http://localhost:8080/findByFName/firoz
	@GetMapping("/findByFName/{firstName}")
	public List<Customer> findByFirstName(@PathVariable String firstName) {
		return repository.findByFirstname(firstName);
	}

	@GetMapping("/findByLName/{lastName}")
	public List<Customer> findByLastName(@PathVariable String lastName) {
		return repository.findByLastname(lastName);
	}

	@GetMapping("/findByAge/{age}")
	public List<Customer> findByAge(@PathVariable String age) {
		return repository.findByAge(age);
	}

}
