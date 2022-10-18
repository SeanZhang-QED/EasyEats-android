package com.easy.easyeats.model;

import java.util.Objects;

public class User {
    public class ProfileImage {
        public String small;
        public String medium;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProfileImage that = (ProfileImage) o;
            return Objects.equals(small, that.small) && Objects.equals(medium, that.medium);
        }

        @Override
        public int hashCode() {
            return Objects.hash(small, medium);
        }

        @Override
        public String toString() {
            return "ProfileImage{" +
                    "small='" + small + '\'' +
                    ", medium='" + medium + '\'' +
                    '}';
        }
    }
    public class Social {
        public String instagram_username;
        public String portfolio_url;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Social social = (Social) o;
            return Objects.equals(instagram_username, social.instagram_username) && Objects.equals(portfolio_url, social.portfolio_url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(instagram_username, portfolio_url);
        }

        @Override
        public String toString() {
            return "Social{" +
                    "instagram_username='" + instagram_username + '\'' +
                    ", portfolio_url='" + portfolio_url + '\'' +
                    '}';
        }
    }

    public String name;
    public String bio;
    public String location;
    public String total_photos;
    public ProfileImage profile_image;
    public Social social;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String bio) {
        this.bio = bio;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(bio, user.bio) && Objects.equals(location, user.location) && Objects.equals(total_photos, user.total_photos) && Objects.equals(profile_image, user.profile_image) && Objects.equals(social, user.social);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bio, location, total_photos, profile_image, social);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", total_photos='" + total_photos + '\'' +
                ", profile_image=" + profile_image +
                ", social=" + social +
                '}';
    }
}

