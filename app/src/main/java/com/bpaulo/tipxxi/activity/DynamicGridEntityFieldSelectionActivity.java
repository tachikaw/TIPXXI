package com.bpaulo.tipxxi.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.database.DatabaseHelper;
import com.bpaulo.tipxxi.fragment.DynamicGridEntityFieldSelectionFragment;
import com.bpaulo.tipxxi.util.Forward;

public class DynamicGridEntityFieldSelectionActivity extends FragmentActivity {

    private static final String TAG = "DynamicGridEntityFieldSelectionActivity";

    private RelativeLayout mRelativeLayout;
    private TextView mTextView;

    private RelativeLayout.LayoutParams mLayoutParamsEditText;

    private TextView mTextViewBreadcrumb;
    private RelativeLayout.LayoutParams mLayoutParamsTextViewBreadcrumb;

    // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)).
    // private AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    private ViewPager mViewPager;

    private FragmentManager mFragmentManager = null;
    private Fragment mFragment = null;
    private FragmentTransaction mFragmentTransaction = null;

    private String mEditFieldName;
    private String mEditFieldEntityName;
    private String mEditFieldInputType;
    private String mEditFieldLabel;

    private Menu mMenu = null;
    private int mPreviousMenuItemId;
    private TextView mTextViewPageInformation;
    private RelativeLayout.LayoutParams mLayoutParamsTextViewPageInformation;

    private DatabaseHelper mDatabaseHelper;

    private Intent mIntent;
    private Forward mForward = null;

    // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

    /*
    private ActionMode.Callback mCallback = new ActionMode.Callback() {

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater menuInflater = actionMode.getMenuInflater();
            menuInflater.inflate(R.menu.actionmode, menu);
            MenuItem menuItem = menu.findItem(R.id.action_text);
            View view = menuItem.getActionView();
            if (view instanceof TextView) {
                ((TextView) view).setText(R.string.actionmode_title);
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            boolean ret = false;
            if (menuItem.getItemId() == R.id.actionmode_cancel) {
                actionMode.finish();
                ret = true;
            }
            return ret;
        }

    };
    */

    // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

        // Set up the action bar.

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

        mDatabaseHelper = DatabaseHelper.getInstance(this);

        mIntent = getIntent();

        // Getting extras from Intent.

        if (mIntent.getStringExtra("editFieldName") != null) {
            mEditFieldName = mIntent.getStringExtra("editFieldName");
        }

        if (mIntent.getStringExtra("editFieldEntityName") != null) {
            mEditFieldEntityName = mIntent.getStringExtra("editFieldEntityName");
        }

        if (mIntent.getStringExtra("editFieldInputType") != null) {
            mEditFieldInputType = mIntent.getStringExtra("editFieldInputType");
        }

        if (mIntent.getStringExtra("editFieldLabel") != null) {
            mEditFieldLabel = mIntent.getStringExtra("editFieldLabel");
        }

        // mTextView definition & settings.

        mTextView = new TextView(this);
        mTextView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mTextView.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mTextView.setEms(10);
        mTextView.setInputType(InputType.TYPE_CLASS_NUMBER);
        mTextView.setTextSize(150);

        mLayoutParamsEditText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsEditText.addRule(RelativeLayout.CENTER_IN_PARENT);
        mLayoutParamsEditText.rightMargin = 450;
        mLayoutParamsEditText.topMargin = 300;

        // mRelativeLayout definition & settings.

        mRelativeLayout = new RelativeLayout(this);
        mRelativeLayout.setId(R.id.mRelativeLayout);

        // mViewPager definition & settings.

        mViewPager = new ViewPager(this);

        ViewGroup.LayoutParams viewGroupLayoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mViewPager.setLayoutParams(viewGroupLayoutParams);

        // mTextViewBreadcrumb definition & settings.

        mTextViewBreadcrumb = new TextView(this);
        mTextViewBreadcrumb.setId(R.id.mTextViewBreadcrumb);
        mTextViewBreadcrumb.setBackgroundColor(getResources().getColor(R.color.gray));
        mTextViewBreadcrumb.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);
        mTextViewBreadcrumb.setTextColor(getResources().getColor(R.color.white));

        if (mIntent.getParcelableExtra("forward") != null) {

            mForward = mIntent.getParcelableExtra("forward");
            mTextViewBreadcrumb.setText(mForward.getBreadcrumbText());

        }

        mLayoutParamsTextViewBreadcrumb = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        mLayoutParamsTextViewBreadcrumb.height = 30;

        // mTextViewPageInformation definition & settings.

        mTextViewPageInformation = new TextView(this);
        mTextViewPageInformation.setId(R.id.mTextViewPageInformation);
        mTextViewPageInformation.setBackgroundColor(getResources().getColor(R.color.gray));
        mTextViewPageInformation.setTextColor(getResources().getColor(R.color.white));
        mTextViewPageInformation.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);

        mLayoutParamsTextViewPageInformation = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsTextViewPageInformation.height = 30;
        mLayoutParamsTextViewPageInformation.rightMargin = 10;
        mLayoutParamsTextViewPageInformation.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        // Adding views.

        mRelativeLayout.addView(mTextViewBreadcrumb, mLayoutParamsTextViewBreadcrumb);
        mRelativeLayout.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);
        mRelativeLayout.addView(mViewPager);

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

        /*
        RelativeLayout relativeLayoutActionBar = new RelativeLayout(this);

        int abContainerViewID = getResources().getIdentifier("action_bar_container", "id", "android");
        FrameLayout actionBarContainer = (FrameLayout)findViewById(abContainerViewID);
        */

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)).
        // ActionBar.LayoutParams actionBarLayoutParams = new ActionBar.LayoutParams(800, 300); // WORKS!

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

        /*
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.height = 120;
        */

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

        /*
        ViewGroup viewGroup = (ViewGroup)(getWindow().getDecorView().getRootView());
        ((ViewGroup)actionBarContainer.getParent()).removeView(actionBarContainer);

        viewGroup.addView(actionBarContainer);
        */

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)).
        // actionBar.setCustomView(viewGroup);

        setContentView(mRelativeLayout);

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

        /*
        int mode = ActionBar.NAVIGATION_MODE_TABS;
        if (savedInstanceState != null) {
            mode = savedInstanceState.getInt("mode", ActionBar.NAVIGATION_MODE_TABS);
        }
        */

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

        /*
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {

                        // When swiping between different app sections, select the corresponding tab.
                        // We can also use ActionBar.Tab#select() to do this if we have a reference to the Tab.
                        // Debugging: tipxxi.

                        // actionBar.setSelectedNavigationItem(position);

                    }

                });
        */

        // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

    }

    // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

    /*
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        String[] entitiesNames;

        public AppSectionsPagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);

            ArrayList<String> arrayListEntitiesNames = DatabaseHelper.getAllEntitiesNames();
            entitiesNames = arrayListEntitiesNames.toArray(new String[arrayListEntitiesNames.size()]);

        }

        @Override
        public Fragment getItem(int position) {

            return DynamicGridEntityFieldSelectionFragment.createDynamicGridEntityFieldSelectionFragment(entitiesNames[position], "", "");

        }

        @Override
        public int getCount() {

            return entitiesNames.length;

        }

        @Override
        public CharSequence getPageTitle(int position) {

            return entitiesNames[position];

        }

    }
    */

    // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

    // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

    /*
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("mode", getActionBar().getNavigationMode());
        super.onSaveInstanceState(outState);

    }
    */

    // Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        mMenu = menu;

        mFragmentManager = getSupportFragmentManager();

        if (mEditFieldInputType.equals("T_TABELA")) {
            mTextViewBreadcrumb.setText("Selecting " + (String) mDatabaseHelper.getEntityFieldAttributeValue(mEditFieldEntityName, new String("ENTITY_CAPTION"), null));
        }
        else {
            mTextViewBreadcrumb.setText("Selecting " + mEditFieldLabel);
        }

        mFragment = DynamicGridEntityFieldSelectionFragment.createDynamicGridEntityFieldSelectionFragment(mEditFieldName, mEditFieldEntityName, mEditFieldInputType);

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(mRelativeLayout.getId(), mFragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();

        mPreviousMenuItemId = 0;

        return super.onCreateOptionsMenu(menu);

    }

    // Managing app's memory.
    @Override
    public void onStop() {

        super.onStop();
        Log.i(TAG, "onStop();"); // Works fine!

    }

    // Managing app's memory.
    @Override
    public void onTrimMemory(int level) {

        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory(" + Integer.toString(level) + ");"); // Don't called!

    }

    // Managing app's memory.
    @Override
    public void onLowMemory() {

        super.onLowMemory();
        Log.i(TAG, "onLowMemory();"); // Don't called!

    }

}
