package com.example.kautilya.pollmaker;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kautilya on 30-3-17.
 */

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder>
{
    private List<ListItem2> listItems;
    private Context context;

    public MyAdapter3(List<ListItem2> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item3,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListItem2 listItem= listItems.get(position);
        final ViewHolder vh=holder;
        holder.textViewHead.setText(listItem.getHead());

        if("Nothing to show".equals(listItem.getHead()))
        {
        }
        else
        {

            final int ans1=listItem.getAns1();
            final int ans2=listItem.getAns2();
            final int ans3=listItem.getAns3();
            final int ans4=listItem.getAns4();
            final int sum=ans1+ans2+ans3+ans4;


            holder.textViewHead.setText(listItem.getHead());
            holder.creation_date.append(listItem.getDate());
            holder.responses.setText(sum+" responses have been recorded so far");
            holder.op1.append(listItem.getChoice1());
            holder.op2.append(listItem.getChoice2());
            if (listItem.getChoice3().equals(""))
            {
                holder.op3.append("--N.A--");
                holder.op4.append("--N.A--");
            }
            else if (listItem.getChoice4().equals(""))
            {
                holder.op4.append("--N.A--");
            }
            else
            {
                holder.op3.append(listItem.getChoice3());
                holder.op4.append(listItem.getChoice4());
            }
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                           Intent i=new Intent(context,EditActivity.class);
                           i.putExtra("poll_id",listItem.getPoll_id());
                           i.putExtra("question",listItem.getHead());
                           i.putExtra("choice1",listItem.getChoice1());
                           i.putExtra("choice2",listItem.getChoice2());
                           i.putExtra("choice3",listItem.getChoice3());
                           i.putExtra("choice4",listItem.getChoice4());
                           context.startActivity(i);
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(context);


                    //  adb.setView(alertDialogView);


                    adb.setTitle("Do you want to delete the poll ?");


                    adb.setIcon(android.R.drawable.ic_dialog_alert);


                    adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                            Response.Listener<String> listener=new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        // boolean success = jsonResponse.getBoolean("success");
                                        boolean res=jsonResponse.getBoolean("success");
                                        if (res) {

                                            Toast.makeText(context, "Deleted Poll" , Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            delPollRequest ll=new delPollRequest(String.valueOf(listItem.getPoll_id()),listener);
                            RequestQueue queue = Volley.newRequestQueue(context);
                            queue.add(ll);
                            Intent intent = new Intent(context,ManageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        }
                    });


                    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                                 }
                    });
                    adb.show();

                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView responses;
        public TextView delete;
        public TextView creation_date;
        public TextView edit,op1,op2,op3,op4;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead=(TextView) itemView.findViewById(R.id.textViewHead);
            delete=(TextView) itemView.findViewById(R.id.delete);
            responses=(TextView) itemView.findViewById(R.id.responses);
            creation_date=(TextView) itemView.findViewById(R.id.creation_date);
            edit=(TextView) itemView.findViewById(R.id.edit);
            op1=(TextView) itemView.findViewById(R.id.op1);
            op2=(TextView) itemView.findViewById(R.id.op2);
            op3=(TextView) itemView.findViewById(R.id.op3);
            op4=(TextView) itemView.findViewById(R.id.op4);
        }
    }
}
