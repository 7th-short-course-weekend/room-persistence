package com.rathana.roompersistence_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rathana.roompersistence_demo.data.MyDatabase;
import com.rathana.roompersistence_demo.data.dao.BookDao;
import com.rathana.roompersistence_demo.data.dao.CategoryDao;
import com.rathana.roompersistence_demo.data.entity.Book;
import com.rathana.roompersistence_demo.data.entity.BookCategory;
import com.rathana.roompersistence_demo.data.entity.Category;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    EditText title, author, desc,thumb;
    Spinner categorySpinner;
    Button btnSave;

    ArrayAdapter categoryAdapter;
    List<String> arrayCategories=new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    CategoryDao categoryDao;
    BookDao bookDao;
    Category category;
    Book book=new Book();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        title=findViewById(R.id.title);
        author=findViewById(R.id.author);
        desc=findViewById(R.id.desc);
        categorySpinner=findViewById(R.id.category);
        thumb=findViewById(R.id.thumb);
        btnSave=findViewById(R.id.btnSave);

        categoryDao= MyDatabase.getInstance(this).categoryDao();
        bookDao=MyDatabase.getInstance(this).bookDao();

        //setup spinner
        getCategories();
        categoryAdapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                arrayCategories
        );

        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=categories.get(position);
                book.categoryId= category.id;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.title=title.getText().toString();
                book.author=author.getText().toString();
                book.description=desc.getText().toString();
                book.thumb=thumb.getText().toString();

                //save
                bookDao.insert(book);
                //go to main activity
                BookCategory bookCategory=new BookCategory();
                bookCategory.id=book.id;
                bookCategory.title=book.title;
                bookCategory.author=book.author;
                bookCategory.description=book.description;
                bookCategory.thumb=book.thumb;
                bookCategory.categoryId=book.categoryId;
                bookCategory.name=category.name;

                Intent intent=new Intent();
                intent.putExtra("data",bookCategory);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    void getCategories(){
        categories.addAll(categoryDao.getCategories());
        for(Category category : categories){
            arrayCategories.add(category.name);
        }
    }

}
