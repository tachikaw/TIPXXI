package com.bpaulo.tipxxi.util;

import java.util.ArrayList;

public class Submenu {

    private String mName;
    private ArrayList<SubmenuItem> mArrayListSubmenuItem;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ArrayList<SubmenuItem> getArrayListSubmenuItem() {
        return mArrayListSubmenuItem;
    }

    public void setArrayListSubmenuItem(ArrayList<SubmenuItem> arrayListSubmenuItem) {
        mArrayListSubmenuItem = arrayListSubmenuItem;
    }

}
