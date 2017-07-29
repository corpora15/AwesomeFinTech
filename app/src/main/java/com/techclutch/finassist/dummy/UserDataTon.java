package com.techclutch.finassist.dummy;

import com.microblink.results.date.Date;

/**
 * Created by ArifH_AW17 on 7/30/2017.
 */

public class UserDataTon {

    public String mFullName = "";
    public String mNRICNumber = "";
    public String mAddress = "";
    public String mSex = "";
    public String mTitle = "";
    public Date mBirthDate;

    private static UserDataTon _instance = new UserDataTon();

    public static UserDataTon Get() {return _instance;}
}
