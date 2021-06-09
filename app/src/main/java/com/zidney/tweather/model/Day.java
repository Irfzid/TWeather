package com.zidney.tweather.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Day implements Parcelable {

    private double maxtemp_c;
    private double mintemp_c;
    private double avgtemp_c;
    private double maxwind_kph;
    private double totalprecip_mm;
    private int avghumidity;
    private double uv;
    private Condition condition;

    public double getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(double maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public double getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(double mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public double getAvgtemp_c() {
        return avgtemp_c;
    }

    public void setAvgtemp_c(double avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }

    public double getMaxwind_kph() {
        return maxwind_kph;
    }

    public void setMaxwind_kph(double maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public double getTotalprecip_mm() {
        return totalprecip_mm;
    }

    public void setTotalprecip_mm(double totalprecip_mm) {
        this.totalprecip_mm = totalprecip_mm;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(int avghumidity) {
        this.avghumidity = avghumidity;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.maxtemp_c);
        dest.writeDouble(this.mintemp_c);
        dest.writeDouble(this.avgtemp_c);
        dest.writeDouble(this.maxwind_kph);
        dest.writeDouble(this.totalprecip_mm);
        dest.writeInt(this.avghumidity);
        dest.writeDouble(this.uv);
        dest.writeParcelable(this.condition, flags);
    }

    public void readFromParcel(Parcel source) {
        this.maxtemp_c = source.readDouble();
        this.mintemp_c = source.readDouble();
        this.avgtemp_c = source.readDouble();
        this.maxwind_kph = source.readDouble();
        this.totalprecip_mm = source.readDouble();
        this.avghumidity = source.readInt();
        this.uv = source.readDouble();
        this.condition = source.readParcelable(Condition.class.getClassLoader());
    }

    public Day() {
    }

    protected Day(Parcel in) {
        this.maxtemp_c = in.readDouble();
        this.mintemp_c = in.readDouble();
        this.avgtemp_c = in.readDouble();
        this.maxwind_kph = in.readDouble();
        this.totalprecip_mm = in.readDouble();
        this.avghumidity = in.readInt();
        this.uv = in.readDouble();
        this.condition = in.readParcelable(Condition.class.getClassLoader());
    }

    public static final Parcelable.Creator<Day> CREATOR = new Parcelable.Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel source) {
            return new Day(source);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}
