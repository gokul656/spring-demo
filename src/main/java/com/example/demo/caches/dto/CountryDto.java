package com.example.demo.caches.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CountryDto implements Serializable {

    private String name;

    private int code;
}
