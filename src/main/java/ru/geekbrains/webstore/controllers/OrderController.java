package ru.geekbrains.webstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.webstore.services.OrderService;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping("/show_all")
    public  String showOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }
}
