// controller/ChequeController.java
package com.checque.cheque_detector.controller;

import com.checque.cheque_detector.model.Cheque;
import com.checque.cheque_detector.service.ChequeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cheques")
@CrossOrigin(origins = "*")
public class ChequeController {

    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @PostMapping
    public Cheque uploadCheque(@RequestBody Cheque cheque) {
        return chequeService.saveCheque(cheque);
    }

    @GetMapping
    public List<Cheque> getAllCheques() {
        return chequeService.getAllCheques();
    }
}
