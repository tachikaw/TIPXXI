package com.bpaulo.tipxxi.util;

import android.os.Parcel;
import android.os.Parcelable;

// TODO: Creating Android/Java abstractions over Navigation related code (to encapsulate). Ex: Navigation Class & methods.

// Implementing Parcelable.
public class ForwardButton implements Parcelable {
// public class Button {

// Not implementing Parcelable causes error!
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: FATAL EXCEPTION: main
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime: java.lang.RuntimeException: Parcel: unable to marshal value com.bpaulo.comunicaxxilight.util.Button@2c99e040
// ...
// 11-13 09:28:15.675 11818-11818/com.bpaulo.comunicaxxilight E/AndroidRuntime:     at com.bpaulo.comunicaxxilight.util.Forward.writeToParcel(Forward.java:116)
// ...

    private String mId;
    private String mAction;
    private String label;
    private String mValidationMethod;
    private String mValidationMethodClassName;
    // private String mValidationMethodParameter;
    // private String mValidationMethodParameterType;

    public ForwardButton() {
    }

    public ForwardButton(Parcel in) {

        String[] stringData = new String[5];
        // String[] stringData = new String[7];

        in.readStringArray(stringData);

        this.mId = stringData[0];
        this.mAction = stringData[1];
        this.label = stringData[2];
        this.mValidationMethod = stringData[3];
        this.mValidationMethodClassName = stringData[4];
        // this.mValidationMethodParameter = stringData[5];
        // this.mValidationMethodParameterType = stringData[6];

    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValidationMethod() {
        return mValidationMethod;
    }

    public void setValidationMethod(String validationMethod) {
        mValidationMethod = validationMethod;
    }

    public String getValidationMethodClassName() {
        return mValidationMethodClassName;
    }

    public void setValidationMethodClassName(String validationMethodClassName) {
        mValidationMethodClassName = validationMethodClassName;
    }

//    public String getValidationMethodParameter() {
//        return mValidationMethodParameter;
//    }
//
//    public void setValidationMethodParameter(String validationMethodParameter) {
//        mValidationMethodParameter = validationMethodParameter;
//    }
//
//    public String getValidationMethodParameterType() {
//        return mValidationMethodParameterType;
//    }
//
//    public void setValidationMethodParameterType(String validationMethodParameterType) {
//        mValidationMethodParameterType = validationMethodParameterType;
//    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {

        out.writeStringArray(new String[]{this.mId,
                this.mAction,
                this.label,
                this.mValidationMethod,
                this.mValidationMethodClassName});
//        out.writeStringArray(new String[]{this.mId,
//                this.mAction,
//                this.label,
//                this.mValidationMethod,
//                this.mValidationMethodClassName,
//                this.mValidationMethodParameter,
//                this.mValidationMethodParameterType});
//        out.writeStringArray(new String[]{this.mId,
//                this.mAction,
//                this.label});

    }

    public static final Creator<ForwardButton> CREATOR = new Creator<ForwardButton>() {

        public ForwardButton createFromParcel(Parcel in) {
            return new ForwardButton(in);
        }

        public ForwardButton[] newArray(int size) {
            return new ForwardButton[size];
        }

    };

}
