package com.rapacon.igna.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView tMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tMsg = findViewById(R.id.tvMsg);
    }

    public void previousActivity (View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

     public void showMessage (View v) {
         SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
         String name = sp.getString("name", null);
         String section = sp.getString("sec", null);

         String message = "Hello " + name + " Your name is " + section;
         tMsg.setText(message);
     }

     public void showInternalData (View v) {
        FileInputStream fin = null;

         try {
              fin = openFileInput("data2.txt");
             int c;

             StringBuffer buffer = new StringBuffer();
             while ( (c = fin.read()) != -1) {
               buffer.append((char)c);
             }
             tMsg.setText("Hello " + buffer);

         } catch (Exception e) {
             Toast.makeText(this, "Error reading message",Toast.LENGTH_LONG).show();

         } finally {
             try {
                 fin.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }

         }


     }

}
