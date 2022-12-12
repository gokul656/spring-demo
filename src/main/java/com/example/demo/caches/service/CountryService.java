package com.example.demo.caches.service;

import com.example.demo.caches.dao.Country;
import com.example.demo.caches.dto.CountryDto;
import com.example.demo.caches.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    public CountryDto add(CountryDto country) {
        countryRepository.findByName(country.getName()).ifPresentOrElse(
                data -> {
                    throw new RuntimeException();
                },
                () -> {
                    countryRepository.save(this.convertToEntity(country));
                }
        );
        return country;
    }

    @CacheEvict(value = "country", key = "#countryDto.code")
    public CountryDto update(CountryDto countryDto) {
        Optional<Country> country = countryRepository.findByCode(countryDto.getCode());
        if (country.isEmpty()) {
            throw new RuntimeException();
        }

        country.get().setCode(countryDto.getCode());
        country.get().setName(countryDto.getName());
        countryRepository.save(country.get());

        return countryDto;
    }

    @Cacheable(value = "country", key = "#code", sync = true)
    public CountryDto getCountry(int code) {
        return convertToDto(countryRepository.findByCode(code).orElseThrow());
    }

    @Cacheable(value = "country-list")
    public List<CountryDto> getAllCountries() {
        return countryRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CountryDto convertToDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }

    public Country convertToEntity(CountryDto countryDto) {
        return modelMapper.map(countryDto, Country.class);
    }
}
