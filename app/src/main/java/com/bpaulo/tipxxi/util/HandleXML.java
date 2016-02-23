package com.bpaulo.tipxxi.util;

import android.content.Context;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.menuprincipal.MenuPrincipalActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class HandleXML {

    private static final String TAG = "HandleXML";

    private XmlPullParser mXMLPullParser;

    public Forward getScreenForward(String pScreen, String pTrigger) {
        // public SparseArray<String> getScreenForward(String pScreen, String pTrigger) {

        // Key (in SparseArray) = Content.

        // screen.forward.breadcrumbText = "Login"
        // screen.forward.path = "DynamicEditSectionActivity"
        // screen.forward.trigger = "R.drawable.button_function_4"

        // Entities:

        // screen.forward.entity.name = "Parametro"

        // screen.forward.entity.field.name = "adminPassword"

        // Buttons:

        // screen.forward.button.action = "DynamicGridSectionFragment"
        // screen.forward.button.id = "R.id.mButtonLogin"

        // screen.forward.button.action = "ArranqueActivity"
        // screen.forward.button.id = "R.id.mButtonCancel"

        boolean hasScreen = false;
        boolean hasScreenForward = false;
        boolean hasScreenForwardEntity = false;

        int buttonKey = 0;

        // Forward class approach (begin).

        Forward forward = null;
        ForwardEntity forwardEntity = null;
        EntityField entityField = null;
        ArrayList<EntityField> arrayListEntityField = new ArrayList();
        ForwardButton forwardButton = null;
        ArrayList<ForwardButton> arrayListForwardButton = new ArrayList();

        // Forward class approach (end).

        // TODO: Uses ArrayList
        // SparseArray<String> sparseArrayString = new SparseArray<String>();

        // TODO: Adapt from comunicaxxilight context to tipxxi context.
        mXMLPullParser = MenuPrincipalActivity.getAppApplicationContext().getResources().getXml(R.xml.navigation);
        // mXMLPullParser = ArranqueActivity.getAppApplicationContext().getResources().getXml(R.xml.navigation);

        try {

            while (mXMLPullParser.next() != XmlPullParser.END_DOCUMENT) {

                if (mXMLPullParser.getEventType() == XmlPullParser.START_TAG) {

                    // Debugging.
//                    Log.i(TAG, "START_TAG: <" + mXMLPullParser.getName() + "... >");
//                    if (mXMLPullParser.getAttributeValue(null, "name") != null) {
//                        Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"name\"); = " + mXMLPullParser.getAttributeValue(null, "name"));
//                    }

                    if (mXMLPullParser.getName().equals("screen")) {

                        if (mXMLPullParser.getAttributeValue(null, "name").equals(pScreen)) {

                            hasScreen = true;

                        }

                    }
                    else if (hasScreen) {

                        if (mXMLPullParser.getName().equals("forward")) {

                            if (mXMLPullParser.getAttributeValue(null, "trigger").equals(pTrigger)) {

                                forward = new Forward();

                                // Debugging.
//                                Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"trigger\") = " + mXMLPullParser.getAttributeValue(null, "trigger"));

                                forward.setTrigger(mXMLPullParser.getAttributeValue(null, "trigger"));

                                if (mXMLPullParser.getAttributeValue(null, "breadcrumbText") != null) {

                                    // Debugging.
//                                    Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"breadcrumbText\") = " + mXMLPullParser.getAttributeValue(null, "breadcrumbText"));

                                    forward.setBreadcrumbText(mXMLPullParser.getAttributeValue(null, "breadcrumbText"));

                                }

                                if (mXMLPullParser.getAttributeValue(null, "className") != null) {

                                    // Debugging.
//                                    Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"className\") = " + mXMLPullParser.getAttributeValue(null, "className"));

                                    forward.setClassName(mXMLPullParser.getAttributeValue(null, "className"));

                                }

                                hasScreenForward = true;

                            }

                        }
                        else if (hasScreenForward) {

                            if (mXMLPullParser.getName().equals("forwardEntity")) {

                                forwardEntity = new ForwardEntity();

                                // Debugging.
//                                Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"name\") = " + mXMLPullParser.getAttributeValue(null, "name"));

                                forwardEntity.setName(mXMLPullParser.getAttributeValue(null, "name"));

                                hasScreenForwardEntity = true;

                            }
                            else if (mXMLPullParser.getName().equals("forwardButton")) {

                                forwardButton = new ForwardButton();

                                // Debugging.
//                                Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"id\") = " + mXMLPullParser.getAttributeValue(null, "id"));

                                forwardButton.setId(mXMLPullParser.getAttributeValue(null, "id"));

                                // Debugging.
//                                Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"action\") = " + mXMLPullParser.getAttributeValue(null, "action"));

                                forwardButton.setAction(mXMLPullParser.getAttributeValue(null, "action"));

                                // Debugging.
//                                Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"label\") = " + mXMLPullParser.getAttributeValue(null, "label"));

                                forwardButton.setLabel(mXMLPullParser.getAttributeValue(null, "label"));

                                if (mXMLPullParser.getAttributeValue(null, "validationMethod") != null) {

                                    // Debugging.
//                                    Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"validationMethod\") = " + mXMLPullParser.getAttributeValue(null, "validationMethod"));

                                    forwardButton.setValidationMethod(mXMLPullParser.getAttributeValue(null, "validationMethod"));

                                }

                                if (mXMLPullParser.getAttributeValue(null, "validationMethodClassName") != null) {

                                    // Debugging.
//                                    Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"validationMethodClassName\") = " + mXMLPullParser.getAttributeValue(null, "validationMethodClassName"));

                                    forwardButton.setValidationMethodClassName(mXMLPullParser.getAttributeValue(null, "validationMethodClassName"));

                                }

                                arrayListForwardButton.add(forwardButton);

                            }

                            if (hasScreenForwardEntity) {

                                if (mXMLPullParser.getName().equals("entityField")) {

                                    entityField = new EntityField();

                                    // Debugging.
//                                    Log.i(TAG, "mXMLPullParser.getAttributeValue(null, \"name\") = " + mXMLPullParser.getAttributeValue(null, "name"));

                                    entityField.setName(mXMLPullParser.getAttributeValue(null, "name"));

                                    arrayListEntityField.add(entityField);

                                }

                            }

                        }

                    }

                }
                else if (mXMLPullParser.getEventType() == XmlPullParser.END_TAG) {

                    // Debugging.
//                    Log.i(TAG, "END_TAG: <" + mXMLPullParser.getName() + "... >");

                    if (mXMLPullParser.getName().equals("forward")) {
                        if (hasScreenForward) {
                            break;
                        }
                    }

                }
            }

        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (forward != null) {
            if (forwardEntity != null) {
                if (arrayListEntityField != null) {
                    forwardEntity.setArrayListEntityField(arrayListEntityField);
                }
                forward.setForwardEntity(forwardEntity);
            }
            if (arrayListForwardButton != null) {
                forward.setArrayListForwardButton(arrayListForwardButton);
            }
            return forward;
        }
        else {
            return null;
        }

    }

    public Submenu[] getSubmenus(Context pContext) {
    // public ArrayList<Submenu> getSubmenus(Context pContext) {

        Submenu submenu = null;
        ArrayList<Submenu> arrayListSubmenu = new ArrayList();
        SubmenuItem submenuItem;
        ArrayList<SubmenuItem> arrayListSubmenuItem = new ArrayList();

        mXMLPullParser = pContext.getResources().getXml(R.xml.menus);

        try {

            while (mXMLPullParser.next() != XmlPullParser.END_DOCUMENT) {

                if (mXMLPullParser.getEventType() == XmlPullParser.START_TAG) {

                    if (mXMLPullParser.getName().equals("submenu")) {

                        // Debugging.
                        // Log.i(TAG, "START_TAG: submenu: mXMLPullParser.getAttributeValue(null, \"name\") = " + mXMLPullParser.getAttributeValue(null, "name"));

                        submenu = new Submenu();

                        submenu.setName(mXMLPullParser.getAttributeValue(null, "name"));

                    }
                    else if (mXMLPullParser.getName().equals("item")) {

                        // Debugging.
                        // Log.i(TAG, "START_TAG: item: mXMLPullParser.getAttributeValue(null, \"name\") = " + mXMLPullParser.getAttributeValue(null, "name"));
                        // Log.i(TAG, "START_TAG: item: mXMLPullParser.getAttributeValue(null, \"className\") = " + mXMLPullParser.getAttributeValue(null, "className"));
                        // Log.i(TAG, "START_TAG: item: mXMLPullParser.getAttributeValue(null, \"type\") = " + mXMLPullParser.getAttributeValue(null, "type"));

                        submenuItem = new SubmenuItem();

                        submenuItem.setName(mXMLPullParser.getAttributeValue(null, "name"));
                        submenuItem.setClassName(mXMLPullParser.getAttributeValue(null, "className"));
                        submenuItem.setType(mXMLPullParser.getAttributeValue(null, "type"));

                        arrayListSubmenuItem.add(submenuItem);

                    }

                }
                else if (mXMLPullParser.getEventType() == XmlPullParser.END_TAG) {

                    // Debugging.
                    // Log.i(TAG, "END_TAG: submenu: mXMLPullParser.getName() = " + mXMLPullParser.getName());

                    if (mXMLPullParser.getName().equals("submenu")) {

                        if (!arrayListSubmenuItem.isEmpty()) {

                            submenu.setArrayListSubmenuItem(arrayListSubmenuItem);
                            arrayListSubmenu.add(submenu);

                            arrayListSubmenuItem = new ArrayList();

                        }

                    }

                }

            }

        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return arrayListSubmenu.toArray(new Submenu[arrayListSubmenu.size()]);
        // return arrayListSubmenu;

    }

    public boolean hasScreen(String pScreen) {

        // TODO: Adapt from comunicaxxilight context to tipxxi context.

        mXMLPullParser = MenuPrincipalActivity.getAppApplicationContext().getResources().getXml(R.xml.navigation);
        // mXMLPullParser = ArranqueActivity.getAppApplicationContext().getResources().getXml(R.xml.navigation);

        try {

            while (mXMLPullParser.next() != XmlPullParser.END_DOCUMENT) {

                if (mXMLPullParser.getEventType() == XmlPullParser.START_TAG) {

                    if (mXMLPullParser.getName().equals("screen")) {

                        if (mXMLPullParser.getAttributeValue(null, "name").equals(pScreen)) {

                            return true;
                        }

                    }

                }

            }
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean hasScreenForward(String pScreen, String pTrigger) {

        boolean hasScreen = false;

        // TODO: Adapt from comunicaxxilight context to tipxxi context.
        mXMLPullParser = MenuPrincipalActivity.getAppApplicationContext().getResources().getXml(R.xml.navigation);
        // mXMLPullParser = ArranqueActivity.getAppApplicationContext().getResources().getXml(R.xml.navigation);

        try {

            while (mXMLPullParser.next() != XmlPullParser.END_DOCUMENT) {

                if (mXMLPullParser.getEventType() == XmlPullParser.START_TAG) {

                    if (mXMLPullParser.getName().equals("screen")) {

                        if (mXMLPullParser.getAttributeValue(null, "name").equals(pScreen)) {

                            hasScreen = true;

                        }

                    }
                    else if (hasScreen) {

                        if (mXMLPullParser.getName().equals("forward")) {

                            if (mXMLPullParser.getAttributeValue(null, "trigger").equals(pTrigger)) {

                                return true;

                            }

                        }

                    }

                }

            }
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

}
