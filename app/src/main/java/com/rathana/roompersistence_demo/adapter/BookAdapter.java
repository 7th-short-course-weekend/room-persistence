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
import com.rathana.roompersistence_demo.data.entity.Book;
import com.rathana.roompersistence_demo.data.entity.BookCategory;
import com.rathana.roompersistence_demo.data.entity.Category;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    List<BookCategory> bookCategories;
    Context context;

    public BookAdapter(List<BookCategory> bookCategories, Context context) {
        this.bookCategories = bookCategories;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return bookCategories.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BookCategory bookCategory = bookCategories.get(i);
        viewHolder.title.setText(bookCategory.title);
        viewHolder.category.setText(bookCategory.name);
        viewHolder.author.setText(bookCategory.author);

    }

    public void addMoreItems(List<BookCategory> bookCategories) {
        int previousSize = this.bookCategories.size();
        this.bookCategories.addAll(bookCategories);
        notifyItemRangeInserted(previousSize, bookCategories.size());
    }

    public void addItem(BookCategory data) {
        this.bookCategories.add(data);
        notifyItemInserted(this.bookCategories.size() - 1);
    }

    public void removeItem(BookCategory bookCategory, int pos) {
        bookCategories.remove(bookCategory);
        notifyItemRemoved(pos);
    }

    public void editItem(BookCategory bookCategory, int pos) {
        bookCategories.set(pos, bookCategory);
        notifyItemChanged(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView btnMore;
        TextView category;
        TextView author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            btnMore = itemView.findViewById(R.id.btnMore);
            category = itemView.findViewById(R.id.category);
            author = itemView.findViewById(R.id.author);
        }
    }
}
