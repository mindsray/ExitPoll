package com.example.exitpoll.model;


import java.util.Locale;

public class Item_poll{
    public final long _id;
    public final String name;
    public final String point;
    public final String image;

    public Item_poll(long _id, String name, String point , String image) {
        this._id = _id;
        this.name = name;
        this.point = point;
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "NAME: %s\nID: %s",
                this.name,
                this._id);
    }
}
