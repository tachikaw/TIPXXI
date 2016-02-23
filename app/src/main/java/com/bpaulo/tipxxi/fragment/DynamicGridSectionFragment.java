package com.bpaulo.tipxxi.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.activity.DynamicEditSectionActivity;
import com.bpaulo.tipxxi.database.DatabaseHelper;
import com.bpaulo.tipxxi.util.Utils;
import com.roomorama.caldroid.CaldroidFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

public class DynamicGridSectionFragment extends Fragment {

	private static final String TAG = "DynamicGridSectionFragment";

	private String mEntityName;

	// Debugging: Return to (select) the selected point (begin).

	private int mSubmenuIndex;
	private int mSubmenuItemIndex;
//	private String mSubmenuIndex;
//	private String mSubmenuItemIndex;

	// Debugging: Return to (select) the selected point (end).

	private FrameLayout mFrameLayout;
	private RelativeLayout mRelativeLayout;

	// Debugging: Page Up / Down feature (begin).

	// LinearLayout approach:
	private LinearLayout mLinearLayout, mLinearLayoutListHeader, mLinearLayoutListRow;
	// private LinearLayout mLinearLayout, mLinearLayoutListRow;

	// Debugging: Page Up / Down feature (end).

	private ListView mListView;

	private Button mButtonNew;
	private RelativeLayout.LayoutParams mLayoutParamsButtonNew;

	// Debugging: Customized Android virtual keyboard: Alphanumeric & numeric keyboards.
	// private Button switchKeyboardButton;

	// Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin): ERROR!

//	private TextView textViewToolbar;
//	private RelativeLayout.LayoutParams layoutParamsTextViewToolbar;

	// Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end): ERROR!

	private TextView[] mTextViewEntityColumnHeader, mTextViewEntityColumn;

	// Debugging: Misplaced elements in header.
	// Debugging.
	private int[][] mTextViewEntityColumnHeaderWidth;
	// private int[] mTextViewEntityColumnHeaderWidth;

	// Debugging: Space between the fields in the row of the Grid.
	private TextView[] mTextViewEntityColumnDivider;

	private ViewGroup.LayoutParams mViewGroupLayoutParams;

	private DatabaseHelper mDatabaseHelper;
	private Field[] mFieldList;

	private String[][] mEntityFieldParameters;

	// Debugging: Fixing ONE_REGISTER feature.
	// Debugging: Grid/Edit option.
	// private boolean isButtonNewEnabled;

	// Debugging: Footer (begin).

	private RelativeLayout mRelativeLayoutFooter;
	private RelativeLayout.LayoutParams mLayoutParamsRelativeLayoutFooter;

	// Debugging: Footer (end).

	// Debugging: Page Up / Down feature (begin).

	private Button mButtonPageDown;
	private RelativeLayout.LayoutParams mLayoutParamsButtonPageDown;
	private Button mButtonPageUp;
	private RelativeLayout.LayoutParams mLayoutParamsButtonPageUp;

	// Debugging: Return ArrayList<Object> option.
	private ArrayList<Object> mArrayListObject;
	private Object[] mListObject;

	// Debugging: Return Object[] option.
//	private Object[] mListObject;

	private EntityListAdapter mEntityListAdapter;

	private int mPageNumber = 1;
	private int mMaxPageNumber = 1;
	private TextView mTextViewPageInformation;

	// LinearLayout approach:
	// private LinearLayout.LayoutParams mLayoutParamsTextViewPageInformation;

	// RelativeLayout approach:
	private RelativeLayout.LayoutParams mLayoutParamsTextViewPageInformation;

	private RelativeLayout mRelativeLayoutListHeader;
	private RelativeLayout.LayoutParams mLayoutParamsRelativeLayoutListHeader;

	// Debugging: Page Up / Down feature (end).

	// Debugging: Navigation flow control (begin).

	private Button mButtonExit;
	private RelativeLayout.LayoutParams mLayoutParamsButtonExit;

	// Debugging: Navigation flow control (end).

	// Debugging: Serial protocol (begin).

//	private Button mButtonSend;
//	private RelativeLayout.LayoutParams mLayoutParamsButtonSend;

	// Debugging: Serial protocol (end).

	// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()).
	private BtnOnClickListenerImpl btnOnClickListenerImpl;

	// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()).
	private BtnOnTouchListenerImpl btnOnTouchListenerImpl;

	// Debugging: Navigation flow control (begin).

	private Intent mIntent;

	// Debugging: Navigation flow control (end).

	// Debugging: Serial protocol (begin).

//	private byte[] buffer = new byte[512];
//	private int bufferSize;

	// Debugging: Serial protocol (end).

	// Debugging (tipxxi): Creating a Calendar (begin).

	private Button mButtonCalendar;
	private RelativeLayout.LayoutParams mLayoutParamsButtonCalendar;

	// Debugging (tipxxi): Creating a Calendar (end).

	public DynamicGridSectionFragment() {
		super();
	}

	// Debugging: "Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less..." (begin).

	// Debugging: Return to (select) the selected point.
	public static final DynamicGridSectionFragment DynamicGridSectionFragmentNewInstance(String pEntityName, int pSubmenuIndex, int pSubmenuItemIndex) {
	// public static final DynamicGridSectionFragment DynamicGridSectionFragmentNewInstance(String pEntityName, String pSubmenuIndex, String pSubmenuItemIndex) {
	// public static final DynamicGridSectionFragment DynamicGridSectionFragmentNewInstance(String pEntity) {

		DynamicGridSectionFragment dynamicGridSectionFragment = new DynamicGridSectionFragment();

		dynamicGridSectionFragment.mEntityName = pEntityName;

		// Debugging: Return to (select) the selected point (begin).

		dynamicGridSectionFragment.mSubmenuIndex = pSubmenuIndex;
		dynamicGridSectionFragment.mSubmenuItemIndex = pSubmenuItemIndex;

		// Debugging: Return to (select) the selected point (end).

		return dynamicGridSectionFragment;

	}

	// public DynamicGridSectionFragment(String pEntity) {
	// 	super();
	// 	mEntityName = pEntity;
	// }

	// Debugging: "Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less..." (end).

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Debugging: Memory problems (begin).

//		/**
//		 * The available memory on the system.  This number should not
//		 * be considered absolute: due to the nature of the kernel, a significant
//		 * portion of this memory is actually in use and needed for the overall
//		 * system to run well.
//		 */
////		public long availMem;
//
//		/**
//		 * The total memory accessible by the kernel.  This is basically the
//		 * RAM size of the device, not including below-kernel fixed allocations
//		 * like DMA buffers, RAM for the baseband CPU, etc.
//		 */
////		public long totalMem;
//
//		/**
//		 * The threshold of {@link #availMem} at which we consider memory to be
//		 * low and start killing background services and other non-extraneous
//		 * processes.
//		 */
////		public long threshold;
//
//		/**
//		 * Set to true if the system considers itself to currently be in a low
//		 * memory situation.
//		 */
////		public boolean lowMemory;
//
//		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
//		ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
//		activityManager.getMemoryInfo(memoryInfo);
//
//		long availMem = memoryInfo.availMem / 1048576L;
//		long totalMem = memoryInfo.totalMem  / 1048576L;
//		long threshold = memoryInfo.threshold  / 1048576L;
//		// Percentage can be calculated for API 16+
//		long percAvail = memoryInfo.availMem / memoryInfo.totalMem;
//
//		Log.i(TAG, "availMem = " + availMem);
//		Log.i(TAG, "totalMem = " + totalMem);
//		Log.i(TAG, "threshold = " + threshold);
//		Log.i(TAG, "percAvail = " + percAvail);

		// Debugging: Memory problems (end).

		// Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin): ERROR!

//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags(
//				WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN); // Useless (the code above works fine alone)?

		// Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end): ERROR!

		// Debugging: Singleton SQLiteOpenHelper.
		mDatabaseHelper = DatabaseHelper.getInstance(getActivity());
		// mDatabaseHelper = new DatabaseHelper(getActivity());

		mFieldList = mDatabaseHelper.getEntityFields(mEntityName);
		mTextViewEntityColumnHeader = new TextView[mFieldList.length];
		// Debugging: Misplaced elements in header.
		// Debugging.
		mTextViewEntityColumnHeaderWidth = new int[mFieldList.length][2];
		// mTextViewEntityColumnHeaderWidth = new int[mFieldList.length];
		// Debugging: Space between the fields in the row of the Grid.
		mTextViewEntityColumnDivider = new TextView[mFieldList.length];
		mEntityFieldParameters = new String[mFieldList.length][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

		// Debugging: FK feature (begin).

//		ArrayList<String> listEntitiesNames = mDatabaseHelper.getAllEntitiesNames();
//		Iterator<String> itListEntitiesNames = listEntitiesNames.iterator();
//		String entityName = "";
//		while (itListEntitiesNames.hasNext()) {
//			entityName = itListEntitiesNames.next();
//			mFieldList = mDatabaseHelper.getEntityFields(entityName);
//			for (int i = 0; i < mFieldList.length; i++) {
//				// Checking if the field is a ID of another entity/table.
//				if (mFieldList[i].getName().startsWith("id") &&
//					listEntitiesNames.contains(mFieldList[i].getName().substring(2, mFieldList[i].getName().length()))) {
//					// Create FK in entity/table definition.
//					Log.i(TAG, mFieldList[i].getName().substring(2, mFieldList[i].getName().length()));
//				}
//			}
//		}

		// Debugging: FK feature (end).

		// Debugging: Grid/Edit option (begin).

		// Debugging: Fixing ONE_REGISTER feature (begin).

//		isButtonNewEnabled = true;
//
//		if ((Boolean) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ONE_REGISTER"))) {
//		// if ((Boolean) object) {
//
//			// Debugging: Return ArrayList<Object> option.
//			if (mDatabaseHelper.getAllEntityInstances(mEntityName).size() == 1) {
//			// Debugging: Return Object[] option.
////			if (mDatabaseHelper.getAllEntityInstances(mEntityName).length == 1) {
//				isButtonNewEnabled = false;
//			}
//
//		}

		// Debugging: Fixing ONE_REGISTER feature (end).

//		// Checking if ALL entity's fields are not printable: mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("false")
//
//		if (isAllEntityFieldsNotPrintable()) {
//
//			ArrayList<Object> mArrayListObject = mDatabaseHelper.getAllEntityInstances(mEntityName);
//			// Object[] mListObject = mArrayListObject.toArray(new Object[mArrayListObject.size()]);
//
//			if (mArrayListObject.isEmpty()) {
//				// Creating a new entity instance.
//
//				Intent intent = new Intent(getActivity(), DynamicEditSectionActivity.class);
//				// Intent intent = new Intent(getActivity()
//				// .getApplicationContext(),
//				// DynamicOperadoresEditSectionActivity.class);
//
//				// Defining the action in edit screen.
//				intent.putExtra("action", "C"); // Create entity.
//
//				// Defining the entity to create.
//				intent.putExtra("entityName", mEntityName);
//
//				startActivityForResult(intent, 100);
//
//				getActivity().overridePendingTransition(
//						R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
//
//			}
//			else {
//				// Retrieving unique entity instance.
//			}
//
//		}
//
//		if (mDatabaseHelper.getAllEntityInstances(mEntityName).size() == 1) {
//
//			Intent intent = new Intent(getActivity()
//					.getApplicationContext(),
//					DynamicEditSectionActivity.class);
//			// Intent intent = new Intent(getActivity()
//			// .getApplicationContext(),
//			// DynamicOperadoresEditSectionActivity.class);
//
//			// Defining the action in edit screen.
//			intent.putExtra("action", "C"); // Create entity.
//
//			// Defining the entity to create.
//			intent.putExtra("entityName", mEntityName);
//
//			startActivityForResult(intent, 100);
//
//			getActivity().overridePendingTransition(
//					R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
//
//		}

		// Debugging: Grid/Edit option (end).

		// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()).
		btnOnClickListenerImpl = new BtnOnClickListenerImpl();

		// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()).
		btnOnTouchListenerImpl = new BtnOnTouchListenerImpl();

		mViewGroupLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

		// mFrameLayout definition & settings.

		mFrameLayout = new FrameLayout(getActivity());
		// Debugging: The Id is necessary? No calls (findViewById) to this Id.
		// mFrameLayout.setId(R.id.frameLayout1);

		// Debugging: tipxxi.
		mFrameLayout.setPadding(0, 30, 0, 0); // Works!
		// mFrameLayout.setPadding(0, 110, 0, 0); // Works!
		// mFrameLayout.setPadding(0, 120, 0, 0); // Works!

		mFrameLayout.setLayoutParams(mViewGroupLayoutParams);

		// Debugging: Setting background image.
		// mFrameLayout.setBackgroundResource(R.drawable.background);
		// mLinearLayout.setBackgroundResource(R.drawable.caratula_visor_20141002);

		// Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin): ERROR!

//		textViewToolbar = new TextView(getActivity());
//		// textViewToolbar = new TextView(this);
//		textViewToolbar.setId(R.id.textViewToolbar);
//		textViewToolbar.setBackgroundColor(getResources().getColor(R.color.gray));
//		textViewToolbar.setTextColor(getResources().getColor(R.color.white));
//		// textViewToolbar.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);
//		textViewToolbar.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large_Inverse);
//		// textViewToolbar.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);
//		textViewToolbar.setText("DynamicGridSectionFragment");
//		// textViewToolbar.setText("Editing " + intent.getStringExtra("editFieldLabel"));
//
//		// layoutParamsTextViewToolbar = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//		layoutParamsTextViewToolbar = new RelativeLayout.LayoutParams(FILL_PARENT, FILL_PARENT);
//		layoutParamsTextViewToolbar.height = 30;

		// Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end): ERROR!

		// <RelativeLayout
		// android:layout_width="match_parent"
		// android:layout_height="match_parent" >

		// mRelativeLayout definition & settings.

		mRelativeLayout = new RelativeLayout(getActivity());
		// Debugging: The Id is necessary? No calls (findViewById) to this Id.
		// mRelativeLayout.setId(R.id.relativeLayout1);
		RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

		// mLinearLayout definition & settings.

		mLinearLayout = new LinearLayout(getActivity());
		// Debugging: The Id is necessary? No calls (findViewById) to this Id.
		// mLinearLayout.setId(R.id.linearLayout1);
		mLinearLayout.setOrientation(LinearLayout.VERTICAL);
		mViewGroupLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
		mViewGroupLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		mLinearLayout.setLayoutParams(mViewGroupLayoutParams);

		// Debugging: Setting background image (begin).
		// mLinearLayout.setBackgroundResource(R.drawable.caratula_visor_20141002);
		// mLinearLayout.setBackground(background);

		// http://stackoverflow.com/questions/7646766/set-linear-layout-background-dynamically

		// Debugging: Setting background image (end).

		// mListView definition & settings.

		mListView = new ListView(getActivity());

		// Debugging: The Id is necessary? No calls (findViewById) to this Id.
		// mListView.setId(R.id.list);
		mViewGroupLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

		mViewGroupLayoutParams.height = 480; // No more needed?

		mListView.setLayoutParams(mViewGroupLayoutParams);

		mListView.setDividerHeight(3);
		// mListView.setSmoothScrollbarEnabled(true);
		// mListView.setFastScrollEnabled(false);

		// Debugging.
		// mListView.setVerticalScrollBarEnabled(false); // Works fine!
		mListView.setVerticalScrollBarEnabled(true); // Works fine!

		// Debugging.
		mListView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		// mListView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);

		mListView.setHeaderDividersEnabled(true);
		mListView.setFooterDividersEnabled(true);

		// http://stackoverflow.com/questions/2372415/how-to-change-color-of-android-listview-separator-line
		int[] colors = {0, 0xFFFFFFFF, 0};
		// int[] colors = {0, 0xFFFF0000, 0}; // red for the example
		mListView.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		mListView.setDividerHeight(1);
		// mListView.setScrollingCacheEnabled(true);
		// mListView.setStackFromBottom(true);

		// mLinearLayoutListHeader definition & settings.

		// Debugging: Page Up / Down feature (begin).

		// RelativeLayout approach:

		mRelativeLayoutListHeader = new RelativeLayout(getActivity());
		mRelativeLayoutListHeader.setLayoutParams(new android.widget.AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

		// LinearLayout approach:

//		mLinearLayoutListHeader = new LinearLayout(getActivity());
//		// Debugging: The Id is necessary? No calls (findViewById) to this Id.
//		// mLinearLayoutListHeader.setId(R.id.mLinearLayoutListHeader);
//		mLinearLayoutListHeader.setOrientation(LinearLayout.HORIZONTAL);
//		mLinearLayoutListHeader
//				.setLayoutParams(new android.widget.AbsListView.LayoutParams(
//						FILL_PARENT, FILL_PARENT));

		// Debugging: Page Up / Down feature (end).

		// mLinearLayoutListHeader.addView
		// mLinearLayoutListHeader.removeViewAt(index);

		// Debugging: Page information position above the header (labels of the fields) (begin).

		// Debugging: Page Up / Down feature (begin).

		mTextViewPageInformation = new TextView(getActivity());
		mTextViewPageInformation.setId(R.id.mTextViewPageInformation);
		mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");
		mTextViewPageInformation.setGravity(Gravity.CENTER_VERTICAL);
		mTextViewPageInformation.setTextColor(getResources().getColor(R.color.black));
		mTextViewPageInformation.setTextSize(24); // Edited.
		// mTextViewPageInformation.setTextSize(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_TAMANHO_LETRA]));
		mTextViewPageInformation.setTypeface(Typeface.DEFAULT_BOLD);

		// RelativeLayout approach:
		mLayoutParamsTextViewPageInformation = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		// LinearLayout approach:
		// mLayoutParamsTextViewPageInformation = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

		mLayoutParamsTextViewPageInformation.rightMargin = 10; // Works fine in this context! (Edited).
		// mLayoutParamsTextViewPageInformation.topMargin = 50; // Works fine!
		// mLayoutParamsTextViewPageInformation.height = 30; // Don't works in this context (see mTextViewPageInformation.setTextSize(24);)!

		mLayoutParamsTextViewPageInformation.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		// Debugging: Misplaced elements in header (begin).

		// RelativeLayout approach:
		mRelativeLayoutListHeader.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);

		// Debugging: Misplaced elements in header (end).

		// mListView.addHeaderView(mRelativeLayoutListHeader, null, false);

		// LinearLayout approach:

//		mLinearLayoutListHeader.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);
//		mListView.addHeaderView(mLinearLayoutListHeader, null, false);

		// mListView.addHeaderView(mLinearLayoutListHeader);

		// Debugging: Page Up / Down feature (end).

		// Debugging: Page information position above the header (labels of the fields) (end).

		// Debugging: Misplaced elements in header (begin).

		// Filling LinearLayout mLinearLayoutListHeader with a set empty TextView mTextViewEntityColumnHeader
		// to further add views to LinearLayout with index (avoid out of bounds error).
		for (int i = 0, j = 0; i < mFieldList.length; i++) {

			// Loading the parameters for each field.
			mEntityFieldParameters[i] = (String[]) DatabaseHelper.invokeMethod(
					Utils.ENTITY_PACKAGE_NAME + mEntityName,
					"getAttributeParameters",
					mFieldList[i].getName(),
					String.class
			);

			if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {

				mTextViewEntityColumnHeader[j] = new TextView(getActivity());

				mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[j], j);

				mTextViewEntityColumnHeaderWidth[j][0] = Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]);
				mTextViewEntityColumnHeaderWidth[j][1] = Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]);
				// mTextViewEntityColumnHeaderWidth[Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX])] = Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]);

//				mTextViewEntityColumnHeader[Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX])] = new TextView(getActivity());
//				mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX])], Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]));
//				mTextViewEntityColumnHeaderWidth[Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX])] = Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]);

//				mTextViewEntityColumnHeader[j] = new TextView(getActivity());
//				mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[j], j);
//				mTextViewEntityColumnHeaderWidth[j] = Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]);

				j++;

				// Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX])

			}

		}
//		for (int i = 0; i < mFieldList.length; i++) {
//
//			if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {
//
//			}
//
//			mTextViewEntityColumnHeader[i] = new TextView(getActivity());
//			mTextViewEntityColumnDivider[i] = new TextView(getActivity());
//
//			// Debugging: Page Up / Down feature (begin).
//
//			// RelativeLayout approach:
//
//			mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[i], i);
//			mRelativeLayoutListHeader.addView(mTextViewEntityColumnDivider[i], i + 1); // Space between the fields in the row of the Grid.
//
//			// LinearLayout approach:
//
////			mLinearLayoutListHeader.addView(mTextViewEntityColumnHeader[i], i);
////			mLinearLayoutListHeader.addView(mTextViewEntityColumnDivider[i], i + 1); // Space between the fields in the row of the Grid.
//
//			// Debugging: Page Up / Down feature (end).
//
//
//		}

		// Debugging: Misplaced elements in header (end).

		// Populating the grid's header with entity's fields.
		for (int i = 0, j = 0, mRelativeLayoutListHeaderIndex; i < mFieldList.length; i++) {
		// for (int i = 0, j = 0, mTextViewEntityColumnHeaderShift = 0; i < mFieldList.length; i++) {

			// Debugging: Grid/Edit option (begin).

			// Getting the parameters for each field.
			mEntityFieldParameters[i] = (String[]) DatabaseHelper.invokeMethod(
					Utils.ENTITY_PACKAGE_NAME + mEntityName,
					"getAttributeParameters",
					mFieldList[i].getName(),
					String.class
			);

			// Debugging: Grid/Edit option (end).

			if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {

				// Debugging: Misplaced elements in header (begin).

				mTextViewEntityColumnHeader[j] = new TextView(getActivity());
				mTextViewEntityColumnHeader[j].setWidth(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]));
				mTextViewEntityColumnHeader[j].setPadding(10, 10, 10, 10);
				mTextViewEntityColumnHeader[j].setGravity(Gravity.CENTER_VERTICAL);
				mTextViewEntityColumnHeader[j].setText(mEntityFieldParameters[i][Utils.CAPTION]);
				mTextViewEntityColumnHeader[j].setTextColor(getResources().getColor(R.color.black));
				mTextViewEntityColumnHeader[j].setTextSize(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_TAMANHO_LETRA]));
				mTextViewEntityColumnHeader[j].setTypeface(Typeface.DEFAULT_BOLD);
//				mTextViewEntityColumnHeader[i] = new TextView(getActivity());
//				mTextViewEntityColumnHeader[i].setWidth(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]));
//				mTextViewEntityColumnHeader[i].setPadding(10, 10, 10, 10);
//				mTextViewEntityColumnHeader[i].setGravity(Gravity.CENTER_VERTICAL);
//				mTextViewEntityColumnHeader[i].setText(mEntityFieldParameters[i][Utils.CAPTION]);
//				mTextViewEntityColumnHeader[i].setTextColor(getResources().getColor(R.color.black));
//				mTextViewEntityColumnHeader[i].setTextSize(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_TAMANHO_LETRA]));
//				mTextViewEntityColumnHeader[i].setTypeface(Typeface.DEFAULT_BOLD);

				// Debugging: Misplaced elements in header (end).

				// Debugging: Page Up / Down feature (begin).

				// RelativeLayout approach:

				mLayoutParamsRelativeLayoutListHeader = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

				// Debugging: Misplaced elements in header (begin).
				// Debugging: Grid/Edit option.

				// Setting the position of header element using a shift based on the width of the previous header element inserted.
				// RelativeLayout.RIGHT_OF option don't works! RelativeLayout.BELOW works fine (but RelativeLayout.RIGHT_OF,.ABOVE,.LEFT_OF,.ALIGN_RIGHT not).
				mLayoutParamsRelativeLayoutListHeader.addRule(RelativeLayout.BELOW, R.id.mTextViewPageInformation);

				mRelativeLayoutListHeaderIndex = 0;

				// Checking if the COL_INDEX of the element to be included = 0 (in this case, with different layout definitions).
				if (!mEntityFieldParameters[i][Utils.COL_INDEX].equals("0")) {

					mLayoutParamsRelativeLayoutListHeader.leftMargin = 0;

					// Iterating over mTextViewEntityColumnHeaderWidth [COL_INDEX, GRID_LARGURA].
					for (int k = 0; k < mTextViewEntityColumnHeaderWidth.length; k++) {

						// If the COL_INDEX of the element it's greater than the COL_INDEX of mTextViewEntityColumnHeaderWidth element and GRID_LARGURA > 0 (valid).
						if (Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) > mTextViewEntityColumnHeaderWidth[k][0] &&
								mTextViewEntityColumnHeaderWidth[k][1] > 0) {
							// Adding the GRID_LARGURA of mTextViewEntityColumnHeaderWidth element to mLayoutParamsRelativeLayoutListHeader.leftMargin.
							mLayoutParamsRelativeLayoutListHeader.leftMargin = mLayoutParamsRelativeLayoutListHeader.leftMargin + mTextViewEntityColumnHeaderWidth[k][1] + 5;
							//
							mRelativeLayoutListHeaderIndex++;
						}

					}

				}

				// Removing the empty TextView mTextViewEntityColumnHeader previously added in
				// LinearLayout mLinearLayoutListHeader to avoid out of bounds error.
				mRelativeLayoutListHeader.removeViewAt(mRelativeLayoutListHeaderIndex);

				// Adding filled TextView mTextViewEntityColumnHeader.
				mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[j],
						mRelativeLayoutListHeaderIndex,
						mLayoutParamsRelativeLayoutListHeader);

				j++;

//				if (j == 0) {
//				// if (i == 0) {
//					mTextViewEntityColumnHeaderShift = mTextViewEntityColumnHeaderShift + Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]) + 5;
//				}
//				else {
//					mLayoutParamsRelativeLayoutListHeader.leftMargin = mTextViewEntityColumnHeaderShift;
//					mTextViewEntityColumnHeaderShift = mTextViewEntityColumnHeaderShift + Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]) + 5;
//				}
//
//				// Debugging: Page information position above the header (labels of the fields).
//				mLayoutParamsRelativeLayoutListHeader.addRule(RelativeLayout.BELOW, R.id.mTextViewPageInformation);
//
//				mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[i],
//						Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2,
//						mLayoutParamsRelativeLayoutListHeader);
//				mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[i],
//				 		Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2);

				// LinearLayout approach:

//				mLinearLayoutListHeader.addView(mTextViewEntityColumnHeader[i],
//						Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2);

				// mLinearLayoutListHeader.addView(mTextViewEntityColumnHeader[i],
				//							   Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]));

				// Debugging: Misplaced elements in header (end).

				// Debugging: Page Up / Down feature (end).

				// Debugging: Space between the fields in the row of the Grid (begin).

				// Debugging: Misplaced elements in header (begin).

//				mTextViewEntityColumnDivider[i] = new TextView(getActivity());
//				mTextViewEntityColumnDivider[i].setWidth(5);
//				// mTextViewEntityColumnDivider[i].setText("|");
//				// mTextViewEntityColumnDivider[i].setBackgroundColor(0xff000000);

				// Debugging: Misplaced elements in header (end).

				// Debugging: Page Up / Down feature (begin).

				// RelativeLayout approach:

				// Debugging: Misplaced elements in header (begin).

//				mRelativeLayoutListHeader.addView(mTextViewEntityColumnDivider[i]);

//				mRelativeLayoutListHeader.addView(mTextViewEntityColumnDivider[i],
//						(Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2) + 1);

				// Debugging: Misplaced elements in header (end).

				// LinearLayout approach:

//				mLinearLayoutListHeader.addView(mTextViewEntityColumnDivider[i],
//						(Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2) + 1);

				// Debugging: Page Up / Down feature (end).

				// Debugging: Space between the fields in the row of the Grid (end).

			}

		}

		// textViewIdHeader = new TextView(getActivity());
		// textViewIdHeader.setId(R.id.textViewIdHeader);
		// textViewIdHeader.setWidth(100);
		// textViewIdHeader.setGravity(Gravity.CENTER_VERTICAL);
		// textViewIdHeader.setPadding(10, 10, 10, 10);
		// textViewIdHeader.setText(getResources().getString(R.string.id));
		// textViewIdHeader.setTextColor(0xff000000);
		// textViewIdHeader.setTextSize(20);
		// textViewIdHeader.setTypeface(Typeface.DEFAULT_BOLD);
		//
		// mLinearLayoutListHeader.addView(textViewIdHeader);

		// Avoid ListView scroll messy elements.

		// Debugging: Page information position above the header (labels of the fields).
		mListView.addHeaderView(mRelativeLayoutListHeader, null, false);

		// Setting the item click listener.
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {

				// Debugging: calling new DynamicEditSectionActivity.
				mIntent = new Intent(getActivity()
						.getApplicationContext(),
						DynamicEditSectionActivity.class);
//				Intent intent = new Intent(getActivity()
//						.getApplicationContext(),
//						DynamicEditSectionActivity.class);
//				Intent intent = new Intent(getActivity()
//						.getApplicationContext(),
//						DynamicOperadoresEditSectionActivity.class);

				// Sending entity data to edit screen.
				TextView entity = (TextView) view
						.findViewById(R.id.mTextViewEntityColumn);
				EntityValueHolder entityValueHolder = (EntityValueHolder) entity.getTag();

				for (int i = 0; i < entityValueHolder.name.length; i++) {
                    // If field type = T_TABELA, entityValueHolder.value[i] = '"nome" + <entity>' content (not the index in DB).

                    // Debugging: Update case of SQLite referential integrity issues (approach 2) (begin).

                    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

                    // Debugging (tipxxi): Fixing T_BOOL type problems (begin).

                    if (mEntityFieldParameters[i][Utils.TIPO].equals("T_BOOL")) {
                        if (entityValueHolder.value[i].equals(getResources().getString(R.string.sim_PT))) {
                            mIntent.putExtra(entityValueHolder.name[i], "1");
                        }
                        else {
                            mIntent.putExtra(entityValueHolder.name[i], "0");
                        }
                    }
                    else {
                        mIntent.putExtra(entityValueHolder.name[i], entityValueHolder.value[i]);
                    }

                    // mIntent.putExtra(entityValueHolder.name[i], entityValueHolder.value[i]);

                    // Debugging (tipxxi): Fixing T_BOOL type problems (end).

//					if (mEntityFieldParameters[i][Utils.TIPO].equals("T_TABELA")) {
//						intent.putExtra(entityValueHolder.name[i], retrieveFieldValue(entityValueHolder.name[i], entityValueHolder.value[i]));
//					}
//					else {
//						intent.putExtra(entityValueHolder.name[i], entityValueHolder.value[i]);
//					}

                    // mTextViewEntityColumn[i].setText(retrieveFieldValue(mFieldList[i].getName(), mFieldList[i].get(data[position]).toString()));

                    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

                    // Debugging: Update case of SQLite referential integrity issues (approach 2) (end).

                }

				// Defining the action in edit screen.
				mIntent.putExtra("action", "U"); // Update entity.
				// intent.putExtra("action", "U"); // Update entity.

				// Defining the entity to edit.
				mIntent.putExtra("entityName", mEntityName);
				// intent.putExtra("entityName", mEntityName);

				startActivityForResult(mIntent, 100);
				// startActivityForResult(intent, 100);

				// getActivity().overridePendingTransition(R.anim.slide_up,
				// R.anim.slide_down);

				// getActivity().overridePendingTransition(R.anim.slide_in_left,
				// R.anim.slide_out_left);

				getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

				// getActivity()
				// .overridePendingTransition(R.anim.anim_slide_in_right,
				// R.anim.anim_slide_out_right);

			}

		});

		// mButtonPageDown definition & settings.

		// Debugging: Page Up / Down feature (begin).

		mButtonPageDown = new Button(getActivity());
		mButtonPageDown.setId(R.id.mButtonPageDown);
		mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
		mButtonPageDown.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		mButtonPageDown.setHeight(60);
		mButtonPageDown.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium_Inverse);
		mButtonPageDown.setTextColor(getResources().getColor(R.color.white));
		mButtonPageDown.setTypeface(Typeface.DEFAULT_BOLD);
		mButtonPageDown.setText(R.string.page_down);

		mLayoutParamsButtonPageDown = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		mLayoutParamsButtonPageDown.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mLayoutParamsButtonPageDown.width = 155;
		mLayoutParamsButtonPageDown.setMargins(5, 5, 0, 5);

		// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (begin).

		mButtonPageDown.setOnClickListener(btnOnClickListenerImpl);
		// mButtonPageDown.setOnClickListener(new BtnOnClickListenerImpl());

//		mButtonPageDown.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View view) {
//				refreshEntityListPageDown();
//			}
//
//		});

		// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (end).

		// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (begin).

		mButtonPageDown.setOnTouchListener(btnOnTouchListenerImpl);
		// mButtonPageDown.setOnTouchListener(new BtnOnTouchListenerImpl());

//		mButtonPageDown.setOnTouchListener(new View.OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View pView, MotionEvent pMotionEvent) {
//
//				switch (pMotionEvent.getAction()) {
//					case MotionEvent.ACTION_DOWN: {
//						pView.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
//						pView.invalidate();
//						break;
//					}
//					case MotionEvent.ACTION_UP: {
//						pView.getBackground().clearColorFilter();
//						pView.invalidate();
//						break;
//					}
//				}
//				return false;
//
//			}
//
//		});

		// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (end).

		// mButtonPageUp definition & settings.

		mButtonPageUp = new Button(getActivity());
		mButtonPageUp.setId(R.id.mButtonPageUp);
		mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
		mButtonPageUp.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		mButtonPageUp.setHeight(60);
		mButtonPageUp.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium_Inverse);
		mButtonPageUp.setTextColor(getResources().getColor(R.color.white));
		mButtonPageUp.setTypeface(Typeface.DEFAULT_BOLD);
		mButtonPageUp.setText(R.string.page_up);

		mLayoutParamsButtonPageUp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		mLayoutParamsButtonPageUp.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonPageDown);
		mLayoutParamsButtonPageUp.width = 155;
		mLayoutParamsButtonPageUp.setMargins(5, 5, 0, 5);

		// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (begin).

		mButtonPageUp.setOnClickListener(btnOnClickListenerImpl);
		// mButtonPageUp.setOnClickListener(new BtnOnClickListenerImpl());

//		mButtonPageUp.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View view) {
//				refreshEntityListPageUp();
//			}
//
//		});

		// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (end).

		// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (begin).

		mButtonPageUp.setOnTouchListener(btnOnTouchListenerImpl);
		// mButtonPageUp.setOnTouchListener(new BtnOnTouchListenerImpl());

//		mButtonPageUp.setOnTouchListener(new View.OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View pView, MotionEvent pMotionEvent) {
//
//				switch (pMotionEvent.getAction()) {
//					case MotionEvent.ACTION_DOWN: {
//						pView.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
//						pView.invalidate();
//						break;
//					}
//					case MotionEvent.ACTION_UP: {
//						pView.getBackground().clearColorFilter();
//						pView.invalidate();
//						break;
//					}
//				}
//				return false;
//
//			}
//
//		});

		// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (end).

		// Debugging: Page Up / Down feature (end).

		// mButtonNew definition & settings.

		mButtonNew = new Button(getActivity());
		mButtonNew.setId(R.id.mButtonNew);

		// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

		if ((Boolean) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CREATE"), null)) {

			if ((Boolean) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("UNIQUE_REGISTER"), null)) {

				if (mDatabaseHelper.getAllEntityInstances(mEntityName).size() == 1) {

					mButtonNew.setEnabled(false);
					mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

				}
				else {

					mButtonNew.setEnabled(true);
					mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_shape));

				}
			}
			else {

				mButtonNew.setEnabled(true);
				mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_shape));

			}

		}
		else {

			mButtonNew.setEnabled(false);
			mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

		}

		/*
		if ((Boolean) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ONE_REGISTER"), null)) {
			if (mDatabaseHelper.getAllEntityInstances(mEntityName).size() == 1) {
				mButtonNew.setEnabled(false);
				mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));
			}
			else {
				mButtonNew.setEnabled(true);
				mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_shape));
			}
		}
		else {
			mButtonNew.setEnabled(true);
			mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_shape));
		}
		*/

		// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

		// mButtonNew.setEms(10);
		mButtonNew.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		mButtonNew.setHeight(60);
		mButtonNew.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium_Inverse);
		// mButtonNew.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
		mButtonNew.setTextColor(getResources().getColor(R.color.white));
		mButtonNew.setTypeface(Typeface.DEFAULT_BOLD);
		mButtonNew.setText(getResources().getString(R.string.buttonNew));

		mLayoutParamsButtonNew = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		mLayoutParamsButtonNew.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonPageUp);
		// mLayoutParamsButtonNew.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mLayoutParamsButtonNew.width = 155;
		mLayoutParamsButtonNew.setMargins(5, 5, 0, 5);

		// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (begin).

		mButtonNew.setOnClickListener(btnOnClickListenerImpl);
		// mButtonNew.setOnClickListener(new BtnOnClickListenerImpl());

//		mButtonNew.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View view) {
//
//				Intent intent = new Intent(getActivity()
//						.getApplicationContext(),
//						DynamicEditSectionActivity.class);
//
//				// Defining the action in edit screen.
//				intent.putExtra("action", "C"); // Create entity.
//
//				// Defining the entity to create.
//				intent.putExtra("entityName", mEntityName);
//
//				startActivityForResult(intent, 100);
//
//				getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
//
//			}
//		});

		// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (end).

		// Debugging: Button with click effect (begin).

		// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (begin).

		mButtonNew.setOnTouchListener(btnOnTouchListenerImpl);
		// mButtonNew.setOnTouchListener(new BtnOnTouchListenerImpl());

//		mButtonNew.setOnTouchListener(new View.OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View pView, MotionEvent pMotionEvent) {
//				switch (pMotionEvent.getAction()) {
//					case MotionEvent.ACTION_DOWN: {
//						pView.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
//						pView.invalidate();
//						break;
//					}
//					case MotionEvent.ACTION_UP: {
//						pView.getBackground().clearColorFilter();
//						pView.invalidate();
//						break;
//					}
//				}
//				return false;
//			}
//
//		});

		// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (end).

		// Debugging: Button with click effect (end).

		// Debugging: Customized Android virtual keyboard: Alphanumeric & numeric keyboards (begin).

//		switchKeyboardButton = new Button(getActivity());
//		switchKeyboardButton.setEms(10);
//		switchKeyboardButton.setText("Switch Keyboard");
//
//		RelativeLayout.LayoutParams layoutParamsSwitchKeyboardButton = new RelativeLayout.LayoutParams(
//				WRAP_CONTENT, WRAP_CONTENT);
//		layoutParamsSwitchKeyboardButton.addRule(RelativeLayout.ALIGN_BASELINE, R.id.mButtonNew);
//		layoutParamsSwitchKeyboardButton.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.mButtonNew);
//		layoutParamsSwitchKeyboardButton.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonNew);
//
//		switchKeyboardButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View view) {
//				SimpleIME.switchKeyboard();
//			}
//
//		});

		// Debugging: Customized Android virtual keyboard: Alphanumeric & numeric keyboards (end).

		// Debugging: Navigation flow control (begin).

		mButtonExit = new Button(getActivity());
		mButtonExit.setId(R.id.mButtonExit);
		mButtonExit.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
		mButtonExit.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		mButtonExit.setHeight(60);
		mButtonExit.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium_Inverse);
		mButtonExit.setTextColor(getResources().getColor(R.color.white));
		mButtonExit.setTypeface(Typeface.DEFAULT_BOLD);
		mButtonExit.setText(R.string.exit);

		mLayoutParamsButtonExit = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		mLayoutParamsButtonExit.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonNew);
		mLayoutParamsButtonExit.width = 155;
		mLayoutParamsButtonExit.setMargins(5, 5, 0, 5);

		mButtonExit.setOnClickListener(btnOnClickListenerImpl);
		mButtonExit.setOnTouchListener(btnOnTouchListenerImpl);

		// Debugging: Navigation flow control (end).

		// Debugging (tipxxi): Creating a Calendar (begin).

		mButtonCalendar = new Button(getActivity());
		mButtonCalendar.setId(R.id.mButtonCalendar);
		mButtonCalendar.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
		mButtonCalendar.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		mButtonCalendar.setHeight(60);
		mButtonCalendar.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium_Inverse);
		mButtonCalendar.setTextColor(getResources().getColor(R.color.white));
		mButtonCalendar.setTypeface(Typeface.DEFAULT_BOLD);
		mButtonCalendar.setText(R.string.calendar);

		mLayoutParamsButtonCalendar = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		mLayoutParamsButtonCalendar.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonExit);
		mLayoutParamsButtonCalendar.width = 155;
		mLayoutParamsButtonCalendar.setMargins(5, 5, 0, 5);

		mButtonCalendar.setOnClickListener(btnOnClickListenerImpl);
		mButtonCalendar.setOnTouchListener(btnOnTouchListenerImpl);

		// Debugging (tipxxi): Creating a Calendar (end).

		// Debugging: Serial protocol (begin).

//		mButtonSend = new Button(getActivity());
//		mButtonSend.setId(R.id.mButtonSend);
//		mButtonSend.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
//		mButtonSend.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//		mButtonSend.setHeight(60);
//		mButtonSend.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium_Inverse);
//		mButtonSend.setTextColor(getResources().getColor(R.color.white));
//		mButtonSend.setTypeface(Typeface.DEFAULT_BOLD);
//		mButtonSend.setText(R.string.send);
//
//		mLayoutParamsButtonSend = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//		mLayoutParamsButtonSend.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonExit);
//		mLayoutParamsButtonSend.width = 155;
//		mLayoutParamsButtonSend.setMargins(5, 5, 0, 5);
//
//		mButtonSend.setOnClickListener(btnOnClickListenerImpl);
//		mButtonSend.setOnTouchListener(btnOnTouchListenerImpl);

		// Debugging: Serial protocol (end).

		// Debugging: Return to the current page (not always to the first) after creating/updating a register (begin).

		// refreshEntityList() (begin);

		mArrayListObject = mDatabaseHelper.getAllEntityInstances(mEntityName);
		mListObject = mArrayListObject.toArray(new Object[mArrayListObject.size()]);

		mEntityListAdapter = new EntityListAdapter(getActivity()
				.getApplicationContext(), R.layout.fragment_list_row,
				mListObject);

		mListView.setAdapter(mEntityListAdapter);

		// Updating the mMaxPageNumber.

		if (mArrayListObject.isEmpty()) {
			mMaxPageNumber = 1;
		}
		else {
			if ((mArrayListObject.size() % EntityListAdapter.MAX_ROW_DISPLAY) > 0) {
				mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY) + 1;
			}
			else {
				mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY);
			}
		}

		// Returning point is always the first page.
		mPageNumber = 1;

		// Updating the page number.
		updatePageInformation();

		// Disabling/enabling "Page Down" and "Page Up" buttons.

		mButtonPageUp.setEnabled(false);
		mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

		// Debugging: Return ArrayList<Object> option (begin).

		// If no registers OR the number of registers is less than the MAX_ROW_DISPLAY (number of registers for page).

		if (mArrayListObject.size() == 0 ||
				mArrayListObject.size() <= EntityListAdapter.MAX_ROW_DISPLAY) {

			mButtonPageDown.setEnabled(false);
			mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

		}
		else if (mArrayListObject.size() > EntityListAdapter.MAX_ROW_DISPLAY) {

			mButtonPageDown.setEnabled(true);
			mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_shape));

		}

		// refreshEntityList() (end);

		// Debugging: Return to the current page (not always to the first) after creating/updating a register (end).

		// Setting the page and the maximum page number.

		// mPageNumber = 1;

//		if ((mArrayListObject.size() % EntityListAdapter.MAX_ROW_DISPLAY) > 0 ) {
//			mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY) + 1;
//		}
//		else {
//			mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY);
//		}

		// mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");

		// Adding views.

		mLinearLayout.addView(mListView);

		mRelativeLayout.addView(mLinearLayout);

		// Debugging: Footer (begin).

		mRelativeLayoutFooter = new RelativeLayout(getActivity());
		mRelativeLayoutFooter.setBackgroundColor(getResources().getColor(R.color.gray));

		mLayoutParamsRelativeLayoutFooter = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		mLayoutParamsRelativeLayoutFooter.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mLayoutParamsRelativeLayoutFooter.height = 60;

		mRelativeLayoutFooter.addView(mButtonNew, mLayoutParamsButtonNew);

		// Debugging: Page Up / Down feature (begin).

		mRelativeLayoutFooter.addView(mButtonPageDown, mLayoutParamsButtonPageDown);
		mRelativeLayoutFooter.addView(mButtonPageUp, mLayoutParamsButtonPageUp);

		// Debugging: Page Up / Down feature (end).

		// Debugging: Navigation flow control (begin).

		mRelativeLayoutFooter.addView(mButtonExit, mLayoutParamsButtonExit);

		// Debugging: Navigation flow control (end).

		// Debugging (tipxxi): Creating a Calendar (begin).

		mRelativeLayoutFooter.addView(mButtonCalendar, mLayoutParamsButtonCalendar);

		// Debugging (tipxxi): Creating a Calendar (end).

		// Debugging: Serial protocol (begin).

//		mRelativeLayoutFooter.addView(mButtonSend, mLayoutParamsButtonSend);

		// Debugging: Serial protocol (end).

		mRelativeLayout.addView(mRelativeLayoutFooter, mLayoutParamsRelativeLayoutFooter);

		// Debugging: Footer (end).

		// Debugging: Customized Android virtual keyboard: Alphanumeric & numeric keyboards.
		// mRelativeLayout.addView(switchKeyboardButton, layoutParamsSwitchKeyboardButton);

		// Debugging: Removing & Customizing/Creating Title Bar (using any theme): ERROR!
//		mFrameLayout.addView(textViewToolbar, layoutParamsTextViewToolbar);
		// mRelativeLayout.addView(textViewToolbar, layoutParamsTextViewToolbar);

		mFrameLayout.addView(mRelativeLayout, relativeLayoutParams);

		return mFrameLayout;

	}

	// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (begin).

	private class BtnOnClickListenerImpl implements View.OnClickListener {

		@Override
		public void onClick(View view) {

			switch (view.getId()) {

				case R.id.mButtonNew:

					mIntent = new Intent(getActivity()
							.getApplicationContext(),
							DynamicEditSectionActivity.class);
					// Intent intent = new Intent(getActivity()
					//		.getApplicationContext(),
					//		DynamicEditSectionActivity.class);

					// Defining the action in edit screen.
					mIntent.putExtra("action", "C"); // Create entity.
					// intent.putExtra("action", "C"); // Create entity.

					// Defining the entity to create.
					mIntent.putExtra("entityName", mEntityName);
					// intent.putExtra("entityName", mEntityName);

					startActivityForResult(mIntent, 100);
					// startActivityForResult(intent, 100);

					getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

					break;

				case R.id.mButtonPageDown:

					refreshEntityListPageDown();

					break;

				case R.id.mButtonPageUp:

					refreshEntityListPageUp();

					break;

				// Debugging: Navigation flow control (begin).

				case R.id.mButtonExit:

					// Debugging: Return to (select) the selected point (begin).

					mIntent = new Intent();

					mIntent.putExtra("submenuIndex", mSubmenuIndex);
					mIntent.putExtra("submenuItemIndex", mSubmenuItemIndex);

					getActivity().setResult(99, mIntent);

					getActivity().finish();

					// Debugging: Don't returns to the weighing screen!
					// getActivity().finish();

					// setResult(99, mIntent);

					// mIntent = new Intent(getActivity().getApplicationContext(), MenuPrincipalActivity.class);
					// mIntent = new Intent(getActivity().getApplicationContext(),ArranqueActivity.class);

					// startActivity(mIntent);

					// Debugging: Return to (select) the selected point (end).

					getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
					// getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

					break;

				// Debugging: Navigation flow control (end).

				// Debugging (tipxxi): Creating a Calendar (begin).

				case R.id.mButtonCalendar:

					// CaldroidFragment approach:

					/**
					 * Initial params key
					 */

					/*
					public final static String
							DIALOG_TITLE = "dialogTitle",
							MONTH = "month",
							YEAR = "year",
							SHOW_NAVIGATION_ARROWS = "showNavigationArrows",
							DISABLE_DATES = "disableDates",
							SELECTED_DATES = "selectedDates",
							MIN_DATE = "minDate",
							MAX_DATE = "maxDate",
							ENABLE_SWIPE = "enableSwipe",
							START_DAY_OF_WEEK = "startDayOfWeek",
							SIX_WEEKS_IN_CALENDAR = "sixWeeksInCalendar",
							ENABLE_CLICK_ON_DISABLED_DATES = "enableClickOnDisabledDates",
							SQUARE_TEXT_VIEW_CELL = "squareTextViewCell",
							THEME_RESOURCE = "themeResource";
					*/

					Bundle bundle = new Bundle();
					Calendar cal = Calendar.getInstance();
					bundle.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
					bundle.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
					bundle.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
					bundle.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

					// Uncomment this to customize startDayOfWeek.
					// bundle.putInt(CaldroidFragment.START_DAY_OF_WEEK,
					// CaldroidFragment.TUESDAY); // Tuesday

					// Uncomment this line to use Caldroid in compact mode.
					bundle.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

					// Uncomment this line to use dark theme.
					bundle.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);

					CaldroidFragment dialogCaldroidFragment = CaldroidFragment.newInstance("Select a date", 2, 2016);
					// CaldroidFragment dialogCaldroidFragment = CaldroidFragment.newInstance("Select a date", 3, 2013);

					dialogCaldroidFragment.setArguments(bundle);

					dialogCaldroidFragment.show(getActivity().getSupportFragmentManager(), "TAG");
					// dialogCaldroidFragment.show(getSupportFragmentManager(),"TAG");

					// CalendarView approach:

					// Utils.showCalendar(getActivity());

					break;

				// Debugging (tipxxi): Creating a Calendar (end).

				// Debugging: Serial protocol (begin).

//				case R.id.mButtonSend:
//
//					// Obs.: After interrupt() (the readBytes) the serial reader thread, it's possible writeBytes (in sendCommand()) in serial.
//
//					// Sending a command.
//					Comm.PortaCOM_Visor.sendCommand("W", "W"); // "W": Get weight.
//
//					// Waiting for a response.
//					new CountDownTimer(5000, 100){
//
//						@Override
//						public void onTick(long millisUntilFinished) {
//
//							// Do something after 1/10 s.
//
//							bufferSize = Comm.PortaCOM_Visor.readBytes(buffer);
//
//							if (bufferSize > 0) {
//
//								if (new String(buffer, 0, bufferSize).substring(0, bufferSize - 2).equals("ACK")) {
//									Log.i(TAG, "Command sent: ACK received!");
//									// Utils.displayToast("Command sent: ACK received!");
//								}
//								else if (new String(buffer, 0, bufferSize).substring(0, bufferSize - 2).equals("NAK")) {
//									Log.i(TAG, "Command sent: NAK received!");
//									// Utils.displayToast("Command sent: NAK received!");
//								}
//								else {
//									Log.i(TAG, "Command sent: Undefined response!");
//									// Utils.displayToast("Command sent: Undefined response!");
//								}
//
//								cancel();
//
//							}
//
//						}
//
//						@Override
//						public void onFinish() {
//
//							// Do something end times 5s.
//
//							// Checks only the first element of byte[] buffer.
//							if (buffer[0] == 0) {
//							// if (bufferSize == 0) { // Don't works! Not registering previous values!
//
//								Log.i(TAG, "Command not sent!");
//								// Utils.displayToast("Command not sent!");
//
//							}
//
//						}
//
//					}.start();
//
//					// Don't works!
//
//					// ArranqueActivity.mReadThread.start(); // java.lang.IllegalThreadStateException: Thread already started.
//					// ArranqueActivity.startReadThread();  // java.lang.IllegalThreadStateException: Thread already started.
//
//					// Sending '01' to serial port:
//
////					buffer = {byte[512]@829676722528}
////					0 = 48
////					1 = 49
////					2 = 0
////					...
//
//					// Sending '140' to serial port:
//
////					buffer = {byte[512]@829676720448}
////					0 = 2 (Dec) = STX (Start of TeXt).
////					1 = 32 (Dec) = " " (Space).
////					2 = 32 (Dec) = " " (Space).
////					3 = 32 (Dec) = " " (Space).
////					4 = 49 (Dec) = "1".
////					5 = 50 (Dec) = "2".
////					6 = 48 (Dec) = "0".
////					7 = 46 (Dec) = ".".
////					8 = 3 (Dec) = ETX (End of TeXt).
////					9 = 13 (Dec) = CR (Carriage Return).
////					10 = 0
////					...
//
////					buffer = {byte[512]@829676720448}
////					0 = 2 (Dec) = STX (Start of TeXt).
////					1 = 32 (Dec) = " " (Space).
////					2 = 53 (Dec) = "5".
////					3 = 56 (Dec) = "8".
////					4 = 54 (Dec) = "6".
////					5 = 50 (Dec) = "2".
////					6 = 48 (Dec) = "0".
////					7 = 46 (Dec) = ".".
////					8 = 3 (Dec) = ETX (End of TeXt).
////					9 = 13 (Dec) = CR (Carriage Return).
////					10 = 0
////					...
//
////					mReadThread = new ReadThread();
////					mReadThread.start();
//
//					break;

				// Debugging: Serial protocol (end).

			}
		}

	}

	// Debugging: Common .setOnClickListener(new BtnOnClickListenerImpl()) (end).

	// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (begin).

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

	// Debugging: Common .setOnTouchListener(new BtnOnTouchListenerImpl()) (end).

	// Debugging: Refactoring updateSpinnerEditEntityFieldValueColumn (~) and loadSpinnerData (~): duplicated code (begin).

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		// Debugging: Fixing ONE_REGISTER feature (begin).

		// Debugging: Retrieve entity field value with any attribute.
		if ((Boolean) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("UNIQUE_REGISTER"), null)) {
		// if ((Boolean) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ONE_REGISTER"))) {

			// Debugging: Return ArrayList<Object> option.
			if (mDatabaseHelper.getAllEntityInstances(mEntityName).size() == 1) {
				// Debugging: Return Object[] option.
//			if (mDatabaseHelper.getAllEntityInstances(mEntityName).length == 1) {
//
				mButtonNew.setEnabled(false);
				mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

			}
			else {

				mButtonNew.setEnabled(true);
				mButtonNew.setBackground(getResources().getDrawable(R.drawable.button_shape));

			}

		}

//		// Debugging: Grid/Edit option (begin).
//
//		if ((Boolean) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ONE_REGISTER"))) {
//
//			Button button = (Button) getView().findViewById(R.id.mButtonNew);
//
//			// Debugging: Return ArrayList<Object> option.
//			if (mDatabaseHelper.getAllEntityInstances(mEntityName).size() == 1) {
//			// Debugging: Return Object[] option.
////			if (mDatabaseHelper.getAllEntityInstances(mEntityName).length == 1) {
//				button.setEnabled(false);
//			}
//			else {
//				button.setEnabled(true);
//			}
//
//		}
//
//		// Debugging: Grid/Edit option (end).

		// Debugging: Fixing ONE_REGISTER feature (end).

		// Debugging: Page Up / Down feature.
		refreshEntityList();
		// refreshEntityListAtReturn();

//		if (resultCode == 100) {
//			String mywebsite = (String) data.getExtras().get("result");
//		}

	}

	private void refreshEntityList() {

		// Debugging: Return to the current page (not always to the first) after creating/updating a register (begin).

		ArrayList<Object> newArrayListObject = new ArrayList<Object>();

		// Checking if a register was created / deleted comparing the previous and current number of elements.
		int lArrayListObjectPreviousLength = mArrayListObject.size();

		// Refreshing the list of registers (after create / delete a register).
		mArrayListObject = mDatabaseHelper.getAllEntityInstances(mEntityName);

		// Updating the mMaxPageNumber.

		if (mArrayListObject.isEmpty()) {
			mMaxPageNumber = 1;
		}
		else {
			if ((mArrayListObject.size() % EntityListAdapter.MAX_ROW_DISPLAY) > 0) {
				mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY) + 1;
			}
			else {
				mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY);
			}
		}

		// Updating the page number.

		// Checking if a register was created comparing the previous and current number of elements.
		if (lArrayListObjectPreviousLength < mArrayListObject.size()) {

			// The new current page is the last (make sense because after create a register, we must
			// go to the mMaxPageNumber page (in some cases, updated)).
			mPageNumber = mMaxPageNumber;

		}
		// Checking if a register was deleted comparing the previous and current number of elements.
		else if (lArrayListObjectPreviousLength > mArrayListObject.size()) {

			// Deleting a single register in the last page. Must go to the new last page (-1).

			if (mPageNumber > mMaxPageNumber) {
				mPageNumber = mMaxPageNumber;
			}

		}

		// Filling the grid.

		for (int i = (mPageNumber - 1) * EntityListAdapter.MAX_ROW_DISPLAY; i < mPageNumber * EntityListAdapter.MAX_ROW_DISPLAY; i++) {

			if (i < mArrayListObject.size()) {
				newArrayListObject.add(mArrayListObject.get(i));
			}

		}

		mListObject = newArrayListObject.toArray(new Object[newArrayListObject.size()]);

		mEntityListAdapter = new EntityListAdapter(getActivity()
				.getApplicationContext(), R.layout.fragment_list_row,
				mListObject);

		mListView.setAdapter(mEntityListAdapter);

		// Setting the page number.
		updatePageInformation();

		// Disabling/enabling "Page Down" and "Page Up" buttons.

		if (mPageNumber > 1) {

			mButtonPageUp.setEnabled(true);
			mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_shape));

		}
		else {

			mButtonPageUp.setEnabled(false);
			mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

		}

		if (mPageNumber < mMaxPageNumber) {

			mButtonPageDown.setEnabled(true);
			mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_shape));

		}
		else {

			mButtonPageDown.setEnabled(false);
			mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

		}

		// Debugging: Return to the current page (not always to the first) after creating/updating a register (end).

	}

	// Debugging: Page Up / Down feature (begin).

	private void refreshEntityListPageDown() {

		// TODO: Put "Return Object[] option" to work! (maybe don't work: size of Object[] need to be defined before (not dinamically grows up).

		// Debugging: Return ArrayList<Object> option (begin).

		ArrayList<Object> newArrayListObject = new ArrayList<Object>();

		// Filling the grid.

		for (int i = mPageNumber * EntityListAdapter.MAX_ROW_DISPLAY; i < (mPageNumber + 1) * EntityListAdapter.MAX_ROW_DISPLAY; i++) {

			if (i < mArrayListObject.size()) {
				newArrayListObject.add(mArrayListObject.get(i));
			}

		}

		mListObject = newArrayListObject.toArray(new Object[newArrayListObject.size()]);

		mEntityListAdapter = new EntityListAdapter(getActivity()
				.getApplicationContext(), R.layout.fragment_list_row,
				mListObject);

		// Debugging: Return ArrayList<Object> option (end).

		// Debugging: Return Object[] option (begin).

//		Object[] newListObject = new Object[EntityListAdapter.MAX_ROW_DISPLAY];
//
//		// Filling the grid.
//		for (int i = mPageNumber * EntityListAdapter.MAX_ROW_DISPLAY, j = 0; i < (mPageNumber + 1) * EntityListAdapter.MAX_ROW_DISPLAY; i++, j++) {
//
//			if (i < mListObject.length) {
//				newListObject[j] = mListObject[i];
//			}
//
//		}
//
//		mEntityListAdapter = new EntityListAdapter(getActivity()
//				.getApplicationContext(), R.layout.fragment_list_row,
//				newListObject);

		// Debugging: Return Object[] option (end).

		// Debugging: Don't needed?
		// mEntityListAdapter.setNotifyOnChange(true);
		// mEntityListAdapter.notifyDataSetChanged();

		mListView.setAdapter(mEntityListAdapter);

		// Updating the page number.
		mPageNumber++;

		// TODO: Enhancing the setting the page numbers.
		// Debugging: Enhancing the Setting the page numbers (begin).

		// http://www.programcreek.com/java-api-examples/index.php?api=android.view.ViewManager

		// mListView.updateViewLayout(mTextViewPageInformation,  new android.view.ViewGroup.LayoutParams(
		//		android.view.ViewGroup.LayoutParams.MATCH_PARENT,
		//		android.view.ViewGroup.LayoutParams.MATCH_PARENT)); // java.lang.IllegalArgumentException: Invalid LayoutParams supplied to android.widget.ListView@2c615a40

		// mListView.updateViewLayout(mTextViewPageInformation, mLayoutParamsTextViewPageInformation); // java.lang.IllegalArgumentException: Invalid LayoutParams supplied to android.widget.ListView@2c6194e0

		// Setting the page number.
		updatePageInformation();

		// Debugging: Enhancing the Setting the page numbers (end).

		// Disabling/enabling "Page Down" and "Page Up" buttons.

		if (mPageNumber == mMaxPageNumber) {
			mButtonPageDown.setEnabled(false);
			mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));
		}

		mButtonPageUp.setEnabled(true);
		mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_shape));

	}

	private void refreshEntityListPageUp() {

		// TODO: Put "Return Object[] option" to work! (maybe don't work: size of Object[] need to be defined before (not dinamically grows up).

		// Debugging: Return ArrayList<Object> option (begin).

		 ArrayList<Object> newArrayListObject = new ArrayList<Object>();

		 // Filling the grid.
		 for (int i = (mPageNumber - 2) * EntityListAdapter.MAX_ROW_DISPLAY; i < (mPageNumber - 1) * EntityListAdapter.MAX_ROW_DISPLAY; i++) {
		 	newArrayListObject.add(mArrayListObject.get(i));
		 }

		 mListObject = newArrayListObject.toArray(new Object[newArrayListObject.size()]);

		 mEntityListAdapter = new EntityListAdapter(getActivity()
		 		.getApplicationContext(), R.layout.fragment_list_row,
		 		mListObject);

		// Debugging: Return ArrayList<Object> option (end).

		// Debugging: Return Object[] option (begin).

//		Object[] newListObject = new Object[EntityListAdapter.MAX_ROW_DISPLAY];
//
//		// Filling the grid.
//		for (int i = (mPageNumber - 2) * EntityListAdapter.MAX_ROW_DISPLAY, j = 0; i < (mPageNumber - 1) * EntityListAdapter.MAX_ROW_DISPLAY; i++, j++) {
//			newListObject[j] = mListObject[i];
//		}
//
//		mEntityListAdapter = new EntityListAdapter(getActivity()
//				.getApplicationContext(), R.layout.fragment_list_row,
//				newListObject);

		// Debugging: Return Object[] option (end).

		// Debugging: Don't needed?
		// mEntityListAdapter.setNotifyOnChange(true);
		// mEntityListAdapter.notifyDataSetChanged();

		mListView.setAdapter(mEntityListAdapter);

		// Updating the page number.
		mPageNumber--;

		// Setting the page number.
		updatePageInformation();

		// Disabling/enabling "Page Down" and "Page Up" buttons.
		if (mPageNumber == 1) {
			mButtonPageUp.setEnabled(false);
			mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));
		}

		mButtonPageDown.setEnabled(true);
		mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_shape));

	}

	private void updatePageInformation() {

		mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");
		mRelativeLayoutListHeader.removeView(mTextViewPageInformation);
		mRelativeLayoutListHeader.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);
		mListView.removeHeaderView(mRelativeLayoutListHeader);
		mListView.addHeaderView(mRelativeLayoutListHeader, null, false);

	}

	// Debugging: Page Up / Down feature (end).

	public class EntityListAdapter extends ArrayAdapter<Object> {

		Context context;
		int layoutResourceId;
		Object data[] = null;

		// Debugging: Page Up / Down feature.
		private static final int MAX_ROW_DISPLAY = 8; // Min. value = 1; Max. value = 6 (for 800 x 400 screen layout).
		// private static final int MAX_ROW_DISPLAY = 6; // Min. value = 1; Max. value = 6 (for 800 x 400 screen layout).

		public EntityListAdapter(Context context, int layoutResourceId,
				Object[] data) {
			super(context, layoutResourceId, data);
			this.layoutResourceId = layoutResourceId;
			this.context = context;
			this.data = data;
		}

		// Debugging: force getView() (begin)
//		@Override
//		public void setNotifyOnChange(boolean notifyOnChange) {
//			
//			Log.i(TAG, "public void setNotifyOnChange(boolean notifyOnChange)");
//			
//		};

		@Override
		public void notifyDataSetChanged() {

			// Debugging.
//			Log.i(TAG, " ");
//			Log.i(TAG, "public void notifyDataSetChanged() / mEntityName = " + mEntityName);

		}
		// Debugging: force getView() (end)

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Debugging: Object variables (could use?).
			Field[] lFieldList;
			EntityValueHolder entityValueHolder = null;

			lFieldList = mDatabaseHelper.getEntityFields(mEntityName);
			// lFieldList = DatabaseHelper.getEntityFields(mEntityName);

			// mLinearLayoutListRow definition & settings.

			mLinearLayoutListRow = new LinearLayout(getActivity());
			// Debugging: The Id is necessary? No calls (findViewById) to this Id.
			// mLinearLayoutListRow.setId(R.id.mLinearLayoutListRow);
			mLinearLayoutListRow.setOrientation(LinearLayout.HORIZONTAL);
			mLinearLayoutListRow.setLayoutParams(new android.widget.AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
			mLinearLayoutListRow.setPadding(10, 10, 10, 10);

			// mTextViewEntityColumn definition & settings.

			mTextViewEntityColumn = new TextView[lFieldList.length];

			// Filling LinearLayout mLinearLayoutListHeader with a set empty TextView mTextViewEntityColumnHeader
			// to further add views to LinearLayout with index (avoid out of bounds error).
			for (int i = 0; i < lFieldList.length; i++) {

				mTextViewEntityColumn[i] = new TextView(getActivity());
				// Debugging: Space between the fields in the row of the Grid.
				mTextViewEntityColumnDivider[i] = new TextView(getActivity());
				mLinearLayoutListRow.addView(mTextViewEntityColumn[i], i);
				// Debugging: Space between the fields in the row of the Grid.
				mLinearLayoutListRow.addView(mTextViewEntityColumnDivider[i], i + 1);

			}

			for (int i = 0; i < lFieldList.length; i++) {

				mTextViewEntityColumn[i] = new TextView(getActivity());
				mTextViewEntityColumn[i].setId(R.id.mTextViewEntityColumn);
				mTextViewEntityColumn[i].setGravity(Gravity.CENTER_VERTICAL);
				mTextViewEntityColumn[i].setTextColor(0xff000000);
				mTextViewEntityColumn[i].setTextSize(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_TAMANHO_LETRA]));
				if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {
					// Debugging: Space between the fields in the row of the Grid.
					mLinearLayoutListRow.addView(mTextViewEntityColumn[i], Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2);
					// mLinearLayoutListRow.addView(mTextViewEntityColumn[i], Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]));
					// Debugging: Space between the fields in the row of the Grid (begin).
					mTextViewEntityColumnDivider[i] = new TextView(getActivity());
					mTextViewEntityColumnDivider[i].setWidth(5);
					// mTextViewEntityColumnDivider[i].setText("|");
					// mTextViewEntityColumnDivider[i].setBackgroundColor(0xff000000);
					mLinearLayoutListRow.addView(mTextViewEntityColumnDivider[i],
							(Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2) + 1);
					// Debugging: Space between the fields in the row of the Grid (end).
				}

			}

			// textViewIdRow = new TextView(getActivity());
			// textViewIdRow.setId(R.id.textViewIdRow);
			// textViewIdRow.setWidth(100);
			// textViewIdRow.setGravity(Gravity.CENTER_VERTICAL);
			// textViewIdRow.setTextColor(0xff000000);
			// textViewIdRow.setTextSize(20);
			//
			// mLinearLayoutListRow.addView(textViewIdRow);

			convertView = mLinearLayoutListRow;

			entityValueHolder = new EntityValueHolder();

			// mFieldList = DatabaseHelper.getEntityFields(data[position].getClass().getSimpleName());
			entityValueHolder.name = new String[lFieldList.length];
			entityValueHolder.value = new String[lFieldList.length];

			// textViewEditEntityFieldNameColumn.setGravity(Gravity.CENTER_VERTICAL);

			for (int i = 0; i < lFieldList.length; i++) {
				lFieldList[i].setAccessible(true);
				try {
					// Returns the value of the field in the specified object.
					if (lFieldList[i].get(data[position]) != null) {
						if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {
							if (mEntityFieldParameters[i][Utils.IS_PASSWD].equals("true")) {
								String passwordField = new String("");
								for (int j = 0; j < lFieldList[i].get(data[position]).toString().length(); j++) {
									passwordField = passwordField + "*";
								}
								mTextViewEntityColumn[i].setText(passwordField);
								// Debugging (tipxxi): IS_PASSWD field.
								entityValueHolder.value[i] = lFieldList[i].get(data[position]).toString();
							}
							else {
								if (mEntityFieldParameters[i][Utils.TIPO].equals("T_BOOL")) {

									if (!lFieldList[i].get(data[position]).toString().equals("")) {
										if (lFieldList[i].get(data[position]).toString().equals("0")) {
											mTextViewEntityColumn[i].setText(getResources().getString(R.string.nao_PT));
										}
										else {
											mTextViewEntityColumn[i].setText(getResources().getString(R.string.sim_PT));
										}
										mTextViewEntityColumn[i].setWidth(mTextViewEntityColumn[i].getText().length() * 100);
									}

									// Debugging: Update case of SQLite referential integrity issues (approach 1).
									// entityValueHolder.value[i] = mTextViewEntityColumn[i].getText().toString();

									// Debugging: Update case of SQLite referential integrity issues (approach 2).
									entityValueHolder.value[i] = mTextViewEntityColumn[i].getText().toString();

								}

								// Debugging: Fixing T_LISTA feature (begin).

								else if (mEntityFieldParameters[i][Utils.TIPO].equals("T_LISTA")) {

									// Debugging: Retrieve entity field value with any attribute.
									mTextViewEntityColumn[i].setText(
											(String) Utils.getEntityFieldFromClass(
													lFieldList[i].getName(),
													lFieldList[i].get(data[position]).toString()));
//									mTextViewEntityColumn[i].setText(
//											retrieveFieldValue(
//												mEntityFieldParameters[i][Utils.TIPO],
//												"nome" + lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
//												lFieldList[i].get(data[position]).toString()));

									entityValueHolder.value[i] = lFieldList[i].get(data[position]).toString();

								}

								// Debugging: Fixing T_LISTA feature (end).

								else if (mEntityFieldParameters[i][Utils.TIPO].equals("T_TABELA")) {

									// Retrieving the value of field with name = "nome" + Entity name using id = lFieldList[i].get(data[position]).toString().

									// Debugging (tipxxi): "nome"[entity] = "id"[entity] (begin).

									if (mDatabaseHelper.
											getEntityField(
													lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
													"nome" + lFieldList[i].getName().substring(2, lFieldList[i].getName().length())) != null) {
										mTextViewEntityColumn[i].setText(
												(String) mDatabaseHelper.getEntityFieldAttributeValue(
														lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
														"nome" + lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
														lFieldList[i].get(data[position]).toString()));

									}
									else {
										mTextViewEntityColumn[i].setText(
												(String) mDatabaseHelper.getEntityFieldAttributeValue(
														lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
														"id" + lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
														lFieldList[i].get(data[position]).toString()));
									}

//									// Debugging: Retrieve entity field value with any attribute.
//									mTextViewEntityColumn[i].setText(
//											(String) mDatabaseHelper.getEntityFieldAttributeValue(
//													lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
//													"nome" + lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
//													lFieldList[i].get(data[position]).toString()));

//									mTextViewEntityColumn[i].setText(
//											retrieveFieldValue(
//													mEntityFieldParameters[i][Utils.TIPO],
//													"nome" + lFieldList[i].getName().substring(2, lFieldList[i].getName().length()),
//													lFieldList[i].get(data[position]).toString()));
//									mTextViewEntityColumn[i].setText(retrieveFieldValue(mEntityFieldParameters[i][Utils.TIPO], lFieldList[i].getName(), lFieldList[i].get(data[position]).toString()));
//									mTextViewEntityColumn[i].setText(retrieveFieldValue(lFieldList[i].getName(), lFieldList[i].get(data[position]).toString()));

									// Debugging (tipxxi): "nome"[entity] = "id"[entity] (end).

									// Debugging: Update case of SQLite referential integrity issues (approach 1).
									// entityValueHolder.value[i] = mFieldList[i].get(data[position]).toString();

									// Debugging: Update case of SQLite referential integrity issues (approach 2).
									entityValueHolder.value[i] = lFieldList[i].get(data[position]).toString();

								}
								else {

									mTextViewEntityColumn[i].setText(lFieldList[i].get(data[position]).toString());

									// Debugging: Update case of SQLite referential integrity issues (approach 1).
									// entityValueHolder.value[i] = mTextViewEntityColumn[i].getText().toString();

									// Debugging: Update case of SQLite referential integrity issues (approach 2).
									entityValueHolder.value[i] = mTextViewEntityColumn[i].getText().toString();

								}
							}

							// Setting width.
							mTextViewEntityColumn[i].setWidth(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]));

							// Setting text alignment.
							switch (Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_ALINHAMENTO])) {

								case 0: // Left.
									mTextViewEntityColumn[i].setGravity(Gravity.LEFT);
									break;

								case 1: // Center.
									mTextViewEntityColumn[i].setGravity(Gravity.CENTER);
									break;

								case 2: // Right.
									mTextViewEntityColumn[i].setGravity(Gravity.RIGHT);
									break;

							}

							// Debugging: Update case of SQLite referential integrity issues (approach 1).
							// Debugging: Update case of SQLite referential integrity issues (approach 2).
							// entityValueHolder.value[i] = mTextViewEntityColumn[i].getText().toString();
						}

						else {
							entityValueHolder.value[i] = lFieldList[i].get(data[position]).toString();
						}
						entityValueHolder.name[i] = lFieldList[i].getName();
						mTextViewEntityColumn[i].setTag(entityValueHolder);

						// Debugging.
//						Log.i(TAG, "entityValueHolder.name[" + i + "] = " + entityValueHolder.name[i]);
//						Log.i(TAG, "entityValueHolder.value[" + i + "] = " + entityValueHolder.value[i]);
//						Log.i(TAG, "mTextViewEntityColumn[" + i + "].getText() = " + mTextViewEntityColumn[i].getText());

					}
					// Debugging (tipxxi): Null column shift (begin).

					else {

						// When the value of the column (lFieldList[i].get(data[position]) is null,
						// set the width of the column to avoid the shift of columns, breaking the
						// layout.

						mTextViewEntityColumn[i].setWidth(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]));

					}

					// Debugging (tipxxi): Null column shift (end).
				}
				catch (IllegalArgumentException e) {
					// Log.i(TAG, "getView(): IllegalArgumentException!");
					e.printStackTrace();
				}
				catch (IllegalAccessException e) {
					// Log.i(TAG, "getView(): IllegalAccessException!");
					e.printStackTrace();
				}
			}

			// Debugging.
//			Log.i(TAG, "return convertview;");

			return convertView;

		}

		// Debugging: Page Up / Down feature (begin).

		@Override
		public int getCount() {

			if (data == null) {
				return 0;
			}
			return Math.min(MAX_ROW_DISPLAY, data.length);

		}

		// Debugging: Page Up / Down feature (end).

	}

	static class EntityValueHolder {
		String[] name;
		String[] value;
	}

	// Debugging: Fixing T_LISTA feature.
	private String retrieveFieldValue(String pFieldType, String pFieldName, String pFieldId) {

		String fieldValue = "";

		try {

			if (pFieldType.equals("T_TABELA")) {

				// Debugging: Using getEntityFieldAttributeValue() (begin).

				fieldValue = (String) mDatabaseHelper.getEntityFieldAttributeValue(pFieldName.substring(4, pFieldName.length()), pFieldName, pFieldId);
				// fieldValue = (String) mDatabaseHelper.getEntityFieldAttributeValue(pFieldName.substring(2, pFieldName.length()), pFieldName, pFieldId);
				// fieldValue = (String) mDatabaseHelper.getEntityFieldAttributeValue(pFieldName.substring(2, pFieldName.length()), pFieldName);

//				clazz = Class.forName("com.bpaulo.comunicaxxilight.entity." + pFieldName.substring(2, pFieldName.length()));
//				object = clazz.newInstance();
//				method = clazz.getMethod("setId" + pFieldName.substring(2, pFieldName.length()), Integer.class);
//				method.invoke(object, Integer.valueOf(pFieldId));
//				mDatabaseHelper.readEntity(object);
//				field = object.getClass().getDeclaredField("nome" + pFieldName.substring(2, pFieldName.length()));
//				field.setAccessible(true);
//
//				fieldValue = field.get(object).toString();

				// Debugging: Using getEntityFieldAttributeValue() (end).

			}
			else if (pFieldType.equals("T_LISTA")) {

				// Debugging: Using getEntityFieldAttributeValue() (begin).

				fieldValue = (String) Utils.getEntityFieldFromClass(pFieldName, pFieldId);

//				clazz = Class.forName("com.bpaulo.comunicaxxilight.util.Utils");
//				object = clazz.newInstance();
//				field = clazz.getDeclaredField(pFieldName.toUpperCase(Locale.getDefault()));
//				field.setAccessible(true);
//				lList = (String[]) field.get(object);
//
//				fieldValue = lList[Integer.valueOf(pFieldId)];

				// Debugging: Using getEntityFieldAttributeValue() (end).

			}

		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

//		// Reading entity instance approach.
//
//		try {
//
//			clazz = Class.forName("com.bpaulo.comunicaxxilight.entity." + pFieldName.substring(2, pFieldName.length()));
//			object = clazz.newInstance();
//			method = clazz.getMethod("setId" + pFieldName.substring(2, pFieldName.length()), Integer.class);
//			method.invoke(object, Integer.valueOf(pFieldId));
//			mDatabaseHelper.readEntity(object);
//			field = object.getClass().getDeclaredField("nome" + pFieldName.substring(2, pFieldName.length()));
//			field.setAccessible(true);
//
//			fieldValue = field.get(object).toString();
//
//		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		catch (java.lang.InstantiationException e) {
//			e.printStackTrace();
//		}
//		catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//		catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
//		catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		}

		// Debugging: Fixing T_LISTA feature (end).

		return fieldValue;

	}

	// Debugging (tipxxi): Scrap code (begin).

	/*
	// Debugging: Grid/Edit option (begin).

	private boolean isAllEntityFieldsNotPrintable() {

		for (int i = 0; i < mFieldList.length; i++) {

			// Getting the parameters for each field.
			mEntityFieldParameters[i] = (String[]) DatabaseHelper.invokeMethod(
					"com.bpaulo.tipxxi.entity." + mEntityName,
					"getAttributeParameters",
					mFieldList[i].getName(),
					String.class
			);

			if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {
				return false;
			}

		}

		return true;

	}

	// Debugging: Grid/Edit option (end).
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

//	@Override
//	public void onTrimMemory(int level) {
//
//		super.onTrimMemory(level);
//		Log.i(TAG, "onTrimMemory(" + Integer.toString(level) + ");");
//
//	}

	@Override
	public void onLowMemory() {

		super.onLowMemory();
		Log.i(TAG, "onLowMemory();"); // Don't called!

	}

	// Debugging: Managing App's Memory (end).

}