package com.example.demo.transactions.repo;

import com.example.demo.transactions.dao.CryptoTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoTxn, Long> {
}
