package com.keyin.hynes.braden.s4qap2sdat.interfaces;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin
public interface Control {
    @DeleteMapping
    String delete(int pk);
}