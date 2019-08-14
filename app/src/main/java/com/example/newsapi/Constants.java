package com.example.newsapi;

import com.example.newsapi.Model.CustomSpinnerItem;

import java.util.ArrayList;

public class Constants {

    public static final String API_KEY = "ef317acea1a94f8887320ae1c48d9ccf";


    public static ArrayList<CustomSpinnerItem> categoryList;
    public static ArrayList<CustomSpinnerItem> getCategoryList() {
        categoryList = new ArrayList<>();
        categoryList.add(new CustomSpinnerItem("All"));
        categoryList.add(new CustomSpinnerItem("business"));
        categoryList.add(new CustomSpinnerItem("entertainment"));
        categoryList.add(new CustomSpinnerItem("general"));
        categoryList.add(new CustomSpinnerItem("health"));
        categoryList.add(new CustomSpinnerItem("science"));
        categoryList.add(new CustomSpinnerItem("sports"));
        categoryList.add(new CustomSpinnerItem("technology"));
        return categoryList;
    }

    public static ArrayList<CustomSpinnerItem> clanguageList;
    public static ArrayList<CustomSpinnerItem> getLanguageList() {
        clanguageList = new ArrayList<>();
        clanguageList.add(new CustomSpinnerItem("All"));
        clanguageList.add(new CustomSpinnerItem("ar"));
        clanguageList.add(new CustomSpinnerItem("en"));
        clanguageList.add(new CustomSpinnerItem("fr"));
        return clanguageList;
    }

    public static ArrayList<CustomSpinnerItem> countryList;
    public static ArrayList<CustomSpinnerItem> getCountryList() {
        countryList = new ArrayList<>();
        countryList.add(new CustomSpinnerItem("All"));
        countryList.add(new CustomSpinnerItem("eg"));
        countryList.add(new CustomSpinnerItem("tr"));
        countryList.add(new CustomSpinnerItem("us"));
        return countryList;
    }

}
