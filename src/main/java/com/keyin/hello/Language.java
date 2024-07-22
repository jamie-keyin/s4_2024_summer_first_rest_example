package com.keyin.hello;

import jakarta.persistence.*;

@Entity
public class Language {

    @Id
    @SequenceGenerator(name = "language_sequence", sequenceName = "language_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "language_sequence")
    private long id;

    // Because I've added functionality that assumes Language names are unique, I've added the UNIQUE column constraint
    @Column(unique = true)
    private String name;

    public Language() {
        this.name = "English";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
