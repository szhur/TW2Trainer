package com.ryan.tw2trainer.manager.entity;

public class City {

    public City(
        int id,
        String name,
        boolean present
    )
    {
        mId = id;
        mName = name;
        mPresent = present;
    }

    public int getId()
    {
        return mId;
    }

    public String getName()
    {
        return mName;
    }

    public void setPresent(boolean present)
    {
        mPresent = present;
    }

    public boolean isPresent()
    {
        return mPresent;
    }

    private int mId;

    private String mName;

    private boolean mPresent;
}
