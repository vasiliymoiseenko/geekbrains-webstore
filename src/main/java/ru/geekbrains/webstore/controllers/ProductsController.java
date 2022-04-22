package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.services.ProductService;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductsController {

  private ProductService productService;

  @GetMapping("/getAll")
  public String showIndexPage(Model model) {
    model.addAttribute("products", productService.findAll());
    return "products";
  }

  @GetMapping("/getAllJson")
  @ResponseBody
  public List<Product> getAllJson() {
    return productService.findAll();
  }

  @GetMapping("/test")
  @ResponseBody
  public String responseBlaBla() {
    return "BlaBla";
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
