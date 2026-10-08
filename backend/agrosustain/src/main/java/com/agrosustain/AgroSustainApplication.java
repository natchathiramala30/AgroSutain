package com.agrosustain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgroSustainApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgroSustainApplication.class, args);
        System.out.println("==========================================================");
        System.out.println("🌾 AgroSustain - Smart Agriculture Backend Running on :8080");
        System.out.println("🌍 Supporting SDGs: SDG 2, SDG 6, SDG 12, SDG 13, SDG 15");
        System.out.println("==========================================================");
    }
}
