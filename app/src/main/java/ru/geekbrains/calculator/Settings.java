package ru.geekbrains.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class Settings implements Parcelable {
    private Boolean darkThemeOn;

    public Settings() {
        this.darkThemeOn = false;
    }

    protected Settings(Parcel in) {
        byte tmpDarkThemeOn = in.readByte();
        darkThemeOn = tmpDarkThemeOn == 0 ? null : tmpDarkThemeOn == 1;
    }

    public static final Creator<Settings> CREATOR = new Creator<Settings>() {
        @Override
        public Settings createFromParcel(Parcel in) {
            return new Settings(in);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };

    public Boolean getDarkThemeOn() {
        return darkThemeOn;
    }

    public void setDarkThemeOn(Boolean darkThemeOn) {
        this.darkThemeOn = darkThemeOn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (darkThemeOn == null ? 0 : darkThemeOn ? 1 : 2));
    }
}
