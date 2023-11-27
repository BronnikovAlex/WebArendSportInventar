package com.arendinventar.repository;

import com.arendinventar.model.ConditionRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRefundRepository extends JpaRepository<ConditionRefund, Long> {
}
