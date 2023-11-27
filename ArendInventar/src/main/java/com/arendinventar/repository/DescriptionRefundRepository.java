package com.arendinventar.repository;

import com.arendinventar.model.DescriptionRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRefundRepository extends JpaRepository<DescriptionRefund, Long> {
}
