package com.example.kautilya.pollmaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class userhome2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome2);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        final Button etlogout=(Button) findViewById(R.id.etLogout);

        etlogout.setOnClickListener(new View.OnClickListener() {
        final SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e=sp.edit();
                e.clear();
                e.commit();
                Intent i=new Intent(userhome2.this,MainActivity.class);
                userhome2.this.startActivity(i);
            }
        });
    }
}

