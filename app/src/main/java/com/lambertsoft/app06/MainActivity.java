package com.lambertsoft.app06;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    final String TAG = "App06";
    Button updateButton;
    ListView bookListView;
    List<Books> booksListArray = new ArrayList<Books>();
    Client mKinveyClient;
    AsyncAppData<Books> myBooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookListView = (ListView) findViewById(R.id.bookList);
        updateButton = (Button) findViewById(R.id.updateButton);

        mKinveyClient = new Client.Builder("kid_WyE5rmap_", "b5f06467ecea486096b5e47104e4e098", getApplicationContext()).build();

        /*
        mKinveyClient.ping(new KinveyPingCallback() {
            public void onFailure(Throwable t) {
                Log.e(TAG, "Kinvey Ping Failed", t);
            }
            public void onSuccess(Boolean b) {
                Log.d(TAG, "Kinvey Ping Success");
            }
        });
        */

        myBooks = mKinveyClient.appData("Books", Books.class);

        updateButton.setOnClickListener(this);

        bookListView.setAdapter(new BooksAdapter());

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getApplicationContext(), "Updating...", 5).show();
        myBooks.get(new KinveyListCallback<Books>() {
            @Override
            public void onSuccess(Books[] bookses) {
                Log.e(TAG, "received" + bookses.length);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e(TAG, "failed");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class BooksAdapter extends ArrayAdapter<Books> {

        public BooksAdapter() {
            super(MainActivity.this, R.layout.books_detail, booksListArray);
        }

        @Override
        public View getView(int position, View view, ViewGroup container){
            if (view == null ) {
                view = getLayoutInflater().inflate(R.layout.books_detail, container, false);
            }
            TextView nameText = (TextView) view.findViewById(R.id.nameText);
            TextView authorText = (TextView) view.findViewById(R.id.authorText);

            Books b = booksListArray.get(position);
            nameText.setText(b.getName());
            authorText.setText(b.getAuthor());

            return view;
        }


    }

}
