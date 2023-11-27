package com.arendinventar.controller;


import com.arendinventar.model.Equipment;
import com.arendinventar.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        return equipmentService.getEquipmentById(id)
                .map(existingEquipment -> {
                    existingEquipment.setNameEquip(updatedEquipment.getNameEquip());
                    existingEquipment.setDescription(updatedEquipment.getDescription());
                    existingEquipment.setCostArend(updatedEquipment.getCostArend());
                    existingEquipment.setDeposit(updatedEquipment.getDeposit());
                    existingEquipment.setTypeEquipment(updatedEquipment.getTypeEquipment());
                    existingEquipment.setViewEquipment(updatedEquipment.getViewEquipment());
                    existingEquipment.setNumberEquipment(updatedEquipment.getNumberEquipment());
                    return ResponseEntity.ok(equipmentService.saveEquipment(existingEquipment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
