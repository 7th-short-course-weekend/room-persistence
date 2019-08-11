package com.rathana.roompersistence_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rathana.roompersistence_demo.R;
import com.rathana.roompersistence_demo.data.entity.Category;

import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categories;
    Context context;

    public CategoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.simple_item_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Category category=categories.get(i);
        viewHolder.name.setText(category.name);
    }

    public void addMoreItems(List<Category> categories){
        int previousSize=this.categories.size();
        this.categories.addAll(categories);
        notifyItemRangeInserted(previousSize,categories.size());
    }

    public void addItem(Category data){
        this.categories.add(data);
        notifyItemInserted(this.categories.size()-1);
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.text1);
        }
    }
}
