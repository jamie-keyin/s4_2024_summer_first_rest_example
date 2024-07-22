package com.keyin.hello;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.util.Objects;

@Entity
public class Language {

    @Id
    @SequenceGenerator(name = "language_sequence", sequenceName = "language_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "language_sequence")
    private long id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return id == language.id && Objects.equals(name, language.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
