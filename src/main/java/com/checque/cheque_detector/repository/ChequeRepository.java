// repository/ChequeRepository.java
package com.checque.cheque_detector.repository;

import com.checque.cheque_detector.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
}
