package com.arendinventar.repository;

import com.arendinventar.model.TypeEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeEquipmentRepository extends JpaRepository<TypeEquipment, Long> {
}
