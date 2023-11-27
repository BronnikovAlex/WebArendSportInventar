package com.arendinventar.controller;

import com.arendinventar.model.OrderArend;
import com.arendinventar.repository.OrderArendRepository;
import com.arendinventar.service.OrderArendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class OrderArendController {

    private final OrderArendService orderArendService;

    @Autowired
    public OrderArendController(OrderArendService orderArendService) {
        this.orderArendService = orderArendService;
    }

    @Autowired
    private OrderArendRepository orderArendRepository;
    @GetMapping
    public List<OrderArend> getAllOrders() {
        return orderArendService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderArend> getOrderById(@PathVariable Long id) {
        return orderArendService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderArend createOrder(@RequestBody OrderArend orderArend) {
        return orderArendService.saveOrder(orderArend);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderArend> updateOrder(@PathVariable Long id, @RequestBody OrderArend updatedOrder) {
        return orderArendService.getOrderById(id)
                .map(existingOrder -> {
                    existingOrder.setStatusOrder(updatedOrder.getStatusOrder());
                    existingOrder.setDateNachaloArend(updatedOrder.getDateNachaloArend());
                    existingOrder.setDateEndArend(updatedOrder.getDateEndArend());
                    existingOrder.setTerm(updatedOrder.getTerm());
                    existingOrder.setEquipment(updatedOrder.getEquipment());
                    existingOrder.setUserArSpIn(updatedOrder.getUserArSpIn());
                    existingOrder.setClient(updatedOrder.getClient());
                    existingOrder.setEmployee(updatedOrder.getEmployee());
                    existingOrder.setRefund(updatedOrder.getRefund());
                    existingOrder.setConditionOrder(updatedOrder.getConditionOrder());
                    return ResponseEntity.ok(orderArendService.saveOrder(existingOrder));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderArendService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public List<OrderArend> testMethod(@PathVariable Long id) {
        return orderArendRepository.findByUserArSpInIdUserArSpIn(id);
    }

}
