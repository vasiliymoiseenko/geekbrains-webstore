package ru.geekbrains.webstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.entities.Order;
import ru.geekbrains.webstore.repositories.OrderRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService implements ru.geekbrains.webstore.services.Service<Order> {

    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
    }
}
