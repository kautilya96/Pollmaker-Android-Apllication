package com.example.kautilya.pollmaker;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem2> listItems;

    BarChart barchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView2);
      //  recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(GraphActivity.this));
        listItems = new ArrayList<>();
        //final int id=getArguments().getInt("id");
       // String id1=String.valueOf(id);
       // System.out.println("\n\n\n\n"+id+"\n\n\n\n");
        final SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        final int id=sp.getInt("id",0);

        String id1=String.valueOf(id);

        final ProgressDialog pd=new ProgressDialog(GraphActivity.this);
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
                        ListItem2 listItem=new ListItem2(
                                0,
                                "Nothing to show",
                                "",
                                "",
                                "",
                                "",0,0,0,0,id,""
                        );
                        listItems.add(listItem);

                        adapter = new MyAdapter2(listItems,GraphActivity.this);
                        recyclerView.setAdapter(adapter);
                    }


                    else
                    {
                        for (int i=0;i<array.length();i++)
                        {
                            JSONObject o=array.getJSONObject(i);
                            ListItem2 listItem=new ListItem2(
                                    o.optInt("id"),
                                    o.optString("question"),
                                    o.optString("choice1"),
                                    o.optString("choice2"),
                                    o.optString("choice3"),
                                    o.optString("choice4"),
                                    o.optInt("ans1"),
                                    o.optInt("ans2"),o.optInt("ans3"),o.optInt("ans4"),id,o.optString("creation_date")



                            );

                            listItems.add(listItem);
                        }

                        adapter = new MyAdapter2(listItems,GraphActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        myPollRequest ll=new myPollRequest(id1,listener);
        RequestQueue queue = Volley.newRequestQueue(GraphActivity.this);
        queue.add(ll);

    }


}
