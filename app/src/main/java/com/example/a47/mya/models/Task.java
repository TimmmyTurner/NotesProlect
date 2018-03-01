package com.example.a47.mya.models;


public class Task {
    private long mId;
    private String mTitle;
    private String mText;


    public Task() {
    }

    public Task(long id) {
        mId = id;
    }

    public Task(String title, String text) {
        mTitle = title;
        mText = text;
    }

    public Task(long id, String title, String text) {
        mId = id;
        mTitle = title;
        mText = text;
    }

    public long getId() {
        return mId;
    }
    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mTitle;
    }
    public void setName(String text) {
        mTitle = text;
    }

    public String getText() {
        return mText;
    }
    public void setText(String text) {
        mText = text;
    }

}
