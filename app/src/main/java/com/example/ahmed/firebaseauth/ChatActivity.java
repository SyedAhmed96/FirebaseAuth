package com.example.ahmed.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ahmed on 1/15/2018.
 */

//Chat Room..
public class ChatActivity extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_PHOTO_PICKER = 2;

    private ListView mMessageListView;
    // private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    public String mUsername = ANONYMOUS;

    //Firebase storage references
    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference mMessageDatabaseReference;
    //For Reading Chat Node //String key_ID
    private DatabaseReference mChatDatabaseReference;
    private ChildEventListener mChildEventListener;

    //String Key ID(For AutoGEN key)
    //Using this key for reading Node(Messages)..
    private String key_ID;

    //Assistant
    // private StorageReference mChatPhotosStorageReference;
    // private FirebaseStorage mFirebaseStorage;

    //For User Email(Participants in Firebase)
    private String email1;
    private String email2;

    //For Keys
    String key1;
    String key2;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        //Getting the value from ContactsActivity(keys)
        Intent intent = getIntent();
        email1 = intent.getStringExtra("U1");
        email2 = intent.getStringExtra("U2");

        //Email Address for User Clicked(Token Taken)
        String[] parts = email1.split("\\@"); // escape .
        key1 = parts[0];
        //String part2 = parts[1];

        //Email Adddress for Current User(Token Taken)
        String[] parts1 = email2.split("\\@"); // escape .
        key2 = parts1[0];

        //Toast.makeText(getApplicationContext(), ""+ part2, Toast.LENGTH_SHORT).show();
       //Toast.makeText(getApplicationContext(), ""+ part1, Toast.LENGTH_SHORT).show();


         String key = key1.concat(key2);
         //Reverse String of key(Copy Key)
         String copyKey = key2.concat(key1);


        //Checking from firebase for key(if Exist)
        checkKey(key);
        checkKey(copyKey);

         //Ternary Operator For Checking And Initializing with Right Key..
        // key = checkCopyK ? ""+true : "copyKey";

        //Initialize Firebase Components
        mFirebasedatabase = FirebaseDatabase.getInstance();


            mMessageDatabaseReference = mFirebasedatabase.getReference().child("Chats").child("" + key);

            //Chats > UsersChat(Self Pushed) > Node(AutoGenerate)
            Participants participants = new Participants("" + email1, "" + email2);
            mMessageDatabaseReference.push().setValue(participants);


            //Update the value of databaseReference key from firebase database
            DatabaseReference pushRef = mMessageDatabaseReference.push();
            //Using this key for reading Node(Messages)..
            key_ID = pushRef.getKey();


        //Chats > UsersChat(Self Pushed) > Node(AutoGenerate)
        FriendlyMessage message = new FriendlyMessage("Hello im user 1", "" + email2);
        mMessageDatabaseReference.push().setValue(message);


        //Initiallizing ChatReference to chat Node..
        mChatDatabaseReference = mFirebasedatabase.getReference().child("Chats").child(""+key).child(key_ID);


        /*
        //Holds Reference For Database
        mMessageDatabaseReference.orderByKey().equalTo(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot==null || dataSnapshot.getChildren()==null) {
                    //Key does not exist
                } else {
                    //Key exists
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        */

        Toast.makeText(ChatActivity.this, "" + key, Toast.LENGTH_LONG).show();

    }

    //Checks if key exists in firebase and updates database Reference accordingly
    void checkKey(String key) {

        mMessageDatabaseReference.orderByKey().equalTo(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null || dataSnapshot.getChildren() == null) {
                    //Key does not exist
                } else {
                    //Key exists

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

       });

    }

}