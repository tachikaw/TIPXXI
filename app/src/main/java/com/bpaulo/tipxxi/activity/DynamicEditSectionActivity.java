package com.bpaulo.tipxxi.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.database.DatabaseHelper;
import com.bpaulo.tipxxi.util.EntityField;
import com.bpaulo.tipxxi.util.Forward;
import com.bpaulo.tipxxi.util.ForwardButton;
import com.bpaulo.tipxxi.util.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DynamicEditSectionActivity extends Activity {

    private static final String TAG = "DynamicEditSectionActivity";

    private FrameLayout mFrameLayout;

    private RelativeLayout mRelativeLayout;
    private RelativeLayout.LayoutParams mRelativeLayoutParams;

    private LinearLayout mLinearLayout, mLinearLayoutListRow;
    private ListView mListView;

    private TextView mTextViewListEntityFieldNameColumn;
    private TextView mTextViewListEntityFieldValueColumn;

    private TextView mTextViewEditEntityFieldNameColumn;
    private RelativeLayout.LayoutParams mLayoutParamsTextViewEditEntityFieldNameColumn;

    private Button mButtonSave;
    private RelativeLayout.LayoutParams mLayoutParamsButtonSave;

    private Button mButtonDelete;
    private RelativeLayout.LayoutParams mLayoutParamsButtonDelete;

    private Button mButtonCancel;
    private RelativeLayout.LayoutParams mLayoutParamsButtonCancel;

    // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin).

    private TextView mTextViewBreadcrumb;
    private RelativeLayout.LayoutParams mLayoutParamsTextViewBreadcrumb;

    // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end).

    // Debugging: Footer (begin).

    private RelativeLayout mRelativeLayoutFooter;
    private RelativeLayout.LayoutParams mLayoutParamsRelativeLayoutFooter;

    // Debugging: Footer (end).

    private ViewGroup.LayoutParams mViewGroupLayoutParams;

    private Intent mIntent;
    private String mEntityName;
    private Field[] mFieldList;
    private DatabaseHelper mDatabaseHelper;
    private EntityValueHolder[] mEntityValueHolderList;
    private Class<?> clazz;

    private int mLastEditPosition;

    private String[][] mEntityFieldParameters;
    private int mEntityFieldParametersIndex;
    private String[][] mEntityFieldParametersNoIndexed;

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

    private CheckBox mCheckBoxEditEntityFieldValueColumn;
    private RelativeLayout.LayoutParams mLayoutParamsCheckBoxEditEntityFieldValueColumn;

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

    private Spinner mSpinnerEditEntityFieldValueColumn;
    private RelativeLayout.LayoutParams mLayoutParamsSpinnerEditEntityFieldValueColumn;

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

    // Debugging: Optimizing/refactoring code.
    private RelativeLayout.LayoutParams mLayoutParamsEditTextEditEntityFieldValueColumn;

    private List<String>[] mSpinnerEditEntityFieldValueList;
    private List<String>[] mSpinnerEditEntityFieldValueIndexList;
    // private List<Integer>[] mSpinnerEditEntityFieldValueIndexList;
    private String[] mSpinnerEditEntityFieldValueListSelected;
    private String mSpinnerItemSelected;

    // Debugging: Page Up / Down feature (begin).

    private Button mButtonPageDown;
    private RelativeLayout.LayoutParams mLayoutParamsButtonPageDown;

    private Button mButtonPageUp;
    private RelativeLayout.LayoutParams mLayoutParamsButtonPageUp;

    // private ArrayList<Object> mArrayListObject;
    // private Object[] mListObject;
    private EntityEditAdapter mEntityEditAdapter;

    private int mPageNumber = 1;
    private int mMaxPageNumber = 1;
    private TextView mTextViewPageInformation;
    private RelativeLayout.LayoutParams mLayoutParamsTextViewPageInformation;

    private BtnOnClickListenerImpl btnOnClickListenerImpl;
    private BtnOnTouchListenerImpl btnOnTouchListenerImpl;

    // Debugging: Memory problems (begin).

    // private static List<IMemoryInfo> memInfoList = new ArrayList<AppContext.IMemoryInfo>();

    // Debugging: Memory problems (end).

    // Debugging: Page Up / Down feature (end).

    // Debugging: Navigation flow control (begin).

    private Forward mForward = null;

    // private ForwardButton[] mForwardButtonList;

    private Button[] mButtonList;
    private RelativeLayout.LayoutParams[] mLayoutParamsButtonList;

    // Debugging: Navigation flow control (end).

    // Debugging (tipxxi): ID/Code alphanumeric.
    private String loadedObjectId = "";

    public void onCreate(Bundle savedInstanceState) {

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
//		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//		// ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
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

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin).

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // Useless (the code above works fine alone)?

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end).

        // Debugging: Singleton SQLiteOpenHelper.
        mDatabaseHelper = DatabaseHelper.getInstance(this);
        // mDatabaseHelper = new DatabaseHelper(this);

        mViewGroupLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mIntent = getIntent();

        // Debugging: Navigation flow control.
//		mEntityName = mIntent.getStringExtra("entityName");

        mLastEditPosition = 0;

        // Debugging: Update case of SQLite referential integrity issues (approach 1).
//		DynamicGridSectionFragment dynamicListSectionFragment = new DynamicGridSectionFragment("");

        // Debugging: Page Up / Down feature (begin).

        btnOnClickListenerImpl = new BtnOnClickListenerImpl();
        btnOnTouchListenerImpl = new BtnOnTouchListenerImpl();

        // Debugging: Page Up / Down feature (end).

        // mFrameLayout definition & settings.

        mFrameLayout = new FrameLayout(this);
        mFrameLayout.setLayoutParams(mViewGroupLayoutParams);

        // mRelativeLayout definition & settings.

        mRelativeLayout = new RelativeLayout(this);
        mRelativeLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // android.widget.RelativeLayout.LayoutParams mRelativeLayoutParams = new android.widget.RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);

        // mLinearLayout definition & settings.

        mLinearLayout = new LinearLayout(this);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setPadding(0, 30, 0, 0); // Works!
        mViewGroupLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mViewGroupLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLinearLayout.setLayoutParams(mViewGroupLayoutParams);

        // mTextViewBreadcrumb definition & settings.

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin).

        mTextViewBreadcrumb = new TextView(this);
        mTextViewBreadcrumb.setId(R.id.mTextViewBreadcrumb);
        // mTextViewBreadcrumb.setId(R.id.textViewToolbar);
        mTextViewBreadcrumb.setBackgroundColor(getResources().getColor(R.color.gray));
        mTextViewBreadcrumb.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);
        mTextViewBreadcrumb.setTextColor(getResources().getColor(R.color.white));

        // Debugging: Adding "CAPTION" class variable (begin).

        // 11-12 15:12:17.050 30519-30519/com.bpaulo.comunicaxxilight E/AndroidRuntime:     at com.bpaulo.comunicaxxilight.activity.DynamicEditSectionActivity.onCreate(DynamicEditSectionActivity.java:266)

        // Debugging: Navigation flow control (begin).

        if (mIntent.getParcelableExtra("forward") != null) {

            mForward = mIntent.getParcelableExtra("forward");
            mTextViewBreadcrumb.setText(mForward.getBreadcrumbText());

        }
        else {

            if (mIntent.getStringExtra("entityName") != null) {

                mEntityName = mIntent.getStringExtra("entityName");

            }

            if (mIntent.getStringExtra("action") != null) {

                if (mIntent.getStringExtra("action").equals("C")) {
                    // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
                    mTextViewBreadcrumb.setText("Creating " + (String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ENTITY_CAPTION"), null));
                    // Debugging: Retrieve entity field value with any attribute.
                    // mTextViewBreadcrumb.setText("Creating " + (String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
                    // mTextViewBreadcrumb.setText("Creating " + (String) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION")));
                }
                else {
                    // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
                    mTextViewBreadcrumb.setText("Editing " + (String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ENTITY_CAPTION"), null));
                    // Debugging: Retrieve entity field value with any attribute.
                    // mTextViewBreadcrumb.setText("Editing " + (String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
                    // mTextViewBreadcrumb.setText("Editing " + (String) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION")));
                }

            }

        }

//		if (mIntent.getStringExtra("action") != null) {
//
//			if (mIntent.getStringExtra("action").equals("C")) {
//				// Debugging: Retrieve entity field value with any attribute.
//				mTextViewBreadcrumb.setText("Creating " + (String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
//				// mTextViewBreadcrumb.setText("Creating " + (String) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
//				// mTextViewBreadcrumb.setText("Creating " + (String) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION")));
//			}
//			else {
//				// Debugging: Retrieve entity field value with any attribute.
//				mTextViewBreadcrumb.setText("Editing " + (String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
//				// mTextViewBreadcrumb.setText("Editing " + (String) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null));
//				// mTextViewBreadcrumb.setText("Editing " + (String) DatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION")));
//			}
//
//		}

        // Debugging: Navigation flow control (end).

//		if (mIntent.getStringExtra("action").equals("C")) {
//			mTextViewBreadcrumb.setText("Creating " + mEntityName);
//		}
//		else {
//			mTextViewBreadcrumb.setText("Editing " + mEntityName);
//		}
//		// mTextViewBreadcrumb.setText("Editing " + mEntityName);

        // Debugging: Adding "CAPTION" class variable (end).

        mLayoutParamsTextViewBreadcrumb = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        mLayoutParamsTextViewBreadcrumb.height = 30;
//		mLayoutParamsTextViewBreadcrumb.leftMargin = 10;
//		mLayoutParamsTextViewBreadcrumb.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end).

        // Debugging: Page Up / Down feature (begin).

        mTextViewPageInformation = new TextView(this);
        mTextViewPageInformation.setId(R.id.mTextViewPageInformation);
        mTextViewPageInformation.setBackgroundColor(getResources().getColor(R.color.gray));
        mTextViewPageInformation.setTextColor(getResources().getColor(R.color.white));
        mTextViewPageInformation.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);

        mLayoutParamsTextViewPageInformation = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsTextViewPageInformation.height = 30;
        mLayoutParamsTextViewPageInformation.rightMargin = 10;
        mLayoutParamsTextViewPageInformation.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        // Debugging: Page Up / Down feature (end).

        // mListView definition & settings.

        mListView = new ListView(this);
        mListView.setId(R.id.mListViewEditEntity);
        mViewGroupLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme).

        mViewGroupLayoutParams.height = 480;
        // mViewGroupLayoutParams.height = 200;
        // mViewGroupLayoutParams.height = 300;

        mListView.setLayoutParams(mViewGroupLayoutParams);

        // FUCKER!
        mSpinnerEditEntityFieldValueColumn = new Spinner(this);

        // Populating the grid with entity's fields.

        // Debugging: Transient entities (begin).

        // Check if the entity is transient or persistent.
//		if (DatabaseHelper.isDatabaseEntity(mEntityName)) { // Works fine!
//			Log.i(TAG, "DatabaseHelper.isDatabaseEntity(mEntityName) = true");
//		}
//		else {
//			Log.i(TAG, "DatabaseHelper.isDatabaseEntity(mEntityName) = false");
//		}

        // Debugging: Transient entities (end).

        // Debugging: Navigation flow control (begin).

        if (mForward != null) {
            // if (mIntent.getParcelableExtra("forward") != null) {

            // If no <field declaration(s), by default, all the fields of <entity will be used.
            if (mForward.getForwardEntity() != null) {

                if (mForward.getForwardEntity().getArrayListEntityField() != null) {

                    mFieldList = new Field[mForward.getForwardEntity().getArrayListEntityField().size()];
                    Iterator<EntityField> itArrayListEntityField = mForward.getForwardEntity().getArrayListEntityField().iterator();

                    int i = 0;
                    while (itArrayListEntityField.hasNext()) {

                        mFieldList[i] = mDatabaseHelper.getEntityField(
                        // mFieldList[i] = DatabaseHelper.getEntityField(
                                mForward.getForwardEntity().getName(),
                                itArrayListEntityField.next().getName());

                        i++;

                    }

                }
                else {

                    mFieldList = mDatabaseHelper.getEntityFields(mEntityName);
                    // mFieldList = DatabaseHelper.getEntityFields(mEntityName);

                }

            }
            else {

                mFieldList = mDatabaseHelper.getEntityFields(mEntityName);
                // mFieldList = DatabaseHelper.getEntityFields(mEntityName);

            }

        }
        else {

            mFieldList = mDatabaseHelper.getEntityFields(mEntityName);
            // mFieldList = DatabaseHelper.getEntityFields(mEntityName);

        }

        // mFieldList = DatabaseHelper.getEntityFields(mEntityName);

        // Debugging: Navigation flow control (end).

        mEntityValueHolderList = new EntityValueHolder[mFieldList.length];
        mEntityFieldParameters = new String[mFieldList.length][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];
        mEntityFieldParametersIndex = 0;
        mEntityFieldParametersNoIndexed = new String[mFieldList.length][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

        mSpinnerEditEntityFieldValueList = new List[mFieldList.length];
        mSpinnerEditEntityFieldValueIndexList = new List[mFieldList.length];
        mSpinnerEditEntityFieldValueListSelected = new String[mFieldList.length];

        for (int i = 0; i < mFieldList.length; i++) {

            // Debugging: Navigation flow control (begin).

            if (mForward != null) {

                if (mForward.getForwardEntity() != null) {

                    mEntityFieldParametersNoIndexed[i] = (String[]) DatabaseHelper.invokeMethod(
                            mForward.getForwardEntity().getName(),
                            "getAttributeParameters",
                            mFieldList[i].getName(),
                            String.class
                    );

                }

            }
            else {

                mEntityFieldParametersNoIndexed[i] = (String[]) DatabaseHelper.invokeMethod(
                        Utils.ENTITY_PACKAGE_NAME + mEntityName,
                        "getAttributeParameters",
                        mFieldList[i].getName(),
                        String.class
                );

            }

            // Debugging: Navigation flow control (end).

            // Debugging: Navigation flow control (begin).

            if (mForward != null) {

                // Debugging: <entity with n <fields.
                mEntityFieldParameters[i] = mEntityFieldParametersNoIndexed[i];
//				mEntityFieldParameters[mEntityFieldParametersIndex] = mEntityFieldParametersNoIndexed[i];

            }
            else {

                mEntityFieldParametersIndex = Integer.valueOf(mEntityFieldParametersNoIndexed[i][Utils.COL_INDEX]);
                mEntityFieldParameters[mEntityFieldParametersIndex] = mEntityFieldParametersNoIndexed[i];

            }

            // Debugging: Navigation flow control (end).

            EntityValueHolder entityValueHolder = new EntityValueHolder();
            entityValueHolder.name = mFieldList[i].getName();

            if (mIntent.getStringExtra(mFieldList[i].getName()) != null) {

                // Updating entity.

                // Debugging: Update case of SQLite referential integrity issues (approach 1) (begin).
//				if (mEntityFieldParameters[mEntityFieldParametersIndex][Utils.TIPO].equals("T_TABELA")) {
//					entityValueHolder.value = dynamicListSectionFragment.retrieveFieldValue(mFieldList[i].getName(), mIntent.getStringExtra(mFieldList[i].getName()));
//					// retrieveFieldValue(mFieldList[i].getName(), mFieldList[i].get(data[position]).toString());
//				}
//				else {
//					entityValueHolder.value = mIntent.getStringExtra(mFieldList[i].getName());
//				}
//				// entityValueHolder.value = mIntent.getStringExtra(mFieldList[i].getName());
                // Debugging: Update case of SQLite referential integrity issues (approach 1) (end).

                entityValueHolder.value = mIntent.getStringExtra(mFieldList[i].getName());

                // Debugging (tipxxi): ID/Code alphanumeric (begin).

                if (entityValueHolder.name.equals("id" + mEntityName)) {
                    loadedObjectId = entityValueHolder.value;
                }

                // Debugging (tipxxi): ID/Code alphanumeric (end).

            }
            else {
                // Creating entity.
                entityValueHolder.value = "";
            }

            // Debugging: Navigation flow control.
            // mEntityValueHolderList[mEntityFieldParametersIndex] = entityValueHolder;

            // Debugging (tipxxi): Scrap code (begin).

            /*
            if (mEntityFieldParameters[mEntityFieldParametersIndex][Utils.TIPO].equals("T_LISTA") ||
                    mEntityFieldParameters[mEntityFieldParametersIndex][Utils.TIPO].equals("T_TABELA")) {

                // Debugging: Navigation flow control (begin).

                updateSpinnerEditEntityFieldValueColumn(mEntityFieldParameters[mEntityFieldParametersIndex][Utils.CAMPO],
                        mEntityFieldParameters[mEntityFieldParametersIndex][Utils.TIPO],
                        i);

                // Debugging: 1st field in the list bug.
//				updateSpinnerEditEntityFieldValueColumn(mEntityFieldParameters[mEntityFieldParametersIndex][Utils.CAMPO],
//						mEntityFieldParameters[mEntityFieldParametersIndex][Utils.TIPO],
//						Integer.valueOf(mEntityFieldParameters[mEntityFieldParametersIndex][Utils.COL_INDEX]));

//				loadSpinnerData(mEntityFieldParameters[mEntityFieldParametersIndex][Utils.CAMPO],
//								mEntityFieldParameters[mEntityFieldParametersIndex][Utils.TIPO],
//								Integer.valueOf(mEntityFieldParameters[mEntityFieldParametersIndex][Utils.COL_INDEX]));

                // Debugging: Navigation flow control (end).

            }
            */

            // Debugging (tipxxi): Scrap code (end).

            // Debugging: Navigation flow control (begin).

            // Debugging: <entity with n <fields.
            mEntityValueHolderList[i] = entityValueHolder;
            // mEntityValueHolderList[mEntityFieldParametersIndex] = entityValueHolder;

            // mEntityValueHolderList[Integer.valueOf(mEntityFieldParameters[mEntityFieldParametersIndex][Utils.COL_INDEX])] = entityValueHolder;

            // Debugging: Navigation flow control (end).

        }

        EntityEditAdapter entityEditAdapter = new EntityEditAdapter(this, R.layout.fragment_list_row, mEntityValueHolderList);

        mListView.setAdapter(entityEditAdapter);
        mListView.setOnItemClickListener(new CustomOnItemClickListener());

        // Debugging: Page Up / Down feature (begin).

        if (mEntityValueHolderList.length == 0) {
            mMaxPageNumber = 1;
        }
        else {
            if ((mEntityValueHolderList.length % EntityEditAdapter.MAX_ROW_DISPLAY) > 0) {
                mMaxPageNumber = (mEntityValueHolderList.length / EntityEditAdapter.MAX_ROW_DISPLAY) + 1;
            }
            else {
                mMaxPageNumber = (mEntityValueHolderList.length / EntityEditAdapter.MAX_ROW_DISPLAY);
            }
        }

//		if (mArrayListObject.isEmpty()) {
//			mMaxPageNumber = 1;
//		}
//		else {
//			if ((mArrayListObject.size() % EntityListAdapter.MAX_ROW_DISPLAY) > 0) {
//				mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY) + 1;
//			}
//			else {
//				mMaxPageNumber = (mArrayListObject.size() / EntityListAdapter.MAX_ROW_DISPLAY);
//			}
//		}

        // Debugging: Page Up / Down feature (end).

        // Entity field edit area.

        // Debugging: Optimizing/refactoring code (begin).

        // mTextViewEditEntityFieldNameColumn definition & settings.

        mTextViewEditEntityFieldNameColumn = new TextView(this);
        mTextViewEditEntityFieldNameColumn.setId(R.id.mTextViewEditEntityFieldNameColumn);
        mTextViewEditEntityFieldNameColumn.setPadding(10, 10, 10, 10);
        // Adding a asterisk to mandatory field's label.

        // Debugging: Navigation flow control (begin).

        if (mForward != null) {

            if (mForward.getForwardEntity() != null) {

                mEntityFieldParameters[0] = (String[]) DatabaseHelper.invokeMethod(
                        mForward.getForwardEntity().getName(),
                        "getAttributeParameters",
                        mEntityValueHolderList[0].name,
                        String.class
                );

            }

        }
        else {

            mEntityFieldParameters[0] = (String[]) DatabaseHelper.invokeMethod(
                    Utils.ENTITY_PACKAGE_NAME + mEntityName,
                    "getAttributeParameters",
                    mEntityValueHolderList[0].name,
                    String.class
            );

        }

//		mEntityFieldParameters[0] = (String[]) DatabaseHelper.invokeMethod(
//			mEntityName, "getAttributeParameters",
//			String.class, mEntityValueHolderList[0].name);

        // Debugging: Navigation flow control (end).

//		if (mEntityFieldParameters[0][Utils.IS_NOT_NULL].equals("true")) {
//			mTextViewEditEntityFieldNameColumn.setText("* " + mEntityFieldParameters[0][Utils.CAPTION]);
//		}
//		else {
//			mTextViewEditEntityFieldNameColumn.setText(mEntityFieldParameters[0][Utils.CAPTION]);
//		}
//		mTextViewEditEntityFieldNameColumn.setTextColor(0xff000000);
//		mTextViewEditEntityFieldNameColumn.setTextSize(20);
//
//		mLayoutParamsTextViewEditEntityFieldNameColumn = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//		mLayoutParamsTextViewEditEntityFieldNameColumn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//		mLayoutParamsTextViewEditEntityFieldNameColumn.bottomMargin = 75;
//		mLayoutParamsTextViewEditEntityFieldNameColumn.leftMargin = 30;
        // Debugging: Removing & Customizing/Creating Title Bar (using any theme).
//		mLayoutParamsTextViewEditEntityFieldNameColumn.addRule(RelativeLayout.BELOW, R.id.textViewToolbar);

        // Debugging: Optimizing/refactoring code (end).

        // Debugging: Removing editTextEditEntityFieldValueColumn (begin).

//		editTextEditEntityFieldValueColumn = new EditText(this);
//		editTextEditEntityFieldValueColumn.setText(mEntityValueHolderList[0].value);
//		// Debugging.
//		editTextEditEntityFieldValueColumn.setMaxEms(Integer.valueOf(mEntityFieldParameters[0][Utils.MAX_LENGTH]));
//		// editTextEditEntityFieldValueColumn.setEms(Integer.valueOf(mEntityFieldParameters[0][Utils.MAX_LENGTH]));
//		// Setting the max. length of field.
//		InputFilter[] inputFilterArray = new InputFilter[1];
//		inputFilterArray[0] = new InputFilter.LengthFilter(Integer.valueOf(mEntityFieldParameters[0][Utils.MAX_LENGTH]));
//		editTextEditEntityFieldValueColumn.setFilters(inputFilterArray);

        // Debugging: Removing editTextEditEntityFieldValueColumn (end).

        // Debugging: Refactoring edit screen running (with the new keyboard screen) (begin): editTextEditEntityFieldValueColumn.setEnabled(false) always.

//		editTextEditEntityFieldValueColumn.setEnabled(false);
//
//		if (mEntityFieldParameters[0][Utils.IS_EDITAVEL].equals("true")) {
//			if (mEntityFieldParameters[0][Utils.IS_PASSWD].equals("true")) {
//				editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//			}
//			else if (mEntityFieldParameters[0][Utils.TIPO].equals("T_ALFANUM")) {
//				editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT);
//			}
//			else if (mEntityFieldParameters[0][Utils.TIPO].equals("T_NUMERICO")) {
//				editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_NUMBER);
//			}
//		}

        // Debugging: Removing editTextEditEntityFieldValueColumn (begin).

//		if (mEntityFieldParameters[0][Utils.IS_EDITAVEL].equals("false")) {
//			editTextEditEntityFieldValueColumn.setEnabled(false);
//		}
//		else {
//			editTextEditEntityFieldValueColumn.setEnabled(true);
//			if (mEntityFieldParameters[0][Utils.IS_PASSWD].equals("true")) {
//				editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//			}
//			else if (mEntityFieldParameters[0][Utils.TIPO].equals("T_ALFANUM")) {
//				editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT);
//			}
//			else if (mEntityFieldParameters[0][Utils.TIPO].equals("T_NUMERICO")) {
//				editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_NUMBER);
//			}
//		}

        // Debugging: Removing editTextEditEntityFieldValueColumn (end).

        // Debugging: Refactoring edit screen running (with the new keyboard screen) (end).

        // Debugging: Optimizing/refactoring code (begin).

        mLayoutParamsEditTextEditEntityFieldValueColumn = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsEditTextEditEntityFieldValueColumn.addRule(
                RelativeLayout.ALIGN_BASELINE,
                R.id.mTextViewEditEntityFieldNameColumn);
        mLayoutParamsEditTextEditEntityFieldValueColumn.addRule(
                RelativeLayout.ALIGN_BOTTOM,
                R.id.mTextViewEditEntityFieldNameColumn);
        mLayoutParamsEditTextEditEntityFieldValueColumn.addRule(
                RelativeLayout.RIGHT_OF,
                R.id.mTextViewEditEntityFieldNameColumn);

        // Debugging: Optimizing/refactoring code (end).

        // http://www.fampennings.nl/maarten/android/09keyboard/index.htm (begin).

//		editTextEditEntityFieldValueColumn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View view, boolean hasFocus) {
//				if (hasFocus) {
//					SimpleIME.showCustomKeyboard(view);
//				} else {
//					SimpleIME.hideCustomKeyboard();
//				}
//			}
//		});

        // Debugging.
//		editTextEditEntityFieldValueColumn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				SimpleIME.showCustomKeyboard(mFrameLayout);
//			}
//		});

        // Debugging: Refactoring edit screen running (with the new keyboard screen) (begin): Useless code (below).

//		editTextEditEntityFieldValueColumn.setOnClickListener(new View.OnClickListener() {
//		// editTextEditEntityFieldValueColumn.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public void onClick(View view) {
//			// public boolean onTouch(View view, MotionEvent motionEvent) {
//
//				// Debugging: Removing ArranqueActivity.getAppApplicationContext() (change for getApplicationContext()).
//				Intent mIntent = new Intent(getApplicationContext(), DynamicEditFieldSectionActivity.class);
//
//				// Edited field label.
//				mIntent.putExtra("editFieldLabel", mTextViewEditEntityFieldNameColumn.getText());
//
//				// Edited field content.
//				mIntent.putExtra("editFieldContent", editTextEditEntityFieldValueColumn.getText().toString());
//
//				// Edited field input type.
//				mIntent.putExtra("editFieldInputType", editTextEditEntityFieldValueColumn.getInputType());
//
//				// Edited field length.
//				mIntent.putExtra("editFieldLength", Integer.toString(editTextEditEntityFieldValueColumn.getMaxEms()));
//
//				startActivityForResult(mIntent, 100);
//
//				// Enter animation definition.
//				overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
//
//				// return true; // Don't works!
//				// return false; // Don't works!
//			}
//		});

        // Debugging: Refactoring edit screen running (with the new keyboard screen) (end).

//		editTextEditEntityFieldValueColumn.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View view, MotionEvent motionEvent) {
//				EditText edittext = (EditText) view;
//				int inType = edittext.getInputType();       // Backup the input type.
//				edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard.
//				edittext.onTouchEvent(motionEvent);               // Call native handler.
//				edittext.setInputType(inType);              // Restore input type.
//				return true; // Consume touch event.
//			}
//		});

        // http://www.fampennings.nl/maarten/android/09keyboard/index.htm (end).

        // Spinner definition & settings.

        // http://stackoverflow.com/questions/2784081/android-create-spinner-programmatically-from-array
        // http://developer.android.com/guide/topics/ui/controls/spinner.html
        // http://www.mkyong.com/android/android-spinner-drop-down-list-example/

        // mSpinnerEditEntityFieldValueColumn = new Spinner(this);
        mSpinnerEditEntityFieldValueColumn.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        mLayoutParamsSpinnerEditEntityFieldValueColumn = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // mLayoutParamsSpinnerEditEntityFieldValueColumn.addRule(
        //		RelativeLayout.ALIGN_BASELINE,
        // 		R.id.mTextViewEditEntityFieldNameColumn);
        mLayoutParamsSpinnerEditEntityFieldValueColumn.addRule(
                RelativeLayout.ALIGN_BOTTOM,
                R.id.mTextViewEditEntityFieldNameColumn);
        mLayoutParamsSpinnerEditEntityFieldValueColumn.addRule(
                RelativeLayout.RIGHT_OF,
                R.id.mTextViewEditEntityFieldNameColumn);
        // mLayoutParamsSpinnerEditEntityFieldValueColumn.width = 220; // Works!

        // mCheckBoxEditEntityFieldValueColumn definition & settings.

        mCheckBoxEditEntityFieldValueColumn = new CheckBox(this);
        mCheckBoxEditEntityFieldValueColumn.setId(R.id.mCheckBoxEditEntityFieldValueColumn);

        mLayoutParamsCheckBoxEditEntityFieldValueColumn = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsCheckBoxEditEntityFieldValueColumn.addRule(
                RelativeLayout.ALIGN_BASELINE,
                R.id.mTextViewEditEntityFieldNameColumn);
        mLayoutParamsCheckBoxEditEntityFieldValueColumn.addRule(
                RelativeLayout.ALIGN_BOTTOM,
                R.id.mTextViewEditEntityFieldNameColumn);
        mLayoutParamsCheckBoxEditEntityFieldValueColumn.addRule(
                RelativeLayout.RIGHT_OF,
                R.id.mTextViewEditEntityFieldNameColumn);

        // Debugging: Page Up / Down feature (begin).

        // Debugging: Navigation flow control (begin).

        // mRelativeLayoutFooter definition & settings.

        mRelativeLayoutFooter = new RelativeLayout(this);
        mRelativeLayoutFooter.setBackgroundColor(getResources().getColor(R.color.gray));

        mLayoutParamsRelativeLayoutFooter = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        mLayoutParamsRelativeLayoutFooter.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mLayoutParamsRelativeLayoutFooter.height = 60;

        if (mForward != null) {

            mButtonList = new Button[mForward.getArrayListForwardButton().size()];
            mLayoutParamsButtonList = new RelativeLayout.LayoutParams[mForward.getArrayListForwardButton().size()];

            Iterator<ForwardButton> itArrayListForwardButton = mForward.getArrayListForwardButton().iterator();
            ForwardButton forwardButton;

            int i = 0;
            while (itArrayListForwardButton.hasNext()) {

                forwardButton = itArrayListForwardButton.next();

                mButtonList[i] = new Button(this);
                mButtonList[i].setId(getResources().getIdentifier(forwardButton.getId(), "id", this.getPackageName()));
                mButtonList[i].setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
                mButtonList[i].setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                mButtonList[i].setHeight(60);
                mButtonList[i].setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
                mButtonList[i].setTextColor(getResources().getColor(R.color.white));
                mButtonList[i].setTypeface(Typeface.DEFAULT_BOLD);
                mButtonList[i].setText(forwardButton.getLabel());

                mButtonList[i].setTag(R.id.mForwardButtonAction, forwardButton.getAction());

                if (forwardButton.getValidationMethod() != null) {

                    mButtonList[i].setTag(R.id.mForwardButtonValidationMethod, forwardButton.getValidationMethod());
                    mButtonList[i].setTag(R.id.mForwardButtonValidationMethodClassName, forwardButton.getValidationMethodClassName());
                    // mButtonList[i].setTag(R.id.ForwardButtonValidationMethodParameter, forwardButton.getValidationMethodParameter());
                    // mButtonList[i].setTag(R.id.ForwardButtonValidationMethodParameterType, forwardButton.getValidationMethodParameterType());

                }

                mLayoutParamsButtonList[i] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (i == 0) {
                    mLayoutParamsButtonList[i].addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                }
                else {
                    mLayoutParamsButtonList[i].addRule(RelativeLayout.RIGHT_OF, mButtonList[i - 1].getId());
                }
                mLayoutParamsButtonList[i].width = 155;
                mLayoutParamsButtonList[i].setMargins(5, 5, 0, 5);

                mButtonList[i].setOnClickListener(new OnClickListener() {

                    public void onClick(View view) {

                        Class<?> actionClazz;
                        Intent intent;

                        try {

                            actionClazz = Class.forName((String) view.getTag(R.id.mForwardButtonAction));

                            if (view.getTag(R.id.mForwardButtonValidationMethod) != null) {

                                // Retrieving the value of Class's field.

//								String fieldValue = (String) DatabaseHelper.getEntityFieldValue((String) view.getTag(R.id.ForwardButtonValidationMethodClassName), "methodParameter");

//								// validationMethodClazz = Class.forName((String) view.getTag(R.id.ForwardButtonValidationMethodClassName));
//								// Field field = validationMethodClazz.getDeclaredField("methodParameter");
//								// field.setAccessible(true);
//								// Object object = field.get(validationMethodClazz.newInstance());

                                // Calling validation method.
                                if (!(Boolean) DatabaseHelper.invokeMethod(
                                        (String) view.getTag(R.id.mForwardButtonValidationMethodClassName),
                                        (String) view.getTag(R.id.mForwardButtonValidationMethod),
                                        view.getContext(),
                                        Context.class)) {
//								if (!(Boolean) DatabaseHelper.invokeMethod(
//										(String) view.getTag(R.id.ForwardButtonValidationMethodClassName),
//										(String) view.getTag(R.id.ForwardButtonValidationMethod),
//										null,
//										null)) {

                                    // Modal screen.

                                    // Debugging: Fullscreen AlertDialog (begin).

                                    // TODO: Adapt from comunicaxxilight context to tipxxi context.
                                    // Utils.displayAlertDialog(view.getContext(), Utils.CONFIRMATION_LEVEL , "Validation error", "Invalid PIN!");

                                    // Debugging: Fullscreen AlertDialog (end).

                                }
                                else {

                                    intent = new Intent(getApplicationContext(), actionClazz);

                                    startActivity(intent);

                                    // Exit animation definition.
                                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

                                }

                            }
                            else {

                                intent = new Intent(getApplicationContext(), actionClazz);

                                startActivity(intent);

                                // Exit animation definition.
                                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

                            }

                        }
                        catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
//						catch (NoSuchFieldException e) {
//							e.printStackTrace();
//						}
//						catch (InstantiationException e) {
//							e.printStackTrace();
//						}
//						catch (IllegalAccessException e) {
//							e.printStackTrace();
//						}

                    }

                });

                mButtonList[i].setOnTouchListener(new View.OnTouchListener() {

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

                });

                mRelativeLayoutFooter.addView(mButtonList[i], mLayoutParamsButtonList[i]);

                i++;

            }

        }
        else {

            // Debugging: Navigation flow control (end).

            // mButtonPageDown definition & settings.

            mButtonPageDown = new Button(this);
            mButtonPageDown.setId(R.id.mButtonPageDown);
            mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
            mButtonPageDown.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonPageDown.setHeight(60);
            mButtonPageDown.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonPageDown.setTextColor(getResources().getColor(R.color.white));
            mButtonPageDown.setTypeface(Typeface.DEFAULT_BOLD);
            mButtonPageDown.setText(R.string.page_down);

            mLayoutParamsButtonPageDown = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParamsButtonPageDown.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mLayoutParamsButtonPageDown.width = 155;
            // mLayoutParamsButtonPageDown.width = 200;
            mLayoutParamsButtonPageDown.setMargins(5, 5, 0, 5);

            mButtonPageDown.setOnClickListener(btnOnClickListenerImpl);

            mButtonPageDown.setOnTouchListener(btnOnTouchListenerImpl);

            // mButtonPageUp definition & settings.

            mButtonPageUp = new Button(this);
            mButtonPageUp.setId(R.id.mButtonPageUp);
            mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
            mButtonPageUp.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonPageUp.setHeight(60);
            mButtonPageUp.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonPageUp.setTextColor(getResources().getColor(R.color.white));
            mButtonPageUp.setTypeface(Typeface.DEFAULT_BOLD);
            mButtonPageUp.setText(R.string.page_up);

            mLayoutParamsButtonPageUp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParamsButtonPageUp.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonPageDown);
            mLayoutParamsButtonPageUp.width = 155;
            // mLayoutParamsButtonPageUp.width = 200;
            mLayoutParamsButtonPageUp.setMargins(5, 5, 0, 5);

            mButtonPageUp.setOnClickListener(btnOnClickListenerImpl);

            mButtonPageUp.setOnTouchListener(btnOnTouchListenerImpl);

            // Debugging: Page Up / Down feature (end).

            // Button "Save" definition & settings.

            mButtonSave = new Button(this);
            mButtonSave.setId(R.id.mButtonSave);

            // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

            if (mIntent.getStringExtra("action") != null) {

                // Updating a entity.
                if (mIntent.getStringExtra("action").equals("U")) {

                    if ((Boolean) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("UPDATE"), null)) {

                        mButtonSave.setEnabled(true);
                        mButtonSave.setBackground(getResources().getDrawable(R.drawable.button_shape));

                    }
                    else {

                        mButtonSave.setEnabled(false);
                        mButtonSave.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

                    }

                }
                // Creating a entity.
                else {

                    mButtonSave.setEnabled(true);
                    mButtonSave.setBackground(getResources().getDrawable(R.drawable.button_shape));

                }

            }

            // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

            // Debugging: Footer (begin).

            // Debugging: Button borders (begin).

            // mButtonSave.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
            // mButtonSave.setBackgroundColor(getResources().getColor(R.color.slategray));
            // mButtonSave.setBackgroundColor(getResources().getColor(R.color.orange));

            // Debugging: Button borders (end).

            // mButtonSave.setEms(10);
            mButtonSave.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonSave.setHeight(60);
            mButtonSave.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonSave.setTextColor(getResources().getColor(R.color.white));
            mButtonSave.setTypeface(Typeface.DEFAULT_BOLD);

            // Debugging: Footer (end).

            mButtonSave.setText(R.string.save);

            // Debugging: Optimizing/refactoring code (begin).

            mButtonSave.setOnClickListener(btnOnClickListenerImpl);

            mButtonSave.setOnTouchListener(btnOnTouchListenerImpl);

            // mButtonSave.setOnClickListener(new BtnOnClickListenerImpl());

            // mButtonSave.setOnTouchListener(new BtnOnTouchListenerImpl());

            // Debugging: Optimizing/refactoring code (end).

            // Debugging: Footer.
            //		RelativeLayout.LayoutParams layoutParamsButtonSave = new RelativeLayout.LayoutParams(
            //				MATCH_PARENT, MATCH_PARENT);
            mLayoutParamsButtonSave = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // Debugging: Footer (begin).
            mLayoutParamsButtonSave.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonPageUp);
            // layoutParamsButtonSave.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            mLayoutParamsButtonSave.width = 155;
            // layoutParamsButtonSave.width = 200;
            mLayoutParamsButtonSave.setMargins(5, 5, 0, 5);

            //		layoutParamsButtonSave.bottomMargin = 30;
            //		layoutParamsButtonSave.leftMargin = 30;

            // Debugging: Footer (end).

            // Button "Delete" definition & settings.

            mButtonDelete = new Button(this);
            mButtonDelete.setId(R.id.mButtonDelete);


            // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

            if ((Boolean) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("DELETE"), null)) {

                if (mIntent.getStringExtra("action") != null) {

                    // Creating a entity.
                    if (mIntent.getStringExtra("action").equals("C")) {

                        mButtonDelete.setEnabled(false);
                        mButtonDelete.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

                    }
                    // Updating a entity.
                    else {

                        mButtonDelete.setEnabled(true);
                        mButtonDelete.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
                        // mButtonDelete.setBackgroundColor(getResources().getColor(R.color.slategray));
                        // mButtonDelete.setBackgroundColor(getResources().getColor(R.color.orangered));

                    }

                }

            }
            else {

                mButtonDelete.setEnabled(false);
                mButtonDelete.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

            }

            // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

            // Debugging: Footer (begin).

            // Debugging: Navigation flow control (begin).

            // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

            /*

            if (mIntent.getStringExtra("action") != null) {

                // Creating a entity.
                if (mIntent.getStringExtra("action").equals("C")) {

                    mButtonDelete.setEnabled(false);
                    mButtonDelete.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

                }
                // Updating a entity.
                else {

                    mButtonDelete.setEnabled(true);
                    mButtonDelete.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
                    // mButtonDelete.setBackgroundColor(getResources().getColor(R.color.slategray));
                    // mButtonDelete.setBackgroundColor(getResources().getColor(R.color.orangered));

                }

            }

            */

            // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

            // Debugging: Navigation flow control (end).

            // Debugging: Button borders (end).

            // mButtonDelete.setEms(10);
            mButtonDelete.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonDelete.setHeight(60);
            mButtonDelete.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonDelete.setTextColor(getResources().getColor(R.color.white));
            mButtonDelete.setTypeface(Typeface.DEFAULT_BOLD);

            // Debugging: Footer (end).

            mButtonDelete.setText(R.string.delete);

            // Debugging: Optimizing/refactoring code (begin).

            mButtonDelete.setOnClickListener(btnOnClickListenerImpl);

            mButtonDelete.setOnTouchListener(btnOnTouchListenerImpl);

            // mButtonDelete.setOnClickListener(new BtnOnClickListenerImpl());

            // mButtonDelete.setOnTouchListener(new BtnOnTouchListenerImpl());

            // Debugging: Optimizing/refactoring code (end).

            mLayoutParamsButtonDelete = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // Debugging: Footer (begin).

            // layoutParamsButtonDelete.addRule(RelativeLayout.ALIGN_BASELINE, R.id.buttonSave);
            // layoutParamsButtonDelete.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.buttonSave);
            mLayoutParamsButtonDelete.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonSave);

            mLayoutParamsButtonDelete.width = 155;
            // layoutParamsButtonDelete.width = 200;
            mLayoutParamsButtonDelete.setMargins(5, 5, 0, 5);

            // Debugging: Footer (end).

            // mButtonCancel definition & settings.

            mButtonCancel = new Button(this);
            mButtonCancel.setId(R.id.mButtonCancel);

            // Debugging: Footer (begin).

            // Debugging: Button borders (begin).

            mButtonCancel.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
            // mButtonCancel.setBackgroundColor(getResources().getColor(R.color.slategray));
            // mButtonCancel.setBackgroundColor(getResources().getColor(R.color.green));

            // Debugging: Button borders (end).

            // mButtonCancel.setEms(10);
            mButtonCancel.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonCancel.setHeight(60);
            mButtonCancel.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonCancel.setTextColor(getResources().getColor(R.color.white));
            mButtonCancel.setTypeface(Typeface.DEFAULT_BOLD);

            // Debugging: Footer (end).

            mButtonCancel.setText(R.string.cancel);

            mButtonCancel.setOnClickListener(btnOnClickListenerImpl);

            mButtonCancel.setOnTouchListener(btnOnTouchListenerImpl);

            mLayoutParamsButtonCancel = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // Debugging: Footer (begin).

            // layoutParamsButtonCancel.addRule(RelativeLayout.ALIGN_BASELINE, R.id.buttonDelete);
            // layoutParamsButtonCancel.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.buttonDelete);
            mLayoutParamsButtonCancel.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonDelete);

            mLayoutParamsButtonCancel.width = 155;
            // layoutParamsButtonCancel.width = 200;
            mLayoutParamsButtonCancel.setMargins(5, 5, 0, 5);

            // Debugging: Navigation flow control (begin).

            // mRelativeLayoutFooter definition & settings.

            //		mRelativeLayoutFooter = new RelativeLayout(this);
            //		mRelativeLayoutFooter.setBackgroundColor(getResources().getColor(R.color.gray));
            //
            //		mLayoutParamsRelativeLayoutFooter = new RelativeLayout.LayoutParams(FILL_PARENT, FILL_PARENT);
            //		mLayoutParamsRelativeLayoutFooter.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            //		mLayoutParamsRelativeLayoutFooter.height = 60;

            // Debugging: Navigation flow control (end).

            // Debugging: Footer (end).

            // Debugging: Page Up / Down feature (begin).

            // Updating the page number.
            updatePageInformation();

            // Disabling/enabling "Page Down" and "Page Up" buttons.

            mButtonPageUp.setEnabled(false);
            mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

            // Disabling/enabling "Page Down" and "Page Up" buttons.

            mButtonPageUp.setEnabled(false);
            mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

            // Debugging: Return ArrayList<Object> option (begin).

            if (mEntityValueHolderList.length == 0 ||
                    mEntityValueHolderList.length <= EntityEditAdapter.MAX_ROW_DISPLAY) {

                mButtonPageDown.setEnabled(false);
                mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));

            }
            else if (mEntityValueHolderList.length > EntityEditAdapter.MAX_ROW_DISPLAY) {

                mButtonPageDown.setEnabled(true);
                mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_shape));

            }

            //		if (mArrayListObject.size() == 0 ||
            //				mArrayListObject.size() <= EntityListAdapter.MAX_ROW_DISPLAY) {
            //
            //			mButtonPageDown.setEnabled(false);
            //			mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));
            //
            //		}
            //		else if (mArrayListObject.size() > EntityListAdapter.MAX_ROW_DISPLAY) {
            //
            //			mButtonPageDown.setEnabled(true);
            //			mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_shape));
            //
            //		}

            // Debugging: Return ArrayList<Object> option (end).

            // Debugging: Navigation flow control (begin).

            mRelativeLayoutFooter.addView(mButtonPageDown, mLayoutParamsButtonPageDown);
		    mRelativeLayoutFooter.addView(mButtonPageUp, mLayoutParamsButtonPageUp);
            mRelativeLayoutFooter.addView(mButtonSave, mLayoutParamsButtonSave);
            mRelativeLayoutFooter.addView(mButtonDelete, mLayoutParamsButtonDelete);
            mRelativeLayoutFooter.addView(mButtonCancel, mLayoutParamsButtonCancel);

        }

        // Debugging: Navigation flow control (end).

        // Debugging: Page Up / Down feature (end).

        // Adding views.

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme).
        mRelativeLayout.addView(mTextViewBreadcrumb, mLayoutParamsTextViewBreadcrumb); // Works fine!
        // mLinearLayout.addView(mTextViewBreadcrumb, mLayoutParamsTextViewBreadcrumb);
        // Debugging: Page Up / Down feature.
        mRelativeLayout.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation); // Works fine!
        // mLinearLayout.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);

        // mRelativeLayout.addView(mListView);
        mLinearLayout.addView(mListView);
        mRelativeLayout.addView(mLinearLayout);

        // Debugging: Optimizing/refactoring code (begin).

        // Debugging: Optimizing/refactoring code.
        // mRelativeLayout.addView(mTextViewEditEntityFieldNameColumn, mLayoutParamsTextViewEditEntityFieldNameColumn);

        if (mEntityFieldParameters[0][Utils.TIPO].equals("T_BOOL")) {

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//			if (mEntityFieldParameters[0][Utils.IS_EDITAVEL].equals("false")) {
//				mCheckBoxEditEntityFieldValueColumn.setEnabled(false);
//			}
//			else {
//				mCheckBoxEditEntityFieldValueColumn.setEnabled(true);
//			}
//			if (mEntityValueHolderList[0].value.equals(getResources().getString(R.string.nao_PT))) {
//				mCheckBoxEditEntityFieldValueColumn.setChecked(false);
//			}
//			else {
//				mCheckBoxEditEntityFieldValueColumn.setChecked(true);
//			}
//
//			mRelativeLayout.addView(mCheckBoxEditEntityFieldValueColumn, mLayoutParamsCheckBoxEditEntityFieldValueColumn);

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).
        }
        else if (mEntityFieldParameters[0][Utils.TIPO].equals("T_LISTA") ||
                mEntityFieldParameters[0][Utils.TIPO].equals("T_TABELA")) {

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//			// Debugging: 1st field in the list bug (begin).
//
//			// mSpinnerEditEntityFieldValueColumn.setSelection(0);
//
//			mSpinnerEditEntityFieldValueColumn.setSelection(
//					mSpinnerEditEntityFieldValueList[Integer.valueOf(mEntityFieldParameters[0][Utils.COL_INDEX])].indexOf(mEntityValueHolderList[0].value)
//			);
//
//			// mSpinnerEditEntityFieldValueList[Integer.valueOf(mEntityFieldParameters[0][Utils.COL_INDEX])].indexOf(
//			//		mSpinnerEditEntityFieldValueList[Integer.valueOf(mEntityFieldParameters[0][Utils.COL_INDEX])].indexOf(mEntityValueHolderList[0].value));
//
//			// Debugging: 1st field in the list bug (end).
//
//			mRelativeLayout.addView(mSpinnerEditEntityFieldValueColumn, mLayoutParamsSpinnerEditEntityFieldValueColumn);

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).
        }
        else if (mEntityFieldParameters[0][Utils.TIPO].equals("T_ALFANUM") ||
                mEntityFieldParameters[0][Utils.TIPO].equals("T_NUMERICO")) {

            // Debugging: Refactoring edit screen running (with the new keyboard screen) (begin): mTextViewEditEntityFieldNameColumn not displayed if TIPO = "T_ALFANUM" || "T_NUMERICO".

            mTextViewEditEntityFieldNameColumn.setVisibility(View.GONE);

            // Debugging: Removing editTextEditEntityFieldValueColumn.
            // editTextEditEntityFieldValueColumn.setVisibility(View.GONE);

            // Debugging: Refactoring edit screen running (with the new keyboard screen) (end): mTextViewEditEntityFieldNameColumn not displayed if TIPO = "T_ALFANUM" || "T_NUMERICO".

            // Debugging: Removing editTextEditEntityFieldValueColumn.
            // mRelativeLayout.addView(editTextEditEntityFieldValueColumn, mLayoutParamsEditTextEditEntityFieldValueColumn);

        }

        // Debugging: Optimizing/refactoring code (end).

        // Debugging: Footer (begin).

        // Debugging: Navigation flow control (begin).

        // Debugging: Page Up / Down feature (begin).

//		mRelativeLayoutFooter.addView(mButtonPageDown, mLayoutParamsButtonPageDown);
//		mRelativeLayoutFooter.addView(mButtonPageUp, mLayoutParamsButtonPageUp);

        // Debugging: Page Up / Down feature (end).

//		mRelativeLayoutFooter.addView(mButtonSave, mLayoutParamsButtonSave);
//		mRelativeLayoutFooter.addView(mButtonDelete, mLayoutParamsButtonDelete);
//		mRelativeLayoutFooter.addView(mButtonCancel, mLayoutParamsButtonCancel);

        // Debugging: Navigation flow control (end).

        mRelativeLayout.addView(mRelativeLayoutFooter, mLayoutParamsRelativeLayoutFooter);

//		mRelativeLayout.addView(mButtonSave, layoutParamsButtonSave);
//		mRelativeLayout.addView(mButtonDelete, layoutParamsButtonDelete);
//		mRelativeLayout.addView(mButtonCancel, layoutParamsButtonCancel);

        // Debugging: Footer (end).

        mFrameLayout.addView(mRelativeLayout, mRelativeLayoutParams);

        // setContentView(mRelativeLayout);
        setContentView(mFrameLayout);

    }

    private class BtnOnClickListenerImpl implements OnClickListener {

        boolean haveValidationError;

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                // Debugging: Page Up / Down feature (begin).

                case R.id.mButtonPageDown:

                    refreshEntityListPageDown();

                    break;

                case R.id.mButtonPageUp:

                    refreshEntityListPageUp();

                    break;

                // Debugging: Page Up / Down feature (end).

                case R.id.mButtonSave:

                    mSpinnerEditEntityFieldValueListSelected[mLastEditPosition] = mSpinnerItemSelected;

                    // Update mEntityValueHolderList[mLastEditPosition] with editTextEditEntityFieldValueColumn or
                    // mCheckBoxEditEntityFieldValueColumn

                    if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_BOOL")) {

                        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//						if (mCheckBoxEditEntityFieldValueColumn.isChecked()) {
//							mEntityValueHolderList[mLastEditPosition].value = getResources().getString(R.string.sim_PT);
//						}
//						else {
//							mEntityValueHolderList[mLastEditPosition].value = getResources().getString(R.string.nao_PT);
//						}

                        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

                    }
                    else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_LISTA")) {

                        // Debugging: Fixing T_LISTA feature.
                        // mEntityValueHolderList[mLastEditPosition].value = mSpinnerEditEntityFieldValueListSelected[mLastEditPosition];

                    }
                    else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_TABELA")) {

                        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity.
                        // mEntityValueHolderList[mLastEditPosition].value = mSpinnerEditEntityFieldValueIndexList[mLastEditPosition].get(mSpinnerEditEntityFieldValueList[mLastEditPosition].indexOf(mSpinnerEditEntityFieldValueListSelected[mLastEditPosition])).toString();

                    }
                    else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_ALFANUM") ||
                            mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_NUMERICO")) {
                        // Debugging: Bug: Not updating the updated field in the list of fields (remove editTextEditEntityFieldValueColumn?).
                        // mEntityValueHolderList[mLastEditPosition].value = editTextEditEntityFieldValueColumn.getText().toString();
                    }

				/*
				 * Updating mEntityValueHolderList of type = T_TABELA with DB index of selected item.
				 * Updating mEntityValueHolderList of type = T_BOOL with 0/1 value.
				 */
                    for (int i = 0; i < mEntityValueHolderList.length; i++) {
                        if (mEntityFieldParameters[i][Utils.TIPO].equals("T_BOOL")) {

                            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//							if (mEntityValueHolderList[i].value.equals(getResources().getString(R.string.sim_PT))) {
//								mEntityValueHolderList[i].value = getResources().getString(R.string.sim);
//							}
//							else {
//								mEntityValueHolderList[i].value = getResources().getString(R.string.nao);
//							}

                            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

                        }
                        else if (mEntityFieldParameters[i][Utils.TIPO].equals("T_TABELA")) {
                            if (mSpinnerEditEntityFieldValueListSelected[i] == null) {

                                // Debugging: 'New' feature bugs (begin).
                                if (!mEntityValueHolderList[i].value.equals("")) {

                                    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity.
                                    // mEntityValueHolderList[i].value = mSpinnerEditEntityFieldValueIndexList[i].get(mSpinnerEditEntityFieldValueList[i].indexOf(mEntityValueHolderList[i].value)).toString();

                                }
                                // mEntityValueHolderList[i].value = mSpinnerEditEntityFieldValueIndexList[i].get(mSpinnerEditEntityFieldValueList[i].indexOf(mEntityValueHolderList[i].value)).toString();
                                // Debugging: 'New' feature bugs (end).

                            }
                            else {
                                mEntityValueHolderList[i].value = mSpinnerEditEntityFieldValueIndexList[i].get(mSpinnerEditEntityFieldValueList[i].indexOf(mSpinnerEditEntityFieldValueListSelected[i])).toString();
                            }
                        }
                    }

                    // Validating form data.
                    haveValidationError = false;

                    for (int i = 0; i < mEntityValueHolderList.length; i++) {
                        if (mEntityFieldParameters[i][Utils.IS_EDITAVEL].equals("true")) {
                            if (mEntityFieldParameters[i][Utils.IS_NOT_NULL].equals("true")) {
                                if (mEntityValueHolderList[i].value.equals("")) {
                                    // if (mEntityValueHolderList[i].value.toString().equals("")) {

                                    // TODO: Adapt from comunicaxxilight context to tipxxi context.

//                                    Toast toast = Toast.makeText(MenuPrincipalActivity.getAppApplicationContext(),
//                                            (CharSequence) mEntityFieldParameters[i][Utils.CAPTION] + " cannot be null",
//                                            Toast.LENGTH_LONG);
//
//                                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
//
//                                    toast.show();

                                    Utils.displayToast(mEntityFieldParameters[i][Utils.CAPTION] + " cannot be null", Toast.LENGTH_LONG);

                                    haveValidationError = true;
                                }
                            }
                        }
                    }
                    if (!haveValidationError) {

                        try {
                            // Setting object values.

                            clazz = Class.forName(Utils.ENTITY_PACKAGE_NAME + mEntityName);
                            Object objectToWrite = clazz.newInstance();
                            Method method;
                            // Debugging (tipxxi): ID/Code alphanumeric.
                            String objectToWriteId = "";

                            for (int i = 0; i < mEntityValueHolderList.length; i++) {

                                if (!mEntityValueHolderList[i].value.toString().equals("")) {

                                    // Debugging (tipxxi): ID/Code alphanumeric (begin).
                                    if (mEntityValueHolderList[i].name.equals("id" + mEntityName)) {
                                        objectToWriteId = mEntityValueHolderList[i].value;
                                    }

                                    // Debugging (tipxxi): ID/Code alphanumeric (end).

                                    method = clazz.getMethod("set"
                                                    + mEntityValueHolderList[i].name.substring(0, 1).toUpperCase(Locale.getDefault())
                                                    + mEntityValueHolderList[i].name.substring(1, mEntityValueHolderList[i].name.length()),
                                            clazz.getDeclaredField(mEntityValueHolderList[i].name).getType());
                                    if (clazz.getDeclaredField(mEntityValueHolderList[i].name).getType().equals(Integer.class)) {
                                        method.invoke(objectToWrite, Integer.valueOf(mEntityValueHolderList[i].value));
                                    }
                                    else if (clazz.getDeclaredField(mEntityValueHolderList[i].name).getType().equals(Float.class)) {
                                        method.invoke(objectToWrite, Float.valueOf(mEntityValueHolderList[i].value));
                                    }
                                    else if (clazz.getDeclaredField(mEntityValueHolderList[i].name).getType().equals(Long.class)) {
                                        method.invoke(objectToWrite, Long.valueOf(mEntityValueHolderList[i].value));
                                    }
                                    else if (clazz.getDeclaredField(mEntityValueHolderList[i].name).getType().equals(String.class)) {
                                        method.invoke(objectToWrite, mEntityValueHolderList[i].value.toString());
                                    }

                                }

                            }

                            if (mIntent.getStringExtra("action").equals("C")) {

                                // Debugging (tipxxi): ID/Code alphanumeric (begin).

                                switch (mDatabaseHelper.createEntity(objectToWrite)) {

                                    case 0:

                                        // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
                                        Utils.displayToast((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ENTITY_CAPTION"), null) + " created", Toast.LENGTH_LONG);
                                        // Utils.displayToast((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null) + " created");
                                        // Utils.displayToast(mEntityName + " created");
                                        // Debugging (tipxxi): "Duplicated ID" case = keep in Edit screen.
                                        finish();
                                        break;

                                    // Debugging (tipxxi): SQLiteException (begin).

                                    case 97:

                                        Utils.displayToast("Foreign key (FK) constraint failed", Toast.LENGTH_LONG);
                                        break;

                                    // Debugging (tipxxi): SQLiteException (end).

                                    case 98:

                                        Utils.displayToast("Duplicated id" + mEntityName, Toast.LENGTH_LONG);
                                        break;

                                    case 99:

                                        Utils.displayToast("Unexpected error", Toast.LENGTH_LONG);
                                        break;

                                }

//                                // Creating entity.
//                                mDatabaseHelper.createEntity(object);
//
//                                Utils.displayToast(mEntityName + " created");

                                // Debugging (tipxxi): ID/Code alphanumeric (end).

                            }
                            else {

                                // Debugging (tipxxi): ID/Code alphanumeric (begin).

                                switch (mDatabaseHelper.updateEntity(objectToWrite, loadedObjectId)) {

                                    case 0:
                                        // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
                                        Utils.displayToast((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ENTITY_CAPTION"), null) + " updated", Toast.LENGTH_LONG);
                                        // Utils.displayToast((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null) + " updated");
                                        // Utils.displayToast(mEntityName + " updated");
                                        // Debugging (tipxxi): "Duplicated ID" case = keep in Edit screen.
                                        finish();
                                        break;

                                    case 97:

                                        Utils.displayToast("id" + mEntityName + " can not be changed (is referenced by another register)", Toast.LENGTH_LONG);
                                        break;

                                    case 98:

                                        Utils.displayToast("Duplicated id" + mEntityName, Toast.LENGTH_LONG);
                                        break;

                                    case 99:

                                        Utils.displayToast("Unexpected error", Toast.LENGTH_LONG);
                                        break;

                                }

//                                // Updating entity.
//                                mDatabaseHelper.updateEntity(objectToWrite);
//
//                                Utils.displayToast(mEntityName + " updated");

                                // Debugging (tipxxi): ID/Code alphanumeric (end).

                            }
                        }
                        catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        catch (InstantiationException instantiationException) {
                            instantiationException.printStackTrace();
                        }
                        catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            illegalArgumentException.printStackTrace();
                        }
                        catch (InvocationTargetException invocationTargetException) {
                            invocationTargetException.printStackTrace();
                        }
                        catch (NoSuchMethodException noSuchMethodException) {
                            noSuchMethodException.printStackTrace();
                        }
                        catch (NoSuchFieldException noSuchFieldException) {
                            noSuchFieldException.printStackTrace();
                        }

                        // Debugging (tipxxi): "Duplicated ID" case = keep in Edit screen.
                        // finish();

                    }

                    // Exiting animation definition.
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

                    break;

                case R.id.mButtonDelete:

                    try {

                        clazz = Class.forName(Utils.ENTITY_PACKAGE_NAME + mEntityName);
                        Object objectDelete = clazz.newInstance();
                        Field lIdField = objectDelete.getClass().getDeclaredField("id" + mEntityName);
                        Method method = clazz.getMethod("setId" + mEntityName, lIdField.getType());
                        // Method method = clazz.getMethod("setId" + mEntityName, Integer.class);
                        if (mIntent.getStringExtra("id" + mEntityName) != null) {

                            if (lIdField.getType().equals(Integer.class)) {
                                method.invoke(objectDelete, Integer.valueOf(mIntent.getStringExtra("id" + mEntityName)));
                            }
                            else {
                                method.invoke(objectDelete, mIntent.getStringExtra("id" + mEntityName));
                            }

                            // method.invoke(objectDelete, Integer.valueOf(mIntent.getStringExtra("id" + mEntityName)));

                        }

                        switch (mDatabaseHelper.deleteEntity(objectDelete)) {

                            case 0:

                                // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
                                Utils.displayToast((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("ENTITY_CAPTION"), null) + " deleted", Toast.LENGTH_LONG);
                                // Utils.displayToast((String) mDatabaseHelper.getEntityFieldAttributeValue(mEntityName, new String("CAPTION"), null) + " deleted");
                                // Utils.displayToast(mEntityName + " deleted");
                                // Debugging (tipxxi): "Duplicated ID" case = keep in Edit screen.
                                finish();
                                break;

                            case 98:

                                Utils.displayToast(mEntityName + " can not be deleted (referenced by another register)", Toast.LENGTH_LONG);
                                break;

                            case 99:

                                Utils.displayToast("Unexpected error", Toast.LENGTH_LONG);
                                break;

                        }

                    }
                    catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    catch (InstantiationException instantiationException) {
                        instantiationException.printStackTrace();
                    }
                    catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                    catch (NoSuchMethodException noSuchMethodException) {
                        noSuchMethodException.printStackTrace();
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        illegalArgumentException.printStackTrace();
                    }
                    catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    }
                    catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }

                    finish();

                    // Exiting animation definition.
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

                    break;

                case R.id.mButtonCancel:

                    mIntent = new Intent();

                    // Debugging: Navigation flow control (begin).

//                    HandleXML handleXML = new HandleXML();
//                    Forward forward = null;

                    finish();

//                    if (handleXML.hasScreen(TAG)) {
//                        if (handleXML.hasScreenForward(TAG, "R.id.mButtonCancel")) {
//                            forward = handleXML.getScreenForward(TAG, "R.id.mButtonCancel");
//                            mIntent.setClassName(Utils.PACKAGE_NAME, forward.getClassName());
//                            mIntent.putExtra("forward", forward);
//                            startActivity(mIntent);
//                        }
//                    }
//                    else {
//                        finish();
//                    }

//                  setResult(99, mIntent);

                    // Debugging: Dialog feature (begin).

//                  openAlertDialog(getApplicationContext(), "Operation canceled");
//					Utils.openAlertDialog(getApplicationContext(), "Operation canceled");
//					Utils.displayToast("Operation canceled");

                    // Debugging: Dialog feature (end).

//                  finish();

                    // Debugging: Navigation flow control (end).

                    // Exiting animation definition.
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

                    break;
            }
        }

    }

    // Debugging: Refactoring updateSpinnerEditEntityFieldValueColumn (~) and loadSpinnerData (~): duplicated code (begin).

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

    // Debugging (tipxxi): Scrap code (begin).

    /*
    // Duplicated code updateSpinnerEditEntityFieldValueColumn() = DynamicEditFieldSectionActivity::updateMSpinnerEditFieldData().
    private void updateSpinnerEditEntityFieldValueColumn(String pField, String pSpinnerDataType, Integer pPosition) {

        List<String> fieldValueList = new ArrayList<String>();
        List<String> fieldValueIndexList = new ArrayList<String>();
        // List<Integer> fieldValueIndexList = new ArrayList<Integer>();

        if (pSpinnerDataType.equals("T_TABELA")) {
            // if (pField.startsWith("id")) {
            // Retrieving T_TABELA.

            // Debugging: Return ArrayList<Object> option (begin).

            ArrayList<Object> arrayListObject = mDatabaseHelper.getAllEntityInstances(pField.substring(2, pField.length()));
            Object[] listObject = arrayListObject.toArray(new Object[arrayListObject.size()]);

            // Debugging: Return ArrayList<Object> option (end).

            // Debugging: Return Object[] option.

//			Object[] listObject = mDatabaseHelper.getAllEntityInstances(pField.substring(2, pField.length()));

            Field[] listField = DatabaseHelper.getEntityFields(pField.substring(2, pField.length()));
            try {
                // Iterating over entity instances.
                for (int i = 0; i < listObject.length; i++) {
                    // Iterating over entity instance fields.
                    for (int j = 0; j < listField.length; j++) {

                        listField[j].setAccessible(true);

                        // Debugging (tipxxi): "nome"[entity] = "id"[entity] (begin).

                        if (listField[j].getName().equals("id" + pField.substring(2, pField.length()))) {

                            fieldValueIndexList.add(listField[j].get(listObject[i]).toString());

                            if (DatabaseHelper.getEntityField(pField.substring(2, pField.length()), "nome" + pField.substring(2, pField.length())) == null) {
                                fieldValueList.add(listField[j].get(listObject[i]).toString());
                            }

                        }

//                        // Getting the content of field with name: "id" + Entity name.
//                        if (listField[j].getName().equals("id" + pField.substring(2, pField.length()))) {
//                            fieldValueIndexList.add(listField[j].get(listObject[i]).toString());
//                            // fieldValueIndexList.add(Integer.valueOf(listField[j].get(listObject[i]).toString()));
//                        }

                        // Debugging (tipxxi): "nome"[entity] = "id"[entity] (end).

                        // Getting the content of field with name: "nome" + Entity name.
                        if (listField[j].getName().equals("nome" + pField.substring(2, pField.length()))) {

                            if (listField[j].getName().equals("nomeEmpresa")) {
                                Log.i(TAG, "nome" + pField.substring(2, pField.length()));
                            }

                            fieldValueList.add(listField[j].get(listObject[i]).toString());
                        }

                    }
                }
                mSpinnerEditEntityFieldValueIndexList[pPosition] = fieldValueIndexList;
                mSpinnerEditEntityFieldValueList[pPosition] = fieldValueList;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
            }
            catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        else if (pSpinnerDataType.equals("T_LISTA")) {
            // else {
            // Retrieving T_LISTA.
            mSpinnerEditEntityFieldValueList[pPosition] = Utils.loadTLista(pField);
        }

        // Selecting a item in 'normal' mode (but little).
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mSpinnerEditEntityFieldValueList[pPosition]);
        // Selecting a item in 'normal' mode (bigger selector).
        // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        // 		android.R.layout.simple_selectable_list_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item); // Selecting a item in 'normal' mode (bigger font).
        // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item); // Selecting a item in 'normal' mode (smaller font).
        // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Selecting a item using radio.
        // dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice); // Selecting a item using radio.
        // dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked); // Selecting a item using checking.

        mSpinnerEditEntityFieldValueColumn.setAdapter(dataAdapter);

    }
    */

    // Debugging (tipxxi): Scrap code (end).

//	private void loadSpinnerData(String pField, String pSpinnerDataType, Integer pPosition) {
//
//		List<String> fieldValueList = new ArrayList<String>();
//		List<Integer> fieldValueIndexList = new ArrayList<Integer>();
//
//		if (pSpinnerDataType.equals("T_TABELA")) {
//			// Retrieving T_TABELA.
//			ArrayList<Object> arrayListObject = mDatabaseHelper.getAllEntityInstances(pField.substring(2, pField.length()));
//			Object[] listObject = arrayListObject.toArray(new Object[arrayListObject.size()]);
//			Field[] listField = DatabaseHelper.getEntityFields(pField.substring(2, pField.length()));
//			try {
//				// Iterating over entity instances.
//				for (int i = 0; i < listObject.length; i++) {
//					// Iterating over entity instance fields.
//					for (int j = 0; j < listField.length; j++) {
//						listField[j].setAccessible(true);
//						// Getting the content of field with name: "id" + Entity name.
//						if (listField[j].getName().equals("id" + pField.substring(2, pField.length()))) {
//							fieldValueIndexList.add(Integer.valueOf(listField[j].get(listObject[i]).toString()));
//						}
//						// Getting the content of field with name: "nome" + Entity name.
//						if (listField[j].getName().equals("nome" + pField.substring(2, pField.length()))) {
//							fieldValueList.add(listField[j].get(listObject[i]).toString());
//						}
//					}
//				}
//				mSpinnerEditEntityFieldValueIndexList[pPosition] = fieldValueIndexList;
//				mSpinnerEditEntityFieldValueList[pPosition] = fieldValueList;
//			}
//			catch (IllegalArgumentException e) {
//				Log.i(TAG, "readEntity(): IllegalArgumentException!");
//				e.printStackTrace();
//			}
//			catch (IllegalAccessException e) {
//				Log.i(TAG, "readEntity(): IllegalArgumentException!");
//				e.printStackTrace();
//			}
//		}
//		else if (pSpinnerDataType.equals("T_LISTA")) {
//			// Retrieving T_LISTA.
//			mSpinnerEditEntityFieldValueList[pPosition] = Utils.loadTLista(pField);
//		}
//
//	}

    // Debugging: Refactoring updateSpinnerEditEntityFieldValueColumn (~) and loadSpinnerData (~): the same coding (end).

    public class EntityEditAdapter extends ArrayAdapter<Object> {

        Context context;
        int layoutResourceId;
        Object data[] = null;

        // Debugging: Page Up / Down feature.
        // private static final int MAX_ROW_DISPLAY = 4; // Min. value = 1; Max. value = 9 (for 800 x 400 screen layout).
        private static final int MAX_ROW_DISPLAY = 9; // Min. value = 1; Max. value = 9 (for 800 x 400 screen layout).

        public EntityEditAdapter(Context context, int layoutResourceId,
                                 EntityValueHolder[] data) {

            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            EntityValueHolder entityValueHolder = (EntityValueHolder) data[position];

            // TODO: Debugging: Messing up mEntityFieldParameters.
            // TODO: Load mEntityFieldParameters[position] only if mEntityFieldParameters[position] == null;

            // Debugging: Correcting position (scroll down big trouble (with Parametro) (begin).
//			if (!mEntityFieldParameters[position][Utils.CAMPO].equals(entityValueHolder.name)){
//				Log.i(TAG, "getView(): old position = " + position);
//				position = correctFieldParametersPosition(entityValueHolder.name);
//				// Debugging.
//				Log.i(TAG, "getView(): new position = " + position);
//			}
            // Debugging: Correcting position (scroll down big trouble (with Parametro) (end).

            if (entityValueHolder != null) {

                // Debugging: Return to the current page (not always to the first) after creating/updating a register (begin).

                position = position + (EntityEditAdapter.MAX_ROW_DISPLAY * (mPageNumber - 1));

                // Debugging: Navigation flow control (begin).

                if (mForward != null) {

                    if (mForward.getForwardEntity() != null) {

                        mEntityFieldParameters[position] = (String[]) DatabaseHelper.invokeMethod(
                                mForward.getForwardEntity().getName(),
                                "getAttributeParameters",
                                entityValueHolder.name,
                                String.class
                        );

                    }
                }
                else {

                    mEntityFieldParameters[position] = (String[]) DatabaseHelper.invokeMethod(
                            Utils.ENTITY_PACKAGE_NAME + mEntityName,
                            "getAttributeParameters",
                            entityValueHolder.name,
                            String.class
                    );

                }

//				mEntityFieldParameters[position] = (String[]) DatabaseHelper.invokeMethod(
//						mEntityName, "getAttributeParameters",
//						String.class, entityValueHolder.name);

                // Debugging: Navigation flow control (end).

                // Debugging: Return to the current page (not always to the first) after creating/updating a register (end).

                mLinearLayoutListRow = new LinearLayout(context.getApplicationContext());
                mLinearLayoutListRow.setOrientation(LinearLayout.HORIZONTAL);
                mLinearLayoutListRow.setLayoutParams(new android.widget.AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
                mLinearLayoutListRow.setPadding(10, 10, 10, 10);

                mTextViewListEntityFieldNameColumn = new TextView(context.getApplicationContext());
                mTextViewListEntityFieldNameColumn.setId(R.id.mTextViewListEntityFieldNameColumn);
                mTextViewListEntityFieldNameColumn.setGravity(Gravity.CENTER_VERTICAL);
                mTextViewListEntityFieldNameColumn.setTextColor(0xff000000);
                mTextViewListEntityFieldNameColumn.setTextSize(20);
                mTextViewListEntityFieldNameColumn.setWidth(200);

                mLinearLayoutListRow.addView(mTextViewListEntityFieldNameColumn);

                mTextViewListEntityFieldValueColumn = new TextView(context.getApplicationContext());
                mTextViewListEntityFieldValueColumn.setId(R.id.mTextViewListEntityFieldValueColumn);
                mTextViewListEntityFieldValueColumn.setGravity(Gravity.CENTER_VERTICAL);
                mTextViewListEntityFieldValueColumn.setTextColor(0xff000000);
                mTextViewListEntityFieldValueColumn.setTextSize(20);
                mTextViewListEntityFieldValueColumn.setWidth(200);

                mLinearLayoutListRow.addView(mTextViewListEntityFieldValueColumn);

                // textViewIdRow = new TextView(getActivity());
                // textViewIdRow.setId(R.id.textViewIdRow);
                // textViewIdRow.setWidth(100);
                // textViewIdRow.setGravity(Gravity.CENTER_VERTICAL);
                // textViewIdRow.setTextColor(0xff000000);
                // textViewIdRow.setTextSize(20);
                //
                // mLinearLayoutListRow.addView(textViewIdRow);

                convertView = mLinearLayoutListRow;

                // Adding a asterisk to mandatory field's label.
                if (mEntityFieldParameters[position][Utils.IS_NOT_NULL].equals("true")) {
                    mTextViewListEntityFieldNameColumn.setText("* " + mEntityFieldParameters[position][Utils.CAPTION]);
                }
                else {
                    mTextViewListEntityFieldNameColumn.setText(mEntityFieldParameters[position][Utils.CAPTION]);
                }

                if (mEntityFieldParameters[position][Utils.IS_PASSWD].equals("true")) {
                    String passwordField = new String("");
                    for (int i = 0; i < entityValueHolder.value.length(); i++) {
                        passwordField = passwordField + "*";
                    }
                    mTextViewListEntityFieldValueColumn.setText(passwordField);
                    mTextViewListEntityFieldValueColumn.setWidth(passwordField.length() * 10);
                }
                else {

                    if (mEntityFieldParameters[position][Utils.TIPO].equals("T_BOOL")) {
                        if (!entityValueHolder.value.equals("")) {
                            if (entityValueHolder.value.equals("0")) {
                                mTextViewListEntityFieldValueColumn.setText(getResources().getString(R.string.nao_PT));
                            }
                            else {
                                mTextViewListEntityFieldValueColumn.setText(getResources().getString(R.string.sim_PT));
                            }
                        }

                    }
                    else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_LISTA")) {

                        // Debugging: Fixing loadTLista (begin).

                        if (entityValueHolder.value != null) {
                            if (!entityValueHolder.value.equals("")) {

                                // Debugging: Retrieve entity field value with any attribute.
                                mTextViewListEntityFieldValueColumn.setText(
                                        (String) Utils.getEntityFieldFromClass(
                                                entityValueHolder.name,
                                                entityValueHolder.value));
//								mTextViewListEntityFieldValueColumn.setText(
//										retrieveFieldValue(
//												mEntityFieldParameters[position][Utils.TIPO],
//												entityValueHolder.name,
//												entityValueHolder.value));

                                mTextViewListEntityFieldValueColumn.setWidth(entityValueHolder.value.length() * 100);

                            }
                        }

//						mTextViewListEntityFieldValueColumn.setText(entityValueHolder.value);
//						mTextViewListEntityFieldValueColumn.setWidth(entityValueHolder.value.length() * 100);

                        // Debugging: Fixing loadTLista (end).

                    }
                    else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_TABELA")) {
                        if (entityValueHolder.value != null) {
                            if (!entityValueHolder.value.equals("")) {

                                // Debugging (tipxxi): "nome"[entity] = "id"[entity] (begin).

                                if (mDatabaseHelper.
                                        getEntityField(
                                                entityValueHolder.name.substring(2, entityValueHolder.name.length()),
                                                "nome" + entityValueHolder.name.substring(2, entityValueHolder.name.length())) != null) {

                                    mTextViewListEntityFieldValueColumn.setText(
                                            (String) mDatabaseHelper.getEntityFieldAttributeValue(
                                                    entityValueHolder.name.substring(2, entityValueHolder.name.length()),
                                                    "nome" + entityValueHolder.name.substring(2, entityValueHolder.name.length()),
                                                    entityValueHolder.value));

                                }
                                else {

                                    mTextViewListEntityFieldValueColumn.setText(
                                            (String) mDatabaseHelper.getEntityFieldAttributeValue(
                                                    entityValueHolder.name.substring(2, entityValueHolder.name.length()),
                                                    "id" + entityValueHolder.name.substring(2, entityValueHolder.name.length()),
                                                    entityValueHolder.value));

                                }

//                                // Debugging: Retrieve entity field value with any attribute.
//                                mTextViewListEntityFieldValueColumn.setText(
//                                        (String) mDatabaseHelper.getEntityFieldAttributeValue(
//                                                entityValueHolder.name.substring(2, entityValueHolder.name.length()),
//                                                "nome" + entityValueHolder.name.substring(2, entityValueHolder.name.length()),
//                                                entityValueHolder.value));

                                // Debugging (tipxxi): "nome"[entity] = "id"[entity] (end).

//								mTextViewListEntityFieldValueColumn.setText(
//										retrieveFieldValue(
//												mEntityFieldParameters[position][Utils.TIPO],
//												entityValueHolder.name,
//												entityValueHolder.value));

                                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity.
                                // mTextViewListEntityFieldValueColumn.setText(retrieveFieldValue(entityValueHolder.name, entityValueHolder.value));

                                mTextViewListEntityFieldValueColumn.setWidth(entityValueHolder.value.length() * 100);

                            }
                        }
                    }
                    else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_ALFANUM") ||
                            mEntityFieldParameters[position][Utils.TIPO].equals("T_NUMERICO")) {
                        mTextViewListEntityFieldValueColumn.setText(entityValueHolder.value);
                        mTextViewListEntityFieldValueColumn.setWidth(entityValueHolder.value.length() * 100);
                    }
                }
            }

            return convertView;

            // Debugging: Page Up / Down feature (begin).

//			}
//			else {
//				return null;
//			}

            // Debugging: Page Up / Down feature (end).

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
        String name;
        String value;
    }

    public class CustomOnItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Debugging: Return to the current page (not always to the first) after creating/updating a register (begin).

            mLastEditPosition = position + (EntityEditAdapter.MAX_ROW_DISPLAY * (mPageNumber - 1));
            position = position + (EntityEditAdapter.MAX_ROW_DISPLAY * (mPageNumber - 1));

            // Debugging: Return to the current page (not always to the first) after creating/updating a register (end).

            mSpinnerEditEntityFieldValueListSelected[mLastEditPosition] = mSpinnerItemSelected;

            // Debugging: Refactoring edit screen running (with the new keyboard screen) (begin): mTextViewEditEntityFieldNameColumn not displayed if TIPO = "T_ALFANUM" || "T_NUMERICO".

//			if (mEntityFieldParameters[position][Utils.TIPO].equals("T_ALFANUM") ||
//					mEntityFieldParameters[position][Utils.TIPO].equals("T_NUMERICO")) {
//
//				// mTextViewEditEntityFieldNameColumn.setVisibility(View.VISIBLE);
//				// editTextEditEntityFieldValueColumn.setVisibility(View.VISIBLE);
//
//				mTextViewEditEntityFieldNameColumn.setVisibility(View.GONE);
//				editTextEditEntityFieldValueColumn.setVisibility(View.GONE);
//
//			}
//			else {
//
//				mTextViewEditEntityFieldNameColumn.setVisibility(View.VISIBLE);
//				editTextEditEntityFieldValueColumn.setVisibility(View.VISIBLE);
//
//				// mTextViewEditEntityFieldNameColumn.setVisibility(View.GONE);
//				// editTextEditEntityFieldValueColumn.setVisibility(View.GONE);
//
//			}

            // Debugging: Refactoring edit screen running (with the new keyboard screen) (end).

            // Updating edited field value in the entity's list of fields.

            if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_BOOL")) {

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//				if (mCheckBoxEditEntityFieldValueColumn.isChecked()) {
//					mEntityValueHolderList[mLastEditPosition].value = getResources().getString(R.string.sim_PT);
//				}
//				else {
//					mEntityValueHolderList[mLastEditPosition].value = getResources().getString(R.string.nao_PT);
//				}

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

            }
            else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_LISTA") ||
                    mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_TABELA")) {

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity.
                // mEntityValueHolderList[mLastEditPosition].value = mSpinnerEditEntityFieldValueListSelected[mLastEditPosition];

            }
            else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_ALFANUM") ||
                    mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_NUMERICO")) {
                // Debugging: Bug: Not updating the updated field in the list of fields (remove editTextEditEntityFieldValueColumn?).
                // mEntityValueHolderList[mLastEditPosition].value = editTextEditEntityFieldValueColumn.getText().toString();
            }

            // Refreshing the list of entity's fields.

            // Debugging: Return to the current page (not always to the first) after creating/updating a register (begin).

//			// Debugging: Removing ArranqueActivity.getAppApplicationContext() (change for getApplicationContext()).
//			EntityEditAdapter entityEditAdapter = new EntityEditAdapter(getApplicationContext(), R.layout.fragment_list_row, mEntityValueHolderList);
//			// EntityEditAdapter entityEditAdapter = new EntityEditAdapter(ArranqueActivity.getAppApplicationContext(), R.layout.fragment_list_row, mEntityValueHolderList);
//			mListView.setAdapter(entityEditAdapter);

            // Debugging: Return to the current page (not always to the first) after creating/updating a register (end).

            // Debugging: Optimizing/refactoring code (begin).

            // Adding a asterisk to mandatory edit field's label.

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//			if (mEntityFieldParameters[position][Utils.IS_NOT_NULL].equals("true")) {
//				mTextViewEditEntityFieldNameColumn.setText("* " + mEntityFieldParameters[position][Utils.CAPTION]);
//			}
//			else {
//				mTextViewEditEntityFieldNameColumn.setText(mEntityFieldParameters[position][Utils.CAPTION]);
//			}

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

            // Updating edit field.

            if (mEntityFieldParameters[position][Utils.TIPO].equals("T_BOOL")) {

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//				// Debugging: The last fuckin' bug (begin): Spinner.
//				if (mTextViewEditEntityFieldNameColumn.getVisibility() == View.GONE) {
//					mTextViewEditEntityFieldNameColumn.setVisibility(View.VISIBLE);
//				}
//				// Debugging: The last fuckin' bug (end).
//
//				if (mEntityValueHolderList[position].value.equals(getResources().getString(R.string.nao_PT))) {
//					mCheckBoxEditEntityFieldValueColumn.setChecked(false);
//				}
//				else {
//					mCheckBoxEditEntityFieldValueColumn.setChecked(true);
//				}
//
//				// Adding/Removing Views.
//
//				if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_LISTA") ||
//					mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_TABELA")) {
//					mRelativeLayout.addView(mCheckBoxEditEntityFieldValueColumn, mLayoutParamsCheckBoxEditEntityFieldValueColumn);
//					mRelativeLayout.removeView(mSpinnerEditEntityFieldValueColumn);
//				}
//				else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_ALFANUM") ||
//						 mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_NUMERICO")) {
//					mRelativeLayout.addView(mCheckBoxEditEntityFieldValueColumn, mLayoutParamsCheckBoxEditEntityFieldValueColumn);
//					// Debugging: Removing editTextEditEntityFieldValueColumn.
//					// mRelativeLayout.removeView(editTextEditEntityFieldValueColumn);
//				}

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

            }
            else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_LISTA") ||
                    mEntityFieldParameters[position][Utils.TIPO].equals("T_TABELA")) {

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

                // Debugging: The last fuckin' bug (begin): Spinner.

//				if (mTextViewEditEntityFieldNameColumn.getVisibility() == View.GONE) {
//					mTextViewEditEntityFieldNameColumn.setVisibility(View.VISIBLE);
//				}
//
//				// Debugging: The last fuckin' bug (end).
//
//				updateSpinnerEditEntityFieldValueColumn(
//						mEntityFieldParameters[position][Utils.CAMPO],
//						mEntityFieldParameters[position][Utils.TIPO],
//						position);
//				// updateSpinnerEditEntityFieldValueColumn(mEntityFieldParameters[position][Utils.CAMPO], position);
//
//				mSpinnerEditEntityFieldValueColumn.setSelection(mSpinnerEditEntityFieldValueList[position].indexOf(mEntityValueHolderList[position].value));
//
//				// Adding/Removing Views.
//
//				if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_BOOL")) {
//					mRelativeLayout.addView(mSpinnerEditEntityFieldValueColumn, mLayoutParamsSpinnerEditEntityFieldValueColumn);
//					mRelativeLayout.removeView(mCheckBoxEditEntityFieldValueColumn);
//				}
//				else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_ALFANUM") ||
//						 mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_NUMERICO")) {
//					mRelativeLayout.addView(mSpinnerEditEntityFieldValueColumn, mLayoutParamsSpinnerEditEntityFieldValueColumn);
//					// Debugging: Removing editTextEditEntityFieldValueColumn.
//					// mRelativeLayout.removeView(editTextEditEntityFieldValueColumn);
//				}

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

            }
            else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_ALFANUM") ||
                    mEntityFieldParameters[position][Utils.TIPO].equals("T_NUMERICO")) {

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

//				// Debugging: The last fuckin' bug (begin): Spinner.
//
//				if (mTextViewEditEntityFieldNameColumn.getVisibility() == View.VISIBLE) {
//					mTextViewEditEntityFieldNameColumn.setVisibility(View.GONE);
//				}
//
//				// Debugging: Removing editTextEditEntityFieldValueColumn (begin).
//
////				if (editTextEditEntityFieldValueColumn.getVisibility() == View.VISIBLE) {
////					editTextEditEntityFieldValueColumn.setVisibility(View.GONE);
////				}
//
//				// Debugging: Removing editTextEditEntityFieldValueColumn (end).
//
//				// Debugging: The last fuckin' bug (end).
//
//				// Debugging: Removing editTextEditEntityFieldValueColumn (begin).
//
////				editTextEditEntityFieldValueColumn.setEms(Integer.valueOf(mEntityFieldParameters[position][Utils.MAX_LENGTH]));
////				// Setting the max. length of field.
////				InputFilter[] inputFilterArray = new InputFilter[1];
////				inputFilterArray[0] = new InputFilter.LengthFilter(Integer.valueOf(mEntityFieldParameters[position][Utils.MAX_LENGTH]));
////				editTextEditEntityFieldValueColumn.setFilters(inputFilterArray);
////				editTextEditEntityFieldValueColumn.setText(mEntityValueHolderList[position].value);
//
//				// Debugging: Removing editTextEditEntityFieldValueColumn (end).
//
//				// Debugging: Refactoring edit screen running (with the new keyboard screen) (begin): editTextEditEntityFieldValueColumn.setEnabled(false) always.
//
////				if (mEntityFieldParameters[position][Utils.IS_EDITAVEL].equals("true")) {
////					if (mEntityFieldParameters[position][Utils.IS_PASSWD].equals("true")) {
////						editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
////					}
////					else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_ALFANUM")) {
////						editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT);
////					}
////					else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_NUMERICO")) {
////						editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_NUMBER);
////					}
////				}
//
//				// Debugging: Removing editTextEditEntityFieldValueColumn (begin).
//
////				if (mEntityFieldParameters[position][Utils.IS_EDITAVEL].equals("false")) {
////					editTextEditEntityFieldValueColumn.setEnabled(false);
////				}
////				else {
////					editTextEditEntityFieldValueColumn.setEnabled(true);
////					if (mEntityFieldParameters[position][Utils.IS_PASSWD].equals("true")) {
////						editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
////					}
////					else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_ALFANUM")) {
////						editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_TEXT);
////					}
////					else if (mEntityFieldParameters[position][Utils.TIPO].equals("T_NUMERICO")) {
////						editTextEditEntityFieldValueColumn.setInputType(InputType.TYPE_CLASS_NUMBER);
////					}
////				}
//
//				// Debugging: Removing editTextEditEntityFieldValueColumn (end).
//
//				// Debugging: Refactoring edit screen running (with the new keyboard screen) (end).
//
//				// Adding/Removing Views.
//
//				if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_BOOL")) {
//
//					// Debugging: Removing editTextEditEntityFieldValueColumn.
//					// mRelativeLayout.addView(editTextEditEntityFieldValueColumn, mLayoutParamsEditTextEditEntityFieldValueColumn);
//
//					mRelativeLayout.removeView(mCheckBoxEditEntityFieldValueColumn);
//				}
//				else if (mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_LISTA") ||
//						 mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_TABELA")) {
//
//					// Debugging: Removing editTextEditEntityFieldValueColumn.
//					// mRelativeLayout.addView(editTextEditEntityFieldValueColumn, mLayoutParamsEditTextEditEntityFieldValueColumn);
//
//					mRelativeLayout.removeView(mSpinnerEditEntityFieldValueColumn);
//				}

                // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

            }

            // Debugging: Optimizing/refactoring code (end).

            // Debugging: Refactoring edit screen running (with the new keyboard screen) (begin).

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).
            if (mEntityFieldParameters[position][Utils.IS_EDITAVEL].equals("true")) {
//			if ((mEntityFieldParameters[position][Utils.TIPO].equals("T_ALFANUM") ||
//					mEntityFieldParameters[position][Utils.TIPO].equals("T_NUMERICO")) &&
//					(mEntityFieldParameters[position][Utils.IS_EDITAVEL].equals("true"))) {
//			if ((mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_ALFANUM") ||
//					mEntityFieldParameters[mLastEditPosition][Utils.TIPO].equals("T_NUMERICO")) &&
//					(mEntityFieldParameters[mLastEditPosition][Utils.IS_EDITAVEL].equals("true"))) {

                // Debugging (tipxxi): T_TABELA & T_LISTA new selection screen (begin).

                Intent intentDynamicEntityFieldEditActivity;

                if ((mEntityFieldParameters[position][Utils.TIPO].equals("T_TABELA") ||
                        (mEntityFieldParameters[position][Utils.TIPO].equals("T_LISTA")))) {

                    intentDynamicEntityFieldEditActivity = new Intent(getApplicationContext(), DynamicGridEntityFieldSelectionActivity.class);

                    intentDynamicEntityFieldEditActivity.putExtra("editFieldName", mEntityValueHolderList[position].name);

                    if ((mEntityFieldParameters[position][Utils.TIPO].equals("T_TABELA"))) {
                        intentDynamicEntityFieldEditActivity.putExtra("editFieldEntityName", mEntityFieldParameters[position][Utils.CAMPO].substring(2, mEntityFieldParameters[position][Utils.CAMPO].length()));
                    }
                    else {
                        intentDynamicEntityFieldEditActivity.putExtra("editFieldEntityName", mEntityName);
                    }

                    intentDynamicEntityFieldEditActivity.putExtra("editFieldInputType", mEntityFieldParameters[position][Utils.TIPO]);

                    intentDynamicEntityFieldEditActivity.putExtra("editFieldLabel", mEntityFieldParameters[position][Utils.CAPTION]);


                }
                else {

                    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

                    intentDynamicEntityFieldEditActivity = new Intent(getApplicationContext(), DynamicEditFieldSectionActivity.class);

                    // Edited field label.

                    // Debugging: Optimizing/refactoring code.
                    intentDynamicEntityFieldEditActivity.putExtra("editFieldLabel", mEntityFieldParameters[position][Utils.CAPTION]);
                    // intentDynamicEditFieldSectionActivity.putExtra("editFieldLabel", mEntityValueHolderList[position].name);
                    // intentDynamicEditFieldSectionActivity.putExtra("editFieldLabel", mTextViewEditEntityFieldNameColumn.getText());

                    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

                    // Edited field name.

                    intentDynamicEntityFieldEditActivity.putExtra("editFieldName", mEntityValueHolderList[position].name);

                    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

                    // Edited field content.

                    // Debugging: Return to the current page (not always to the first) after creating/updating a register (begin).

//				    intentDynamicEditFieldSectionActivity.putExtra("editFieldContent", mEntityValueHolderList[(EntityEditAdapter.MAX_ROW_DISPLAY * (mPageNumber - 1)) + position].value);

                    // Debugging: Removing editTextEditEntityFieldValueColumn.
                    intentDynamicEntityFieldEditActivity.putExtra("editFieldContent", mEntityValueHolderList[position].value);
                    // intentDynamicEditFieldSectionActivity.putExtra("editFieldContent", editTextEditEntityFieldValueColumn.getText().toString());

                    // Debugging: Return to the current page (not always to the first) after creating/updating a register (end).

                    // Edited field input type.

                    // Debugging: Removing editTextEditEntityFieldValueColumn.
                    intentDynamicEntityFieldEditActivity.putExtra("editFieldInputType", mEntityFieldParameters[position][Utils.TIPO]);
                    // intentDynamicEditFieldSectionActivity.putExtra("editFieldInputType", Integer.toString(editTextEditEntityFieldValueColumn.getInputType()));

                    // Edited field length.

                    // Debugging: Removing editTextEditEntityFieldValueColumn.
                    intentDynamicEntityFieldEditActivity.putExtra("editFieldLength", mEntityFieldParameters[position][Utils.MAX_LENGTH]);
                    // intentDynamicEditFieldSectionActivity.putExtra("editFieldLength", Integer.toString(editTextEditEntityFieldValueColumn.getMaxEms()));

                    // Debugging: Navigation flow control (begin).
                    intentDynamicEntityFieldEditActivity.putExtra("editFieldIsPasswd", mEntityFieldParameters[position][Utils.IS_PASSWD]);
                    // Debugging: Navigation flow control (end).

                }

                startActivityForResult(intentDynamicEntityFieldEditActivity, 100);

                // Enter animation definition.
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

                // Debugging (tipxxi): T_TABELA & T_LISTA new selection screen (end).

            }

            // Debugging: Refactoring edit screen running (with the new keyboard screen) (end).

            mLastEditPosition = position;

        }

    }

    private class CustomOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            mSpinnerItemSelected = parent.getItemAtPosition(pos).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }

    }

    // Debugging: Correcting position (scroll down big trouble (with Parametro) (begin).
//	private int correctFieldParametersPosition(String pCampo) {
//
//		int correctedPosition = 0;
//
//		for (int i = 0; i < mEntityFieldParameters.length; i++) {
//			if (mEntityFieldParameters[i][Utils.CAMPO].equals(pCampo)){
//				correctedPosition = i;
//			}
//		}
//
//		return correctedPosition;
//
//	}
    // Debugging: Correcting position (scroll down big trouble (with Parametro) (end).

    // TODO: Returning point after edit field. Better solution update only the position in mEntityValueHolderList than read from DB:
    // TODO: DynamicGridSectionFragment > refreshEntityList() > mArrayListObject = mDatabaseHelper.getAllEntityInstances(mEntityName);

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // OK! (Refreshing the list of entity's fields and don't spoiling Spinner).

        EntityValueHolder[] newEntityValueHolderList;

        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {

            // Debugging: Return to the current page (not always to the first) after creating/updating a register (begin).

            // Debugging.
            mEntityValueHolderList[mLastEditPosition].value = data.getStringExtra("editedFieldContent");
            // mEntityValueHolderList[mLastEditPosition].value = data.getStringExtra("editTextEditFieldContent");
            // mEntityValueHolderList[(EntityEditAdapter.MAX_ROW_DISPLAY * (mPageNumber - 1)) + mLastEditPosition].value = data.getStringExtra("editTextEditFieldContent");

            // Debugging.
            if (mPageNumber == 1 ||
                    mPageNumber == mMaxPageNumber) {
                // if ((mPageNumber + 1) == mMaxPageNumber) {
                newEntityValueHolderList = new EntityValueHolder[mEntityValueHolderList.length - ((mPageNumber - 1) * EntityEditAdapter.MAX_ROW_DISPLAY)];
                // newEntityValueHolderList = new EntityValueHolder[mEntityValueHolderList.length - (mPageNumber * EntityEditAdapter.MAX_ROW_DISPLAY)];
            }
            else {
                newEntityValueHolderList = new EntityValueHolder[EntityEditAdapter.MAX_ROW_DISPLAY];
            }

            // Filling the grid.

            for (int i = (mPageNumber - 1) * EntityEditAdapter.MAX_ROW_DISPLAY, j = 0; i < mPageNumber * EntityEditAdapter.MAX_ROW_DISPLAY; i++) {

                if (i < mEntityValueHolderList.length) {
                    newEntityValueHolderList[j] = mEntityValueHolderList[i];
                    j++;
                }

            }

            mEntityEditAdapter = new EntityEditAdapter(getApplicationContext(),
                    R.layout.fragment_list_row,
                    newEntityValueHolderList);

            mListView.setAdapter(mEntityEditAdapter);

//			// Debugging: Refactoring edit screen running (with the new keyboard screen): editTextEditEntityFieldValueColumn.setEnabled(false) always.
//			// editTextEditEntityFieldValueColumn.setText(data.getStringExtra("editTextEditFieldContent")); // Updating edit field: Useless now!
//
//			// Debugging: Refactoring edit screen running (with the new keyboard screen) (begin).
//
//			// Updating edited field value in the entity's list of fields.
//
//			// Debugging: Bug: Not updating the updated field in the list of fields (remove editTextEditEntityFieldValueColumn?).
//			mEntityValueHolderList[mLastEditPosition].value = data.getStringExtra("editTextEditFieldContent");
//			// mEntityValueHolderList[mLastEditPosition].value = editTextEditEntityFieldValueColumn.getText().toString();
//
//			// Refreshing the list of entity's fields.
//
//			EntityEditAdapter entityEditAdapter = new EntityEditAdapter(getApplicationContext(), R.layout.fragment_list_row, mEntityValueHolderList);
//			mListView.setAdapter(entityEditAdapter);
//
//			// Debugging: Refactoring edit screen running (with the new keyboard screen) (end).

            // Debugging: Return to the current page (not always to the first) after creating/updating a register (end).

        }

    }
    // Debugging: Page Up / Down feature (begin).

    private void refreshEntityListPageDown() {

        // TODO: Put "Return Object[] option" to work! (maybe don't work: size of Object[] need to be defined before (not dinamically grows up).

        // Debugging: Return ArrayList<Object> option (begin).

        // Debugging: Adapting DynamicGridSectionFragment algorithm (begin).

        // Checking if the next page is the last ( = mMaxPageNumber).

        EntityValueHolder[] newEntityValueHolderList;

        if ((mPageNumber + 1) == mMaxPageNumber) {
            newEntityValueHolderList = new EntityValueHolder[mEntityValueHolderList.length - (mPageNumber * EntityEditAdapter.MAX_ROW_DISPLAY)];
        }
        else {
            newEntityValueHolderList = new EntityValueHolder[EntityEditAdapter.MAX_ROW_DISPLAY];
        }

        // Filling the grid.

        for (int i = mPageNumber * EntityEditAdapter.MAX_ROW_DISPLAY, j = 0; i < (mPageNumber + 1) * EntityEditAdapter.MAX_ROW_DISPLAY; i++) {

            if (i < mEntityValueHolderList.length) {
                newEntityValueHolderList[j] = mEntityValueHolderList[i];
                j++;
            }

        }

        mEntityEditAdapter = new EntityEditAdapter(getApplicationContext(),
                R.layout.fragment_list_row,
                newEntityValueHolderList);

//		ArrayList<Object> newArrayListObject = new ArrayList<Object>();
//
//		// Filling the grid.
//
//		for (int i = mPageNumber * EntityEditAdapter.MAX_ROW_DISPLAY; i < (mPageNumber + 1) * EntityEditAdapter.MAX_ROW_DISPLAY; i++) {
//
//			if (i < mArrayListObject.size()) {
//				newArrayListObject.add(mArrayListObject.get(i));
//			}
//
//		}
//
//		mListObject = newArrayListObject.toArray(new Object[newArrayListObject.size()]);
//
//		mEntityEditAdapter = new EntityListAdapter(getActivity().getApplicationContext(), R.layout.fragment_list_row, mListObject);

        // Debugging: Adapting DynamicGridSectionFragment algorithm (end).

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

        mListView.setAdapter(mEntityEditAdapter);

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

        // Debugging: Adapting DynamicGridSectionFragment algorithm (begin).

        EntityValueHolder[] newEntityValueHolderList;

        // Debugging.
        newEntityValueHolderList = new EntityValueHolder[EntityEditAdapter.MAX_ROW_DISPLAY];
//		if ((mPageNumber + 1) == mMaxPageNumber) {
//			newEntityValueHolderList = new EntityValueHolder[mEntityValueHolderList.length - (mPageNumber * EntityEditAdapter.MAX_ROW_DISPLAY)];
//		}
//		else {
//			newEntityValueHolderList = new EntityValueHolder[EntityEditAdapter.MAX_ROW_DISPLAY];
//		}

        // Filling the grid.

        for (int i = (mPageNumber - 2) * EntityEditAdapter.MAX_ROW_DISPLAY, j = 0; i < (mPageNumber - 1) * EntityEditAdapter.MAX_ROW_DISPLAY; i++) {

            if (i < mEntityValueHolderList.length) {
                newEntityValueHolderList[j] = mEntityValueHolderList[i];
                j++;
            }

        }

        mEntityEditAdapter = new EntityEditAdapter(getApplicationContext(), R.layout.fragment_list_row, newEntityValueHolderList);

//		ArrayList<Object> newArrayListObject = new ArrayList<Object>();
//
//		// Filling the grid.
//
//		for (int i = (mPageNumber - 2) * EntityEditAdapter.MAX_ROW_DISPLAY; i < (mPageNumber - 1) * EntityEditAdapter.MAX_ROW_DISPLAY; i++) {
//			newArrayListObject.add(mArrayListObject.get(i));
//		}
//
//		mListObject = newArrayListObject.toArray(new Object[newArrayListObject.size()]);
//
//		mEntityEditAdapter = new EntityListAdapter(getActivity().getApplicationContext(), R.layout.fragment_list_row, mListObject);

        // Debugging: Adapting DynamicGridSectionFragment algorithm (end).

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

        mListView.setAdapter(mEntityEditAdapter);

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

        // TODO: Correcting the parent element.

        mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");
//		mRelativeLayoutListHeader.removeView(mTextViewPageInformation);
//		mRelativeLayoutListHeader.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);
//		mListView.removeHeaderView(mRelativeLayoutListHeader);
//		mListView.addHeaderView(mRelativeLayoutListHeader, null, false);


    }

    // Debugging: Page Up / Down feature (end).

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

    // Debugging: Fixing T_LISTA feature.
    private String retrieveFieldValue(String pFieldType, String pFieldName, String pFieldId) {
        // private String retrieveFieldValue(String pFieldName, String pFieldId, ) {

        String fieldValue = "";
        Class<?> clazz;
        Object object;
        Method method;
        Field field;
        // Debugging: Fixing T_LISTA feature (begin).
        String[] lList;

        // Debugging: Fixing T_LISTA feature (begin).

        try {

            if (pFieldType.equals("T_TABELA")) {

                clazz = Class.forName(Utils.ENTITY_PACKAGE_NAME + pFieldName.substring(2, pFieldName.length()));
                object = clazz.newInstance();
                method = clazz.getMethod("setId" + pFieldName.substring(2, pFieldName.length()), Integer.class);
                method.invoke(object, Integer.valueOf(pFieldId));
                mDatabaseHelper.readEntity(object); // DatabaseHelper::readEntity(): retrieveFieldValue() not used!
                field = object.getClass().getDeclaredField("nome" + pFieldName.substring(2, pFieldName.length()));
                field.setAccessible(true);

                fieldValue = field.get(object).toString();

            }
            else if (pFieldType.equals("T_LISTA")) {

                clazz = Class.forName("com.bpaulo.tipxxi.util.Utils");
                object = clazz.newInstance();
                field = clazz.getDeclaredField(pFieldName.toUpperCase(Locale.getDefault()));
                field.setAccessible(true);
                lList = (String[]) field.get(object);

                fieldValue = lList[Integer.valueOf(pFieldId)];

            }

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//
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

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

    // Debugging: Memory problems (begin).

    public static abstract interface IMemoryInfo {
        public void goodTimeToReleaseMemory();
    }

//	@Override
//	public void onTrimMemory(int level) {
//
//		super.onTrimMemory(level);
//
//		// don't compare with == as intermediate stages also can be reported, always better to check >= or <=
//		if (level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {
//			try {
//				// Activity at the front will get earliest than activity at the
//				// back
//				for (int i = memInfoList.size() - 1; i >= 0; i--) {
//					try {
//						memInfoList.get(i).goodTimeToReleaseMemory();
//					}
//					catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}

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

    // Debugging: Memory problems (end).

    // Debugging: Dialog feature (begin).

    public void openAlertDialog(Context pContext, String pMessage) {
        // public void openAlertDialog(View view, String pTitle, String pMessage) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DynamicEditSectionActivity.this);
//		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getApplicationContext());
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("DynamicEditSectionActivity");
        // alertDialogBuilder.setTitle(pTitle);
        alertDialogBuilder.setMessage(pMessage);

//		alertDialogBuilder.setNeutralButton("OK",
//				new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface arg0, int arg1) {
//						Intent loginActivity = new Intent(
//								getApplicationContext(),
//								com.bpaulo.comunicaxxilight.activity.LoginActivity.class);
//						startActivity(loginActivity);
//
//					}
//				});

        // Don't works!
//		Dialog dialog = alertDialogBuilder.create();
//		dialog.show();

        // Don't works!
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    // Debugging: Dialog feature (end).

    // Debugging (tipxxi): Scrap code (begin).

    /*
    // Debugging: Navigation flow control (begin).

    // This method is invoked only by reflection.
    public boolean loginValidation(Context pContext) {

        Class<?> clazz;
        Object object;
        Method method;
        Field field;

        try {

            // Retrieving the context.
            DynamicEditSectionActivity dynamicEditSectionActivity = (DynamicEditSectionActivity) pContext;

            // Retrieving Parametro register (with adminPassword field).
            clazz = Class.forName("com.bpaulo.comunicaxxilight.entity.Parametro");
            object = clazz.newInstance();
            method = clazz.getMethod("setIdParametro", Integer.class);
            method.invoke(object, dynamicEditSectionActivity.mDatabaseHelper.getHighestID("Parametro"));
            dynamicEditSectionActivity.mDatabaseHelper.readEntity(object);
            field = object.getClass().getDeclaredField("adminPassword");
            field.setAccessible(true);

            if (field != null) {
                if (dynamicEditSectionActivity.mEntityValueHolderList[0].value.equals(field.get(object).toString())) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return false;

    }

    // Debugging: Navigation flow control (end).
    */

    // Debugging (tipxxi): Scrap code (end).

}