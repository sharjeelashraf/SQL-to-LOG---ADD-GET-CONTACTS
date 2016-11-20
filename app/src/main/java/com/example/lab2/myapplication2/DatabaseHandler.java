package com.example.lab2.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lab2 on 20-Nov-16.
 */

public class DatabaseHandler extends SQLiteOpenHelper{
String TAG = "SQLDATABASE";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactDatabase";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    // Adding new contact
    public long addContact(contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name()); // Contact Name
        values.put(KEY_PH_NO, contact.get_number()); // Contact Phone Number

        // Inserting Row
       long id = db.insert(TABLE_CONTACTS, null, values);
        Log.i(TAG, "addContact: "+ id);

        db.close(); // Closing database connection
        return id;
    }

    // Getting single contact
    public contacts getContact(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        contacts contact = new contacts();
        contact.set_number(cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));
        contact.set_name(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        contact.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<contacts> getAllContacts() {
        List<contacts> contactList = new ArrayList<contacts>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                contacts contact = new contacts();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_number(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }
}
