package com.techclutch.finassist.dummy;

import com.microblink.results.date.Date;

/**
 * Created by ArifH_AW17 on 7/30/2017.
 */

public class UserDataTron {

    public String mFullName = "";
    public String mNRICNumber = "";
    public String mAddress = "";
    public String mSex = "";
    public String mTitle = "";
    public Date mBirthDate;

    private static UserDataTron _instance = new UserDataTron();

    public static UserDataTron Get() {return _instance;}

    private String MakeOneliner(String str) {return str.replaceAll("[\r\n]+", " ");}

    public String GetName(){return MakeOneliner(mFullName);}
    public String GetNRIC(){return MakeOneliner(mNRICNumber);}
    public String GetAddress(){return MakeOneliner(mAddress);}
    public String GetSex(){return MakeOneliner(mSex);}
    public String GetTitle(){return MakeOneliner(mTitle);}
}
