package com.phillipmixon.bookfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_INPUT_INTENT = "com.phillipmixon.bookfinder.SEARCH_INPUT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                EditText editText = (EditText) findViewById(R.id.search_input);
                String message = editText.getText().toString();
                intent.putExtra(SEARCH_INPUT_INTENT, message);
                startActivity(intent);

            }
        });

    }
}
