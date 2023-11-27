package com.arendinventar.service;

import com.arendinventar.model.ViewEquipment;
import com.arendinventar.repository.ViewEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViewEquipmentService {
    private final ViewEquipmentRepository viewEquipmentRepository;

    @Autowired
    public ViewEquipmentService(ViewEquipmentRepository viewEquipmentRepository) {
        this.viewEquipmentRepository = viewEquipmentRepository;
    }

    public List<ViewEquipment> getAllViewEquipments() {
        return viewEquipmentRepository.findAll();
    }

    public Optional<ViewEquipment> getViewEquipmentById(Long id) {
        return viewEquipmentRepository.findById(id);
    }

    public ViewEquipment saveViewEquipment(ViewEquipment viewEquipment) {
        return viewEquipmentRepository.save(viewEquipment);
    }

    public void deleteViewEquipment(Long id) {
        viewEquipmentRepository.deleteById(id);
    }

}
