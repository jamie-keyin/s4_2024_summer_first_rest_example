package com.keyin.hynes.braden.s4qap2sdat.classes.services;
import com.keyin.hynes.braden.s4qap2sdat.interfaces.repositories.GreetingRepository;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.GreetingEntity;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.LanguageEntity;
import com.keyin.hynes.braden.s4qap2sdat.interfaces.Serve;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
public final class GreetingService implements Serve {
    @Autowired
    private GreetingRepository repo;
    private GreetingEntity current;
    public GreetingService() {}
    /**
     * @name    list
     * @desc    List all greetings
     * @route   GET /api/greetings
     * @access  public
     */
    public List<GreetingEntity> list() {
        return StreamSupport.stream(
            repo.findAll().spliterator(),
            false
        ).collect(Collectors.toList());
    }
    /**
     * @name    getByPk
     * @desc    Get a greeting by its primary key
     * @route   GET /api/greetings/:pk
     * @access  public
     */
    public GreetingEntity getByPk(int pk) {
        return repo.findById(pk).orElse(null);
    }
    /**
     * @name    getByNameAndGreeting
     * @desc    Get a greeting by its body and the subject's name
     * @route   GET /api/greetings/:name:body
     * @access  public
     */
    public GreetingEntity getByNameAndBody(
        String name,
        String body
    ) {
        return repo.findByNameAndBody(name, body);
    }
    /**
     * @name    add
     * @desc    Add a greeting
     * @route   POST /api/greetings
     * @access  public
     */
    public GreetingEntity add(GreetingEntity greeting) {
        return repo.save(greeting);
    }
    /**
     * @name    addLanguage
     * @desc    Add a language to a greeting
     * @route   PATCH /api/language/:pk
     * @access  public
     */
    public GreetingEntity addLanguage(
        int pk,
        LanguageEntity language
    ) {
        this.current = repo.findById(pk).get();
        current.getLanguages().addLast(language);
        return repo.save(current);
    }
    /**
     * @name    edit
     * @desc    Edit a greeting
     * @route   PUT /api/greetings/:pk
     * @access  public
     */
    public GreetingEntity edit(
        int pk,
        GreetingEntity update
    ) {
        this.current = repo.findById(pk).get();
        current.setName(update.getName());
        current.setGreeting(update.getGreeting());
        current.setLanguages(update.getLanguages());
        return repo.save(current);
    }
    /**
     * @name    delete
     * @desc    Delete a language
     * @route   DELETE /api/greetings/:pk
     * @access  public
     */
    @Override
    public String delete(int pk) {
        repo.deleteById(pk);
        return "Greeting deleted.";
    }
}