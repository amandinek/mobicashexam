package com.chel.mobicashexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button signUp;
    private Button cancel;
    private EditText firstName,lastName,email,password,phoneNumber;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//        Realm.init(context);
        setContentView( R.layout.activity_register );

        signUp = (Button) findViewById( R.id.signUp );
        cancel = (Button) findViewById( R.id.cancel );
        firstName = (EditText) findViewById( R.id.firstName );
        lastName = (EditText) findViewById( R.id.lastName );
        email = (EditText) findViewById( R.id.email );
        password = (EditText) findViewById( R.id.password );
        phoneNumber = (EditText) findViewById( R.id.PhoneNbr );

        signUp.setOnClickListener(  this );
        cancel.setOnClickListener( this );
        auth = FirebaseAuth.getInstance();
//        git

        createAuthListner();
//        createAuthProgressDialog();}
    }

    @Override
    public void onClick(View v) {
        if(v== signUp){
            createnUser();
        }

        if(v== cancel){
//            something
        }

    }
    public void createnUser(){
        final String frstName =firstName.toString().trim();
        final String lstName =lastName.toString().trim();
        final String userEmail = email.toString().trim();
        final String phnumber = phoneNumber.toString().trim();
        String passwrd = password.toString().trim();

        auth.createUserWithEmailAndPassword( userEmail,passwrd )
                .addOnCompleteListener( this , new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText( Register.this , "authentication successful" , Toast.LENGTH_SHORT ).show();
                        }else{
                            Toast.makeText( Register.this , "authentication failed" , Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );




    }
    public void createAuthListner(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user !=null){
                    Intent intent = new Intent( Register.this,UserDetails.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity( intent );
                    finish();
                }
            }
        };
    }




}
