package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    ArrayList<SampleData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("firebasetest");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    TextView tvData = findViewById(R.id.tvData);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        SampleData data = snapshot.getValue(SampleData.class);
                        tvData.setText(tvData.getText() + "\n" + "Name: " + data.getName() + "    Description: " + data.getDescription());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        //mDatabaseReference.addChildEventListener(mChildEventListener);
        // https://www.youtube.com/watch?v=WeoryL3XyA4
        //1. Select * From firebasetest
        mDatabaseReference.addListenerForSingleValueEvent(valueEventListener);

        //2. Select * from firebasetest WHERE name = "Dario"
        //Query query1 = mDatabaseReference.orderByChild("name").equalTo("Dario");
        //query1.addListenerForSingleValueEvent(valueEventListener);

        //3. Select * from firebasetest WHERE description description = "Test%"
        //Query query1 = mDatabaseReference.orderByChild("description").startAt ("Test").endAt("Test\\uf8ff");
        //query1.addListenerForSingleValueEvent(valueEventListener);



        /*
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TextView tvData = findViewById(R.id.tvData);
                SampleData data = dataSnapshot.getValue(SampleData.class);
                tvData.setText(tvData.getText() + "\n" + "Name: " + data.getName() + "    Description: " + data.getDescription());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        */
     }
}