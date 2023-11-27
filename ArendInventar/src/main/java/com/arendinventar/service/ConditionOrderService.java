package com.arendinventar.service;

import com.arendinventar.model.ConditionOrder;
import com.arendinventar.repository.ConditionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionOrderService {
    private final ConditionOrderRepository conditionOrderRepository;

    @Autowired
    public ConditionOrderService(ConditionOrderRepository conditionOrderRepository) {
        this.conditionOrderRepository = conditionOrderRepository;
    }
    public List<ConditionOrder> getAllConditions() {
        return conditionOrderRepository.findAll();
    }

    public Optional<ConditionOrder> getConditionById(Long id) {
        return conditionOrderRepository.findById(id);
    }

    public ConditionOrder saveCondition(ConditionOrder conditionOrder) {
        return conditionOrderRepository.save(conditionOrder);
    }

    public void deleteCondition(Long id) {
        conditionOrderRepository.deleteById(id);
    }

}
