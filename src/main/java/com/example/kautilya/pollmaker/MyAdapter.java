package com.example.kautilya.pollmaker;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * Created by Kautilya on 30-3-17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
    final ListItem listItem= listItems.get(position);
        final ViewHolder vh=holder;
        holder.textViewHead.setText(listItem.getHead());
        holder.rd1.setText(listItem.getChoice1());
        holder.rd2.setText(listItem.getChoice2());
        if("Nothing to show".equals(listItem.getHead()))
        {
            holder.rgroup.setVisibility(View.INVISIBLE);
        }
        else {
            if ("".equals(listItem.getChoice3())) {
                holder.rd3.setVisibility(View.INVISIBLE);
                holder.rd4.setVisibility(View.INVISIBLE);
                ViewGroup.LayoutParams l = holder.rgroup.getLayoutParams();

                l.height = 600;
                holder.rgroup.setLayoutParams(l);
            } else {
                holder.rd3.setText(listItem.getChoice3());
                if ("".equals(listItem.getChoice4())) {
                    holder.rd4.setVisibility(View.INVISIBLE);
                    ViewGroup.LayoutParams l = holder.rgroup.getLayoutParams();
                    l.height = 750;
                    holder.rgroup.setLayoutParams(l);
                } else {
                    holder.rd4.setText(listItem.getChoice4());

                }
            }
        }
        final String checkedMark = "\u2713";

        if(listItem.getVoted()==true)
        {

            int choice=listItem.getChoice();

            final int ans1=listItem.getAns1();
            final int ans2=listItem.getAns2();
            final int ans3=listItem.getAns3();
            final int ans4=listItem.getAns4();
            final int sum=ans1+ans2+ans3+ans4;
            final float percent1=(float)((ans1*100)/sum);
            final float percent2=(float)((ans2*100)/sum);
            final float percent3=(float)((ans3*100)/sum);
            final float percent4=(float)((ans4*100)/sum);


            switch(choice){
                case 1:
                    holder.rd1.setChecked(true);
                    holder.rd1.setText(listItem.getChoice1()+"\t\t"+checkedMark+"\t\t"+percent1+" %");
                    holder.rd1.setBackgroundColor(Color.parseColor("#89c5a2"));
                    holder.rd2.setEnabled(false);
                    holder.rd3.setEnabled(false);
                    holder.rd4.setEnabled(false);
                    holder.rd2.setText(listItem.getChoice2()+"\t\t\t\t\t"+percent2+" %");
                    holder.rd3.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent3+" %");
                    holder.rd4.setText(listItem.getChoice4()+"\t\t\t\t\t"+percent4+" %");
                    holder.rgroup.setEnabled(false);
                    break;
                case 2:
                    holder.rd1.setEnabled(false);
                    holder.rd2.setChecked(true);
                    holder.rd2.setText(listItem.getChoice2()+"\t\t"+checkedMark+"\t\t"+percent2+" %");
                    holder.rd2.setBackgroundColor(Color.parseColor("#89c5a2"));

                    holder.rd3.setEnabled(false);
                    holder.rd4.setEnabled(false);
                    holder.rd1.setText(listItem.getChoice1()+"\t\t\t\t\t"+percent1+" %");
                    holder.rd3.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent3+" %");
                    holder.rd4.setText(listItem.getChoice4()+"\t\t\t\t\t"+percent4+" %");
                    holder.rgroup.setEnabled(false);
                    break;

                case 3:

                    holder.rd3.setChecked(true);
                    holder.rd3.setText(listItem.getChoice2()+"\t\t"+checkedMark+"\t\t"+percent3+" %");
                    holder.rd3.setBackgroundColor(Color.parseColor("#89c5a2"));
                    holder.rd2.setEnabled(false);
                    holder.rd1.setEnabled(false);
                    holder.rd4.setEnabled(false);
                    holder.rd1.setText(listItem.getChoice2()+"\t\t\t\t\t"+percent1+" %");
                    holder.rd2.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent2+" %");
                    holder.rd4.setText(listItem.getChoice4()+"\t\t\t\t\t"+percent4+" %");
                    holder.rgroup.setEnabled(false);
                    break;

                case 4:

                    holder.rd4.setChecked(true);
                    holder.rd4.setText(listItem.getChoice2()+"\t\t"+checkedMark+"\t\t"+percent4+" %");
                    holder.rd4.setBackgroundColor(Color.parseColor("#89c5a2"));
                    holder.rd2.setEnabled(false);
                    holder.rd3.setEnabled(false);
                    holder.rd1.setEnabled(false);
                    holder.rd2.setText(listItem.getChoice2()+"\t\t\t\t\t"+percent2+" %");
                    holder.rd3.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent3+" %");
                    holder.rd1.setText(listItem.getChoice1()+"\t\t\t\t\t"+percent1+" %");
                    holder.rgroup.setEnabled(false);
                    break;
            }


        }
        final int flag = 0;
        final int ch = 0;
              /*  holder.rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // checkedId is the RadioButton selected
                        String choice="";
                        int votes=0;
                        String s_votes="";
                        switch(checkedId){
                            case R.id.rd1:
                                choice=String.valueOf(1);
                                votes=listItem.getAns1();
                                votes++;
                                s_votes=String.valueOf(votes);
                                break;
                            case R.id.rd2:
                                choice=String.valueOf(2);
                                votes=listItem.getAns2();
                                votes++;
                                s_votes=String.valueOf(votes);
                                break;
                            case R.id.rd3:
                                choice=String.valueOf(3);
                                votes=listItem.getAns3();
                                votes++;
                                s_votes=String.valueOf(votes);
                                break;
                            case R.id.rd4:
                                choice=String.valueOf(4);
                                votes=listItem.getAns4();
                                votes++;
                                s_votes=String.valueOf(votes);
                                break;
                        }
                        String user_id=String.valueOf(listItem.getId());
                        String poll_id=String.valueOf(listItem.getPoll_id());
                        Response.Listener<String> listener=new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    // boolean success = jsonResponse.getBoolean("success");
                                    boolean res=jsonResponse.getBoolean("success");
                                    if (res) {

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setMessage("Unable to process your vote")
                                                .setNeutralButton("Retry", null);

                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                       }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        VoteRequest ll=new VoteRequest(user_id,s_votes,choice,poll_id,listener);
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(ll);

                    }
                });
*/
               holder.rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       String choice = "";
                       int votes = 0;
                       String s_votes = "";
                       if (isChecked) {


                           choice=String.valueOf(1);
                           votes=listItem.getAns1();
                           votes++;
                           s_votes=String.valueOf(votes);
                           String user_id = String.valueOf(listItem.getId());
                           String poll_id = String.valueOf(listItem.getPoll_id());
                           Response.Listener<String> listener = new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   try {
                                       JSONObject jsonResponse = new JSONObject(response);
                                       // boolean success = jsonResponse.getBoolean("success");
                                       boolean res = jsonResponse.getBoolean("success");
                                       if (res) {
                                           int ans1=listItem.getAns1();
                                           ans1++;
                                           int ans2=listItem.getAns2();
                                           int ans3=listItem.getAns3();
                                           int ans4=listItem.getAns4();
                                           int sum=ans1+ans2+ans3+ans4;
                                           float percent1=(float)((ans1*100)/sum);
                                           float percent2=(float)((ans2*100)/sum);
                                           float percent3=(float)((ans3*100)/sum);
                                           float percent4=(float)((ans4*100)/sum);

                                           holder.rd1.setChecked(true);
                                           holder.rd1.setText(listItem.getChoice1()+"\t\t"+checkedMark+"\t\t"+percent1+" %");
                                           holder.rd1.setBackgroundColor(Color.parseColor("#89c5a2"));
                                           holder.rd2.setEnabled(false);
                                           holder.rd3.setEnabled(false);
                                           holder.rd4.setEnabled(false);
                                           holder.rd2.setText(listItem.getChoice2()+"\t\t\t\t\t"+percent2+" %");
                                           holder.rd3.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent3+" %");
                                           holder.rd4.setText(listItem.getChoice4()+"\t\t\t\t\t"+percent4+" %");
                                           holder.rgroup.setEnabled(false);
                                       } else {
                                           AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                           builder.setMessage("Unable to process your vote")
                                                   .setNeutralButton("Retry", null);

                                           AlertDialog dialog = builder.create();
                                           dialog.show();
                                       }
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           };
                           VoteRequest ll = new VoteRequest(user_id, s_votes, choice, poll_id, listener);
                           RequestQueue queue = Volley.newRequestQueue(context);
                           queue.add(ll);

                       }
                   }

               });

        holder.rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String choice = "";
                int votes = 0;
                String s_votes = "";
                if (isChecked) {
                    choice=String.valueOf(2);
                    votes=listItem.getAns2();
                    votes++;
                    s_votes=String.valueOf(votes);
                    String user_id = String.valueOf(listItem.getId());
                    String poll_id = String.valueOf(listItem.getPoll_id());
                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                // boolean success = jsonResponse.getBoolean("success");
                                boolean res = jsonResponse.getBoolean("success");
                                if (res) {
                                    int ans1=listItem.getAns1();
                                    int ans2=listItem.getAns2();
                                    ans2++;
                                    int ans3=listItem.getAns3();
                                    int ans4=listItem.getAns4();
                                    int sum=ans1+ans2+ans3+ans4;
                                    float percent1=(float)((ans1*100)/sum);
                                    float percent2=(float)((ans2*100)/sum);
                                    float percent3=(float)((ans3*100)/sum);
                                    float percent4=(float)((ans4*100)/sum);
                                    holder.rd2.setChecked(true);
                                    holder.rd2.setText(listItem.getChoice2()+"\t\t"+checkedMark+"\t\t"+percent2+" %");
                                    holder.rd2.setBackgroundColor(Color.parseColor("#89c5a2"));
                                    holder.rd1.setEnabled(false);
                                    holder.rd3.setEnabled(false);
                                    holder.rd4.setEnabled(false);
                                    holder.rd1.setText(listItem.getChoice1()+"\t\t\t\t\t"+percent1+" %");
                                    holder.rd3.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent3+" %");
                                    holder.rd4.setText(listItem.getChoice4()+"\t\t\t\t\t"+percent4+" %");
                                    holder.rgroup.setEnabled(false);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Unable to process your vote")
                                            .setNeutralButton("Retry", null);

                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    VoteRequest ll = new VoteRequest(user_id, s_votes, choice, poll_id, listener);
                    RequestQueue queue = Volley.newRequestQueue(context);
                    queue.add(ll);

                }
            }

        });
        holder.rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String choice = "";
                int votes = 0;
                String s_votes = "";
                if (isChecked) {
                    choice=String.valueOf(3);
                    votes=listItem.getAns3();
                    votes++;
                    s_votes=String.valueOf(votes);
                    String user_id = String.valueOf(listItem.getId());
                    String poll_id = String.valueOf(listItem.getPoll_id());
                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                // boolean success = jsonResponse.getBoolean("success");
                                boolean res = jsonResponse.getBoolean("success");
                                if (res) {
                                    int ans1=listItem.getAns1();
                                    int ans2=listItem.getAns2();

                                    int ans3=listItem.getAns3();
                                    ans3++;
                                    int ans4=listItem.getAns4();

                                    int sum=ans1+ans2+ans3+ans4;
                                    float percent1=(float)((ans1*100)/sum);
                                    float percent2=(float)((ans2*100)/sum);
                                    float percent3=(float)((ans3*100)/sum);
                                    float percent4=(float)((ans4*100)/sum);
                                    holder.rd3.setChecked(true);
                                    holder.rd3.setText(listItem.getChoice2()+"\t\t"+checkedMark+"\t\t"+percent3+" %");
                                    holder.rd3.setBackgroundColor(Color.parseColor("#89c5a2"));
                                    holder.rd2.setEnabled(false);
                                    holder.rd1.setEnabled(false);
                                    holder.rd4.setEnabled(false);
                                    holder.rd1.setText(listItem.getChoice2()+"\t\t\t\t\t"+percent1+" %");
                                    holder.rd2.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent2+" %");
                                    holder.rd4.setText(listItem.getChoice4()+"\t\t\t\t\t"+percent4+" %");
                                    holder.rgroup.setEnabled(false);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Unable to process your vote")
                                            .setNeutralButton("Retry", null);

                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    VoteRequest ll = new VoteRequest(user_id, s_votes, choice, poll_id, listener);
                    RequestQueue queue = Volley.newRequestQueue(context);
                    queue.add(ll);

                }
            }

        });

        holder.rd4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String choice = "";
                int votes = 0;
                String s_votes = "";
                if (isChecked) {
                    choice=String.valueOf(4);
                    votes=listItem.getAns4();
                    votes++;
                    s_votes=String.valueOf(votes);
                    String user_id = String.valueOf(listItem.getId());
                    String poll_id = String.valueOf(listItem.getPoll_id());
                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                // boolean success = jsonResponse.getBoolean("success");
                                boolean res = jsonResponse.getBoolean("success");
                                if (res) {
                                    int ans1=listItem.getAns1();
                                    int ans2=listItem.getAns2();

                                    int ans3=listItem.getAns3();

                                    int ans4=listItem.getAns4();
                                    ans4++;
                                    int sum=ans1+ans2+ans3+ans4;
                                    float percent1=(float)((ans1*100)/sum);
                                    float percent2=(float)((ans2*100)/sum);
                                    float percent3=(float)((ans3*100)/sum);
                                    float percent4=(float)((ans4*100)/sum);
                                    holder.rd4.setChecked(true);
                                    holder.rd4.setText(listItem.getChoice2()+"\t\t"+checkedMark+"\t\t"+percent4+" %");
                                    holder.rd4.setBackgroundColor(Color.parseColor("#89c5a2"));
                                    holder.rd2.setEnabled(false);
                                    holder.rd3.setEnabled(false);
                                    holder.rd1.setEnabled(false);
                                    holder.rd2.setText(listItem.getChoice2()+"\t\t\t\t\t"+percent2+" %");
                                    holder.rd3.setText(listItem.getChoice3()+"\t\t\t\t\t"+percent3+" %");
                                    holder.rd1.setText(listItem.getChoice1()+"\t\t\t\t\t"+percent1+" %");
                                    holder.rgroup.setEnabled(false);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Unable to process your vote")
                                            .setNeutralButton("Retry", null);

                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    VoteRequest ll = new VoteRequest(user_id, s_votes, choice, poll_id, listener);
                    RequestQueue queue = Volley.newRequestQueue(context);
                    queue.add(ll);

                }
            }

        });




    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView textViewHead;
        public RadioButton rd1;
        public RadioButton rd2;
        public RadioButton rd3;
        public RadioButton rd4;
        public RadioGroup rgroup;
        public LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead=(TextView) itemView.findViewById(R.id.textViewHead);
            rd1=(RadioButton) itemView.findViewById(R.id.rd1);
            rd2=(RadioButton) itemView.findViewById(R.id.rd2);
            rd3=(RadioButton) itemView.findViewById(R.id.rd3);
            rd4=(RadioButton) itemView.findViewById(R.id.rd4);
            rgroup=(RadioGroup) itemView.findViewById(R.id.rgroup);
            ll=(LinearLayout) itemView.findViewById(R.id.ll);
           }
    }
}
