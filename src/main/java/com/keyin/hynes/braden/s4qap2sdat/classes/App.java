package com.keyin.hynes.braden.s4qap2sdat.classes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
    "com.keyin.hynes.braden.s4qap2sdat.classes.controllers",
    "com.keyin.hynes.braden.s4qap2sdat.classes.entities",
    "com.keyin.hynes.braden.s4qap2sdat.classes.services",
    "com.keyin.hynes.braden.s4qap2sdat.interfaces.repositores"
})
public class App {
    public App() {}
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}