package com.arendinventar.service;

import com.arendinventar.model.DescriptionRefund;
import com.arendinventar.repository.DescriptionRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescriptionRefundService {

    private final DescriptionRefundRepository descriptionRefundRepository;

    @Autowired
    public DescriptionRefundService(DescriptionRefundRepository descriptionRefundRepository) {
        this.descriptionRefundRepository = descriptionRefundRepository;
    }

    public List<DescriptionRefund> getAllDescriptions() {
        return descriptionRefundRepository.findAll();
    }

    public Optional<DescriptionRefund> getDescriptionById(Long id) {
        return descriptionRefundRepository.findById(id);
    }

    public DescriptionRefund saveDescription(DescriptionRefund descriptionRefund) {
        return descriptionRefundRepository.save(descriptionRefund);
    }

    public void deleteDescription(Long id) {
        descriptionRefundRepository.deleteById(id);
    }
}
