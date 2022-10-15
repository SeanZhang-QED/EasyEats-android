package com.easy.easyeats.model;

import java.util.List;
import java.util.Objects;

public class Pin {
    public class Urls {
        public String raw;
        public String regular;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Urls urls = (Urls) o;
            return Objects.equals(raw, urls.raw) && Objects.equals(regular, urls.regular);
        }

        @Override
        public int hashCode() {
            return Objects.hash(raw, regular);
        }

        @Override
        public String toString() {
            return "Urls{" +
                    "raw='" + raw + '\'' +
                    ", regular='" + regular + '\'' +
                    '}';
        }

        public String getRaw() {
            return raw;
        }

        public String getRegular() {
            return regular;
        }
    }

    public String id;
    public String alt_description;
    public String likes;
    public Urls urls;
    public User user;
    public List<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return Objects.equals(id, pin.id) && Objects.equals(alt_description, pin.alt_description) && Objects.equals(likes, pin.likes) && Objects.equals(urls, pin.urls) && Objects.equals(user, pin.user) && Objects.equals(tags, pin.tags);
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

    public Urls getUrls() {
        return this.urls;
    }
}
