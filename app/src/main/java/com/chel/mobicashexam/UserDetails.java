package com.chel.mobicashexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chel.mobicashexam.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class UserDetails extends AppCompatActivity {
    private static final String TAG = "UserDetails";

    private TextView textView;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseDatabase mDb;
    private DatabaseReference mRef;
    private ListView listView;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_details );

        textView =(TextView) findViewById(R.id.text);
        listView = (ListView) findViewById(R.id.listView);

        mDb = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        mRef = mDb.getReference();
        userID = user.getUid();

        //////////////////////////////////authenticating the user/////////////////////////////////
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(this,"Successfully signed in with: " + user.getEmail(),Toast.LENGTH_SHORT ).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

 //////////////////////////////fetch data from database///////////////////////////////////////////////
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User userInfo = new User();
                    userInfo.setFirstName(ds.child(userID).getValue(User.class).getFirstName());
                    userInfo.setLastName(ds.child(userID).getValue(User.class).getLastName());
                    userInfo.setEmail(ds.child(userID).getValue(User.class).getEmail());
                    userInfo.setPhone(ds.child(userID).getValue(User.class).getPhone());

                    //display all the information
                    Log.d(TAG, "showData: name: " + userInfo.getFirstName());
                    Log.d(TAG, "showData: name: " + userInfo.getLastName());
                    Log.d(TAG, "showData: email: " + userInfo.getEmail());
                    Log.d(TAG, "showData: phone_num: " + userInfo.getPhone());

                    ArrayList<String> array  = new ArrayList<>();

                    array.add(userInfo.getFirstName());
                    array.add(userInfo.getLastName());
                    array.add(userInfo.getEmail());
                    array.add(userInfo.getPhone());
                    ArrayAdapter adapter =new ArrayAdapter(this,)
                    listView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });
    }



}
