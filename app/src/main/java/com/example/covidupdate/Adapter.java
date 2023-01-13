package com.example.covidupdate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    int m=1;
    Context context;
    List<ModelClass> countrylist;

    public Adapter(Context context, List<ModelClass> countrylist) {
        this.context = context;
        this.countrylist=countrylist;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.itemlayout,null ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        ModelClass modelClass = countrylist.get(position);
        if(m==1)
        {
            holder.itemcase.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getTotalCases())));
        }
        else if(m==2)
        {
            holder.itemcase.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getTotalRecovered())));
        }
        else if(m==3)
        {
            holder.itemcase.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getTotalDeaths())));
        }
        else
        {
            holder.itemcase.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getActiveCases())));
        }

        holder.country.setText(modelClass.getCountry());
    }

    @Override
    public int getItemCount() {
        return countrylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemcase, country;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemcase =itemView.findViewById(R.id.countrycase);
            country = itemView.findViewById(R.id.countryname);
        }
    }


    public  void filter(String charText)
    {
              if(charText.equals("cases"))
              {
                  m=1;
              }
              else if(charText.equals("recovered"))
              {
                  m=2;
              }
              else if(charText.equals("deaths"))
              {
                  m=3;
              }
              else
              {
                  m=4;
              }
    }
}
