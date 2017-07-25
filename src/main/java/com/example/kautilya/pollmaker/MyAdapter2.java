package com.example.kautilya.pollmaker;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>
{
    private List<ListItem2> listItems;
    private Context context;

    public MyAdapter2(List<ListItem2> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item2,parent,false);
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
            final float percent1,percent2,percent3,percent4;
            if(sum!=0)
            {
                percent1=(float)((ans1*100)/sum);
                percent2=(float)((ans2*100)/sum);
                percent3=(float)((ans3*100)/sum);
                percent4=(float)((ans4*100)/sum);

            }
            else
            {
                percent1=0;
                percent2=0;
                percent3=0;
                percent4=0;
            }


            ArrayList<BarEntry> barEntries=new ArrayList<>();

            ArrayList<String> theDates=new ArrayList<>();
            if ("".equals(listItem.getChoice3()))
            {
                barEntries.add(new BarEntry(percent1,0));
                barEntries.add(new BarEntry(percent2,1));

                theDates.add(listItem.getChoice1());
                theDates.add(listItem.getChoice2());

            }
            else
            {
                if ("".equals(listItem.getChoice4())){
                    barEntries.add(new BarEntry(percent1,0));
                    barEntries.add(new BarEntry(percent2,1));
                    barEntries.add(new BarEntry(percent3,2));
                    theDates.add(listItem.getChoice1());
                    theDates.add(listItem.getChoice2());
                    theDates.add(listItem.getChoice3());

                }
                else
                {
                    barEntries.add(new BarEntry(percent1,0));
                    barEntries.add(new BarEntry(percent2,1));
                    barEntries.add(new BarEntry(percent3,2));

                    barEntries.add(new BarEntry(percent4,3));
                    theDates.add(listItem.getChoice1());
                    theDates.add(listItem.getChoice2());
                    theDates.add(listItem.getChoice3());
                    theDates.add(listItem.getChoice4());

                }
            }
            BarDataSet barDataSet=new BarDataSet(barEntries,"Percentage of choice");
            barDataSet.setColor(Color.parseColor("#06a96f"));
            BarData theData=new BarData(theDates,barDataSet);
            holder.barchart.setData(theData);
            holder.barchart.setTouchEnabled(true);
            holder.barchart.fitScreen();
            holder.barchart.setDragEnabled(true);
            holder.barchart.setScaleEnabled(true);
            holder.barchart.setVisibleXRangeMaximum(3);

        }




    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView textViewHead;
       public BarChart barchart;
        public LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead=(TextView) itemView.findViewById(R.id.textViewHead);
            barchart=(BarChart) itemView.findViewById(R.id.bargraph);
            ll=(LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}
