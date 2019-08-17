package com.rathana.roompersistence_demo;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rathana.roompersistence_demo.adapter.CategoryAdapter;
import com.rathana.roompersistence_demo.callback.SingleItemClickCallback;
import com.rathana.roompersistence_demo.data.MyDatabase;
import com.rathana.roompersistence_demo.data.dao.CategoryDao;
import com.rathana.roompersistence_demo.data.entity.Category;
import com.rathana.roompersistence_demo.utils.FormDialog;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity
        implements FormDialog.DialogCallback<String>,
        SingleItemClickCallback<Category> {

    FloatingActionButton btnAdd;
    RecyclerView rvCategory;
    CategoryDao categoryDao;
    MyDatabase database;

    CategoryAdapter categoryAdapter;
    List<Category> categories=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnAdd=findViewById(R.id.btnAdd);
        rvCategory=findViewById(R.id.rvCategory);

        btnAdd.setOnClickListener(v->{
            FormDialog dialogFragment=new FormDialog();
            dialogFragment.show(getSupportFragmentManager(),"add dialog");
            dialogFragment.setCallback(this);
        });

        //get Dao object
        database=MyDatabase.getInstance(this);
        categoryDao=database.categoryDao();

        //getup RecyclerView
        setupUI();
        getCategories();
    }

    void setupUI(){
        categoryAdapter=new CategoryAdapter(categories,this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rvCategory.setLayoutManager(manager);
        rvCategory.setAdapter(categoryAdapter);
    }

    private void getCategories(){
        List<Category> categories= categoryDao.getCategories();
        categoryAdapter.addMoreItems(categories);
    }

    @Override
    public void onClicked(String data) {
        Log.e(TAG, "onClicked: "+ data );
        //todo save data db
        Category category=new Category();
        category.name=data;
        categoryDao.insert(category);
        categoryAdapter.addItem(category);
        rvCategory.smoothScrollToPosition(categories.size()-1);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private static final String TAG = "CategoryActivity";

    //delete
    @Override
    public void onEdit(Category category,int pos) {
        FormDialog formDialog= new FormDialog();
        formDialog.show(getSupportFragmentManager(),"edit");
        formDialog.setData(category);
        formDialog.setButtonLabel("save change");
        formDialog.setCallback(new FormDialog.DialogCallback<String>() {
            @Override
            public void onClicked(String data) {
                Log.e(TAG, "onClicked: "+ data);
                category.name=data;
                categoryDao.update(category);
                categoryAdapter.editItem(category,pos);
            }
        });

    }

    @Override
    public void onRemove(Category category, int pos) {
        categoryDao.delete(category);
        categoryAdapter.removeItem(category,pos);
    }
}
