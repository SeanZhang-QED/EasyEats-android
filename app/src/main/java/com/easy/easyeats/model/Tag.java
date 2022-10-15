package com.easy.easyeats.model;

import java.util.Objects;

public class Tag {
    public String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(title, tag.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Tag{" +
                " title='" + title + '\'' +
                '}';
    }
}
