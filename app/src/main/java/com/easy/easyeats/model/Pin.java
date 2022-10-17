package com.easy.easyeats.model;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Pin {

    @NonNull
    @PrimaryKey
    public String id;
    public String alt_description;
    public String likes;

    public Url urls;

    public User user;

    @Ignore
    public List<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return id.equals(pin.id) && Objects.equals(alt_description, pin.alt_description) && Objects.equals(likes, pin.likes) && Objects.equals(urls, pin.urls) && Objects.equals(user, pin.user) && Objects.equals(tags, pin.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alt_description, likes, urls, user, tags);
    }

    @Override
    public String toString() {
        return "Pin{" +
                "id='" + id + '\'' +
                ", alt_description='" + alt_description + '\'' +
                ", likes='" + likes + '\'' +
                ", urls=" + urls +
                ", user=" + user +
                ", tags=" + tags +
                '}';
    }
}