package com.easy.easyeats.model;

import androidx.room.TypeConverter;

public class UrlConverter {
    @TypeConverter
    public static Url fromString(String value) {
        return value == null ? null : new Url(value);
    }

    @TypeConverter
    public static String toString(Url url) {
        return url == null ? null : url.getRegular();
    }
}
