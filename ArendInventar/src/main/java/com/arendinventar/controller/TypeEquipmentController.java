package com.arendinventar.controller;

import com.arendinventar.model.TypeEquipment;
import com.arendinventar.service.TypeEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeEquipmentController {

    private final TypeEquipmentService typeEquipmentService;

    @Autowired
    public TypeEquipmentController(TypeEquipmentService typeEquipmentService) {
        this.typeEquipmentService = typeEquipmentService;
    }

    @GetMapping
    public List<TypeEquipment> getAllTypes() {
        return typeEquipmentService.getAllTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeEquipment> getTypeById(@PathVariable Long id) {
        return typeEquipmentService.getTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TypeEquipment createType(@RequestBody TypeEquipment typeEquipment) {
        return typeEquipmentService.saveType(typeEquipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeEquipment> updateType(@PathVariable Long id, @RequestBody TypeEquipment updatedType) {
        return typeEquipmentService.getTypeById(id)
                .map(existingType -> {
                    existingType.setNameTypeEquipment(updatedType.getNameTypeEquipment());
                    return ResponseEntity.ok(typeEquipmentService.saveType(existingType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        typeEquipmentService.deleteType(id);
        return ResponseEntity.noContent().build();
    }


}
