package com.chel.mobicashexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private Button signUp,cancel;
    private EditText email,password;
    private TextView textView;
    private RelativeLayout rootLayout;

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
        rootLayout =(RelativeLayout)findViewById(R.id.rootLayout);

        signUp.setOnClickListener(this);
        cancel.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signUp){
            userLogin();
        }

        if(v == cancel){
            Intent intent = new Intent( LogIn.this, MainActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( intent );
            finish();

        }
        if(v == textView){
            Intent intent = new Intent( LogIn.this, Register.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( intent );
            finish();

        }




    }

    private void userLogin() {

        if(TextUtils.isEmpty(email.getText().toString())){
            Snackbar.make(rootLayout,"please enter email adress",Snackbar.LENGTH_SHORT)
                    .show();
            return;

        }

        if(TextUtils.isEmpty(password.getText().toString())){
            Snackbar.make(rootLayout,"please enter password adress",Snackbar.LENGTH_SHORT)
                    .show();
            return;

        }

        auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(LogIn.this,UserDetails.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(rootLayout,"failed",Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}
