package com.rathana.roompersistence_demo.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rathana.roompersistence_demo.data.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category... category);

    @Insert
    long insert(Category category);

    @Insert
    void insert(List<Category> categories);

    @Update
    void update(Category category);
    @Update
    void update(List<Category> categories);

    @Delete
    void delete(Category ... categories);

    @Query("SELECT * FROM category ORDER BY id ASC")
    List<Category> getCategories();

    @Query("SELECT * FROM category WHERE id = :id")
    Category getOne(int id);

}
