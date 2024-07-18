package com.keyin.hynes.braden.s4qap2sdat.classes.controllers;
import com.keyin.hynes.braden.s4qap2sdat.classes.services.LanguageService;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.LanguageEntity;
import com.keyin.hynes.braden.s4qap2sdat.interfaces.Control;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
public final class LanguageController implements Control {
    @Autowired
    private LanguageService service;
    public LanguageController() {
        this.service = new LanguageService();
    }
    /**
     * @name    list
     * @desc    List all languages
     * @route   GET /api/languages
     * @access  public
     */
    @GetMapping("/api/languages")
    public List<LanguageEntity> list() {
        return service.list();
    }
    /**
     * @name    getByPk
     * @desc    Get a language by its primary key
     * @route   GET /api/languages/:pk
     * @access  public
     */
    @GetMapping("/api/languages/{pk}")
    public LanguageEntity getByPk(@PathVariable int pk) {
        return service.getByPk(pk);
    }
    /**
     * @name    getByName
     * @desc    Get a language by its name
     * @route   GET /api/languages/:name
     * @access  public
     */
    @GetMapping("/api/languages/{name}")
    public LanguageEntity getByName(@PathVariable String name) {
        return service.getByName(name);
    }
    /**
     * @name    add
     * @desc    Add a language
     * @route   POST /api/languages
     * @access  public
     */
    @PostMapping("/api/languages")
    public void add(@RequestBody LanguageEntity language) {
        service.add(language);
    }
    /**
     * @name    edit
     * @desc    Edit a language
     * @route   PUT /api/languages/:pk
     * @access  public
     */
    @PutMapping("/api/languages/{pk}")
    public void edit(
        @PathVariable int pk,
        @RequestBody LanguageEntity update
    ) {
        service.edit(pk, update);
    }
    /**
     * @name    delete
     * @desc    Delete a language
     * @route   DELETE /api/languages/:pk
     * @access  public
     */
    @Override
    @DeleteMapping("/api/languages/{pk}")
    public void delete(@PathVariable int pk) {
        service.delete(pk);
    }
}