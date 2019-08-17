package com.rathana.roompersistence_demo.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;

public class BookCategory  implements Parcelable {

    public int id;
    public String title;
    public String description;
    public String author;
    public String thumb;

    @ColumnInfo(name = "category_id")
    public int categoryId;
    public String name;

    public BookCategory() {
    }

    protected BookCategory(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        author = in.readString();
        thumb = in.readString();
        categoryId = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(author);
        dest.writeString(thumb);
        dest.writeInt(categoryId);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookCategory> CREATOR = new Creator<BookCategory>() {
        @Override
        public BookCategory createFromParcel(Parcel in) {
            return new BookCategory(in);
        }

        @Override
        public BookCategory[] newArray(int size) {
            return new BookCategory[size];
        }
    };
}
