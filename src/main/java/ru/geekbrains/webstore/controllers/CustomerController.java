package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.dtos.CustomerDto;
import ru.geekbrains.webstore.entities.Customer;
import ru.geekbrains.webstore.exceptions.ResourceNotFoundException;
import ru.geekbrains.webstore.services.CustomerService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private CustomerService customerService;

  @GetMapping
  public Page<CustomerDto> getAllCustomers(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    if (pageIndex < 1) {
      pageIndex = 1;
    }
    return customerService.findAll(pageIndex - 1, 10).map(CustomerDto::new);
  }

  @GetMapping("/{id}")
  public CustomerDto GetCustomerById(@PathVariable Long id) {
    Customer customer = customerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer id = " + id + " not found"));
    return new CustomerDto(customer);
  }

  @PostMapping
  public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
    Customer customer = new Customer();
    customer.setName(customerDto.getName());
    customerService.save(customer);
    return new CustomerDto(customer);
  }

  @PutMapping
  public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
    Long id = customerDto.getId();
    Customer customer = customerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer id = " + id + " not found"));
    customer.setName(customerDto.getName());
    customerService.save(customer);
    return new CustomerDto(customer);
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable Long id) {
    customerService.deleteById(id);
  }
}
