package com.easy.easyeats.model;

import androidx.room.TypeConverter;

public class UserConverter {
    private static final String SPLITTER = " + bio: ";

    @TypeConverter
    public static User fromString(String value) {
        if (value == null) {
            return  null;
        }
        String[] values = value.split(SPLITTER);
        if(values.length < 2) {
            return new User(values[0]);
        }
        return new User(values[0], values[1]);
    }

    @TypeConverter
    public static String toString(User user) {
        if(user == null) {
            return null;
        }

        return user.bio == null ? user.name : user.name + SPLITTER + user.bio;
    }
}
