package com.zidney.tweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Condition implements Parcelable {


    private String text;
    private String icon;
    private String code;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.icon);
        dest.writeString(this.code);
    }

    public void readFromParcel(Parcel source) {
        this.text = source.readString();
        this.icon = source.readString();
        this.code = source.readString();
    }

    public Condition() {
    }

    protected Condition(Parcel in) {
        this.text = in.readString();
        this.icon = in.readString();
        this.code = in.readString();
    }

    public static final Parcelable.Creator<Condition> CREATOR = new Parcelable.Creator<Condition>() {
        @Override
        public Condition createFromParcel(Parcel source) {
            return new Condition(source);
        }

        @Override
        public Condition[] newArray(int size) {
            return new Condition[size];
        }
    };
}
