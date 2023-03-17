package codes.platform.demo.controller;

import codes.platform.demo.entity.Customer;
import codes.platform.demo.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;

    public CustomerController(RestTemplate restTemplate, CustomerRepository customerRepository) {
        this.restTemplate = restTemplate;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public ResponseEntity<Customer> post(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return restTemplate.getForEntity("http://app-exemple-platform-k8s.vkpr:8081/", String.class);
    }
}
