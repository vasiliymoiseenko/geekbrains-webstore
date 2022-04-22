package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.services.ProductService;

@Controller
@AllArgsConstructor
public class ProductsController {

  private ProductService productService;

  @GetMapping
  public String showIndexPage(Model model) {
    model.addAttribute("products", productService.findAll());
    return "index";
  }

  @GetMapping("/create")
  public String showCreatePage() {
    return "create";
  }

  @PostMapping("/create")
  public String addProduct(@RequestParam Long id, @RequestParam String title, @RequestParam Double price) {
    //productService.add(new Product(id, title, price));
    return "redirect:/";
  }
}
