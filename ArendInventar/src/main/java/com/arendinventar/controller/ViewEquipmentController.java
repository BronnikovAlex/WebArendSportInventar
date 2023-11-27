package com.arendinventar.controller;

import com.arendinventar.model.ViewEquipment;
import com.arendinventar.service.ViewEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/view-equipments")
public class ViewEquipmentController {
    private final ViewEquipmentService viewEquipmentService;

    @Autowired
    public ViewEquipmentController(ViewEquipmentService viewEquipmentService) {
        this.viewEquipmentService = viewEquipmentService;
    }

    @GetMapping
    public List<ViewEquipment> getAllViewEquipments() {
        return viewEquipmentService.getAllViewEquipments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewEquipment> getViewEquipmentById(@PathVariable Long id) {
        return viewEquipmentService.getViewEquipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ViewEquipment createViewEquipment(@RequestBody ViewEquipment viewEquipment) {
        return viewEquipmentService.saveViewEquipment(viewEquipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViewEquipment> updateViewEquipment(@PathVariable Long id, @RequestBody ViewEquipment updatedViewEquipment) {
        return viewEquipmentService.getViewEquipmentById(id)
                .map(existingViewEquipment -> {
                    // Update ViewEquipment properties
                    existingViewEquipment.setNameViewEquipment(updatedViewEquipment.getNameViewEquipment());
                    existingViewEquipment.setCost(updatedViewEquipment.getCost());

                    return ResponseEntity.ok(viewEquipmentService.saveViewEquipment(existingViewEquipment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViewEquipment(@PathVariable Long id) {
        viewEquipmentService.deleteViewEquipment(id);
        return ResponseEntity.noContent().build();
    }

}
