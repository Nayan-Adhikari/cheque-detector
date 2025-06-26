// model/Cheque.java
package com.checque.cheque_detector.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chequeNumber;
    private String accountNumber;
    private String payeeName;
    private String amount;
    private String date;
    private String status; // e.g., "PENDING", "GENUINE", "FRAUD"
    private String signatureMatch; // YES/NO
    private String forgeryDetected; // YES/NO
    @Column(name = "image_path")
    private String imagePath;
}
