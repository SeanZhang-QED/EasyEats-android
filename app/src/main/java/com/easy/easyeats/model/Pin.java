package com.easy.easyeats.model;

import java.util.Objects;

public class Pin {
    public String id;
    public String alt_description;
    public Urls urls;
    public String likes;

    class Urls {
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return Objects.equals(id, pin.id) && Objects.equals(alt_description, pin.alt_description) && Objects.equals(urls, pin.urls) && Objects.equals(likes, pin.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alt_description, urls, likes);
    }

    @Override
    public String toString() {
        return "Pin{" +
                "id='" + id + '\'' +
                ", alt_description='" + alt_description + '\'' +
                ", urls=" + urls +
                ", likes='" + likes + '\'' +
                '}';
    }
}