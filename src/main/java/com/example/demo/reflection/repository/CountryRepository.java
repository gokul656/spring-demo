package com.example.demo.reflection.repository;

import com.example.demo.reflection.dao.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);

    Optional<Country> findByCode(int code);
}
