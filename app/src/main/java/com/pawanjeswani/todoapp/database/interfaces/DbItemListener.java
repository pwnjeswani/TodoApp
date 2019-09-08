package com.pawanjeswani.todoapp.database.interfaces;

import java.util.List;

public interface DbItemListener<I> {
    void onReadSuccess(I item);
    void onReadSuccess(List<I> item);
    void onFailure(String error);
}
