package com.rathana.roompersistence_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.rathana.roompersistence_demo.R;
import com.rathana.roompersistence_demo.callback.SingleItemClickCallback;
import com.rathana.roompersistence_demo.data.entity.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categories;
    Context context;

    SingleItemClickCallback callback;

    public CategoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
        callback = (SingleItemClickCallback) context;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Category category = categories.get(i);
        viewHolder.name.setText(category.name);
        viewHolder.onMenuClicked(category);

    }

    public void addMoreItems(List<Category> categories) {
        int previousSize = this.categories.size();
        this.categories.addAll(categories);
        notifyItemRangeInserted(previousSize, categories.size());
    }

    public void addItem(Category data) {
        this.categories.add(data);
        notifyItemInserted(this.categories.size() - 1);
    }

    public void removeItem(Category category, int pos){
        categories.remove(category);
        notifyItemRemoved(pos);
    }

    public void editItem(Category category, int pos){
        categories.set(pos,category);
        notifyItemChanged(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView btnMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text1);
            btnMenu = itemView.findViewById(R.id.btnMenu);
        }

        void onMenuClicked(Category category) {
            btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createPopupMenu(v, category);
                }
            });
        }

        void createPopupMenu(View view, Category category) {
            PopupMenu menu = new PopupMenu(context, view);
            menu.inflate(R.menu.category_popup_menu);
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (callback != null) {
                        switch (item.getItemId()) {
                            case R.id.btnEdit:
                                //call back to  activity
                                callback.onEdit(category,getAdapterPosition());
                                return true;
                            case R.id.btnRemove:
                                //call back to  activity
                                callback.onRemove(category,getAdapterPosition());
                                return true;
                        }
                    }
                    return false;
                }
            });
            menu.show();
        }

    }
}
