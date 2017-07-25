package com.example.kautilya.pollmaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.media.tv.TvInputService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        final  String MyPREFERENCES="MyPrefs";
        final EditText etusername=(EditText) findViewById(R.id.etusername);
        final EditText etpassword=(EditText) findViewById(R.id.etpassword);
        final Button etlogin=(Button) findViewById(R.id.etLogin);
        final TextView etregisterhere=(TextView) findViewById(R.id.etregisterhere);
        final SharedPreferences sp,sp2;
        sp=getSharedPreferences("login",MODE_PRIVATE);

        if(sp.contains("email") && sp.contains("firstname") && sp.contains("id") && sp.contains("lastname")){
            startActivity(new Intent(MainActivity.this,userhome.class));
            finish();   //finish current activity
        }

       // final SessionManager session = new SessionManager(getApplicationContext());
       // if(session.isLoggedIn())
       // {
         //   Intent registerIntent=new Intent(MainActivity.this,userhome.class);
          //  MainActivity.this.startActivity(registerIntent);
        //}
        etregisterhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(MainActivity.this,register.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        etlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etusername.getText().toString();
                final String password = etpassword.getText().toString();

                Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                           // boolean success = jsonResponse.getBoolean("success");
                            boolean res=jsonResponse.getBoolean("success");
                            if (res) {

                                String firstname = jsonResponse.getString("firstname");

                                String lastname = jsonResponse.getString("lastname");
                                int id=jsonResponse.getInt("id");
                              //  session.createLoginSession(email, firstname);
                                SharedPreferences.Editor e=sp.edit();
                                e.putBoolean("islogin",true);
                                e.putString("email",email);
                                e.putString("firstname",firstname);
                                e.putString("lastname",lastname);
                                e.putInt("id",id);
                                e.commit();

                                final ProgressDialog pd=new ProgressDialog(MainActivity.this);
                                pd.setMessage("Logging In");
                                pd.show();
                                Intent in = new Intent(MainActivity.this, userhome.class);

                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                MainActivity.this.startActivity(in);

                                finish();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                etusername.setText("");
                                etpassword.setText("");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest ll=new LoginRequest(email,password,listener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(ll);
            }
        });
    }
}
