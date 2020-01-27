package com.chel.mobicashexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chel.mobicashexam.model.DetailAdapter;
import com.chel.mobicashexam.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
    List<String> detailList;
    ArrayAdapter<String> adapter;

    private String[] detail= new String[]{"firstName","LastNmae","Email","Phone number"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_details );

        textView =(TextView) findViewById(R.id.text);
        listView = (ListView) findViewById(R.id.listView);
        detailList =new ArrayList<>();

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
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());

                } else {

                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
            }
        };

 //////////////////////////////fetch data from database///////////////////////////////////////////////
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailList.clear();
                //////////////retrieving user informations /////////////////
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User userInfo = new User();
                    userInfo.setFirstName(ds.child(userID).getValue(User.class).getFirstName());
                    userInfo.setLastName(ds.child(userID).getValue(User.class).getLastName());
                    userInfo.setEmail(ds.child(userID).getValue(User.class).getEmail());
                    userInfo.setPhone(ds.child(userID).getValue(User.class).getPhone());


                    Log.d(TAG, "showData: name: " + userInfo.getFirstName());
                    Log.d(TAG, "showData: name: " + userInfo.getLastName());
                    Log.d(TAG, "showData: email: " + userInfo.getEmail());
                    Log.d(TAG, "showData: phone_num: " + userInfo.getPhone());

                    detailList.add(userInfo.getFirstName());
                    detailList.add(userInfo.getLastName());
                    detailList.add(userInfo.getEmail());
                    detailList.add(userInfo.getPhone());
                    adapter =new ArrayAdapter<String>(UserDetails.this,android.R.layout.simple_list_item_1,detailList);
                    listView.setAdapter(adapter);

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });
    }

    /////////////////////cusumize a toast /////////////
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }






}
