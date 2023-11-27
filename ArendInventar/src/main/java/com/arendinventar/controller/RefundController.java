package com.arendinventar.controller;

import com.arendinventar.model.Refund;
import com.arendinventar.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    @Autowired
    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @GetMapping
    public List<Refund> getAllRefunds() {
        return refundService.getAllRefunds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refund> getRefundById(@PathVariable Long id) {
        return refundService.getRefundById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Refund createRefund(@RequestBody Refund refund) {
        return refundService.saveRefund(refund);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Refund> updateRefund(@PathVariable Long id, @RequestBody Refund updatedRefund) {
        return refundService.getRefundById(id)
                .map(existingRefund -> {
                    existingRefund.setDateRefund(updatedRefund.getDateRefund());
                    existingRefund.setDescriptionRefund(updatedRefund.getDescriptionRefund());
                    existingRefund.setConditionRefund(updatedRefund.getConditionRefund());
                    existingRefund.setUserArSpIn(updatedRefund.getUserArSpIn());
                    existingRefund.setOrderArend(updatedRefund.getOrderArend());
                    return ResponseEntity.ok(refundService.saveRefund(existingRefund));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefund(@PathVariable Long id) {
        refundService.deleteRefund(id);
        return ResponseEntity.noContent().build();
    }

}
