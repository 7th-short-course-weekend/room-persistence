package com.rathana.roompersistence_demo.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rathana.roompersistence_demo.data.entity.Book;
import com.rathana.roompersistence_demo.data.entity.BookCategory;

import java.util.List;

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Book ... books);
    @Update
    int edit(Book ... books);
    @Delete
    int remove(Book ... books);


    //get Books with categories
    @Query("SELECT book.*, c.id as category_id, c.name" +
            " FROM book inner join category c on c.id= book.category_id " +
            " ORDER BY book.id asc;")
    List<BookCategory> getBooks();
}
