package com.example.demo.transactions.repo;

import com.example.demo.transactions.dao.FiatTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiatRepository extends JpaRepository<FiatTxn, Long> {
}
