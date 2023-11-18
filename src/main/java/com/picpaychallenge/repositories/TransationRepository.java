package com.picpaychallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpaychallenge.transaction.Transaction;

@Repository
public interface TransationRepository extends JpaRepository<Transaction, Long> {
    
}
