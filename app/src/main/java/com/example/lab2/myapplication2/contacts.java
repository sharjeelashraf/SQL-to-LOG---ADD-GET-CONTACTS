package com.example.lab2.myapplication2;

/**
 * Created by lab2 on 20-Nov-16.
 */
public class contacts {
    //declare variables
    int _id;
    String _name, _number;

    //empty constructor
    public contacts(){

    }
    //constructor
    public contacts(int id, String name, String phonenumber){
        this._id=id;
        this._name= name;
        this._number=phonenumber;
    }
    //constructor
    public contacts( String name, String phonenumber){

        this._name= name;
        this._number=phonenumber;
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_number() {
        return _number;
    }

    public void set_number(String _number) {
        this._number = _number;
    }




}
