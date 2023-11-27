package com.arendinventar.controller;

import com.arendinventar.model.ConditionOrder;
import com.arendinventar.service.ConditionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conditionOrders")
public class ConditionOrderController {

    private final ConditionOrderService conditionOrderService;

    @Autowired
    public ConditionOrderController(ConditionOrderService conditionOrderService) {
        this.conditionOrderService = conditionOrderService;
    }

    public List<ConditionOrder> getAllConditions() {
        return conditionOrderService.getAllConditions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConditionOrder> getConditionById(@PathVariable Long id) {
        return conditionOrderService.getConditionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
