package com.easy.easyeats.model;

import androidx.room.TypeConverter;

public class UserConverter {
    public static final String SPLITTER = " + bio: ";

    @TypeConverter
    public static User fromString(String value) {
        if (value == null) {
            return  null;
        }
        return new User(value);
    }

    @TypeConverter
    public static String toString(User user) {
        if(user == null) {
            return null;
        }

        return user.bio == null ? user.name : user.name;
    }
}
