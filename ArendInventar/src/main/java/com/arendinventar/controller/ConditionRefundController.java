package com.arendinventar.controller;

import com.arendinventar.model.ConditionRefund;
import com.arendinventar.service.ConditionRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conditionRefunds")
public class ConditionRefundController {

    private final ConditionRefundService conditionRefundService;

    @Autowired
    public ConditionRefundController(ConditionRefundService conditionRefundService) {
        this.conditionRefundService = conditionRefundService;
    }

    @GetMapping
    public List<ConditionRefund> getAllConditions() {
        return conditionRefundService.getAllConditions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConditionRefund> getConditionById(@PathVariable Long id) {
        return conditionRefundService.getConditionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConditionRefund createCondition(@RequestBody ConditionRefund conditionRefund) {
        return conditionRefundService.saveCondition(conditionRefund);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConditionRefund> updateCondition(@PathVariable Long id, @RequestBody ConditionRefund updatedCondition) {
        return conditionRefundService.getConditionById(id)
                .map(existingCondition -> {
                    existingCondition.setNameCondition(updatedCondition.getNameCondition());
                    return ResponseEntity.ok(conditionRefundService.saveCondition(existingCondition));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long id) {
        conditionRefundService.deleteCondition(id);
        return ResponseEntity.noContent().build();
    }


}
