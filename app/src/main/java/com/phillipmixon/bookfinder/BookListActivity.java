package com.phillipmixon.bookfinder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private BookListAdapter mAdapter;

    private String mBookSearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.SEARCH_INPUT_INTENT);

        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        mBookSearchInput = message;

        mAdapter = new BookListAdapter(BookListActivity.this, new ArrayList<Book>());

        ListView bookListView = (ListView) findViewById(R.id.book_list_view);
        bookListView.setAdapter(mAdapter);

        mAdapter.add(new Book("The Hens of Mens","Phillip Mixon"));
        mAdapter.add(new Book("The Hens of Mens","Phillip Mixon"));
        mAdapter.add(new Book("The Hens of Mens","Phillip Mixon"));
        mAdapter.add(new Book("The Hens of Mens","Phillip Mixon"));

    }
}
