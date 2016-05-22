package com.cs246.robert.scripturesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

/**************************************
 * Robert Dickerson
 * cs 246
 * Scripture Search Assignment
 * Collaborators Tavis Confer, Chris Simmons
 ***************************************/
public class MainActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button fab = (Button) findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                String message = "Your Favorite Scripture is: " +
                        ((EditText) findViewById(R.id.editBook)).getText().toString()
                        + " " +
                        ((EditText) findViewById(R.id.editChapter)).getText().toString()
                        + ":" +
                        ((EditText) findViewById(R.id.editVerse)).getText().toString();
                intent.putExtra("EXTRA_MESSAGE", message);
                startActivity(intent);
            }
        });
    }

    /*public void sendMessage(View view) {
        Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        String message = "Your favorite scripture is:\n" +
                            ((EditText) findViewById(R.id.editBook)).getText().toString()
                            + " " +
                            ((EditText) findViewById(R.id.editChapter)).getText().toString()
                            + ":" +
                            ((EditText) findViewById(R.id.editVerse)).getText().toString();
                    intent.putExtra("EXTRA_MESSAGE", message);
        startActivity(intent);
    }*/

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
}
