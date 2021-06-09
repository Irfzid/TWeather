package com.zidney.tweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Forecastday implements Parcelable {

    private List<Hour> hour;
    private String date;
    private Day day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Hour> getHour() {
        return hour;
    }

    public void setHour(List<Hour> hour) {
        this.hour = hour;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.hour);
        dest.writeString(this.date);
        dest.writeParcelable((Parcelable) this.day, flags);
    }

    public void readFromParcel(Parcel source) {
        this.hour = source.createTypedArrayList(Hour.CREATOR);
        this.date = source.readString();
        this.day = source.readParcelable(Day.class.getClassLoader());
    }

    public Forecastday() {
    }

    protected Forecastday(Parcel in) {
        this.hour = in.createTypedArrayList(Hour.CREATOR);
        this.date = in.readString();
        this.day = in.readParcelable(Day.class.getClassLoader());
    }

    public static final Parcelable.Creator<Forecastday> CREATOR = new Parcelable.Creator<Forecastday>() {
        @Override
        public Forecastday createFromParcel(Parcel source) {
            return new Forecastday(source);
        }

        @Override
        public Forecastday[] newArray(int size) {
            return new Forecastday[size];
        }
    };
}
