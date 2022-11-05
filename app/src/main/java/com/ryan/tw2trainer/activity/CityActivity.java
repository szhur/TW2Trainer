package com.ryan.tw2trainer.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.tw2trainer.R;
import com.ryan.tw2trainer.manager.DbManager;
import com.ryan.tw2trainer.manager.dao.CityDao;
import com.ryan.tw2trainer.manager.entity.City;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        CityDao dao = DbManager.getInstance().cityDao();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.backBtn).setOnClickListener(view -> goToMain());

        LinearLayout citiesLayout = findViewById(R.id.citiesLayout);
        for (int i = 0; i < dao.size(); ++i)
        {
            City city = dao.getCity(i);

            LinearLayout cityRow = (LinearLayout) getLayoutInflater().inflate(
                    R.layout.city_row, null, false
            );

            TextView nameTxt = cityRow.findViewById(R.id.nameTxt);
            nameTxt.setText(city.getName());

            CheckBox checkBox = cityRow.findViewById(R.id.checkBox);
            checkBox.setChecked(city.isPresent());
            checkBox.setOnClickListener(view -> {
                city.setPresent(checkBox.isChecked());
                dao.updateCity(city);
            });

            citiesLayout.addView(cityRow);
        }
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}