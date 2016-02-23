package com.bpaulo.tipxxi.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

// TODO: Creating Android/Java abstractions over Navigation related code (to encapsulate). Ex: Navigation Class & methods.

// Implementing Parcelable.
public class ForwardEntity implements Parcelable {
// public class Entity {

// Not implementing Parcelable causes error!
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: FATAL EXCEPTION: main
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: java.lang.RuntimeException: Parcel: unable to marshal value com.bpaulo.comunicaxxilight.util.Button@2c99e040
// ...
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime:     at com.bpaulo.comunicaxxilight.util.Forward.writeToParcel(Forward.java:116)
// ...

    private String mName;
    private ArrayList<EntityField> mArrayListEntityField;

    public ForwardEntity() {
    }

    public ForwardEntity(Parcel in) {

        this.mName = in.readString();

        this.mArrayListEntityField = in.readArrayList(EntityField.class.getClassLoader());

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public ArrayList<EntityField> getArrayListEntityField() {
        return mArrayListEntityField;
    }

    public void setArrayListEntityField(ArrayList<EntityField> arrayListField) {
        mArrayListEntityField = arrayListField;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {

        out.writeString(this.mName);

        out.writeList(this.mArrayListEntityField);

    }

    public static final Creator<ForwardEntity> CREATOR = new Creator<ForwardEntity>() {

        public ForwardEntity createFromParcel(Parcel in) {
            return new ForwardEntity(in);
        }

        public ForwardEntity[] newArray(int size) {
            return new ForwardEntity[size];
        }

    };

}
