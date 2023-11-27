package com.arendinventar.service;

import com.arendinventar.model.OrderArend;
import com.arendinventar.repository.OrderArendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderArendService {

    private final OrderArendRepository orderArendRepository;

    @Autowired
    public OrderArendService(OrderArendRepository orderArendRepository) {
        this.orderArendRepository = orderArendRepository;
    }

    public List<OrderArend> getAllOrders() {
        return orderArendRepository.findAll();
    }

    public Optional<OrderArend> getOrderById(Long id) {
        return orderArendRepository.findById(id);
    }

    public OrderArend saveOrder(OrderArend orderArend) {
        return orderArendRepository.save(orderArend);
    }

    public void deleteOrder(Long id) {
        orderArendRepository.deleteById(id);
    }

}
