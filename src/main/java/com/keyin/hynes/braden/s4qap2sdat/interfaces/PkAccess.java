package com.keyin.hynes.braden.s4qap2sdat.interfaces;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
@Entity
public interface PkAccess {
    @SequenceGenerator(
        name = "pk",
        sequenceName = "pk"
    )
    int getPk();
    void setPk(int pk);
}