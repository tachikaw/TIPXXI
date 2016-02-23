package com.bpaulo.tipxxi.util;

import android.os.Parcel;
import android.os.Parcelable;

// TODO: Creating Android/Java abstractions over Navigation related code (to encapsulate). Ex: Navigation Class & methods.

// Implementing Parcelable.
public class EntityField implements Parcelable {
// public class Field {

// Not implementing Parcelable causes error!
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: FATAL EXCEPTION: main
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: java.lang.RuntimeException: Parcel: unable to marshal value com.bpaulo.comunicaxxilight.util.Button@2c99e040
// ...
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime:     at com.bpaulo.comunicaxxilight.util.Forward.writeToParcel(Forward.java:116)
// ...

    private String mName;

    public EntityField() {
    }

    public EntityField(Parcel in) {

        this.mName = in.readString();

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {

        out.writeString(this.mName);

    }

    public static final Creator<EntityField> CREATOR = new Creator<EntityField>() {

        public EntityField createFromParcel(Parcel in) {
            return new EntityField(in);
        }

        public EntityField[] newArray(int size) {
            return new EntityField[size];
        }

    };

}
