package com.example.ahmed.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 1/13/2018.
 */

public class ContactsActivity extends AppCompatActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    //Adapter,Listview
    private ListView mUserListView;
    private UsersAdapter mUsersAdapter;


    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference mUsersDatabaseReference;
    private ChildEventListener mChildEventListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //Initialize Firebase Components
        mFirebasedatabase = FirebaseDatabase.getInstance();

        //Reference to users node
        mUsersDatabaseReference = mFirebasedatabase.getReference().child("users");

        //Initializing References to ListView
        mUserListView = (ListView) findViewById(R.id.messageListView);

        // Initialize message ListView and its adapter
        final List<Users> users = new ArrayList<>();
        mUsersAdapter = new UsersAdapter(this, R.layout.item_contacts, users);
        mUserListView.setAdapter(mUsersAdapter);


        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
       // textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
       // textViewUserEmail.setText("Welcome " + user.getEmail());

        //adding listener to button //Logout
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if logout is pressed
                if (view == buttonLogout) {
                    //logging out the user
                    firebaseAuth.signOut();
                    //closing activity
                    finish();
                    //starting login activity
                    startActivity(new Intent(ContactsActivity.this, LoginActivity.class));
                    }
                }
            });

        //Adding Listener to ListView
        mUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <?> parentAdapter, View view, int position,
                                    long id) {
                //Toast.makeText(getApplicationContext(), ""+ users.get(position).getUid(), Toast.LENGTH_SHORT).show();
                //Place code here with the action
                //Starting Chat Activity for perticular person

                /* //Adding New Chat Node in Real time database..
                Users user = new Users(""+name,""+email);

                mMessageDatabaseReference.push().setValue(user);
                finish();
                startActivity(new Intent(getApplicationContext(), ContactsActivity.class));
                 */


                Intent i = new Intent(ContactsActivity.this,ChatActivity.class);

                i.putExtra("U1",""+users.get(position).getUid());
                i.putExtra("U2", ""+user.getEmail()); // key is used to get value in Second Activiyt

                //start the Chat activity
                startActivity(i);
            }
        });

        //Attaching Listener..
        attachDatabaseReadListener();
        }

    //My Implimentation
    private void updateTask(DataSnapshot dataSnapshot) {
        Users users = dataSnapshot.getValue(Users.class);
        mUsersAdapter.add(users);
    }

    //Reading data from firebase
    void attachDatabaseReadListener() {

        if (mChildEventListener == null) {

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    updateTask(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateTask(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    updateTask(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mUsersDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

}

