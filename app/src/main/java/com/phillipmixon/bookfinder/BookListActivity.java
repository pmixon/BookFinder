package com.phillipmixon.bookfinder;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private static final String DATA_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

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

        new GetBooksDataTask().execute();

    }

    private class GetBooksDataTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... strings) {
            return QueryUtils.fetchBookData(DATA_URL);
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            mAdapter.addAll(books);
        }
    }
}
