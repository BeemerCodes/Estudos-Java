package com.pedro.lista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
public class App {
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
