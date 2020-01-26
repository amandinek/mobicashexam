package com.chel.mobicashexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn,btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        btn =(Button) findViewById( R.id.btn );
        btnSignIn =(Button) findViewById(R.id.btnsignIn);
        btn.setOnClickListener( this );
        btnSignIn.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if(v== btn){
            Intent intent = new Intent( MainActivity.this,Register.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity( intent );
                    finish();
        }

        if(v == btnSignIn ){
            Intent intent = new Intent( MainActivity.this,LogIn.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( intent );
            finish();
        }
    }
}
