

////////////////////////////////////home page/////////////////////

package com.chel.mobicashexam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chel.mobicashexam.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn,viewDetails;

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

/////////////calling the views//////////////
        btn =(Button) findViewById( R.id.btn );
        text =(TextView) findViewById(R.id.text);
        viewDetails=(Button) findViewById(R.id.view);
        btn.setOnClickListener( this );
        text.setOnClickListener( this );
        viewDetails.setOnClickListener(this);
    }

    //////////////onClick method for Buttons

    @Override
    public void onClick(View v) {
        if(v== btn){
            Intent intent = new Intent( MainActivity.this, Register.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity( intent );
                    finish();
        }

        if(v == text ){
            Intent intent = new Intent( MainActivity.this, LogIn.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( intent );
            finish();
        }

        if(v== viewDetails){
            Intent intent = new Intent( MainActivity.this, UserDetails.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( intent );
            finish();
        }
    }
}
