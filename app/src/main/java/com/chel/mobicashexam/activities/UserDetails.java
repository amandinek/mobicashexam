

///////////////////////retrieving and displaying user datas////////////


package com.chel.mobicashexam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chel.mobicashexam.R;
import com.chel.mobicashexam.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDetails extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UserDetails";

    private TextView textView;
    List<String> detailList;
    ArrayAdapter<String> adapter;
    private ListView listView;
    private Button logOut;



    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        textView = (TextView) findViewById(R.id.text);
        listView = (ListView) findViewById(R.id.listView);
        detailList = new ArrayList<>();
        logOut = (Button) findViewById(R.id.logOut);


    }
/////////////////////log out button/////////
    @Override
    public void onClick(View v) {
        if(v== logOut){

           FirebaseAuth.getInstance().signOut();

        }

    }


}
