package codes.platform.demo.controller;

import codes.platform.demo.entity.Customer;
import codes.platform.demo.repository.CustomerRepository;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  private final RestTemplate restTemplate;
  private final CustomerRepository customerRepository;

  public CustomerController(RestTemplate restTemplate, CustomerRepository customerRepository) {
    this.restTemplate = restTemplate;
    this.customerRepository = customerRepository;
  }

  @PostMapping
  public ResponseEntity<Customer> post(@RequestBody Customer customer) {
    logger.info(String.format(String.format("Posting costumer %s", customer.getCustomerName())));
    return ResponseEntity.ok(customerRepository.save(customer));
  }

  @GetMapping
  public ResponseEntity<String> get() {
    logger.info("Calling k8s service /info");
    ResponseEntity<String> forEntity = restTemplate.getForEntity("http://app-exemple-platform-k8s-service-0.vkpr:8081/info",
        String.class);
    logger.info(String.format("Response from k8s service /info: %s", forEntity.getBody()));
    return forEntity;
  }
}
