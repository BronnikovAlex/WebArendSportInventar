package com.arendinventar.repository;

import com.arendinventar.model.ViewEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewEquipmentRepository extends JpaRepository<ViewEquipment, Long> {
}
