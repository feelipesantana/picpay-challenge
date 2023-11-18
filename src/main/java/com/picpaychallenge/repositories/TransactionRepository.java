package com.picpaychallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpaychallenge.domain.transaction.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
