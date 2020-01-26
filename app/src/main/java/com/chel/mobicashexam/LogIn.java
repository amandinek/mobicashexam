package com.chel.mobicashexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private Button signUp,cancel;
    private EditText email,password;
    private TextView textView;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_log_in );

        auth =FirebaseAuth.getInstance();


        signUp=(Button) findViewById(R.id.signIn);
        cancel=(Button) findViewById(R.id.cancel);
        email =(EditText) findViewById(R.id.editEmail);
        password =(EditText) findViewById(R.id.editPassword);
        textView =(TextView) findViewById(R.id.text);

        signUp.setOnClickListener(this);
        cancel.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
