package com.example.kautilya.pollmaker;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Kautilya on 26-3-17.
 */

public class VoteFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.vote,container,false);
        recyclerView=(RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();
        final int id=getArguments().getInt("id");
        String id1=String.valueOf(id);
        System.out.println("\n\n\n\n"+id+"\n\n\n\n");

        final ProgressDialog pd=new ProgressDialog(getActivity());
        pd.setMessage("Loading Feed.....");
        pd.show();


        Response.Listener<String> listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    pd.dismiss();

                    //  JSONObject jsonResponse = new JSONObject(response);
                   //  boolean success = jsonResponse.getBoolean("success");
                   // boolean res=jsonResponse.getBoolean("success");
                    JSONArray array=new JSONArray(response);

                    if (array.length()==0) {
                        ListItem listItem=new ListItem(
                                false,
                                0,
                                0,
                                "Nothing to show",
                                "",
                                "",
                                "",
                                "",0,0,0,0,id
                        );
                        listItems.add(listItem);

                        adapter = new MyAdapter(listItems,getActivity());
                        recyclerView.setAdapter(adapter);
                    }


                    else
                    {
                        for (int i=0;i<array.length();i++)
                        {
                            JSONObject o=array.getJSONObject(i);
                            ListItem listItem=new ListItem(
                                    o.optBoolean("voted"),
                                    o.optInt("choice"),
                                    o.optInt("id"),
                                    o.optString("question"),
                                    o.optString("choice1"),
                                    o.optString("choice2"),
                                    o.optString("choice3"),
                                    o.optString("choice4"),
                                    o.optInt("ans1"),
                                    o.optInt("ans2"),o.optInt("ans3"),o.optInt("ans4"),id



                            );

                            listItems.add(listItem);
                        }

                        adapter = new MyAdapter(listItems,getActivity());
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        PollRequest ll=new PollRequest(id1,listener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(ll);



      /*  for (int i=0;i<=10;i++){
            ListItem listItem=new ListItem(
                    "heading"+ (i+1),
                    "Dummy text"
            );
            listItems.add(listItem);
        }
       adapter = new MyAdapter(listItems,getActivity());
        recyclerView.setAdapter(adapter);
       */
        return v;
    }
}

