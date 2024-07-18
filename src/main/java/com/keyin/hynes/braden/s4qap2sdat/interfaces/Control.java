package com.keyin.hynes.braden.s4qap2sdat.interfaces;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
@Controller
public interface Control {
    @DeleteMapping
    void delete(int pk);
}