package com.keyin.hynes.braden.s4qap2sdat.classes.controllers;
import com.keyin.hynes.braden.s4qap2sdat.classes.services.GreetingService;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.GreetingEntity;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.LanguageEntity;
import com.keyin.hynes.braden.s4qap2sdat.interfaces.Control;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
public class GreetingController implements Control {
    @Autowired
    private GreetingService service;
    public GreetingController() {
        this.service = new GreetingService();
    }
    /**
     * @name    list
     * @desc    List all greetings
     * @route   GET /api/greetings
     * @access  public
     */
    @GetMapping("/api/greetings")
    public List<GreetingEntity> list() {
        return service.list();
    }
    /**
     * @name    getByPk
     * @desc    Get a greeting by its primary key
     * @route   GET /api/greetings/:pk
     * @access  public
     */
    @GetMapping("/api/greetings/{pk}")
    public GreetingEntity getByPk(@PathVariable int pk) {
        return service.getByPk(pk);
    }
    /**
     * @name    getByNameAndGreeting
     * @desc    Get a greeting by its body and the subject's name
     * @route   GET /api/greetings/:name:body
     * @access  public
     */
    @GetMapping("/api/greetings/{name}{body}")
    public GreetingEntity getByNameAndBody(
        @PathVariable String name,
        @PathVariable String body
    ) {
        return service.getByNameAndBody(name, body);
    }
    /**
     * @name    add
     * @desc    Add a greeting
     * @route   POST /api/greetings
     * @access  public
     */
    @PostMapping("/api/greetings")
    public GreetingEntity add(@RequestBody GreetingEntity greeting) {
        return service.add(greeting);
    }
    /**
     * @name    addLanguage
     * @desc    Add a language to a greeting
     * @route   PATCH /api/language/:pk
     * @access  public
     */
    @PatchMapping("/api/greetings/{pk}")
    public GreetingEntity addLanguage(
        @PathVariable int pk,
        @RequestBody LanguageEntity language
    ) {
        return service.addLanguage(pk, language);
    }
    /**
     * @name    edit
     * @desc    Edit a greeting
     * @route   PUT /api/greetings/:pk
     * @access  public
     */
    @PutMapping("/api/greetings/{pk}")
    public GreetingEntity edit(
        @PathVariable int pk,
        @RequestBody GreetingEntity update
    ) {
        return service.edit(pk, update);
    }
    /**
     * @name    delete
     * @desc    Delete a language
     * @route   DELETE /api/greetings/:pk
     * @access  public
     */
    @Override
    @DeleteMapping("/api/greetings/{pk}")
    public String delete(@PathVariable int pk) {
        return service.delete(pk);
    }
}