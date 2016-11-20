package com.example.lab2.myapplication2;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String log, TAG = "SQLDATABASE";
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.text);


         db = new DatabaseHandler(this);

    insertContacts();

//        getContacts();

        }
private void insertContacts(){
    contacts contact  = new contacts(0, "test", "12345-");
    for (int i =0; i<10; i++)
    {
        contact._name = contact._name + 1;
        contact._number = contact._number+1;
        long id = db.addContact(contact);
        Log.i(TAG, "insertContact: "+ id);
contacts contct = db.getContact(id);
        Log.i(TAG, "insertContact: "+ contct._name);
        }
    }
    private void getContacts(){

    }


    }




