package com.checque.cheque_detector.controller;

import com.checque.cheque_detector.model.Cheque;
import com.checque.cheque_detector.repository.ChequeRepository;
import com.checque.cheque_detector.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cheques")
@CrossOrigin(origins = "*")
public class ChequeController {

    @Autowired
    private ChequeService chequeService;

    @Autowired
    private ChequeRepository chequeRepository;

    // ➤ Get all cheques
    @GetMapping
    public List<Cheque> getAllCheques() {
        return chequeService.getAllCheques();
    }

    // ➤ Create cheque from JSON (simple POST)
    @PostMapping
    public Cheque createCheque(@RequestBody Cheque cheque) {
        return chequeService.saveCheque(cheque);
    }

    // ➤ Upload cheque image with metadata
    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCheque(
            @RequestParam("chequeNumber") String chequeNumber,
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("payeeName") String payeeName,
            @RequestParam("amount") String amount,
            @RequestParam("date") String date,
            @RequestParam("image") MultipartFile file
    ) {
        try {
            // Create uploads directory if not exists
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            // Save image file
            String fileName = chequeNumber + "_" + file.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            file.transferTo(destination);

            // Save cheque details to DB
            Cheque cheque = new Cheque();
            cheque.setChequeNumber(chequeNumber);
            cheque.setAccountNumber(accountNumber);
            cheque.setPayeeName(payeeName);
            cheque.setAmount(amount);
            cheque.setDate(date);
            cheque.setStatus("PENDING");
            cheque.setSignatureMatch("UNKNOWN");
            cheque.setForgeryDetected("UNKNOWN");
            cheque.setImagePath(uploadDir + fileName);

            chequeRepository.save(cheque);

            return ResponseEntity.ok("Cheque uploaded successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cheque upload failed."+e.getMessage());
        }
    }
}
