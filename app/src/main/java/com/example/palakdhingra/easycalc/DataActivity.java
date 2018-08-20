package com.example.palakdhingra.easycalc;

import java.io.Serializable;

/**
 * Created by PALAK DHINGRA on 8/20/2018.
 */

public class DataActivity implements Serializable{

    private String mDate;
    private String mPayableTo;
    private int mAmount;

    public DataActivity(String mDate, String mPayableTo, int mAmount) {
        this.mDate = mDate;
        this.mPayableTo = mPayableTo;
        this.mAmount = mAmount;
    }
    public DataActivity(String mDate, String mPayableTo) {
        this.mDate = mDate;
        this.mPayableTo = mPayableTo;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmPayableTo() {
        return mPayableTo;
    }

    public void setmPayableTo(String mPayableTo) {
        this.mPayableTo = mPayableTo;
    }

    public int getmAmount() {
        return mAmount;
    }

    public void setmAmount(int mAmount) {
        this.mAmount = mAmount;
    }
}
