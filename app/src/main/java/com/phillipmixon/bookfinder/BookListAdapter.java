package com.phillipmixon.bookfinder;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by phill on 12/29/2017.
 */

public class BookListAdapter extends ArrayAdapter<Book> {

    public BookListAdapter(@NonNull Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View bookView = convertView;
        if (bookView == null) {
            bookView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list,parent,false);
        }

        final Book currentBook = getItem(position);

        TextView titleTextView = (TextView) bookView.findViewById(R.id.title);
        titleTextView.setText(currentBook.getmTitle());

        TextView authorTextView = (TextView) bookView.findViewById(R.id.author);
        authorTextView.setText(currentBook.getmAuthor());

        return bookView;
    }
}
