package com.poutchinskaya.malik.sotunisia.Dao;

import android.database.Cursor;

/**
 * Created by Malik on 27/12/2015.
 */
public interface IMotDao {
    void open();

    void close();

    Cursor getMots();
}
