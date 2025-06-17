// service/ChequeService.java
package com.checque.cheque_detector.service;

import com.checque.cheque_detector.model.Cheque;
import com.checque.cheque_detector.repository.ChequeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChequeService {

    private final ChequeRepository chequeRepository;

    public ChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    public Cheque saveCheque(Cheque cheque) {
        return chequeRepository.save(cheque);
    }

    public List<Cheque> getAllCheques() {
        return chequeRepository.findAll();
    }
}
