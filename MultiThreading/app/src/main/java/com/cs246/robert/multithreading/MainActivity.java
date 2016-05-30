package com.cs246.robert.multithreading;

// android java imports
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

// Java imports
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Thread;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    File file;
    ListView listView;
    ProgressBar progressBar;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        // has a sweet working progress bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_selectable_list_item, list);
        listView.setAdapter(adapter);
        file = new File(getFilesDir(), "numbers.txt");

        // the program is button based
        Button bCreate = (Button) findViewById(R.id.create);
        Button bLoad = (Button) findViewById(R.id.load);
        Button bClear = (Button) findViewById(R.id.clear);

        if (bCreate != null)
            bCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new WriteFile().execute();
                }
            });

        if (bLoad != null)
            bLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ReadFile().execute();
                }
            });

        if (bClear != null)
            bClear.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    progressStatus = 0;
                    progressBar.setProgress(progressStatus);
                    list.clear();
                    adapter.clear();
                }
            });
    }

    // ReadFile class works with the load button
    private class ReadFile extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... Params) {
            try {
                InputStream is = new FileInputStream(file.toString());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = br.readLine()) != null) {
                    list.add("Read Line: " + line);
                    progressStatus += 10;
                    publishProgress();
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        System.out.print("This won't happen!");
                    }
                }
                br.close();
                progressStatus = 0;
                publishProgress();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Void... Params) {
            adapter.notifyDataSetChanged();
            progressBar.setProgress(progressStatus);
        }
    }

    // The wirte file class works with the create button
    private class WriteFile extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... Params) {
            try {
                FileOutputStream output =
                        new FileOutputStream
                                (file.toString());
                String temp;
                for (int i = 0; i < 10; i++) {
                    progressStatus += 10;
                    temp = (Integer.toString(i + 1) + "\n");
                    output.write(temp.getBytes());
                    publishProgress();
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        System.out.print("This won't happen!");
                    }
                }
                output.close();
                progressStatus = 0;
                publishProgress();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Void... Params) {
            progressBar.setProgress(progressStatus);
        }
    }
}

