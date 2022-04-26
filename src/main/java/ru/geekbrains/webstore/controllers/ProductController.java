package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.services.ProductService;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  @GetMapping("/show_all")
  public String showAllProducts(Model model) {
    model.addAttribute("products", productService.findAll());
    return "products";
  }

  @GetMapping("/show/{id}")
  public String showProduct(Model model, @PathVariable Long id) {
    model.addAttribute("product", productService.findById(id));
    return "product_info";
  }

  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable Long id) {
    productService.deleteById(id);
    return "redirect:/products/show_all";
  }

  @GetMapping("/update")
  public String updateProduct() {
    return "product_update";
  }

  @PostMapping("/update")
  public String updateProduct(@RequestParam Long id, @RequestParam String title, @RequestParam Double price) {
    Product product = new Product();
    product.setId(id);
    product.setTitle(title);
    product.setPrice(price);
    productService.update(product);
    return "redirect:/products/show_all";
  }

  @GetMapping("/create")
  public String showCreatePage() {
    return "product_create";
  }

  @PostMapping("/create")
  public String addProduct(@RequestParam String title, @RequestParam Double price) {
    Product product = new Product();
    product.setTitle(title);
    product.setPrice(price);
    productService.save(product);
    return "redirect:/products/show_all";
  }
}
