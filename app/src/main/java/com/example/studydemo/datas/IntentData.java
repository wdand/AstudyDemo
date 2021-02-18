package com.example.studydemo.datas;

import android.os.Parcel;
import android.os.Parcelable;

public class IntentData implements Parcelable {

    private String name;
    private int age;

    public IntentData() {}

    protected IntentData(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }


    public static final Creator<IntentData> CREATOR = new Creator<IntentData>() {
        @Override
        public IntentData createFromParcel(Parcel in) {
            IntentData IntentData = new IntentData();
            IntentData.name = in.readString();
            IntentData.age = in.readInt();
            return IntentData;
        }

        @Override
        public IntentData[] newArray(int size) {
            return new IntentData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
