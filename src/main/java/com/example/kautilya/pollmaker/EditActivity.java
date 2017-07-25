package com.example.kautilya.pollmaker;

import android.app.AlertDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final EditText etquestion=(EditText) findViewById(R.id.etquestion);
        final EditText etchoice1=(EditText) findViewById(R.id.etchoice1);
        final EditText etchoice2=(EditText) findViewById(R.id.etchoice2);
        final EditText etchoice3=(EditText) findViewById(R.id.etchoice3);
        final EditText etchoice4=(EditText) findViewById(R.id.etchoice4);

        etquestion.setText(getIntent().getExtras().getString("question"));
        etchoice1.setText(getIntent().getExtras().getString("choice1"));
        etchoice2.setText(getIntent().getExtras().getString("choice2"));
        etchoice3.setText(getIntent().getExtras().getString("choice3"));
        etchoice4.setText(getIntent().getExtras().getString("choice4"));
        final int poll_id=getIntent().getExtras().getInt("poll_id");
        final TextView go=(TextView) findViewById(R.id.etgo);

        go.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final String question = etquestion.getText().toString();
                final String choice1 = etchoice1.getText().toString();
                final String choice2 = etchoice2.getText().toString();
                final String choice3 = etchoice3.getText().toString();
                final String choice4 = etchoice4.getText().toString();

                boolean valid=true;
                if (question.isEmpty())
                {
                    etquestion.setError("Field Cannot be empty");
                    valid=false;
                }
                if(choice1.isEmpty())
                {
                    etchoice1.setError("Please mention the option 1");
                    valid=false;
                }
                if(choice2.isEmpty())
                {
                    etchoice2.setError("Please mention the option 2");
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
                                    Toast.makeText(EditActivity.this, "Poll successfully edited",
                                            Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(EditActivity.this,ManageActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                                    builder.setMessage("Poll cannot be edited")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    EditRequest cr = new EditRequest(question, choice1,choice2,choice3,choice4,poll_id,date,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(EditActivity.this);
                    queue.add(cr);
                }
            }
        });
    }
}
