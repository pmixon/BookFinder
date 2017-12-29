package com.phillipmixon.bookfinder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    public static final String LOG_TAG = BookListActivity.class.getName();
    private static final String SEARCH_TERM = "SEARCH_TERM";
    private String dataURL = "https://www.googleapis.com/books/v1/volumes?q=SEARCH_TERM&maxResults=20";
    private ListView bookListView;
    Parcelable bookListScrollState;


    private BookListAdapter mAdapter;

    private String mBookSearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.SEARCH_INPUT_INTENT);

        mBookSearchInput = message;
        dataURL = dataURL.replace(SEARCH_TERM, mBookSearchInput);

        mAdapter = new BookListAdapter(BookListActivity.this, new ArrayList<Book>());

        bookListView = (ListView) findViewById(R.id.book_list_view);
        bookListView.setAdapter(mAdapter);
        bookListView.setEmptyView(findViewById(R.id.no_data_message));

        if(bookListScrollState != null) {
            Log.d(LOG_TAG, "restoring booklist scroll state..");
            bookListView.onRestoreInstanceState(bookListScrollState);
        }

        ConnectivityManager cm =
                (ConnectivityManager)BookListActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            new GetBooksDataTask().execute();
        }
        else {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);

            TextView textView = (TextView) findViewById(R.id.no_data_message);
            textView.setText(R.string.no_internet_message);
        }
    }

    @Override
    public void onPause() {
        Log.d(LOG_TAG, "saving booklist scroll state @ onPause");
        bookListScrollState = bookListView.onSaveInstanceState();
        super.onPause();
    }

    private class GetBooksDataTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... strings) {
            return QueryUtils.fetchBookData(dataURL);
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);

            TextView textView = (TextView) findViewById(R.id.no_data_message) ;
            textView.setText(R.string.no_book_data_label);

            mAdapter.addAll(books);
        }
    }
}
