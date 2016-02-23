package com.bpaulo.tipxxi.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

// TODO: Creating Android/Java abstractions over Navigation related code (to encapsulate). Ex: Navigation Class & methods.

// Implementing Parcelable.
public class Forward implements Parcelable {
// public class Forward {

// Not implementing Parcelable causes error!
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: FATAL EXCEPTION: main
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: java.lang.RuntimeException: Parcel: unable to marshal value com.bpaulo.comunicaxxilight.util.Button@2c99e040
// ...
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime:     at com.bpaulo.comunicaxxilight.util.Forward.writeToParcel(Forward.java:116)
// ...

    private String mBreadcrumbText;
    private String mClassName;
    private String mTrigger;
//    private String mValidationMethod;
//    private String mValidationMethodClassName;
//    private String mValidationMethodParameters;
    private ForwardEntity mForwardEntity;
    private ArrayList<ForwardButton> mArrayListForwardButton;

    public Forward() {
    }

    private Forward(Parcel in) {

        String[] stringData = new String[3];
//        String[] stringData = new String[6];

        in.readStringArray(stringData);

        this.mBreadcrumbText = stringData[0];
        this.mClassName = stringData[1];
        this.mTrigger = stringData[2];
//        this.mValidationMethod = stringData[3];
//        this.mValidationMethodClassName = stringData[4];
//        this.mValidationMethodParameters = stringData[5];

        this.mForwardEntity = in.readParcelable(ForwardEntity.class.getClassLoader());

        this.mArrayListForwardButton = in.readArrayList(ForwardButton.class.getClassLoader());

    }

    public String getBreadcrumbText() {
        return mBreadcrumbText;
    }

    public void setBreadcrumbText(String breadcrumbText) {
        mBreadcrumbText = breadcrumbText;
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
    }

    public String getTrigger() {
        return mTrigger;
    }

    public void setTrigger(String trigger) {
        mTrigger = trigger;
    }

//    public String getValidationMethod() {
//        return mValidationMethod;
//    }
//
//    public void setValidationMethod(String validationMethod) {
//        mValidationMethod = validationMethod;
//    }
//
//    public String getValidationMethodClassName() {
//        return mValidationMethodClassName;
//    }
//
//    public void setValidationMethodClassName(String validationMethodClassName) {
//        mValidationMethodClassName = validationMethodClassName;
//    }
//
//    public String getValidationMethodParameter() {
//        return mValidationMethodParameter;
//    }
//
//    public void setValidationMethodParameter(String validationMethodParameter) {
//        mValidationMethodParameter = validationMethodParameter;
//    }

    public ForwardEntity getForwardEntity() {
        return mForwardEntity;
    }

    public void setForwardEntity(ForwardEntity forwardEntity) {
        mForwardEntity = forwardEntity;
    }

    public ArrayList<ForwardButton> getArrayListForwardButton() {
        return mArrayListForwardButton;
    }

    public void setArrayListForwardButton(ArrayList<ForwardButton> arrayListForwardButton) {
        mArrayListForwardButton = arrayListForwardButton;
    }

    public int describeContents() { // OK.
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {

        out.writeStringArray(new String[]{this.mBreadcrumbText,
                this.mClassName,
                this.mTrigger});
//        out.writeStringArray(new String[]{this.mBreadcrumbText,
//                this.mClassName,
//                this.mTrigger,
//                this.mValidationMethod,
//                this.mValidationMethodClassName,
//                this.mValidationMethodParameters});

        out.writeParcelable(this.mForwardEntity,flags);

        out.writeList(this.mArrayListForwardButton);

    }

    public static final Creator<Forward> CREATOR = new Creator<Forward>() {

        public Forward createFromParcel(Parcel in) {
            return new Forward(in);
        }

        public Forward[] newArray(int size) {
            return new Forward[size];
        }

    };

}
