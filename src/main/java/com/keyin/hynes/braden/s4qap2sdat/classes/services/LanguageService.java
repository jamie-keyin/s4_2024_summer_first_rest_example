package com.keyin.hynes.braden.s4qap2sdat.classes.services;
import com.keyin.hynes.braden.s4qap2sdat.interfaces.repositories.LanguageRepository;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.LanguageEntity;
import com.keyin.hynes.braden.s4qap2sdat.interfaces.Serve;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
public final class LanguageService implements Serve {
    @Autowired
    private LanguageRepository repo;
    private LanguageEntity current;
    public LanguageService() {}
    /**
     * @name    list
     * @desc    List all languages
     * @route   GET /api/languages
     * @access  public
     */
    public List<LanguageEntity> list() {
        return StreamSupport.stream(
            repo.findAll().spliterator(),
            false
        ).collect(Collectors.toList());
    }
    /**
     * @name    getByPk
     * @desc    Get a language by its primary key
     * @route   GET /api/languages/:pk
     * @access  public
     */
    public LanguageEntity getByPk(int pk) {
        return repo.findById(pk).orElse(null);
    }
    /**
     * @name    getByName
     * @desc    Get a language by its name
     * @route   GET /api/languages/:name
     * @access  public
     */
    public LanguageEntity getByName(String name) {
        return repo.findByName(name);
    }
    /**
     * @name    add
     * @desc    Add a language
     * @route   POST /api/languages
     * @access  public
     */
    public void add(LanguageEntity language) {
        repo.save(language);
    }
    /**
     * @name    edit
     * @desc    Edit a language
     * @route   PUT /api/languages/:pk
     * @access  public
     */
    public void edit(
        int pk,
        LanguageEntity update
    ) {
        this.current = repo.findById(pk).get();
        current.setName(update.getName());
        repo.save(current);
    }
    /**
     * @name    delete
     * @desc    Delete a language
     * @route   DELETE /api/languages/:pk
     * @access  public
     */
    @Override
    public void delete(int pk) {
        repo.deleteById(pk);
    }
}