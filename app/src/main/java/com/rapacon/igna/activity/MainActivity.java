package com.rapacon.igna.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText eName, eSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        eSection = findViewById(R.id.etSection);
    }

    public void nextActivity (View v) {
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }

    public void saveData (View v) {
        SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();

        String name = eName.getText().toString();
        String section = eSection.getText().toString();

        writer.putString("name", name);
        writer.putString("sec", section);

        writer.commit();

        Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
    }

    public void saveInternal (View v) {
        FileOutputStream fos = null;

        try {
            fos = openFileOutput("data2.txt", MODE_PRIVATE);
            String name2 = eName.getText().toString();
            fos.write(name2.getBytes());
            Toast.makeText(this, " Save Inside Internal Storage", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error Writing Data", Toast.LENGTH_LONG).show();

        } finally {

            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        public void saveExternal (View v) {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file  = new File(folder, "data3.txt");
        FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(file);
                String name2 = eName.getText().toString();
                fos.write(name2.getBytes());
                Toast.makeText(this, " Save Inside External Storage", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(this, "Error Writing Data", Toast.LENGTH_LONG).show();

            } finally {

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    //Exception is parent of all Exception Classes
    //String = immutable
    //String Buffer = can be changed, can edit content



}
