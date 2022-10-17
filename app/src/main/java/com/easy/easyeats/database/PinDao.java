package com.easy.easyeats.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.easy.easyeats.model.Pin;

import java.util.List;

@Dao
// this interface specifies the operations needed to access the pin table
public interface PinDao {
    @Insert
    void savePin(Pin pin);

    @Query("SELECT * FROM pin")
    LiveData<List<Pin>> getAllPins();

    @Delete
    void deletePin(Pin pin);
}
