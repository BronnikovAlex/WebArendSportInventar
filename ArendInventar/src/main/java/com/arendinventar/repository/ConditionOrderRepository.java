package com.arendinventar.repository;

import com.arendinventar.model.ConditionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionOrderRepository extends JpaRepository<ConditionOrder,Long> {
}
