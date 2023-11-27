package com.arendinventar.service;

import com.arendinventar.model.Refund;
import com.arendinventar.repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefundService {

    private final RefundRepository refundRepository;

    @Autowired
    public RefundService(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }

    public List<Refund> getAllRefunds() {
        return refundRepository.findAll();
    }

    public Optional<Refund> getRefundById(Long id) {
        return refundRepository.findById(id);
    }

    public Refund saveRefund(Refund refund) {
        return refundRepository.save(refund);
    }

    public void deleteRefund(Long id) {
        refundRepository.deleteById(id);
    }


}
