package com.rathana.roompersistence_demo.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rathana.roompersistence_demo.data.dao.CategoryDao;
import com.rathana.roompersistence_demo.data.entity.Book;
import com.rathana.roompersistence_demo.data.entity.Category;

@Database(
        version = 1,
        entities = {Category.class, Book.class}
)
public abstract class MyDatabase extends RoomDatabase {

    static final String DB_NAME="book_db";

    public abstract CategoryDao categoryDao();

    public static MyDatabase getInstance(Context context){
        return Room.databaseBuilder(
                context, MyDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();

    }

}
