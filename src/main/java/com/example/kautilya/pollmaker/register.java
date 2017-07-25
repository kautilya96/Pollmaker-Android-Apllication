package com.example.kautilya.pollmaker;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
public class register extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        final EditText etfirstname = (EditText) findViewById(R.id.etfirstname);
        final EditText etlastname = (EditText) findViewById(R.id.etlastname);
        final EditText etemail = (EditText) findViewById(R.id.etemail);
        final EditText etPassword = (EditText) findViewById(R.id.etpassword);
        final Button bRegister = (Button) findViewById(R.id.etRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstname = etfirstname.getText().toString();
                final String lastname = etlastname.getText().toString();
                final String email = etemail.getText().toString();
                final String password = etPassword.getText().toString();
                boolean valid=true;
                if(firstname.matches(".*\\d+.*") || firstname.isEmpty())
                {
                    etfirstname.setError("Please enter valid Firstname");
                    valid=false;
                }
                if(lastname.matches(".*\\d+.*") || lastname.isEmpty())
                {
                    etlastname.setError("Please enter valid Lastname");
                    valid=false;
                }
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    etemail.setError("Please enter valid email Address");
                    valid=false;
                }
                if(password.isEmpty() || password.length()<8 || password.length()>12 )
                {
                    etPassword.setError("Please enter password between 8 and 12 characters");
                    valid=false;
                }
                if(valid==true) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(register.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    register.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                                    builder.setMessage("User Already registerd")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    RegisterRequest registerRequest = new RegisterRequest(firstname, lastname, email, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(register.this);
                    queue.add(registerRequest);
                }
            }
        });
    }
}