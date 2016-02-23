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
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.database.DatabaseHelper;
import com.bpaulo.tipxxi.fragment.DynamicGridSectionFragment;
import com.bpaulo.tipxxi.util.Forward;

// Debugging: Dropdown menu + Action bar navigation (http://www.tutorialspoint.com/android/android_spinner_control.htm).
// public class ControlPanelActivity extends FragmentActivity implements ActionBar.TabListener, AdapterView.OnItemSelectedListener {

// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (begin).

public class ControlPanelActivity extends FragmentActivity {
// public class ControlPanelActivity extends Activity {
// public class ControlPanelActivity extends FragmentActivity implements ActionBar.TabListener { // Tab navigation.

// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (end).

// Debugging: Dropdown menu + Action bar navigation (http://www.vogella.com/tutorials/AndroidActionBar/article.html#actionbar_navigation_dropdown).
// public class ControlPanelActivity extends FragmentActivity implements ActionBar.OnNavigationListener { // Dropdown menu + Action bar navigation.

	// Debugging: Prototype (begin).

	private static final String TAG = "ControlPanelActivity";

	private RelativeLayout mRelativeLayout;
	private TextView mTextView;

	private RelativeLayout.LayoutParams mLayoutParamsEditText;

	// Debugging: Prototype (end).

	private TextView mTextViewBreadcrumb;
	private RelativeLayout.LayoutParams mLayoutParamsTextViewBreadcrumb;

	// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)).
	// private AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	private ViewPager mViewPager;

	// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (begin).

	private MenuItem mMenuItem = null;

	// Debugging: Search feature (begin).

//	private MenuItem mSearchItem = null;
//	private EditText mSearch = null;
//	private Button mDelete = null;

	// Debugging: Search feature (end).

	private FragmentManager mFragmentManager = null;
	private Fragment mFragment = null;
	private FragmentTransaction mFragmentTransaction = null;

	// Debugging: tipxxi.
	private String mEntityName;
	// private String[] mEntitiesNames;

	// Debugging: Return to (select) the selected point (begin).

	private int mSubmenuIndex;
	private int mSubmenuItemIndex;
//	private String mSubmenuIndex;
//	private String mSubmenuItemIndex;

	// Debugging: Return to (select) the selected point (end).

	private Menu mMenu = null;
	private int mPreviousMenuItemId;
	private int mLastMenuItemEntity;
	private int mPageNumber;
	private int mMaxPageNumber;
	private TextView mTextViewPageInformation;
	private RelativeLayout.LayoutParams mLayoutParamsTextViewPageInformation;
	private String mSpacing;

	private int[][] mActionBarMenuItemNumber;

	// Debugging: Retrieve entity field value with any attribute.
	private DatabaseHelper mDatabaseHelper;

	// Debugging: Navigation flow control (begin).

	private Intent mIntent;
	private Forward mForward = null;

	// Debugging: Navigation flow control (end).

	// Causes error with "public class ControlPanelActivity extends FragmentActivity" (begin).

	/*
	private class MyTabListener implements ActionBar.TabListener {

		private Fragment mFragment;
		private final Activity mActivity;
		private final String mFragmentName;

		public MyTabListener(Activity activity, String fragName) {
			mActivity = activity;
			mFragmentName = fragName;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
			mFragment = Fragment.instantiate(mActivity, mFragmentName);
			// Debugging.
			// fragmentTransaction.add(android.R.id.content, mFragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
			// Debugging.
			// fragmentTransaction.remove(mFragment);
			mFragment = null;
		}
	}
	*/

	// Causes error with "public class ControlPanelActivity extends FragmentActivity" (end).

	// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

	/*
	private ActionMode.Callback mCallback = new ActionMode.Callback() {

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.actionmode, menu);
			MenuItem item = menu.findItem(R.id.action_text);
			View v = item.getActionView();
			if (v instanceof TextView) {
				((TextView) v).setText(R.string.actionmode_title);
			}
			return true;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			boolean ret = false;
			if (item.getItemId() == R.id.actionmode_cancel) {
				mode.finish();
				ret = true;
			}
			return ret;
		}
	};
	*/

	// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

	// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (end).

	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

		// Debugging: tipxxi (begin).

//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags(
//				WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Debugging: tipxxi (end).

		// Debugging: Singleton SQLiteOpenHelper.
		// Debugging: Retrieve entity field value with any attribute.
		mDatabaseHelper = DatabaseHelper.getInstance(this);
//		mDatabaseHelper = new DatabaseHelper(this);

		// Debugging: Navigation flow control (begin).
		mIntent = getIntent();
		// Debugging: Navigation flow control (end).

		if (mIntent.getStringExtra("entityName") != null) {
			mEntityName = mIntent.getStringExtra("entityName");
		}

		// Debugging: Return to (select) the selected point (begin).

		mSubmenuIndex = mIntent.getIntExtra("submenuIndex", 0);
//		if (mIntent.getStringExtra("submenuIndex") != null) {
//			mSubmenuIndex = mIntent.getStringExtra("submenuIndex");
//		}

		mSubmenuItemIndex = mIntent.getIntExtra("submenuItemIndex", 0);
//		if (mIntent.getStringExtra("submenuItemIndex") != null) {
//			mSubmenuItemIndex = mIntent.getStringExtra("submenuItemIndex");
//		}

//		mIntent.putExtra("submenuIndex", submenuIndex);
//		mIntent.putExtra("submenuItemIndex", submenuItemIndex[submenuIndex]);

		// Debugging: Return to (select) the selected point (end).

		// Debugging: Dropdown menu + Action bar navigation (http://www.tutorialspoint.com/android/android_spinner_control.htm) (begin).

		/*

		// Spinner element.
		Spinner spinner = new Spinner(this);
		spinner.setId(R.id.spinner);

		// Spinner click listener.
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements.
		List<String> categories = new ArrayList<String>();
		categories.add("Automobile");
		categories.add("Business Services");
		categories.add("Computers");
		categories.add("Education");
		categories.add("Personal");
		categories.add("Travel");

		// Creating adapter for spinner.
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

		// Drop down layout style - list view with radio button.
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Attaching data adapter to spinner.
		spinner.setAdapter(dataAdapter);

		*/

		// Debugging: Dropdown menu + Action bar navigation (http://www.tutorialspoint.com/android/android_spinner_control.htm) (end).

		// http://stackoverflow.com/questions/10031180/getactionbar-returns-null
		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)).
		// getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		// activity_control_panel.xml (begin).
		
		ViewGroup.LayoutParams viewGroupLayoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		
		// Debugging: Prototype (begin).  

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
		
		// Adding views.

		// setContentView(mRelativeLayout);
		
		// Debugging: Prototype (end).
		
		// <android.support.v4.view.ViewPager xmlns:android="http://schemas.android.com/apk/res/android"
		//		android:id="@+id/pager"
		//		android:layout_width="match_parent"
		//		android:layout_height="match_parent" >


		// Debugging: Refactoring ViewPager instances (begin).

//		ViewPager mViewPager = new ViewPager(this);
//		mViewPager.setId(R.id.pager);
//		mViewPager.setLayoutParams(viewGroupLayoutParams);

		// Debugging: Refactoring ViewPager instances (end).

		// Debugging: Prototype (begin).

		// Debugging: Prototype (end).
		
		// activity_control_panel.xml (end).
		
		// Create the adapter that will return a fragment for each of the three primary sections of the app.
		// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4).
		// mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

		// Set up the action bar.

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		/*
		// Debugging: tipxxi.
		final ActionBar actionBar = getActionBar();
		// final ActionBar actionBar = getSupportActionBar();
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

		// Specify that the Home/Up button should not be enabled, since there is
		// no hierarchical parent.
		// actionBar.setHomeButtonEnabled(false);
		
		// Specify that we will be displaying tabs in the action bar.

		// Debugging: Early getView() of the next tab.
		// Debugging: Dropdown menu + Action bar navigation (http://www.vogella.com/tutorials/AndroidActionBar/article.html#actionbar_navigation_dropdown).
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST); // Dropdown menu + Action bar navigation.
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); // Don't works!
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // Tab navigation (default option).

		// Debugging (begin).

//		actionBar.setTitle("Title"); // Works (but with wrong layout options)!
//		actionBar.setSubtitle("SubTitle"); // Works!
		// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4).
//		actionBar.setDisplayShowTitleEnabled(true); // Works (but with wrong layout options)!

		// actionBar.setDisplayUseLogoEnabled(false); // Don't works!

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		/*
		// Debugging: tipxxi.
		actionBar.setDisplayShowHomeEnabled(false); // Works!
		// actionBar.setDisplayHomeAsUpEnabled(false); // For now, don't works!
		// actionBar.hide(); // Works!
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

		// Debugging (end).

		// Set up the ViewPager, attaching the adapter and setting up a listener
		// for when the user swipes between sections.
		
		// http://stackoverflow.com/questions/19692355/android-get-view-reference-to-actionbar-navigation
		// getActivity().getActionBar().setListNavigationCallbacks(mNavigationListAdapter, this);

		// Debugging: Dropdown menu + Action bar navigation (http://www.vogella.com/tutorials/AndroidActionBar/article.html#actionbar_navigation_dropdown) (begin).

		/*

		// Array of choices.
		// final String[] dropDownValues = {"Red", "Blue", "White", "Yellow", "Black", "Green", "Purple", "Orange", "Grey"};
		final String[] dropDownValues = getResources().getStringArray(R.array.array0);
		// final String[] dropDownValues = getResources().getStringArray(R.array.dropdown);

		// Specify a SpinnerAdapter to populate the dropdown list.
		// ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dropDownValues);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dropDownValues);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(actionBar.getThemedContext(), android.R.layout.simple_spinner_item, android.R.id.text1, dropdownValues);

		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(arrayAdapter, this);

		// Use getActionBar().getThemedContext() to ensure that the text color is always appropriate for the action bar background rather than the activity background.

		*/

		// Debugging: Dropdown menu + Action bar navigation (http://www.vogella.com/tutorials/AndroidActionBar/article.html#actionbar_navigation_dropdown) (end).

		// activity_control_panel.xml (begin).

		// Debugging: Refactoring ViewPager instances (begin).

		mRelativeLayout = new RelativeLayout(this);
		mRelativeLayout.setId(R.id.mRelativeLayout);

		mViewPager = new ViewPager(this);
		mViewPager.setLayoutParams(viewGroupLayoutParams);

		mTextViewBreadcrumb = new TextView(this);
		mTextViewBreadcrumb.setId(R.id.mTextViewBreadcrumb);
		mTextViewBreadcrumb.setBackgroundColor(getResources().getColor(R.color.gray));
		mTextViewBreadcrumb.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);
		mTextViewBreadcrumb.setTextColor(getResources().getColor(R.color.white));

		// Debugging: Navigation flow control (begin).

		if (mIntent.getParcelableExtra("forward") != null) {

			mForward = mIntent.getParcelableExtra("forward");
			mTextViewBreadcrumb.setText(mForward.getBreadcrumbText());

		}

		// Debugging: Navigation flow control (end).

		mLayoutParamsTextViewBreadcrumb = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		mLayoutParamsTextViewBreadcrumb.height = 30;
//		mLayoutParamsTextViewBreadcrumb.leftMargin = 10;
//		mLayoutParamsTextViewBreadcrumb.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		mTextViewPageInformation = new TextView(this);
		mTextViewPageInformation.setId(R.id.mTextViewPageInformation);
		mTextViewPageInformation.setBackgroundColor(getResources().getColor(R.color.gray));
		mTextViewPageInformation.setTextColor(getResources().getColor(R.color.white));
		mTextViewPageInformation.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);

		mLayoutParamsTextViewPageInformation = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		mLayoutParamsTextViewPageInformation.height = 30;
		mLayoutParamsTextViewPageInformation.rightMargin = 10;
		mLayoutParamsTextViewPageInformation.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		mRelativeLayout.addView(mTextViewBreadcrumb, mLayoutParamsTextViewBreadcrumb);
		mRelativeLayout.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);
		mRelativeLayout.addView(mViewPager);

		// Debugging: Customizing Action bar (begin).

		// Code in http://www.vogella.com/tutorials/AndroidActionBar/article.html#menu_customviews

//		actionBar.setCustomView(R.layout.search_field);
//		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

		// Code in: http://stackoverflow.com/questions/15279647/display-view-on-top-of-action-bar

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		/*
		RelativeLayout relativeLayoutActionBar = new RelativeLayout(this);

		int abContainerViewID = getResources().getIdentifier("action_bar_container", "id", "android");
		FrameLayout actionBarContainer = (FrameLayout)findViewById(abContainerViewID);
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

//		int childCount = actionBarContainer.getChildCount();
//		View view = actionBarContainer.getChildAt(0);
//		view.getId();
//		mLayoutParamsTextViewBreadcrumb.addRule(RelativeLayout.ABOVE, view.getId());

		// actionBarContainer.addView(mTextViewBreadcrumb, mLayoutParamsTextViewBreadcrumb); // Don't works!
		// actionBarContainer.addView(mTextViewBreadcrumb, 2, mLayoutParamsTextViewBreadcrumb); // Don't works!

		// Approach 00: http://stackoverflow.com/questions/17439683/how-to-change-action-bar-size

		// Approach 01: http://developer.android.com/reference/android/app/ActionBar.LayoutParams.html

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)).
		// ActionBar.LayoutParams actionBarLayoutParams = new ActionBar.LayoutParams(800, 300); // WORKS!

		// actionBar.setDisplayShowCustomEnabled(true); // java.lang.StackOverflowError

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		/*
		// Approach 02:

		// This block of code works!
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
		// RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT); // java.lang.IllegalStateException: ActionBarView can only be used with android:layout_width="match_parent" (or fill_parent)
		layoutParams.height = 120;
		// mLayoutParamsTextViewBreadcrumb.addRule(RelativeLayout.BELOW, R.id.mViewPager);
		// mLayoutParamsTextViewBreadcrumb.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		// layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		/*
		ViewGroup viewGroup = (ViewGroup)(getWindow().getDecorView().getRootView());
		((ViewGroup)actionBarContainer.getParent()).removeView(actionBarContainer);

		viewGroup.addView(actionBarContainer);
		// viewGroup.addView(actionBarContainer, actionBarLayoutParams);
		// viewGroup.addView(actionBarContainer, layoutParams); // Caused by: java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

		// mLayoutParamsTextViewBreadcrumb.addRule(RelativeLayout.ABOVE, actionBarContainer.getId()); // Don't works!
		// viewGroup.addView(mTextViewBreadcrumb, mLayoutParamsTextViewBreadcrumb);

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		/*
		// Debugging: Dropdown menu + Action bar navigation (http://www.vogella.com/tutorials/AndroidActionBar/article.html#actionbar_navigation_dropdown).
		// Debugging: tipxxi.
		actionBar.setCustomView(viewGroup);
		// actionBar.hide();
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

//		ViewGroup vg = (ViewGroup)(getWindow().getDecorView().getRootView());
//		vg.addView(myNewView, params);

		// Approach 03:

//		((ViewGroup)actionBarContainer.getParent()).removeView(actionBarContainer);
//
//		relativeLayoutActionBar.addView(actionBarContainer);
//		relativeLayoutActionBar.addView(mTextViewBreadcrumb);
//		actionBar.setCustomView(relativeLayoutActionBar);

		// Approach 04:

//		LayoutInflater layoutInflater = getLayoutInflater();
//		View customView = layoutInflater.inflate(R.layout.search_field, null);
//		actionBarContainer.addView(customView);

//		actionBar.setCustomView(actionBarContainer);

		// Code in: https://android.googlesource.com/platform/frameworks/base.git/+/45c0b1954d7dfa6e2590ed76b915a98ae971414c%5E!/

		// Debugging: Customizing Action bar (end).

		// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4).
		setContentView(mRelativeLayout);

		// setContentView(mViewPager);

//		mViewPager = mViewPager;
		// mViewPager = (ViewPager) findViewById(R.id.pager);

		// Debugging: Refactoring ViewPager instances (end).

		// activity_control_panel.xml (end).

		// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (begin).

		// super.onCreate(savedInstanceState); // Duplicated code (see above).

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

		/*
		int mode = ActionBar.NAVIGATION_MODE_TABS;

		if (savedInstanceState != null) {
			mode = savedInstanceState.getInt("mode", ActionBar.NAVIGATION_MODE_TABS);
		}
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

		// ActionBar ab = getActionBar(); // Duplicated code (see above).
 		// ab.setDisplayShowTitleEnabled(false); // Duplicated code (see above).

		// Debugging: Removing Spinner from ActionBar: Commenting actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// setListNavigation(actionBar); // Works!
		/*
		if (mode == ActionBar.NAVIGATION_MODE_TABS) {
			setTabNavigation(actionBar);
			// setTabNavigation(ab);
		}
		else {
			setListNavigation(actionBar);
			// setListNavigation(ab);
		}
		*/

		// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (end).

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
						actionBar.setSelectedNavigationItem(position);
					}

				});
		*/

		// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

		// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (begin).

//		// For each of the sections in the app, add a tab to the action bar.
//		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
//
//			/*
//			 * Create a tab with text corresponding to the page title defined by the adapter.
//			 * Also specify this Activity object, which implements the TabListener interface,
//			 * as the listener for when this tab is selected.
//			 *
//			 */
//
//			// Debugging: Early getView() of the next tab.
//			actionBar.addTab(actionBar.newTab()
//					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
//					// .setTabListener(new TabListener(null, null, null)));
//					.setTabListener(
//
//					new ActionBar.TabListener() {
//
//						@Override
//						public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
//
//							// Debugging.
////							Log.i(TAG, " ");
////							Log.i(TAG, "onTabReselected(Tab tab, android.app.FragmentTransaction ft)");
////							Log.i(TAG, "tab.getText() = " + tab.getText());
//
//						}
//
//						@Override
//						public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
//
//							mViewPager.setCurrentItem(tab.getPosition());
//
//							// Debugging.
////							Log.i(TAG, " ");
////							Log.i(TAG, "onTabSelected(Tab tab, android.app.FragmentTransaction ft)");
////							Log.i(TAG, "tab.getText() = " + tab.getText());
//
//						}
//
//						@Override
//						public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
//
//							// Debugging.
////							Log.i(TAG, " ");
////							Log.i(TAG, "onTabUnselected(Tab tab, android.app.FragmentTransaction ft)");
////							Log.i(TAG, "tab.getText() = " + tab.getText());
//
//						}
//					})
//
//			);
//
//		}

		// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (end).

		// Debugging: Starting in "Operadores" tab.
		// actionBar.setSelectedNavigationItem(3);
		
	}

	// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

	/*
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		String[] entitiesNames;

		public AppSectionsPagerAdapter(FragmentManager fm) {
			
			super(fm);
			
			ArrayList<String> arrayListEntitiesNames = DatabaseHelper.getAllEntitiesNames();
			entitiesNames = arrayListEntitiesNames.toArray(new String[arrayListEntitiesNames.size()]);
			
		}

		// Debugging: force getView()
		@Override
		public void notifyDataSetChanged() {

			// Debugging.
//			Log.i(TAG, "public void notifyDataSetChanged()");
			
		};
		
		@Override
		public Fragment getItem(int i) {
			
			// Debugging: Entities-based Fragment return (begin).
			
			// Debugging.
//			Log.i(TAG, "public Fragment getItem(int i): " + i);
		
//			ArrayList<String> ArrayListEntitiesNames = dbHelper.getAllEntitiesNames();
//			String[] mEntitiesNames = ArrayListEntitiesNames.toArray(new String[ArrayListEntitiesNames.size()]);

			// Debugging: "Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less...".
			// Debugging: Return to (select) the selected point.
			return DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(entitiesNames[i], 0, 0);
			// return DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(entitiesNames[i], mSubmenuIndex, mSubmenuItemIndex);
			// return DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(entitiesNames[i]);
			// return new DynamicGridSectionFragment(mEntitiesNames[i]);

//			switch (i) {
//			case 0:
//				// Debugging.
//				return new DynamicGridSectionFragment("Parametro");
//				// return new ParametrosSectionFragment();
//			case 1:
//				// Debugging.
//				return new DynamicGridSectionFragment("Visor");
//				// return new VisoresSectionFragment();
//			case 2:
//				// Debugging.
//				return new DynamicGridSectionFragment("VisorPosto");
//				// return new VisorPostoSectionFragment();
//			case 3:
//				// Debugging (begin)
//				return new DynamicGridSectionFragment("Operador");
//				// return new DynamicOperadoresListSectionFragment();
//				// return new OperadoresListSectionFragment();
				
//				dbHelper = new DatabaseHelper(ArranqueActivity.getAppApplicationContext());
//				List listTbOperadores = dbHelper.getAllTbOperadores();
//				if (listTbOperadores.isEmpty()) {
//					return new OperadoresEditSectionFragment();
//				}
//				else {
//					return new OperadoresListSectionFragment();
//				}
				
				// return new LaunchpadSectionFragment();				
				// return new OperadoresSectionFragment();
				
				// Debugging (end)
				
				// case 0:
				// // The first section of the app is the most interesting -- it
				// // offers a launchpad into the other demonstrations in this
				// example
				// // application.
				// return new LaunchpadSectionFragment();

//			default:
//				// The other sections of the app are dummy placeholders.
//				Fragment fragment = new DummySectionFragment();
//				Bundle args = new Bundle();
//				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
//				fragment.setArguments(args);
//				return fragment;
//			}
			
			// Debugging: Entities-based Fragment return (end).
			
		}

		@Override
		public int getCount() {
			
			// Debugging: Entities-based count return (begin).
			
			return entitiesNames.length;
			// return 4;
			
			// Debugging: Entities-based count return (end).
			
		}

		@Override
		public CharSequence getPageTitle(int position) {

			// Debugging: Entities-based page titles (begin).
			
			// Debugging.
//			Log.i(TAG, "public CharSequence getPageTitle(int position): " + position);
			
//			ArrayList<String> ArrayListEntitiesNames = dbHelper.getAllEntitiesNames();
//			String[] mEntitiesNames = ArrayListEntitiesNames.toArray(new String[ArrayListEntitiesNames.size()]);
			return entitiesNames[position];

//			switch (position) {
//			case 0:
//				return "Parametros";
//			case 1:
//				return "Visores";
//			case 2:
//				return "Visor Posto";
//			case 3:
//				return "Operadores";
//			default:
//				return "";
//			}
			// return "Section " + (position + 1);
			
			// Debugging: Entities-based page titles (end).
			
		}
		
	}
	*/

	// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

	// Tab navigation option (begin).

	// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (begin).

	/*
	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {

		// Debugging: Grid/Edit option.
		// Debugging: onTabSelected don't works!
//		Log.i(TAG, "onTabSelected(Tab tab, android.app.FragmentTransaction ft)");
//		Log.i(TAG, "Tab tab = " + tab.getText());
//		Log.i(TAG, "android.app.FragmentTransaction ft = " + ft.toString());

	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {

		// Debugging: Grid/Edit option.
		// Debugging: onTabUnselected don't works!
//		Log.i(TAG, "onTabUnselected(Tab tab, android.app.FragmentTransaction ft)");
//		Log.i(TAG, "Tab tab = " + tab.getText());
//		Log.i(TAG, "android.app.FragmentTransaction ft = " + ft.toString());

	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {

		// Debugging: Grid/Edit option.
		// Debugging: onTabReselected don't works!
//		Log.i(TAG, "onTabReselected(Tab tab, android.app.FragmentTransaction ft)");
//		Log.i(TAG, "Tab tab = " + tab.getText());
//		Log.i(TAG, "android.app.FragmentTransaction ft = " + ft.toString());

	}
	*/

	// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (end).

	// Tab navigation option (end).

	// Debugging: Dropdown menu + Action bar navigation (http://www.tutorialspoint.com/android/android_spinner_control.htm) (begin).

//	@Override
//	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//		// On selecting a spinner item
//		String item = parent.getItemAtPosition(position).toString();
//
//		// Showing selected spinner item
//		Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//	}

//	public void onNothingSelected(AdapterView<?> arg0) {
//	}

	// Debugging: Dropdown menu + Action bar navigation (http://www.tutorialspoint.com/android/android_spinner_control.htm) (end).

	// Debugging: Dropdown menu + Action bar navigation (http://www.vogella.com/tutorials/AndroidActionBar/article.html#actionbar_navigation_dropdown) (begin).

//	@Override
//	public boolean onNavigationItemSelected(int position, long id) {
//
//		// When the given dropdown item is selected, show its contents in the container view.
//
//		/*
//		Fragment fragment = new DummySectionFragment();
//		Bundle args = new Bundle();
//		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
//		fragment.setArguments(args);
//		getFragmentManager().beginTransaction()
//				.replace(R.id.container, fragment).commit();
//		*/
//
//		return true;
//	}

	// Debugging: Dropdown menu + Action bar navigation (http://www.vogella.com/tutorials/AndroidActionBar/article.html#actionbar_navigation_dropdown) (end).

	// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (begin).

	// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (begin).

	/*
	@Override
	protected void onSaveInstanceState(Bundle outState) {

		outState.putInt("mode", getActionBar().getNavigationMode());
		super.onSaveInstanceState(outState);

	}
	*/

	// Debugging (tipxxi): Useless? Scrap code (in this specific context. Maybe can not be extended to ControlPanelActivity (and the same code (?) in ComunicaXXILight)) (end).

	// http://stackoverflow.com/questions/15191550/android-create-a-simple-menu-programmatically (begin).

	/**
	 * Initialize the contents of the Activity's standard options menu.  You
	 * should place your menu items in to <var>menu</var>.
	 *
	 * <p>This is only called once, the first time the options menu is
	 * displayed.  To update the menu every time it is displayed, see
	 * {@link #onPrepareOptionsMenu}.
	 *
	 * <p>The default implementation populates the menu with standard system
	 * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
	 * they will be correctly ordered with application-defined menu items.
	 * Deriving classes should always call through to the base implementation.
	 *
	 * <p>You can safely hold on to <var>menu</var> (and any items created
	 * from it), making modifications to it as desired, until the next
	 * time onCreateOptionsMenu() is called.
	 *
	 * <p>When you add items to the menu, you can implement the Activity's
	 * {@link #onOptionsItemSelected} method to handle them there.
	 *
	 * @param menu The options menu in which you place your items.
	 *
	 * @return You must return true for the menu to be displayed;
	 *         if you return false it will not be shown.
	 *
	 * @see #onPrepareOptionsMenu
	 * @see #onOptionsItemSelected
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		mMenu = menu;

		// public MenuItem add(int groupId, int itemId, int order, CharSequence title);

		// Approach 0: http://stackoverflow.com/questions/6543745/create-sub-menu-in-android-through-code: OK.

//		int FILE = 0;
//		int EDIT = 1;
//
//		int NEW_MENU_ITEM = Menu.FIRST;
//		int SAVE_MENU_ITEM = NEW_MENU_ITEM + 1;
//
//		int UNDO_MENU_ITEM = SAVE_MENU_ITEM + 1;
//		int REDO_MENU_ITEM = UNDO_MENU_ITEM + 1;
//
//		SubMenu fileMenu = menu.addSubMenu("File");
//		SubMenu editMenu = menu.addSubMenu("Edit");
//		fileMenu.add(FILE, NEW_MENU_ITEM, 0, "new");
//		fileMenu.add(FILE, SAVE_MENU_ITEM, 1, "save");
//		editMenu.add(EDIT, UNDO_MENU_ITEM, 0, "undo");
//		editMenu.add(EDIT, REDO_MENU_ITEM, 1, "redo");
//
//		return super.onCreateOptionsMenu(menu);

		// Approach 1:

		// private static final int MENU_ITEM_ITEM1 = 1;

//		menu.add(Menu.NONE, 1, Menu.NONE, "Item name 01");
//		menu.add(Menu.NONE, 2, Menu.NONE, "Item name 02");
//		menu.add(Menu.NONE, 3, Menu.NONE, "Item name 03");
//		menu.add(Menu.NONE, 4, Menu.NONE, "Item name 04");
//
//		SubMenu subMenu = menu.findItem(1).getSubMenu(); // Don't works!
//
//		// menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Item name");
//
//		return true;

//		SubMenu subMenu = menu.findItem(R.id.submenu1).getSubMenu();
//		subMenu.setHeaderTitle("SubMenu 1 (programmatically)"); // Don't Works!
//
//		subMenu.add("SubMenu 1 Item 3 (programmatically)");
//		subMenu.add("SubMenu 1 Item 4 (programmatically)");
//		subMenu.add("SubMenu 1 Item 5 (programmatically)");
//
//		SubMenu subMenu2 = menu.addSubMenu("SubMenu 2 (programmatically)");
//		subMenu2.add("SubMenu 2 Item 1 (programmatically)");
//		subMenu2.add("SubMenu 2 Item 2 (programmatically)");
//		subMenu2.add("SubMenu 2 Item 3 (programmatically)");

		// Approach 2:

		// private final int MenuItem_EditId = 1, MenuItem_DeleteId = 0;

//		MenuItem edit_item = menu.add(0, 1, 0, "edit");
//		// MenuItem edit_item = menu.add(0, MenuItem_EditId, 0, R.string.edit);
//		// edit_item.setIcon(R.drawable.edit);
//		edit_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//		MenuItem delete_item = menu.add(0, 0, 1, "edit");
//		delete_item.setIcon(R.drawable.delete);
//		delete_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//		return super.onCreateOptionsMenu(menu);

		// Debugging: tipxxi.
//		mEntitiesNames = DatabaseHelper.getAllEntitiesNames().toArray(new String[DatabaseHelper.getAllEntitiesNames().size()]);

		/*

		// Setting the number of elements (MenuItem) in ActionBar for each page.

		mActionBarMenuItemNumber = new int[mEntitiesNames.length - 1][2];

		for (int i = 0, entitiesNamesLength = 0, index = 0, menuItemNumber = 0; i < mEntitiesNames.length; i++) {

			entitiesNamesLength = entitiesNamesLength + mEntitiesNames[i].length();

			if (entitiesNamesLength > 60) {

				if (menuItemNumber == 0) {
					menuItemNumber = 1;
				}

				mActionBarMenuItemNumber[index][0] = menuItemNumber;
				mActionBarMenuItemNumber[index][1] = entitiesNamesLength - mEntitiesNames[i].length();
				entitiesNamesLength = mEntitiesNames[i].length();
				menuItemNumber = 1;
				index++;

			}
			else {
				menuItemNumber++;
			}

			// Last element of mEntitiesNames[].
			if (i == (mEntitiesNames.length - 1)) {
				mActionBarMenuItemNumber[index][0] = menuItemNumber;
				mActionBarMenuItemNumber[index][1] = entitiesNamesLength;
				mMaxPageNumber = ++index;
			}

		}

		// Debugging: Only to run!
//		// Adding MenuItem with previous page (REW) feature.
//		menu.add(Menu.NONE, -1, Menu.NONE, "").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW).setIcon(R.drawable.ic_ic_media_rew);

		// Setting the mSpacing.
		mSpacing = getStringWithLengthAndFilledWithCharacter((60 - mActionBarMenuItemNumber[0][1]) / (mActionBarMenuItemNumber[0][0]), " ".charAt(0));

		// Adding MenuItem with entity name.

		for (int i = 0; i < mActionBarMenuItemNumber[0][0]; i++) {

			menu.add(Menu.NONE, i, Menu.NONE, mSpacing + mEntitiesNames[i] + mSpacing).setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
			mLastMenuItemEntity = i;

		}

		// Debugging: Only to run!
//		// Adding MenuItem with next page (FF) feature.
//		menu.add(Menu.NONE, -99, Menu.NONE, "").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW).setIcon(R.drawable.ic_ic_media_ff);

		*/

		mFragmentManager = getSupportFragmentManager();

		/*

		// Debugging: Adding "CAPTION" class variable (begin).

		// Setting the text of breadcrumb.

		// Debugging: Retrieve entity field value with any attribute.

		*/

		// Debugging: Navigation flow control (begin).

		if (mForward == null) {

			// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
			mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ENTITY_CAPTION"), null));
			// mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
			// mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntitiesNames[0], new String("CAPTION"), null));

		}

		// mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntitiesNames[0], new String("CAPTION"), null));

		// Debugging: Navigation flow control (end).

		// mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntitiesNames[0], new String("CAPTION"), null));
		// mTextViewBreadcrumb.setText((String) DatabaseHelper.getEntityFieldAttributeValue(mEntitiesNames[0], new String("CAPTION")));
		// mTextViewBreadcrumb.setText(mEntitiesNames[0]);

		// Debugging: Adding "CAPTION" class variable (end).

		/*

		// Setting the page and the maximum page number.
		mPageNumber = 1;
		mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");

		*/

		// Filling the screen (Fragment) with first grid (default).

		// Debugging: "Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less..." (begin).

		// Debugging: tipxxi.

		// Debugging: Return to (select) the selected point.
		mFragment = DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(mEntityName, mSubmenuIndex, mSubmenuItemIndex);
		// mFragment = DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(mEntityName);
		// mFragment = DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(mEntitiesNames[0]);
		// mFragment = new DynamicGridSectionFragment(mEntitiesNames[0]);

		// Debugging: "Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less..." (end).

		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.add(mRelativeLayout.getId(), mFragment);
		mFragmentTransaction.addToBackStack(null);
		mFragmentTransaction.commit();

		mPreviousMenuItemId = 0;

		// public MenuItem setActionView(View view);

		/*
		MenuItem menuItem05 = menu.add("Operador").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);

		textView.setText("Operador");
		menuItem05.setActionView(textView); // // Works! (clickable).
		// menuItem05.setActionView(linearLayout); // Don't works! (not clickable).
		// menuItem05.setActionView(button); // Works! (clickable).
		// menuItem05.setIcon(android.R.drawable.btn_plus); // Works! (without setActionView() (above)).

		// menuItem05.setTitle("Asshole!");
		// menuItem05.getActionView().setBackgroundColor(getResources().getColor(R.color.blueviolet)); // Works! (ActionView not null).
		// menuItem05.getActionView().setBackgroundColor(getResources().getColor(R.color.blueviolet)); // Error: null pointer.
		// menuItem05.setEnabled(false); // Works!

		menuItem05.getActionView().setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				// Debugging.
				// Utils.displayToast("View.OnClickListener() > onMenuItemClick (MenuItem menuItem05)");
				// mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.purple)); // Works!

				// Debugging: Opening DynamicGridSectionFragment(mEntitiesNames[i]) (begin).

//				Intent intent = new Intent(getApplicationContext(), DynamicGridSectionFragment.class);
//
//				// startActivityFromFragment(new DynamicGridSectionFragment("Operador"), intent, 100);
//				startActivityFromFragment(new android.app.Fragment(), intent, 100);

				// return new DynamicGridSectionFragment(mEntitiesNames[i]);

				// Debugging: Opening DynamicGridSectionFragment(mEntitiesNames[i]) (end).

				// Debugging: Starting other Activity (begin): Works!

//				Intent intent = new Intent(getApplicationContext(), DynamicEditSectionActivity.class);
//
//				// Defining the action in edit screen.
//				intent.putExtra("action", "C"); // Create entity.
//
//				// Defining the entity to create.
//				intent.putExtra("entityName", "Operador");
//				// intent.putExtra("entityName", lEntityName);
//
//				startActivityForResult(intent, 100);
//
//				overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
//				// getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

				// Debugging: Starting other Activity (end).

			}

		});

		menuItem05.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // Don't works with setActionView() (not clickable).

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				Utils.displayToast("MenuItem.OnMenuItemClickListener() > onMenuItemClick (MenuItem menuItem05)");
				return true;

			}

		});
		*/

//		public static void setOnMenuItemSelected(final MenuItem menuItem, final Runnable runnable) {
//			final View view = menuItem.getActionView();
//			if (view != null) {
//				view.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(final View v) {
//						runnable.run();
//					}
//
//				});
//			}
//			else {
//				menuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//
//					@Override
//					public boolean onMenuItemClick(final MenuItem item) {
//						runnable.run();
//						return true;
//					}
//
//				});
//			}
//		}

		/*
		MenuItem menuItem06 = menu.add("").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS).setIcon(android.R.drawable.ic_media_ff); // Works! (without setActionView() (above)).
//		MenuItem menuItem06 = menu.add("").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS).setIcon(android.R.drawable.btn_plus); // Works! (without setActionView() (above)).

		menuItem06.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				// Debugging.
				Utils.displayToast("onMenuItemClick (MenuItem menuItem06)");

				// Removing ItemMenus from ActionBar.

				item.getMenuInfo();
				item.getActionView();

				// Debugging.
				// mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.stacked_green)); // Works!

				return true;

			}

		});
		*/

//		MenuItem menuItem3 = menu.add(0, 3, 0, "menuItem3");
//		menuItem3.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
//
//		MenuItem menuItem4 = menu.add(0, 4, 0, "menuItem4");
//		menuItem4.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		return super.onCreateOptionsMenu(menu);

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
////
//		getMenuInflater().inflate(R.menu.main, menu);
//
////		mMenuItem = menu.findItem(R.id.menu_spinner);
////		setupSpinner(mMenuItem);
//
//		// Debugging: Adding spinner in action bar (begin).
//
////		mMenuItem2 = menu.findItem(R.id.menu_spinner2);
////		setupSpinner(mMenuItem2);
//
//		// Debugging: Adding spinner in action bar (end).
////
//		// Debugging: Creating programmatically a new Spinner (begin).
//
//		// public interface Menu {
//		// public interface MenuItem {
//
////		menu.addSubMenu("Test One"); // Add item in android:icon="@android:drawable/ic_menu_more".
////		menu.addSubMenu("Test Two"); // Add item in android:icon="@android:drawable/ic_menu_more".
////		menu.add("Bitch!"); // Add item in android:icon="@android:drawable/ic_menu_more".
////		menu.addSubMenu(1, 1, 1, "One"); // Add item in android:icon="@android:drawable/ic_menu_more".
////		menu.addSubMenu(2, 2, 2, "Two"); // Add item in android:icon="@android:drawable/ic_menu_more".
////
////		menu.add(2, 2, 2, "2");
//
//		// Adding menu with groups.
//
////		/**
////		 * Add a new item to the menu. This item displays the given title for its
////		 * label.
////		 *
////		 * @param groupId The group identifier that this item should be part of.
////		 *        This can be used to define groups of items for batch state
////		 *        changes. Normally use {@link #NONE} if an item should not be in a
////		 *        group.
////		 * @param itemId Unique item ID. Use {@link #NONE} if you do not need a
////		 *        unique ID.
////		 * @param order The order for the item. Use {@link #NONE} if you do not care
////		 *        about the order. See {@link MenuItem#getOrder()}.
////		 * @param title The text to display for the item.
////		 * @return The newly added menu item.
////		 */
////		public MenuItem add(int groupId, int itemId, int order, CharSequence title);
//
//		// Item added in android:icon="@android:drawable/ic_menu_more".
//
////		menu.add(1, 1, 1, "Menu Item 1"); // Works! (int order).
////		menu.add(2, 2, 2, "Menu Item 2"); // Works! (int order).
////		menu.add(2, 3, 3, "Menu Item 3"); // Works! (int order).
////
////		menu.setGroupEnabled(1, false); // Works!
////		// public void setGroupEnabled(int group, boolean enabled);
////		menu.setGroupVisible(2, true); // Works!
////		//public void setGroupVisible(int group, boolean visible);
////		// menu.setGroupCheckable(2, true, false); // Works!
////		menu.setGroupCheckable(2, true, true); // Works!
////		// public void setGroupCheckable(int group, boolean checkable, boolean exclusive);
////		menu.setQwertyMode(true);
////		// public void setQwertyMode(boolean isQwerty);
//
////		/**
////		 * Add a new sub-menu to the menu. This item displays the given
////		 * <var>title</var> for its label. To modify other attributes on the
////		 * submenu's menu item, use {@link SubMenu#getItem()}.
////		 *<p>
////		 * Note that you can only have one level of sub-menus, i.e. you cannnot add
////		 * a subMenu to a subMenu: An {@link UnsupportedOperationException} will be
////		 * thrown if you try.
////		 *
////		 * @param groupId The group identifier that this item should be part of.
////		 *        This can also be used to define groups of items for batch state
////		 *        changes. Normally use {@link #NONE} if an item should not be in a
////		 *        group.
////		 * @param itemId Unique item ID. Use {@link #NONE} if you do not need a
////		 *        unique ID.
////		 * @param order The order for the item. Use {@link #NONE} if you do not care
////		 *        about the order. See {@link MenuItem#getOrder()}.
////		 * @param title The text to display for the item.
////		 * @return The newly added sub-menu
////		 */
////		SubMenu addSubMenu(final int groupId, final int itemId, int order, final CharSequence title);
//
////		menu.addSubMenu(1, 1, 1, "SubMenu"); // Don't Works!
//
//		// http://stackoverflow.com/questions/7042958/android-adding-a-submenu-to-a-menuitem-where-is-addsubmenu
//
//		SubMenu subMenu = menu.findItem(R.id.submenu1).getSubMenu();
//		subMenu.setHeaderTitle("SubMenu 1 (programmatically)"); // Don't Works!
//
//		subMenu.add("SubMenu 1 Item 3 (programmatically)");
//		subMenu.add("SubMenu 1 Item 4 (programmatically)");
//		subMenu.add("SubMenu 1 Item 5 (programmatically)");
//
//		SubMenu subMenu2 = menu.addSubMenu("SubMenu 2 (programmatically)");
//		subMenu2.add("SubMenu 2 Item 1 (programmatically)");
//		subMenu2.add("SubMenu 2 Item 2 (programmatically)");
//		subMenu2.add("SubMenu 2 Item 3 (programmatically)");
//
//		// http://www.itcsolutions.eu/2011/09/26/android-tutorial-how-to-add-a-menu-programmatic-vs-declarative-solution/
//
//		// Debugging: Creating programmatically a new Spinner (end).
//
//		// Debugging: Search feature (begin).
//		/*
//		mSearchItem = menu.findItem(R.id.menu_search);
//		mSearchItem
//				.setVisible(getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS);
//		mSearch = (EditText) mSearchItem.getActionView().findViewById(
//				R.id.search);
//		mDelete = (Button) mSearchItem.getActionView().findViewById(
//				R.id.delete);
//		mDelete.setVisibility(mSearch.getText().length() > 0 ? View.VISIBLE
//				: View.GONE);
//		mSearch.addTextChangedListener(new TextWatcher() {
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before,
//									  int count) {
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start,
//										  int count, int after) {
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//				mDelete.setVisibility(s.length() > 0 ? View.VISIBLE
//						: View.GONE);
//			}
//		});
//		*/
//		// Debugging: Search feature (end).
//
//		return true;
//
//	}

	// http://stackoverflow.com/questions/15191550/android-create-a-simple-menu-programmatically (end).

	// Debugging (tipxxi): Scrap code (begin).

	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		// http://stackoverflow.com/questions/6543745/create-sub-menu-in-android-through-code (begin).

		// switch (menuItem.getItemId()) {
		//	case 1:
		//		Utils.displayToast("switch > Item name 01 (menu.add)!");
		//		break;
		// }

		// Cleaning the screen (Approach 01).

		// mRelativeLayout.removeAllViews(); // Don't works! (remove the mTextViewBreadcrumb).

		// http://stackoverflow.com/questions/6543745/create-sub-menu-in-android-through-code (end).

		if (menuItem.getItemId() != -1 &&
				menuItem.getItemId() != -99) {

			if (menuItem.getItemId() != mPreviousMenuItemId) {

				// Cleaning the screen (Fragment) with previous grid.

				if (mFragmentTransaction != null &&
						mFragmentManager != null &&
						mFragment != null) {

					mFragmentTransaction = mFragmentManager.beginTransaction();
					mFragmentTransaction.remove(mFragment);
					mFragmentTransaction.commit();

				}

				// Filling the screen (Fragment) with selected grid.

				// Debugging: "Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less..." (begin).

				// Debugging: Return to (select) the selected point.
				mFragment = DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(menuItem.getTitle().toString().trim(), mSubmenuIndex, mSubmenuItemIndex);
				// mFragment = DynamicGridSectionFragment.DynamicGridSectionFragmentNewInstance(menuItem.getTitle().toString().trim());
				// mFragment = new DynamicGridSectionFragment(menuItem.getTitle().toString().trim());

				// Debugging: "Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less..." (end).

				mFragmentTransaction = mFragmentManager.beginTransaction();
				mFragmentTransaction.add(mRelativeLayout.getId(), mFragment);
				mFragmentTransaction.addToBackStack(null);
				mFragmentTransaction.commit();

				// Debugging: Adding "CAPTION" class variable (begin).

				// Setting the text of breadcrumb.
				// Debugging: Retrieve entity field value with any attribute.

				// Debugging: Navigation flow control (begin).

				if (mForward == null) {

					// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
					mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ENTITY_CAPTION"), null));
					// mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
					// mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntitiesNames[0], new String("CAPTION"), null));

				}

				// mTextViewBreadcrumb.setText((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntitiesNames[0], new String("CAPTION"), null));

				// Debugging: Navigation flow control (end).

				// mTextViewBreadcrumb.setText((String) DatabaseHelper.getEntityFieldAttributeValue(menuItem.getTitle().toString().trim(), new String("CAPTION"), null));
				// mTextViewBreadcrumb.setText((String) DatabaseHelper.getEntityFieldAttributeValue(menuItem.getTitle().toString().trim(), new String("CAPTION")));
				// mTextViewBreadcrumb.setText(menuItem.getTitle().toString().trim());

				// Debugging: Adding "CAPTION" class variable (end).

				mPreviousMenuItemId = menuItem.getItemId();

			}
		}
		// Previous page (REW) feature.
		else if(menuItem.getItemId() == -1) {

			if (mPageNumber >= 2) {

				// Updating and setting the page number.
				mPageNumber--;
				mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");

				// Removing the previous MenuItem from ActionBar.
				mMenu.removeGroup(Menu.NONE);

				// Debugging: Only to run!
//				// Adding MenuItem with previous page (REW) feature.
//				mMenu.add(Menu.NONE, -1, Menu.NONE, "").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW).setIcon(R.drawable.ic_ic_media_rew);

				// Setting the mSpacing.
				mSpacing = getStringWithLengthAndFilledWithCharacter((60 - mActionBarMenuItemNumber[mPageNumber - 1][1]) / (mActionBarMenuItemNumber[mPageNumber - 1][0]), " ".charAt(0));

				// Adding MenuItem with entity name.

				int j = (mLastMenuItemEntity - (mActionBarMenuItemNumber[mPageNumber][0] + mActionBarMenuItemNumber[mPageNumber - 1][0]) + 1);
				int k = (mLastMenuItemEntity - mActionBarMenuItemNumber[mPageNumber][0]) + 1;
				for (int i = j; i < k; i++) {

					// Debugging: tipxxi.
					// mMenu.add(Menu.NONE, i, Menu.NONE, mSpacing + mEntitiesNames[i] + mSpacing).setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
					mLastMenuItemEntity = i;

				}

				// Debugging: Only to run!
//				// Adding MenuItem with next page (FF) feature.
//				mMenu.add(Menu.NONE, -99, Menu.NONE, "").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT).setIcon(R.drawable.ic_ic_media_ff);

			}

		}
		// Next page (FF) feature.
		else if(menuItem.getItemId() == -99) {

			if (mPageNumber < mMaxPageNumber) {

				// Updating and setting the page number.
				mPageNumber++;
				mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");

				// Removing the previous MenuItem from ActionBar.
				mMenu.removeGroup(Menu.NONE);

				// Debugging: Only to run!
//				// Adding MenuItem with previous page (REW) feature.
//				mMenu.add(Menu.NONE, -1, Menu.NONE, "").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW).setIcon(R.drawable.ic_ic_media_rew);

				// Setting the mSpacing.
				mSpacing = getStringWithLengthAndFilledWithCharacter((60 - mActionBarMenuItemNumber[mPageNumber - 1][1]) / (mActionBarMenuItemNumber[mPageNumber - 1][0]), " ".charAt(0));

				// Adding MenuItem with entity name.

				int j = (mLastMenuItemEntity + 1) + mActionBarMenuItemNumber[mPageNumber - 1][0];
				for (int i = mLastMenuItemEntity + 1; i < j; i++) {

					// Debugging: tipxxi.
					// mMenu.add(Menu.NONE, i, Menu.NONE, mSpacing + mEntitiesNames[i] + mSpacing).setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
					mLastMenuItemEntity = i;

				}

				// Debugging: Only to run!
//				// Adding MenuItem with next page (FF) feature.
//				mMenu.add(Menu.NONE, -99, Menu.NONE, "").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT).setIcon(R.drawable.ic_ic_media_ff);

			}
		}

		// Debugging: Action Mode / Settings feature (begin).

//		if (menuItem.getItemId() == R.id.menu_settings) {
//			// Handle Settings
//			ret = true;
//		}

		// Debugging: Action Mode / Settings feature (end).

		// Debugging: Search feature (begin).

//		else if (menuItem.getItemId() == R.id.menu_toggle) {
//			ActionBar ab = getActionBar();
//			if (ab.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
//				setListNavigation(ab);
//				mSearchItem.setVisible(false);
//			}
//			else {
//				setTabNavigation(ab);
//				mSearchItem.setVisible(true);
//			}
//			ret = true;
//		}

		// Debugging: Search feature (end).

		// Debugging: Action Mode / Settings feature (begin).

//		else if (menuItem.getItemId() == R.id.menu_actionmode) {
//			startActionMode(mCallback);
//			ret = true;
//		}
//		else {
//			ret = super.onOptionsItemSelected(menuItem);
//		}
//		return ret;

		return super.onOptionsItemSelected(menuItem);

		// Debugging: Action Mode / Settings feature (end).

	}
	*/

	// Debugging (tipxxi): Scrap code (end).

	// Debugging (tipxxi): Scrap code (begin).

	/*
	private void setTabNavigation(ActionBar actionBar) {

		actionBar.removeAllTabs();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setTitle(R.string.app_name);

		// Causes error with "public class ControlPanelActivity extends FragmentActivity" (begin).

//		Tab tab = actionBar
//				.newTab()
//				.setText(R.string.frag1)
//				.setTabListener(
//						new MyTabListener(this, Fragment1.class.getName()));
//		actionBar.addTab(tab);
//
//		tab = actionBar
//				.newTab()
//				.setText(R.string.frag2)
//				.setTabListener(
//						new MyTabListener(this, Fragment2.class.getName()));
//		actionBar.addTab(tab);

		// Causes error with "public class ControlPanelActivity extends FragmentActivity" (end).

		if (mMenuItem != null) {
			mMenuItem.setVisible(false);
		}

	}
	*/

	// Debugging (tipxxi): Scrap code (end).

	// Debugging (tipxxi): Scrap code (begin).

	/*
	// Debugging: Dropdown menu + Action bar navigation (https://github.com/StylingAndroid/StylingActionBar/tree/StylingPart4) (end).

	protected String getStringWithLengthAndFilledWithCharacter(int length, char charToFill) {
		if (length > 0) {
			char[] array = new char[length];
			Arrays.fill(array, charToFill);
			return new String(array);
		}
		return "";
	}
	*/

	// Debugging (tipxxi): Scrap code (end).

	// Debugging: Managing App's Memory (begin).

//		https://developer.android.com/reference/android/app/ActivityManager.html#getMemoryClass()
//
//		Return the approximate per-application memory class of the current device.
//		This gives you an idea of how hard a memory limit you should impose on your
//		application to let the overall system work best. The returned value is in megabytes; the baseline Android memory class is 16 (which happens to be the
//		Java heap limit of those devices); some device with more memory may return 24
//		or even higher numbers.

	@Override
	public void onStop() {

		super.onStop();
		Log.i(TAG, "onStop();"); // Works fine!

	}

	@Override
	public void onTrimMemory(int level) {

		super.onTrimMemory(level);
		Log.i(TAG, "onTrimMemory(" + Integer.toString(level) + ");"); // Don't called!

	}

	@Override
	public void onLowMemory() {

		super.onLowMemory();
		Log.i(TAG, "onLowMemory();"); // Don't called!

	}

	// Debugging: Managing App's Memory (end).

}