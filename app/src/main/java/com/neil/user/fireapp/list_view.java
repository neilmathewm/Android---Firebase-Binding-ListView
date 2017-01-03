package com.neil.user.fireapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;


public class list_view extends Activity {

    private Firebase mRef;
    private ListView mview;
    private ArrayList<String> u_name=new ArrayList<>(); //array of strings to hold retrieved data


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        mview=(ListView)findViewById(R.id.listView); //list view
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,u_name);


        mRef = new Firebase("https://fireapp-60157.firebaseio.com/");

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                Log.e("Data: ", "" + dataSnapshot.getValue());
                Map<String, String> map = dataSnapshot.getValue(Map.class); //retrieve data of child as json type

                String value=map.get("Name");                               //extract a value by key from json
                Log.e("Child Name", map.get("Name"));
                u_name.add(value);     //adding value to array of string
                mview.setAdapter(arrayAdapter);//showing the list


               /* for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.e("Child Data: " ,""+postSnapshot.getValue());

                    String Name = map.get("Name");
                    u_name.add(Name);
                }*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}
