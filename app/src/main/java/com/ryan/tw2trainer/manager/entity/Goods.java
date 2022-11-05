package com.ryan.tw2trainer.manager.entity;

public class Goods {

    public Goods(
        int id,
        String name,
        int minPrice,
        int maxPrice,
        boolean isLocked
    )
    {
        mId = id;
        mName = name;
        mMinPrice = minPrice;
        mMaxPrice = maxPrice;
        mIsLocked = isLocked;
    }

    public int getId() { return mId; }

    public String getName()
    {
        return mName;
    }

    public void setMinPrice(int minPrice)
    {
        mMinPrice = minPrice;
    }

    public int getMinPrice()
    {
        return mMinPrice;
    }

    public void setMaxPrice(int maxPrice)
    {
        mMaxPrice = maxPrice;
    }

    public int getMaxPrice()
    {
        return mMaxPrice;
    }

    public void setIsLocked(boolean isLocked)
    {
        mIsLocked = isLocked;
    }

    public boolean isLocked()
    {
        return mIsLocked;
    }

    private int mId;

    private String mName;

    private int mMinPrice;
    private int mMaxPrice;

    private boolean mIsLocked;
}
