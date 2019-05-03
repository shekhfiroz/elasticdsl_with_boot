package sa.com.is.cyberwatch.web.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import sa.com.is.cyberwatch.web.dto.LoginFormDto;
import sa.com.is.cyberwatch.web.model.Customer;
import sa.com.is.cyberwatch.web.repository.CustomerRepository;
import sa.com.is.cyberwatch.web.repository.ElasticSearchCustomerTemplate;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private ElasticSearchCustomerTemplate customerTemplate;

	public CustomerService(CustomerRepository customerRepository, ElasticSearchCustomerTemplate customerTemplate) {
		this.customerRepository = customerRepository;
		this.customerTemplate = customerTemplate;
	}

	public Customer getCustomer(LoginFormDto request) {
		return customerRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
	}

	public List<Customer> getAllCustomer() {
		return StreamSupport.stream(customerRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
		// return customerRepository.findByEmailAndPassword(request.getEmail(),
		// request.getPassword());
	}

	public List<Customer> getByQuery(String q) {
		return customerTemplate.getAllBy(q);
	}

	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

	public Customer getById(String id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent())
			return customer.get();
		return null;
	}

}
