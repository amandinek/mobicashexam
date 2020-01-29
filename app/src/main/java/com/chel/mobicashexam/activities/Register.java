
///////////////user registration activity/////////


package com.chel.mobicashexam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.chel.mobicashexam.R;
import com.chel.mobicashexam.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth;
//    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase db;
    DatabaseReference users;

    private Button signUp;
    private Button cancel;
    private EditText firstName,lastName,email,password,phoneNumber;
    private RelativeLayout rootLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        signUp = (Button) findViewById( R.id.signUp );
        cancel = (Button) findViewById( R.id.cancel );
        firstName = (EditText) findViewById( R.id.firstName );
        lastName = (EditText) findViewById( R.id.lastName );
        email = (EditText) findViewById( R.id.email );
        password = (EditText) findViewById( R.id.password );
        phoneNumber = (EditText) findViewById( R.id.PhoneNbr );
        rootLayout =(RelativeLayout)findViewById(R.id.rootLayout);

        auth = FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        users =db.getReference("users");
        users.keepSynced(true);

        signUp.setOnClickListener(  this );
        cancel.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        if(v== signUp){
            createnUser();
        }

        if(v== cancel){
            Intent intent = new Intent( Register.this, MainActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( intent );
            finish();
        }

    }
    ///////////////////////////creating a new user and verifying if the entry are not empty//////////
    public void createnUser(){



          if(TextUtils.isEmpty(email.getText().toString())){
              Snackbar.make(rootLayout,"please enter your email",Snackbar.LENGTH_SHORT)
                      .show();
              return;

          }

        if(password.getText().length()<6){
            Snackbar.make(rootLayout,"password too short",Snackbar.LENGTH_SHORT)
                    .show();
            return;

        }
        if(TextUtils.isEmpty(phoneNumber.getText().toString())){
            Snackbar.make(rootLayout,"please enter your phone number",Snackbar.LENGTH_SHORT)
                    .show();
            return;

        }
        if(TextUtils.isEmpty(firstName.getText().toString())){
            Snackbar.make(rootLayout,"please enter your First name",Snackbar.LENGTH_SHORT)
                    .show();
            return;

        }
        if(TextUtils.isEmpty(lastName.getText().toString())){
            Snackbar.make(rootLayout,"please enter your last name",Snackbar.LENGTH_SHORT)
                    .show();
            return;

        }
        ////register new user///////
        //
        auth.createUserWithEmailAndPassword( email.getText().toString(),password.getText().toString() )
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setFirstName(firstName.getText().toString());
                        user.setLastName(lastName.getText().toString());
                        user.setPhone(phoneNumber.getText().toString());

                        users.child(FirebaseAuth.getInstance().getUid())
                                .setValue(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Snackbar.make(rootLayout, "register successful", Snackbar.LENGTH_SHORT)
                                                .show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(rootLayout, "registration failed", Snackbar.LENGTH_SHORT)
                                                .show();
                                    }
                                });

                    }
                });


    }


}
