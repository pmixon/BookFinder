package com.phillipmixon.bookfinder;

/**
 * Created by phill on 12/29/2017.
 */

public class Book {

    private String mTitle;
    private String mAuthor;

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public Book(String mTitle, String mAuthor) {
        this.mAuthor = mAuthor;
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

}
