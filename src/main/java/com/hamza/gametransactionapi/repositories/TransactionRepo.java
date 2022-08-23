package com.hamza.gametransactionapi.repositories;

import com.hamza.gametransactionapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Optional<Transaction>> findByUserId(Long id);

    ArrayList<Transaction> findByAmountLessThan(BigDecimal amount);

    ArrayList<Transaction> findByAmountGreaterThan(BigDecimal amount);
}
