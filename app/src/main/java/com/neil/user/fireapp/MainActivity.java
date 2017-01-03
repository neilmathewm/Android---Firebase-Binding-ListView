package com.neil.user.fireapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MainActivity extends ActionBarActivity {

    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://YOUR FIREBASE URL.firebaseio.com/");


    }

    public void insert(View view) {
        final EditText ins = (EditText) findViewById(R.id.textView);
        final EditText ins2 = (EditText) findViewById(R.id.textView2);
        String value = ins.getText().toString();
        String value2 = ins2.getText().toString();
//        Firebase mRefChild=mRef.child("Name");
//        mRefChild.setValue(value);
//        Firebase mRefChild2=mRef.child("Age");
//        mRefChild2.setValue(value2);
        String date = new SimpleDateFormat("dd-mm-yy").format(new Date());
        String date1 = new Date().toString();
        Firebase mRefChild = mRef.child(date1);
        Firebase mRefChild21 = mRefChild.child("Name");
        Firebase mRefChild22 = mRefChild.child("Age");
        mRefChild21.setValue(value);
        mRefChild22.setValue(value2);
    }

    public void select(View view) {

        mRef.child("Mon Jan 02 18:45:06 GMT+05:30 2017").addValueEventListener(new ValueEventListener()//giv key to find uniquely..here date
               // mRef.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        System.out.println(snapshot.getValue());
                        Log.v("E.value", "Data:" + snapshot.getValue());

                        Map<String, String> map = snapshot.getValue(Map.class);

                       if(snapshot.getValue()!=null) {
                           String Name = map.get("Name");
                           String age = map.get("Age");
                           final TextView tv = (TextView) findViewById(R.id.textView3);
                           tv.setText(Name);
                           Toast toast = Toast.makeText(getApplicationContext(),
                                   Name, Toast.LENGTH_SHORT);
                           toast.show();
                       }
                        else
                       {
                           Toast toast = Toast.makeText(getApplicationContext(),
                                    "  No Data Found", Toast.LENGTH_SHORT);
                           toast.show();
                       }

                    }

                    @Override
                    public void onCancelled(FirebaseError error) {
                    }
                });
    }


    public void List_View(View view) {
        Log.v("E.value", "heloo");
        Intent intent = new Intent(this, list_view.class);
        startActivity(intent);
    }



}



