package com.keyin.hynes.braden.s4qap2sdat.classes.entities;
import com.keyin.hynes.braden.s4qap2sdat.classes.abstracts.DataEntity;
public final class LanguageEntity extends DataEntity {
    private String name;
    public LanguageEntity(String name) {
        super();
        this.name = name;
    }
    public LanguageEntity() {
        super();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}