package ru.geekbrains.webstore.services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.entities.Customer;
import ru.geekbrains.webstore.repositories.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService implements ru.geekbrains.webstore.services.Service<Customer> {

  private CustomerRepository customerRepository;

  @Override
  public Page<Customer> findAll(int pageIndex, int pageSize) {
    return customerRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id")));
  }

  @Override
  public Optional<Customer> findById(Long id) {
    return customerRepository.findById(id);
  }

  @Override
  public void deleteById(Long id) {
    customerRepository.deleteById(id);
  }

  @Override
  public void save(Customer customer) {
    customerRepository.save(customer);
  }

  public Optional<Customer> findByName(String name) {
    return customerRepository.findCustomerByName(name);
  }
}
