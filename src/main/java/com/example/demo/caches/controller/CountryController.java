package com.example.demo.caches.controller;

import com.example.demo.caches.dto.CountryDto;
import com.example.demo.caches.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("add")
    public ResponseEntity<?> addCountry(@RequestBody CountryDto country) {
        return ResponseEntity.ok(countryService.add(country));
    }

    @PutMapping("add")
    public ResponseEntity<?> updateCountry(@RequestBody CountryDto country) {
        return ResponseEntity.ok(countryService.update(country));
    }

    @GetMapping("{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(countryService.getCountry(id));
    }

    @GetMapping("list")
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }
}
