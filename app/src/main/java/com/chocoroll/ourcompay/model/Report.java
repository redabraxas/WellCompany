package com.chocoroll.ourcompay.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RA on 2015-05-17.
 */
public class Report  implements Parcelable {
    String num;
    String companyNum;

    String id;
    String purpose;
    String content;
    String picture;

    public Report(Parcel in) {
        readFromParcel(in);
    }
    public Report(String num, String companyNum, String id, String purpose, String content, String picture){
        this.num = num;
        this.companyNum = companyNum;
        this.id= id;
        this.purpose = purpose;
        this.content =content;
        this.picture =picture;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(num);
        parcel.writeString(companyNum);

        parcel.writeString(id);
        parcel.writeString(purpose);
        parcel.writeString(content);
        parcel.writeString(picture);

    }

    private void readFromParcel(Parcel in){
        num = in.readString();
        companyNum = in.readString();

        id = in.readString();
        purpose = in.readString();
        content = in.readString();
        picture = in.readString();


    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
}
