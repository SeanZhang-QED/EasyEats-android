package com.easy.easyeats.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.easy.easyeats.model.Pin;
import com.easy.easyeats.model.UrlConverter;
import com.easy.easyeats.model.UserConverter;

// TODO: why the method and class is abstract
// We do not need to implement it, ROom annotation processor will implement it automatically
// In detail, The ViewModel query APIs abstract away
// the implementation details about networking or database operations.

@Database(entities = {Pin.class}, version = 1, exportSchema = false)
// - version specifies a current version. Once we introduce/modify the new version,
//   we have to increase the version and define the migration strategy.
// - Entities specifies the tables the database contains.
// - exportSchema option is for dumping a database schema to file system. We do not need that.
@TypeConverters({UrlConverter.class, UserConverter.class})
public abstract class EasyEatsDatabase extends RoomDatabase {
    public abstract PinDao pinDao();
}
