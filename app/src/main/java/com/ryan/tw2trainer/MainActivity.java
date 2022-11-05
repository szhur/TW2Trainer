package com.ryan.tw2trainer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.tw2trainer.manager.DbManager;
import com.ryan.tw2trainer.manager.dao.GoodsDao;
import com.ryan.tw2trainer.manager.entity.Goods;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager.init(this);

        GoodsDao dao = DbManager.getInstance().goodsDao();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.button).setOnClickListener(view -> save());

        LinearLayout goodsLayout = findViewById(R.id.goodsLayout);
        for (int i = 0; i < dao.size(); ++i)
        {
            Goods goods = dao.getGoods(i);

            LinearLayout tradeRow = (LinearLayout) getLayoutInflater().inflate(
                    R.layout.trade_row, null, false
            );

            TextView nameTxt = tradeRow.findViewById(R.id.nameTxt);
            nameTxt.setText(goods.getName());

            EditText minPriceTxt = tradeRow.findViewById(R.id.minPriceTxt);
            minPriceTxt.setText(Integer.toString(goods.getMinPrice()));
            minPriceTxt.setEnabled(!goods.isLocked());

            EditText maxPriceTxt = tradeRow.findViewById(R.id.maxPriceTxt);
            maxPriceTxt.setText(Integer.toString(goods.getMaxPrice()));
            maxPriceTxt.setEnabled(!goods.isLocked());

            CheckBox checkBox = tradeRow.findViewById(R.id.checkBox);
            checkBox.setOnClickListener(view -> {
                minPriceTxt.setEnabled(checkBox.isEnabled());
                maxPriceTxt.setEnabled(checkBox.isEnabled());
            });

            goodsLayout.addView(tradeRow);
            mTradeRows.add(tradeRow);
        }
    }

    private void save()
    {
        GoodsDao dao = DbManager.getInstance().goodsDao();

        for (int i = 0; i < mTradeRows.size(); ++i)
        {
            Goods goods = dao.getGoods(i);
            LinearLayout tradeRow = mTradeRows.get(i);

            EditText minPriceTxt = tradeRow.findViewById(R.id.minPriceTxt);
            goods.setMinPrice(Integer.parseInt(String.valueOf(minPriceTxt.getText())));

            EditText maxPriceTxt = tradeRow.findViewById(R.id.maxPriceTxt);
            goods.setMaxPrice(Integer.parseInt(String.valueOf(maxPriceTxt.getText())));

            CheckBox checkBox = tradeRow.findViewById(R.id.checkBox);
            goods.setIsLocked(!checkBox.isChecked());

            dao.updateGoods(goods);
        }
    }

    private ArrayList<LinearLayout> mTradeRows = new ArrayList<>();
}