package com.zidney.tweather.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Hour implements Parcelable {

    private String time;
    private double temp_c;
    private int is_day;
    private Condition condition;
    private double wind_kph;
    private double pressure_mb;
    private double precip_mm;

    public String getTime() {
        return time;
    }

    public void setTime(String date) {
        this.time = time;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public int getIs_day() {
        return is_day;
    }

    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }

    public double getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(double pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public double getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(double precip_mm) {
        this.precip_mm = precip_mm;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeDouble(this.temp_c);
        dest.writeInt(this.is_day);
        dest.writeParcelable((Parcelable) this.condition, flags);
        dest.writeDouble(this.wind_kph);
        dest.writeDouble(this.pressure_mb);
        dest.writeDouble(this.precip_mm);
    }

    public void readFromParcel(Parcel source) {
        this.time = source.readString();
        this.temp_c = source.readDouble();
        this.is_day = source.readInt();
        this.condition = source.readParcelable(Condition.class.getClassLoader());
        this.wind_kph = source.readDouble();
        this.pressure_mb = source.readDouble();
        this.precip_mm = source.readDouble();
    }

    public Hour() {
    }

    protected Hour(Parcel in) {
        this.time = in.readString();
        this.temp_c = in.readDouble();
        this.is_day = in.readInt();
        this.condition = in.readParcelable(Condition.class.getClassLoader());
        this.wind_kph = in.readDouble();
        this.pressure_mb = in.readDouble();
        this.precip_mm = in.readDouble();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel source) {
            return new Hour(source);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };
}
