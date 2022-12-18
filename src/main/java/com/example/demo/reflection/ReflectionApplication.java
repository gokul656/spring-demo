package com.example.demo.reflection;

import com.example.demo.reflection.dao.Country;
import com.example.demo.reflection.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@EnableCaching
@SpringBootApplication
public class ReflectionApplication implements CommandLineRunner {

    public ReflectionApplication(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReflectionApplication.class, args);
    }

    private final CountryRepository countryRepository;

    @Override
    public void run(String... args) {

        // Before updating fields in a Class, make sure that the variable is "public"
        Country country = new Country("Argentina", 432);
        try {
            /*
             This method lists all the fields present in class
             for (Field declaredField : countryClass.getDeclaredFields()) {
                log.info("FIELD : {}", declaredField.getName());
             }

             Here you should give the class from which you want to retrieve data from
             For eg: here `country` has data with name as 'Argentina' and 'code' as 432,
             so I should the `country` variable.

             If I use any other variable like `new Country()` it'll return null since no value has been initialized here.
             */

            Class countryClass = country.getClass();
            Field name = countryClass.getField("name");
            log.info("BEFORE : {}", name.get(country));

            name.set(country, "New Argentina");

            log.info("BEFORE :: {}", country.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // For updating using API

        HashMap<String, ?> datumToBeUpdated = new HashMap<>() {{
            put("name", UUID.randomUUID().toString());
            put("code", 143);
        }};

        Optional<Country> countryData = countryRepository.findById(1L);
        countryData.ifPresent(it -> {
            Class countryClass = country.getClass();

            datumToBeUpdated.forEach((k, v) -> {
                try {
                    Field field = countryClass.getField(k);
                    log.info("FIELD BEFORE :: {}", field.get(it));
                    field.set(it, v);

                    log.info("FIELD AFTER  :: {}", field.get(it));
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    log.error("FIELD NOT FOUND :: {}", k);
                }
            });

            countryRepository.save(it);
        });
    }
}
