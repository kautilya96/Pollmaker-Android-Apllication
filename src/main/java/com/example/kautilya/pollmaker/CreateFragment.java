package com.example.kautilya.pollmaker;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Kautilya on 26-3-17.
 */

public class CreateFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.create,container,false);
        final EditText etquestion=(EditText) v.findViewById(R.id.etquestion);
        final EditText etchoice1=(EditText) v.findViewById(R.id.etchoice1);
        final EditText etchoice2=(EditText) v.findViewById(R.id.etchoice2);
        final EditText etchoice3=(EditText) v.findViewById(R.id.etchoice3);
        final EditText etchoice4=(EditText) v.findViewById(R.id.etchoice4);

        final TextView go=(TextView) v.findViewById(R.id.etgo);

        go.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final String question = etquestion.getText().toString();
                final String choice1 = etchoice1.getText().toString();
                final String choice2 = etchoice2.getText().toString();
                final String choice3 = etchoice3.getText().toString();
                final String choice4 = etchoice4.getText().toString();
                final int id=getArguments().getInt("id");
                final String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                final String email2=getArguments().getString("email2");
                final String firstname2=getArguments().getString("firstname2");
                final Bundle bundle = new Bundle();
                bundle.putString("email", email2);
                bundle.putInt("id",id);
                bundle.putString("firstname",firstname2);

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
                                    Toast.makeText(getActivity(), "Poll Successfully created",
                                            Toast.LENGTH_LONG).show();
                                    HomeFragment f=new HomeFragment();
                                    f.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.Frame, f).commit();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("Poll cannot be added")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    CreateRequest cr = new CreateRequest(question, choice1, choice2,choice3,choice4,id,date, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(cr);
                }
            }
        });
        return v;
    }
}

