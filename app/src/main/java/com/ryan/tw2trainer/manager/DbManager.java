package com.ryan.tw2trainer.manager;

import android.annotation.SuppressLint;

import com.ryan.tw2trainer.manager.dao.GoodsDao;
import com.ryan.tw2trainer.manager.entity.Goods;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbManager extends SQLiteOpenHelper {
    private static DbManager dbInstance;

    public static void init(Context context) {
        dbInstance = new DbManager(context);
    }

    public static DbManager getInstance() {
        return dbInstance;
    }

    public GoodsDao goodsDao() {
        return mGoodsDao;
    }

    private static String DB_PATH;
    private static String DB_NAME = "tw2.db";
    private static final int SCHEMA = 1;
    private Context myContext;

    @SuppressLint("Range")
    DbManager(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;

        create_db();

        SQLiteDatabase db = open();

        Cursor goodsCursor = db.rawQuery("SELECT * FROM goods", null);
        for (goodsCursor.moveToFirst(); !goodsCursor.isAfterLast(); goodsCursor.moveToNext()) {
            mGoodsDao.addGoods(new Goods(
                    goodsCursor.getInt(goodsCursor.getColumnIndex("_id")),
                    goodsCursor.getString(goodsCursor.getColumnIndex("name")),
                    goodsCursor.getInt(goodsCursor.getColumnIndex("min_price")),
                    goodsCursor.getInt(goodsCursor.getColumnIndex("max_price")),
                    goodsCursor.getInt(goodsCursor.getColumnIndex("locked")) != 0
            ));
        }
        goodsCursor.close();

        close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) { }

    void create_db(){
        File file = new File(DB_PATH);
        if (!file.exists()) {
            try(InputStream myInput = myContext.getAssets().open(DB_NAME);
                OutputStream myOutput = new FileOutputStream(DB_PATH)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DbManager", ex.getMessage());
            }
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private GoodsDao mGoodsDao = new GoodsDao();
}

