package com.bpaulo.tipxxi.menuprincipal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.database.DatabaseHelper;
import com.bpaulo.tipxxi.moedeiro.MoedeiroActivity;
import com.bpaulo.tipxxi.util.HandleXML;
import com.bpaulo.tipxxi.util.Submenu;
import com.bpaulo.tipxxi.util.Utils;
import com.google.android.gms.appindexing.Action;

public class MenuPrincipalActivity extends Activity {

    private static final String TAG = "MenuPrincipalActivity";

    private RelativeLayout mRelativeLayout;
    // private RelativeLayout.LayoutParams mRelativeLayoutParams;

    private RelativeLayout mRelativeLayoutSubmenu;
    private RelativeLayout.LayoutParams mLayoutParamsRelativeLayoutSubmenu;

    private Submenu[] mArraySubmenu;
    private int submenuIndex;
    private int submenuItemIndex[];

    private LinearLayout[] mArrayLinearLayout;
    private RelativeLayout.LayoutParams[] mArrayLayoutParamsLinearLayout;
    // private LinearLayout.LayoutParams[] mArrayLayoutParamsLinearLayout;

    // Submenu RelativeLayout option.
//    private RelativeLayout[] mArrayRelativeLayout;
//    private RelativeLayout.LayoutParams[] mLayoutParamsRelativeLayoutArray;

    private TextView mTextViewSubmenuLabel;
    private TextView mTextViewPreviousElement;
    private Button mButtonUpElement;
    private Button mButtonSelectElement;
    private Button mButtonDownElement;
    private TextView mTextViewNextElement;

    private Button mButtonMoedeiro;
    private RelativeLayout.LayoutParams mLayoutParamsButtonMoedeiro;

    private Intent mIntent;

    private BtnOnClickListenerImpl btnOnClickListenerImpl;
    private BtnOnTouchListenerImpl btnOnTouchListenerImpl;

    private DatabaseHelper mDatabaseHelper;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//  private GoogleApiClient mClient;

    private static Context appApplicationContext;

    public static Context getAppApplicationContext() {
        return appApplicationContext;
    }

    public static void setAppApplicationContext(Context appApplicationContext) {
        MenuPrincipalActivity.appApplicationContext = appApplicationContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setAppApplicationContext(getApplicationContext());

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnOnClickListenerImpl = new BtnOnClickListenerImpl();
        btnOnTouchListenerImpl = new BtnOnTouchListenerImpl();

        HandleXML handleXML = new HandleXML();

        mDatabaseHelper = DatabaseHelper.getInstance(this);

        mArraySubmenu = handleXML.getSubmenus(getApplicationContext());
        // ArrayList<Submenu> arrayListSubmenu = handleXML.getSubmenus(getApplicationContext());
        submenuItemIndex = new int[mArraySubmenu.length];
        // mRelativeLayout definition & settings (setContentView(mRelativeLayout);).

        mRelativeLayout = new RelativeLayout(this);
        // mRelativeLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // Button "Moedeiro" definition & settings.

        mButtonMoedeiro = new Button(this);
        mButtonMoedeiro.setId(R.id.mButtonMoedeiro);
        mButtonMoedeiro.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
        mButtonMoedeiro.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        // mButtonMoedeiro.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        mButtonMoedeiro.setHeight(60);
        mButtonMoedeiro.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
        mButtonMoedeiro.setTextColor(getResources().getColor(R.color.white));
        mButtonMoedeiro.setTypeface(Typeface.DEFAULT_BOLD);
        mButtonMoedeiro.setTextSize(40);
        mButtonMoedeiro.setText(R.string.pesagem);

        mButtonMoedeiro.setOnClickListener(btnOnClickListenerImpl);
        mButtonMoedeiro.setOnTouchListener(btnOnTouchListenerImpl);

        mLayoutParamsButtonMoedeiro = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mLayoutParamsButtonMoedeiro.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        // mLayoutParamsButtonMoedeiro.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonMoedeiro);
        // mLayoutParamsButtonMoedeiro.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        mLayoutParamsButtonMoedeiro.height = 120;
        // mLayoutParamsButtonMoedeiro.height = 150;
        mLayoutParamsButtonMoedeiro.width = 800;
        // mLayoutParamsButtonMoedeiro.setMargins(5, 5, 0, 5);

        // Debugging: Creating submenus (begin).

        // mRelativeLayoutSubmenu definition & settings.

        mRelativeLayoutSubmenu = new RelativeLayout(this);

        mLayoutParamsRelativeLayoutSubmenu = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsRelativeLayoutSubmenu.addRule(RelativeLayout.BELOW, R.id.mButtonMoedeiro);

        mArrayLinearLayout = new LinearLayout[mArraySubmenu.length];
        mArrayLayoutParamsLinearLayout = new RelativeLayout.LayoutParams[mArraySubmenu.length];
//        mArrayLinearLayout = new LinearLayout[arrayListSubmenu.size()];
//        mArrayLayoutParamsLinearLayout = new RelativeLayout.LayoutParams[arrayListSubmenu.size()];
//        // mArrayLayoutParamsLinearLayout = new LinearLayout.LayoutParams[arrayListSubmenu.size()];

        // Submenu RelativeLayout option.
//        mArrayRelativeLayout = new RelativeLayout[arrayListSubmenu.size()];
//        mLayoutParamsRelativeLayoutArray = new RelativeLayout.LayoutParams[arrayListSubmenu.size()];

//        Iterator<Submenu> itArrayListEntityField = arrayListSubmenu.iterator();
//        int i = 0;
//        int submenuId = 0;


        for (int i = 0, submenuId = 0; i < mArraySubmenu.length; i++) {
//        while (itArrayListEntityField.hasNext()) {

            submenuId = ((i + 1) * 10);
            submenuItemIndex[i] = 0;

            // mArrayLinearLayout definition & settings.

            mArrayLinearLayout[i] = new LinearLayout(this);
            // id = 0 don't works!
            mArrayLinearLayout[i].setId(submenuId);
            mArrayLinearLayout[i].setOrientation(LinearLayout.VERTICAL);
            mArrayLinearLayout[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.gray));
            // mArrayLinearLayout[i].setPadding(2, 5, 2, 5);

            mArrayLayoutParamsLinearLayout[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            mArrayLayoutParamsLinearLayout[i].height = 360;
            // mArrayLayoutParamsLinearLayout[i].height = 400;
            mArrayLayoutParamsLinearLayout[i].width = 200;
            if (i == 0) {
                mArrayLayoutParamsLinearLayout[i].addRule(RelativeLayout.BELOW, R.id.mButtonMoedeiro);
                mArrayLayoutParamsLinearLayout[i].setMargins(0, 5, 0, 5);
                // mArrayLayoutParamsLinearLayout[i].setMargins(0, 10, 0, 10);
            }
            else {
                mArrayLayoutParamsLinearLayout[i].addRule(RelativeLayout.RIGHT_OF, i * 10);
                mArrayLayoutParamsLinearLayout[i].setMargins(5, 5, 0, 5);
                // mArrayLayoutParamsLinearLayout[i].setMargins(5, 10, 0, 10);
            }

            // Submenu RelativeLayout option.

//            mArrayRelativeLayout[i] = new RelativeLayout(this);
//
//            // id = 0 don't works!
//            mArrayRelativeLayout[i].setId(submenuId);
//            // mArrayRelativeLayout[i].setId(i + 1);
//            mArrayRelativeLayout[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.gray));
//            // mArrayRelativeLayout[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.blue + i));
//            // mArrayRelativeLayout[i].setPadding(5, 5, 5, 5); // Don't works!
//
//            mLayoutParamsRelativeLayoutArray[i] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            mLayoutParamsRelativeLayoutArray[i].height = 400;
//            mLayoutParamsRelativeLayoutArray[i].width = 200;
//            if (i == 0) {
//                mLayoutParamsRelativeLayoutArray[i].setMargins(0, 10, 0, 10);
//                mLayoutParamsRelativeLayoutArray[i].addRule(RelativeLayout.BELOW, R.id.mButtonMoedeiro);
//            }
//            else {
//                mLayoutParamsRelativeLayoutArray[i].setMargins(5, 10, 0, 10);
//                mLayoutParamsRelativeLayoutArray[i].addRule(RelativeLayout.RIGHT_OF, i * 10);
//            }

            // mTextViewSubmenuLabel definition & settings.

            mTextViewSubmenuLabel = new TextView(this);
            mTextViewSubmenuLabel.setId(submenuId + 1);
            mTextViewSubmenuLabel.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            mTextViewSubmenuLabel.setTextColor(getResources().getColor(R.color.black));
            mTextViewSubmenuLabel.setTextSize(30);
            mTextViewSubmenuLabel.setTypeface(Typeface.DEFAULT_BOLD);
            mTextViewSubmenuLabel.setText(mArraySubmenu[i].getName());
//            mTextViewSubmenuLabel.setText(itArrayListEntityField.next().getName());
            mTextViewSubmenuLabel.setPadding(0, 10, 0, 10);

            // http://developer.android.com/reference/java/util/Hashtable.html

            mArrayLinearLayout[i].addView(mTextViewSubmenuLabel);

            // Submenu RelativeLayout option.
//            mArrayRelativeLayout[i].addView(mTextViewSubmenuLabel);

            // mTextViewPreviousElement definition & settings.

            mTextViewPreviousElement = new TextView(this);
            mTextViewPreviousElement.setId(submenuId + 2);
            mTextViewPreviousElement.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            mTextViewPreviousElement.setTextColor(getResources().getColor(R.color.black));
            mTextViewPreviousElement.setTextSize(18);
            String previousElement = getResources().getString(R.string.previous) + ": -";
            mTextViewPreviousElement.setText(previousElement);

            mArrayLinearLayout[i].addView(mTextViewPreviousElement);

            // Submenu RelativeLayout option.
//            RelativeLayout.LayoutParams mLayoutParamsTextViewPrevious = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);;
//            mLayoutParamsTextViewPrevious.addRule(RelativeLayout.BELOW, submenuId + 1);
//            mArrayRelativeLayout[i].addView(mTextViewPreviousElement, mLayoutParamsTextViewPrevious);

            // mButtonUpElement definition & settings.

            mButtonUpElement = new Button(this);
            mButtonUpElement.setId(submenuId + 3);

            mButtonUpElement.setTag(i);

            mButtonUpElement.setHeight(60);
            mButtonUpElement.setWidth(200);
            // mButtonUpElement.setBackground(getResources().getDrawable(R.drawable.button_shape_menu_principal));
            mButtonUpElement.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonUpElement.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonUpElement.setTextColor(getResources().getColor(R.color.white));
            mButtonUpElement.setTypeface(Typeface.DEFAULT_BOLD);
            mButtonUpElement.setText(R.string.page_up);
            mButtonUpElement.setEnabled(false);

            mButtonUpElement.setOnClickListener(btnOnClickListenerImpl);
            mButtonUpElement.setOnTouchListener(btnOnTouchListenerImpl);

            mArrayLinearLayout[i].addView(mButtonUpElement);

            // Submenu RelativeLayout option.
//            RelativeLayout.LayoutParams mLayoutParamsButton = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);;
//            mLayoutParamsButton.addRule(RelativeLayout.BELOW, submenuId + 2);
//            mArrayRelativeLayout[i].addView(mButtonUpElement, mLayoutParamsButton);

            // mButtonSelectElement definition & settings.

            mButtonSelectElement = new Button(this);
            mButtonSelectElement.setId(submenuId + 4);

            mButtonSelectElement.setTag(i);

            mButtonSelectElement.setHeight(120);
            mButtonSelectElement.setWidth(200);
            // mButtonSelectElement.setBackground(getResources().getDrawable(R.drawable.button_shape_menu_principal));
            mButtonSelectElement.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonSelectElement.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonSelectElement.setTextColor(getResources().getColor(R.color.black));
            mButtonSelectElement.setTypeface(Typeface.DEFAULT_BOLD);
//            mButtonSelectElement.setSingleLine(false);
//            mButtonSelectElement.setInputType(mButtonSelectElement.getInputType() | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            String element = mArraySubmenu[i].getArrayListSubmenuItem().get(0).getName();
            // String element = itArrayListEntityField.next().getArrayListSubmenuItem().get(0).getName();
            String pageInformation = "(1/" + mArraySubmenu[i].getArrayListSubmenuItem().size() + ")";
            mButtonSelectElement.setTextSize(25);
            mButtonSelectElement.setText(element + "\n" + pageInformation);

            mButtonSelectElement.setOnClickListener(btnOnClickListenerImpl);
            mButtonSelectElement.setOnTouchListener(btnOnTouchListenerImpl);

            mArrayLinearLayout[i].addView(mButtonSelectElement);

            // mButtonDownElement definition & settings.

            mButtonDownElement = new Button(this);
            mButtonDownElement.setId(submenuId + 5);

            mButtonDownElement.setTag(i);
//            mButtonDownElement.setTag(SUBMENU_INDEX_TAG, i);
//            mButtonDownElement.setTag(SUBMENU_ITEM_INDEX_TAG, submenuItemIndex);
            // mButtonDownElement.setHint("mButtonDownElement Hint"); // Works fine!

            mButtonDownElement.setHeight(60);
            mButtonDownElement.setWidth(200);
            // mButtonDownElement.setBackground(getResources().getDrawable(R.drawable.button_shape_menu_principal));
            mButtonDownElement.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonDownElement.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonDownElement.setTextColor(getResources().getColor(R.color.white));
            mButtonDownElement.setTypeface(Typeface.DEFAULT_BOLD);
            mButtonDownElement.setText(R.string.page_down);
            // Checks if the submenu items > 1 then disable the Down element button.
            if (mArraySubmenu[i].getArrayListSubmenuItem().size() == 1) {
                mButtonDownElement.setEnabled(false);
            }

            mButtonDownElement.setOnClickListener(btnOnClickListenerImpl);
            mButtonDownElement.setOnTouchListener(btnOnTouchListenerImpl);

            mArrayLinearLayout[i].addView(mButtonDownElement);

            // mTextViewNextElement definition & settings.

            mTextViewNextElement = new TextView(this);
            mTextViewNextElement.setId(submenuId + 6);
            mTextViewNextElement.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            mTextViewNextElement.setTextColor(getResources().getColor(R.color.black));
            mTextViewNextElement.setTextSize(18);

            // Checks if the submenu items > 1 then set the text of the Next element TextView.
            if (mArraySubmenu[i].getArrayListSubmenuItem().size() > 1) {
                if (mArraySubmenu[i].getArrayListSubmenuItem().get(1) != null) {
                    mTextViewNextElement.setText(getResources().getString(R.string.next) + ": " + mArraySubmenu[i].getArrayListSubmenuItem().get(1).getName());
                }
                else {
                    mTextViewNextElement.setText(getResources().getString(R.string.next) + ": -");
                }
            }
            else {
                mTextViewNextElement.setText(getResources().getString(R.string.next) + ": -");
            }

            mArrayLinearLayout[i].addView(mTextViewNextElement);

            mRelativeLayoutSubmenu.addView(mArrayLinearLayout[i], mArrayLayoutParamsLinearLayout[i]);

            // Submenu RelativeLayout option.
//            mRelativeLayoutSubmenu.addView(mArrayRelativeLayout[i], mLayoutParamsRelativeLayoutArray[i]);

//            i++;

        }

        // Debugging: Creating submenus (end).

        // Adding views.

        mRelativeLayout.addView(mButtonMoedeiro, mLayoutParamsButtonMoedeiro);

        mRelativeLayout.addView(mRelativeLayoutSubmenu, mLayoutParamsRelativeLayoutSubmenu);

        setContentView(mRelativeLayout);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

//        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    public void onStart() {

        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

//        mClient.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "MenuPrincipal Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.bpaulo.tipxxi.menuprincipal/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(mClient, viewAction);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MenuPrincipal Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.bpaulo.tipxxi.menuprincipal/http/host/path")
        );
        // AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {

        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "MenuPrincipal Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.bpaulo.tipxxi.menuprincipal/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(mClient, viewAction);
//        mClient.disconnect();

    }

    private class BtnOnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Toast toast;
            LinearLayout linearLayoutToast;
            TextView textViewToast;

//            Integer i = (Integer) view.getTag(SUBMENU_INDEX_TAG);
//            Integer j = (Integer) view.getTag(SUBMENU_ITEM_INDEX_TAG);

//            submenuIndex = ((Integer) view.getTag() + 1) * 10;

            if (view.getId() == R.id.mButtonMoedeiro) {

                mIntent = new Intent(getApplicationContext(), MoedeiroActivity.class);

                startActivity(mIntent);

                // Enter animation definition.
                // overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

            }
            else {

                // Submenu buttons.

                submenuIndex = (Integer) view.getTag();

                switch (view.getId() - ((submenuIndex + 1) * 10)) {
//                switch (view.getId() - ((submenuIndex + 1) * 10)) {
//                switch (view.getId() - (submenuIndex * 10)) {

                    // Some Submenu Up Element (mButtonUpElement (submenuId + 3)) button button_pressed.
                    case 3:

                        submenuItemIndex[submenuIndex]--;

                        // Updating mTextViewPreviousElement (submenuId + 2);

                        // Checking if first element of submenu.
                        if (submenuItemIndex[submenuIndex] == 0) {
                            ((TextView) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 2)).setText(
                                    getResources().getString(R.string.previous) + ": -");
                        }
                        else {
                            ((TextView) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 2)).setText(
                                    getResources().getString(R.string.previous) + ": " + mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex] - 1).getName());
                        }

                        // Updating mButtonUpElement (submenuId + 3);

                        // Checking if first element of submenu.
                        if (submenuItemIndex[submenuIndex] == 0) {
                            getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 3).setEnabled(false);
                        }

                        // Updating mButtonSelectElement (submenuId + 4);

                        String elementUp = mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex]).getName();
                        String pageInformationUp = "(" + (submenuItemIndex[submenuIndex] + 1) + "/" + mArraySubmenu[submenuIndex].getArrayListSubmenuItem().size() + ")";

                        ((Button) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 4)).setText(elementUp + "\n" + pageInformationUp);

                        // Updating mButtonDownElement (submenuId + 5);

                        // Checking if last element of submenu.
                        if (submenuItemIndex[submenuIndex] < (mArraySubmenu[submenuIndex].getArrayListSubmenuItem().size() - 1)) {
                            getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 5).setEnabled(true);
                        }

                        // Updating mTextViewNextElement (submenuId + 6);

                        ((TextView) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 6)).setText(
                                getResources().getString(R.string.next) + ": " + mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex] + 1).getName());

                        break;

                    // Some Submenu Select Element (mButtonSelectElement (submenuId + 4)) button button_pressed.
                    case 4:

                        // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

                        // Splitting the entity class name to get only the last part of the name (excluding the package).
                        String[] entityNameParts = mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex]).getClassName().split("\\.");

                        // Checking entity access feature: (R)ead.
                        if ((Boolean) mDatabaseHelper.getEntityFieldAttributeValue(entityNameParts[entityNameParts.length - 1], new String("READ"), null)) {

                            // TODO: Calling other action from here!

                            Class<?> actionClazz = null;
                            try {
                                actionClazz = Class.forName("com.bpaulo.tipxxi.activity.ControlPanelActivity");
                                // actionClazz = Class.forName((String) view.getTag(R.id.mForwardButtonAction));
                            }
                            catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            mIntent = new Intent(getApplicationContext(), actionClazz);
                            mIntent.putExtra("entityName", entityNameParts[entityNameParts.length - 1]);
                            // mIntent.putExtra("entityName", mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex]).getClassName());

                            // Debugging: Return to (select) the selected point (begin).

                            mIntent.putExtra("submenuIndex", submenuIndex);
                            mIntent.putExtra("submenuItemIndex", submenuItemIndex[submenuIndex]);

                            startActivityForResult(mIntent, 99);
                            // startActivity(mIntent); // Don't works: no call of onActivityResult(int requestCode, int resultCode, Intent data)!

                            // Debugging: Return to (select) the selected point (end).

                            // Exit animation definition.
                            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

                        }
                        else {

                            Utils.displayAlertDialog(
                                    view.getContext(),
                                    Utils.CONFIRMATION_LEVEL,
                                    "Authorization error",
                                    "Forbidden access to " + mDatabaseHelper.getEntityFieldAttributeValue(entityNameParts[entityNameParts.length - 1], new String("ENTITY_CAPTION"), null) + " table!");
                            // Utils.displayAlertDialog(view.getContext(), Utils.CONFIRMATION_LEVEL, "Validation error", "Invalid PIN!");
                            // Utils.displayToast(mDatabaseHelper.getEntityFieldAttributeValue(entityNameParts[entityNameParts.length - 1], new String("ENTITY_CAPTION"), null) + " table access forbidden!");

                        }

                        // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

                        break;

                    // Some Submenu Down Element (mButtonDownElement (submenuId + 5)) button button_pressed.
                    case 5:

                        submenuItemIndex[submenuIndex]++;

                        // Updating mTextViewPreviousElement (submenuId + 2);

                        ((TextView) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 2)).setText(
                                getResources().getString(R.string.previous) + ": " + mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex] - 1).getName());

                        // Updating mButtonUpElement (submenuId + 3);

                        // Checking if first element of submenu.
                        if (submenuItemIndex[submenuIndex] == 1) {
                        // if (!getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 3).isEnabled()) {
                            getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 3).setEnabled(true);
                            // ((Button) getWindow().getDecorView().getRootView().findViewById(submenuIntegerIndex + 3)).setEnabled(true);
                        }

                        // Updating mButtonSelectElement (submenuId + 4);

                        String elementDown = mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex]).getName();
                        String pageInformationDown = "(" + (submenuItemIndex[submenuIndex] + 1) + "/" + mArraySubmenu[submenuIndex].getArrayListSubmenuItem().size() + ")";

                        ((Button) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 4)).setText(elementDown + "\n" + pageInformationDown);

                        // Updating mButtonDownElement (submenuId + 5);

                        // Checking if last element of submenu.
                        if (submenuItemIndex[submenuIndex] == (mArraySubmenu[submenuIndex].getArrayListSubmenuItem().size() - 1)) {
                            getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 5).setEnabled(false);
                        }

                        // Updating mTextViewNextElement (submenuId + 6);

                        // Checking if last element of submenu.
                        if (submenuItemIndex[submenuIndex] == (mArraySubmenu[submenuIndex].getArrayListSubmenuItem().size() - 1)) {
                        // if ((submenuItemIndex[submenuIndex] + 1) == mArraySubmenu[submenuIndex].getArrayListSubmenuItem().size()) {
                            ((TextView) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 6)).setText(
                                    getResources().getString(R.string.next) + ": -");
                        }
                        else {
                            ((TextView) getWindow().getDecorView().getRootView().findViewById(((submenuIndex + 1) * 10) + 6)).setText(
                                    getResources().getString(R.string.next) + ": " + mArraySubmenu[submenuIndex].getArrayListSubmenuItem().get(submenuItemIndex[submenuIndex] + 1).getName());
                        }

                        break;

                }

            }

//            Button buttonUpElement = (Button) getWindow().getDecorView().getRootView().findViewById(submenuIndex + 3); // null.
//            Button buttonSelectElement = (Button) getWindow().getDecorView().getRootView().findViewById(submenuIndex + 4); // null.
//            Button buttonDownElement = (Button) getWindow().getDecorView().getRootView().findViewById(submenuIndex + 5); // = Button.

            // TODO: UI changes (begin).

            // TODO: mTextViewPreviousElement (submenuId + 2);

            // ((TextView) getWindow().getDecorView().getRootView().findViewById(submenuIntegerIndex + 2)).setText("Works!"); // Works fine!

            // Submenu mButtonUpElement (submenuId + 3);
//            getWindow().getDecorView().getRootView().findViewById(submenuIndex + 3).setEnabled(true);
            // ((Button) getWindow().getDecorView().getRootView().findViewById(submenuIntegerIndex + 3)).setEnabled(true);

            // (OK) TODO: mButtonSelectElement (submenuId + 4);
//            ((Button) getWindow().getDecorView().getRootView().findViewById(submenuIndex + 4)).setText("Works!"); // Works fine!
            // buttonSelectElement.setText("Works!"); // Works fine!

            // TODO: mButtonDownElement (submenuId + 5);

            // TODO: mTextViewNextElement (submenuId + 6);

            // TODO: UI changes (end).

//            switch (view.getId()) {
//
//                case R.id.mButtonMoedeiro:
//
//                    mIntent = new Intent(
//                            getApplicationContext(),
//                            MoedeiroActivity.class);
//
//                    startActivity(mIntent);
//
//                    // Enter animation definition.
//                    // overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
//
//                    break;
//
//                // Submenu 1: Page Up button.
//                case 13:
//
//                    toast = Toast.makeText(getApplicationContext(), "Consultas > 'Page Up'" , Toast.LENGTH_SHORT);
//                    toast.show();
//
//                    break;
//
//                // Submenu 1: Select element button.
//                case 14:
//
//                    toast = Toast.makeText(getApplicationContext(), "Consultas > 'Histórico'" , Toast.LENGTH_SHORT);
//                    toast.show();
//
//                    break;
//
//                // Submenu 1: Page Down button.
//                case "p":
//
//                    toast = Toast.makeText(getApplicationContext(), "Consultas > 'Page Down'" , Toast.LENGTH_SHORT);
//                    toast.show();
//
//                    break;
//
//                case:
//
//                    break;
//            }

        }

    }

    private class BtnOnTouchListenerImpl implements View.OnTouchListener {

        @Override
        public boolean onTouch(View pView, MotionEvent pMotionEvent) {

            switch (pMotionEvent.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    pView.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                    pView.invalidate();

                    break;

                case MotionEvent.ACTION_UP:

                    pView.getBackground().clearColorFilter();
                    pView.invalidate();

                    break;

            }

            return false;

        }

    }

    // Debugging: Return to (select) the selected point (begin).

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        submenuIndex = data.getExtras().getInt("submenuIndex");
        submenuItemIndex[submenuIndex] = data.getExtras().getInt("submenuItemIndex");

        Log.i(TAG, "onActivityResult(int requestCode, int resultCode, Intent data);");

    }

    // Debugging: Return to (select) the selected point (end).


//    public static void displayToast(String message) {
//
//        Toast toast = Toast.makeText(getA, (CharSequence) message, Toast.LENGTH_LONG);
//
//        LinearLayout linearLayoutToast = (LinearLayout) toast.getView();
//        TextView textViewToast = (TextView) linearLayoutToast.getChildAt(0);
//        textViewToast.setTextSize(24);
//
//        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
//
//        toast.show();
//
//    }

}
