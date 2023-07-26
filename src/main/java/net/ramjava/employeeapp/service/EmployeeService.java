package net.ramjava.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import net.ramjava.employeeapp.entity.Employee;
import net.ramjava.employeeapp.repo.EmployeeRepository;
import net.ramjava.employeeapp.response.AddressResponse;
import net.ramjava.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;	
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	//@Value("${addressservice.base.url}")
	//private String addressBaseUrl;
	
	/*
	 * public EmployeeService(@Value("${addressservice.base.url}")String
	 * addressBaseUrl, RestTemplateBuilder builder) { //System.out.println("uri " +
	 * addressBaseUrl); // Initialize RestTemplate this.restTemplate = builder
	 * .rootUri(addressBaseUrl) .build();
	 * 
	 * }
	 */
	
	// Database call
	public EmployeeResponse getEmployeeById(int id) {
		
		// addressResponse -> set data by making a REST API call
		//AddressResponse addressReponse = new AddressResponse();
		
		// employee -> EmployeeResponse
		Employee emp = employeeRepo.findById(id).get();
		// Use ModelMapper instead of manually adding EmployeeResponse object
		EmployeeResponse empResponse = modelMapper.map(emp, EmployeeResponse.class);
		// RestTemplate call --- Block the code flow until the response is received.
		//AddressResponse addressReponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
		// Asynchronous call using WebClient, replacing RestTemplate which is blocking by nature
		AddressResponse addressReponse = webClient
				.get()
				.uri("/address/" + id)
				.retrieve()
				.bodyToMono(AddressResponse.class)
				.block();
		empResponse.setAddressResponse(addressReponse);
		
		/*
		 * EmployeeResponse empResponse = new EmployeeResponse();
		 * empResponse.setId(emp.getId()); empResponse.setName(emp.getName());
		 * empResponse.setEmail(emp.getEmail());
		 * empResponse.setBloodGroup(emp.getBloodGroup());
		 */		
		
		return empResponse;
	}
	
	/*
	 * private AddressResponse callingAddressServiceUsingRestTemplate(int id) {
	 * return restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
	 * }
	 */
}
