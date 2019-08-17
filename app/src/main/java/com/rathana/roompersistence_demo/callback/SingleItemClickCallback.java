package com.rathana.roompersistence_demo.callback;

public interface SingleItemClickCallback <T>{

    void onRemove(T t , int pos);

    void onEdit(T t, int pos);

}
