package com.ryan.tw2trainer.manager.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.ryan.tw2trainer.manager.DbManager;
import com.ryan.tw2trainer.manager.entity.Goods;

import java.util.ArrayList;

public class GoodsDao {
    public void addGoods(Goods goods)
    {
        mGoods.add(goods);
    }

    public void updateGoods(Goods goods)
    {
        mGoods.set(goods.getId(), goods);

        SQLiteDatabase db = DbManager.getInstance().open();

        ContentValues cv = new ContentValues();
        cv.put("min_price", goods.getMinPrice());
        cv.put("max_price", goods.getMaxPrice());
        cv.put("locked", goods.isLocked());

        db.update("goods", cv, "_id=" + goods.getId(), null);

        db.close();
    }

    public Goods getGoods(int id)
    {
        return mGoods.get(id);
    }

    public int size()
    {
        return mGoods.size();
    }

    private ArrayList<Goods> mGoods = new ArrayList<>();
}
