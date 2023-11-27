package com.arendinventar.service;

import com.arendinventar.model.ConditionRefund;
import com.arendinventar.repository.ConditionRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionRefundService {

    private final ConditionRefundRepository conditionRefundRepository;

    @Autowired
    public ConditionRefundService(ConditionRefundRepository conditionRefundRepository) {
        this.conditionRefundRepository = conditionRefundRepository;
    }

    public List<ConditionRefund> getAllConditions() {
        return conditionRefundRepository.findAll();
    }

    public Optional<ConditionRefund> getConditionById(Long id) {
        return conditionRefundRepository.findById(id);
    }

    public ConditionRefund saveCondition(ConditionRefund conditionRefund) {
        return conditionRefundRepository.save(conditionRefund);
    }

    public void deleteCondition(Long id) {
        conditionRefundRepository.deleteById(id);
    }
}
