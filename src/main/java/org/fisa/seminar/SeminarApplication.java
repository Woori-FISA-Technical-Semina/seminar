package org.fisa.seminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SeminarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeminarApplication.class, args);
    }

}
