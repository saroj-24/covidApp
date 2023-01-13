package com.example.covidupdate;

public class ModelClass {

    public  String Country;
    public  String TotalCases;
    public String NewCases;
    public  String TotalDeaths;
    public  String NewDeaths;
    public  String TotalRecovered;
    public String NewRecovered;
    public  String ActiveCases;

    public ModelClass(String country, String totalCases, String newCases, String totalDeaths, String newDeaths,
                      String totalRecovered, String newRecovered, String activeCases) {
        Country = country;
        TotalCases = totalCases;
        NewCases = newCases;
        TotalDeaths = totalDeaths;
        NewDeaths = newDeaths;
        TotalRecovered = totalRecovered;
        NewRecovered = newRecovered;
        ActiveCases = activeCases;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getTotalCases() {
        return TotalCases;
    }

    public void setTotalCases(String totalCases) {
        TotalCases = totalCases;
    }

    public String getNewCases() {
        return NewCases;
    }

    public void setNewCases(String newCases) {
        NewCases = newCases;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public String getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        NewDeaths = newDeaths;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public String getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(String newRecovered) {
        NewRecovered = newRecovered;
    }

    public String getActiveCases() {
        return ActiveCases;
    }

    public void setActiveCases(String activeCases) {
        ActiveCases = activeCases;
    }


}
