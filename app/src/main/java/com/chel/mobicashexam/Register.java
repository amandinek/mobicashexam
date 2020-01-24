package com.chel.mobicashexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    private Button signUp;
    private Button cancel;
    private EditText firstName,lastName,email,password,phoneNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//        Realm.init(context);
        setContentView( R.layout.activity_register );

        signUp=(Button)findViewById( R.id.signUp );
        cancel=(Button)findViewById( R.id.cancel );
        firstName=(EditText)findViewById( R.id.firstName );
        lastName=(EditText)findViewById( R.id.lastName );
        email=(EditText)findViewById( R.id.email );
        password=(EditText)findViewById( R.id.password );
        phoneNumber=(EditText)findViewById( R.id.PhoneNbr);


    }
}
