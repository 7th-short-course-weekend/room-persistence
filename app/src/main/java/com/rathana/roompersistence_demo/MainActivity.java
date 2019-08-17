package com.rathana.roompersistence_demo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rathana.roompersistence_demo.adapter.BookAdapter;
import com.rathana.roompersistence_demo.data.MyDatabase;
import com.rathana.roompersistence_demo.data.dao.BookDao;
import com.rathana.roompersistence_demo.data.entity.BookCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rvBook;
    BookAdapter adapter;
    List<BookCategory> books =new ArrayList<>();

    FloatingActionButton btnAddBook;

    BookDao bookDao;
    static final int BOOK_REQUEST_CODE=111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookDao = MyDatabase.getInstance(this).bookDao();
        btnAddBook=findViewById(R.id.btnAddBook);
        rvBook=findViewById(R.id.rvBook);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rvBook.setLayoutManager(layoutManager);
        adapter=new BookAdapter(books,this);
        rvBook.setAdapter(adapter);

        getBooks();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(
                        MainActivity.this,
                        AddBookActivity.class
                );

                startActivityForResult(intent,BOOK_REQUEST_CODE);
            }
        });
    }

    void getBooks(){
        List<BookCategory> books= bookDao.getBooks();
        adapter.addMoreItems(books);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(BOOK_REQUEST_CODE==requestCode && resultCode== RESULT_OK){
            BookCategory bookCategory= data.getParcelableExtra("data");
            adapter.addItem(bookCategory);
        }
    }
}
