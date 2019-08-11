package com.rathana.roompersistence_demo.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "book",
foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id",
        childColumns = "category_id"))

public class Book {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public String author;
    public String thumb;

    @ColumnInfo(name = "category_id")
    public int categoryId;
}
