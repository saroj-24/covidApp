package com.example.covidupdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CountryCodePicker countryCodePicker;
    TextView mtodyatotal, mtotal,mactive,mtodayactive,mrecovered,mtotayrecovered,mdeaths,mtodaydeaths;
    String country;
    TextView mfilter;
    Spinner spinner;
    String[] type = {"cases","deaths","recovered","active"};
    private ArrayList<ModelClass> modelClassList;
    private ArrayList<ModelClass> modelClassList2;
    PieChart mpieChart;
    private RecyclerView recyclerView;
    com.example.covidupdate.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        countryCodePicker =findViewById(R.id.cpp);
        mtodayactive =findViewById(R.id.todayactive);
        mactive = findViewById(R.id.activecase);
        mtotal = findViewById(R.id.totalcase);
        mtodyatotal=findViewById(R.id.todaytotal);
        mrecovered=findViewById(R.id.totalrecovered);
        mtotayrecovered=findViewById(R.id.todayrecovered);
        mdeaths = findViewById(R.id.totaldeath);
        mtodaydeaths = findViewById(R.id.todaydeath);
        mpieChart = findViewById(R.id.piechart);
        spinner = findViewById(R.id.spinner);
        mfilter = findViewById(R.id.filter);
        recyclerView = findViewById(R.id.recycleview);

        modelClassList=new ArrayList<>();
        modelClassList2 =new ArrayList<>();

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelected(true);




       ApiUtilities.getApiInterfce().getcountryclass().enqueue(new Callback<List<ModelClass>>() {
           @Override
           public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
               modelClassList2.addAll(response.body());
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<List<ModelClass>> call, Throwable t) {

           }
       });

        adapter = new Adapter(getApplicationContext(),modelClassList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        countryCodePicker.setAutoDetectedCountry(true);
        country=countryCodePicker.getSelectedCountryName();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = countryCodePicker.getSelectedCountryName();
                fetchdata();
            }
        });

         fetchdata();



    }

    private void fetchdata() {

        ApiUtilities.getApiInterfce().getcountryclass().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(@NonNull Call<List<ModelClass>> call, @NonNull Response<List<ModelClass>> response) {
                modelClassList.addAll(response.body());

                for(int i = 0;i<modelClassList.size();i++)
                {

                        if(modelClassList.get(i).getCountry().equals(country))
                        {
                            mactive.setText((modelClassList.get(i).getActiveCases()));
                            mdeaths.setText((modelClassList.get(i).getTotalDeaths()));
                            mtodaydeaths.setText((modelClassList.get(i).getNewCases()));
                            mrecovered.setText((modelClassList.get(i).getTotalRecovered()));
                            mtodayactive.setText((modelClassList.get(i).getNewCases()));
                            mtotayrecovered.setText((modelClassList.get(i).getNewRecovered()));
                            mtotal.setText((modelClassList.get(i).getTotalCases()));
                            mtodyatotal.setText((modelClassList.get(i).getNewCases()));

                            int total,active,recover,death;

                            active=Integer.parseInt(modelClassList.get(i).getActiveCases());
                            total=Integer.parseInt(modelClassList.get(i).getTotalCases());
                            recover=Integer.parseInt(modelClassList.get(i).getTotalRecovered());
                            death=Integer.parseInt(modelClassList.get(i).getTotalDeaths());
                            updateGraph(active,total,recover,death);

                        }


                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                System.out.println("API is not working "+t.getMessage());
            }

        });
    }

    private void updateGraph(int active, int total, int recover, int death) {
        mpieChart.clearChart();
        mpieChart.addPieSlice(new PieModel("Confirm",total, Color.parseColor("#FF8B13")));
        mpieChart.addPieSlice(new PieModel("Active",active,Color.parseColor("#03C988")));
        mpieChart.addPieSlice(new PieModel("Recovered",recover,Color.parseColor("#0081B4")));
        mpieChart.addPieSlice(new PieModel("Deaths",death,Color.parseColor("#CD0404")));
        mpieChart.startAnimation();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           String item =type[position];
           mfilter.setText(item);
           adapter.filter(item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}