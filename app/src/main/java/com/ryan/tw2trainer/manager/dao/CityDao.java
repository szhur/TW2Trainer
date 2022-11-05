package com.ryan.tw2trainer.manager.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.ryan.tw2trainer.manager.DbManager;
import com.ryan.tw2trainer.manager.entity.City;

import java.util.ArrayList;

public class CityDao {
    public void addCity(City city)
    {
        mCities.add(city);
    }

    public void updateCity(City city)
    {
        mCities.set(city.getId(), city);

        SQLiteDatabase db = DbManager.getInstance().open();

        ContentValues cv = new ContentValues();
        cv.put("present", city.isPresent());

        db.update("cities", cv, "_id=" + city.getId(), null);

        db.close();
    }

    public ArrayList<City> getPresentCities()
    {
        ArrayList<City> cities = new ArrayList<>();

        for (City city : mCities)
            if (city.isPresent())
                cities.add(city);

        return cities;
    }

    public City getCity(int id)
    {
        return mCities.get(id);
    }

    public int size()
    {
        return mCities.size();
    }

    private ArrayList<City> mCities = new ArrayList<>();
}
