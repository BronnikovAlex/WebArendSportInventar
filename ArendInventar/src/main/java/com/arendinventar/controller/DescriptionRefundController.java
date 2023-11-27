package com.arendinventar.controller;

import com.arendinventar.model.DescriptionRefund;
import com.arendinventar.service.DescriptionRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/descriptionRefunds")
public class DescriptionRefundController {

    private final DescriptionRefundService descriptionRefundService;

    @Autowired
    public DescriptionRefundController(DescriptionRefundService descriptionRefundService) {
        this.descriptionRefundService = descriptionRefundService;
    }

    @GetMapping
    public List<DescriptionRefund> getAllDescriptions() {
        return descriptionRefundService.getAllDescriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescriptionRefund> getDescriptionById(@PathVariable Long id) {
        return descriptionRefundService.getDescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DescriptionRefund createDescription(@RequestBody DescriptionRefund descriptionRefund) {
        return descriptionRefundService.saveDescription(descriptionRefund);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescriptionRefund> updateDescription(@PathVariable Long id, @RequestBody DescriptionRefund updatedDescription) {
        return descriptionRefundService.getDescriptionById(id)
                .map(existingDescription -> {
                    existingDescription.setNameDescript(updatedDescription.getNameDescript());
                    return ResponseEntity.ok(descriptionRefundService.saveDescription(existingDescription));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDescription(@PathVariable Long id) {
        descriptionRefundService.deleteDescription(id);
        return ResponseEntity.noContent().build();
    }
}
