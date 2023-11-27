package com.arendinventar.service;

import com.arendinventar.model.TypeEquipment;
import com.arendinventar.repository.TypeEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeEquipmentService {

    private final TypeEquipmentRepository typeEquipmentRepository;

    @Autowired
    public TypeEquipmentService(TypeEquipmentRepository typeEquipmentRepository) {
        this.typeEquipmentRepository = typeEquipmentRepository;
    }

    public List<TypeEquipment> getAllTypes() {
        return typeEquipmentRepository.findAll();
    }

    public Optional<TypeEquipment> getTypeById(Long id) {
        return typeEquipmentRepository.findById(id);
    }

    public TypeEquipment saveType(TypeEquipment typeEquipment) {
        return typeEquipmentRepository.save(typeEquipment);
    }

    public void deleteType(Long id) {
        typeEquipmentRepository.deleteById(id);
    }

}
