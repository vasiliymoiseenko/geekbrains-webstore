package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webstore.entities.Customer;
import ru.geekbrains.webstore.services.CustomerService;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

  private CustomerService customerService;

  @GetMapping("/show_all")
  public String showAllCustomers(Model model) {
    model.addAttribute("customers", customerService.findAll());
    return "customers";
  }

  @GetMapping("/show/{id}")
  public String showCustomer(Model model, @PathVariable Long id) {
    model.addAttribute("customer", customerService.findById(id).get());
    return "customer_info";
  }

  @GetMapping("/delete/{id}")
  public String deleteCustomer(@PathVariable Long id) {
    customerService.deleteById(id);
    return "redirect:/customers/show_all";
  }

  @GetMapping("/update")
  public String updateCustomer() {
    return "customer_update";
  }

  @PostMapping("/update")
  public String updateCustomer(@RequestParam Long id, @RequestParam String name) {
    Customer customer = new Customer();
    customer.setId(id);
    customer.setName(name);
    customerService.save(customer);
    return "redirect:/customers/show_all";
  }

  @GetMapping("/create")
  public String showCreatePage() {
    return "customer_create";
  }

  @PostMapping("/create")
  public String addCustomer(@RequestParam String name) {
    Customer customer = new Customer();
    customer.setName(name);
    customerService.save(customer);
    return "redirect:/customers/show_all";
  }
}
