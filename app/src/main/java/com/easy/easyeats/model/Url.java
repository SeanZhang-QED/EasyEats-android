package com.easy.easyeats.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

public class Url {

    public String regular;

    public Url(String regular) {
        this.regular = regular;
    }

    public Url() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(regular, url.regular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regular);
    }


    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}

