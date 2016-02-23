package com.bpaulo.tipxxi.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.database.DatabaseHelper;
import com.bpaulo.tipxxi.keyboard.CustomKeyboard;
import com.bpaulo.tipxxi.util.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Debugging: BreadCrumb implementation: FragmentBreadCrumbs approach.
public class DynamicEditFieldSectionActivity extends FragmentActivity {
// public class DynamicEditFieldSectionActivity extends Activity {

    // Debugging: Building keyboard screen programmatically (begin).

    private static final String TAG = "DynamicEditFieldSectionActivity";

    private KeyboardView mKeyboardView;
    private RelativeLayout.LayoutParams mLayoutParamsKeyboardView;

    private ViewGroup.LayoutParams mViewGroupLayoutParams;

    // Debugging: Building keyboard screen programmatically (end).

    private CustomKeyboard mCustomKeyboard;

    // Debugging: Building keyboard screen programmatically.
    private RelativeLayout mRelativeLayout;

    private Intent mIntent;

    private TextView mTextViewBreadcrumb;
    private RelativeLayout.LayoutParams mLayoutParamsTextViewBreadcrumb;

    private TextView mTextViewEditField;
    private RelativeLayout.LayoutParams mLayoutParamsTextViewEditField;

    private EditText mEditTextEditField;
    private RelativeLayout.LayoutParams mLayoutParamsEditTextEditField;

    // Debugging: BreadCrumb implementation: FragmentBreadCrumbs approach (begin).

//    private FragmentManager mFragmentManager;
//    private FragmentBreadCrumbs mFragmentBreadCrumbs;

    // Debugging: BreadCrumb implementation: FragmentBreadCrumbs approach (end).

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

    private DatabaseHelper mDatabaseHelper;

    private CheckBox mCheckBoxEditField;
    private RelativeLayout.LayoutParams mLayoutParamsCheckBoxEditField;

    private Spinner mSpinnerEditField;
    private RelativeLayout.LayoutParams mLayoutParamsSpinnerEditField;

    private String mSpinnerEditFieldItemSelected;
    private ArrayList<String> mSpinnerEditFieldValueList;
    // Debugging (tipxxi): "nome"[entity] = "id"[entity].
    private ArrayList<String> mSpinnerEditFieldIndexList;
    // private ArrayList<Integer> mSpinnerEditFieldIndexList;

    private Button mButtonDone;
    private RelativeLayout.LayoutParams mLayoutParamsButtonDone;

    private Button mButtonCancel;
    private RelativeLayout.LayoutParams mLayoutParamsButtonCancel;

    private RelativeLayout mRelativeLayoutFooter;
    private RelativeLayout.LayoutParams mLayoutParamsRelativeLayoutFooter;

    private BtnOnClickListenerImpl btnOnClickListenerImpl;
    private BtnOnTouchListenerImpl btnOnTouchListenerImpl;

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

    // Debugging: startActivity() problem.
//    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // http://stackoverflow.com/questions/3170691/how-to-get-current-memory-usage-in-android

        // Debugging: Memory problems (begin).

//        /**
//         * The available memory on the system.  This number should not
//         * be considered absolute: due to the nature of the kernel, a significant
//         * portion of this memory is actually in use and needed for the overall
//         * system to run well.
//         */
////		public long availMem;
//
//        /**
//         * The total memory accessible by the kernel.  This is basically the
//         * RAM size of the device, not including below-kernel fixed allocations
//         * like DMA buffers, RAM for the baseband CPU, etc.
//         */
////		public long totalMem;
//
//        /**
//         * The threshold of {@link #availMem} at which we consider memory to be
//         * low and start killing background services and other non-extraneous
//         * processes.
//         */
////		public long threshold;
//
//        /**
//         * Set to true if the system considers itself to currently be in a low
//         * memory situation.
//         */
////		public boolean lowMemory;
//
//        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
//        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        // ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
//        activityManager.getMemoryInfo(memoryInfo);
//
//        long availMem = memoryInfo.availMem / 1048576L;
//        long totalMem = memoryInfo.totalMem  / 1048576L;
//        long threshold = memoryInfo.threshold  / 1048576L;
//        // Percentage can be calculated for API 16+
//        long percAvail = memoryInfo.availMem / memoryInfo.totalMem;
//
//        Log.i(TAG, "availMem = " + availMem);
//        Log.i(TAG, "totalMem = " + totalMem);
//        Log.i(TAG, "threshold = " + threshold);
//        Log.i(TAG, "percAvail = " + percAvail);

        // Debugging: Memory problems (end).

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin).

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // Useless (the code above works fine alone)?

        // Debugging: Building keyboard screen programmatically (begin).

        mViewGroupLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mIntent = getIntent();

        // Debugging: Singleton SQLiteOpenHelper.
        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity.
        mDatabaseHelper = DatabaseHelper.getInstance(this);
//        mDatabaseHelper = new DatabaseHelper(this);

        // Instantiating common fields for all cases.

        // mRelativeLayout definition & settings.

        mRelativeLayout = new RelativeLayout(this);
        mRelativeLayout.setLayoutParams(mViewGroupLayoutParams);

        // mTextViewBreadcrumb definition & settings.

        mTextViewBreadcrumb = new TextView(this);
        mTextViewBreadcrumb.setId(R.id.mTextViewBreadcrumb);
        mTextViewBreadcrumb.setBackgroundColor(getResources().getColor(R.color.gray));
        mTextViewBreadcrumb.setTextColor(getResources().getColor(R.color.white));
        mTextViewBreadcrumb.setTextAppearance(this, android.R.style.TextAppearance_Large_Inverse);
        mTextViewBreadcrumb.setText("Editing " + mIntent.getStringExtra("editFieldLabel"));

        mLayoutParamsTextViewBreadcrumb = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        mLayoutParamsTextViewBreadcrumb.height = 30;

        // mTextViewEditField definition & settings.

        mTextViewEditField = new TextView(this);
        mTextViewEditField.setId(R.id.mTextViewEditField);
        mTextViewEditField.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        mTextViewEditField.setText(mIntent.getStringExtra("editFieldLabel"));

        mLayoutParamsTextViewEditField = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsTextViewEditField.addRule(RelativeLayout.BELOW, R.id.mTextViewBreadcrumb);
        mLayoutParamsTextViewEditField.leftMargin = 30;
        mLayoutParamsTextViewEditField.topMargin = 30;

        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

        if (mIntent.getStringExtra("editFieldInputType").equals("T_ALFANUM") ||
                mIntent.getStringExtra("editFieldInputType").equals("T_NUMERICO")) {

            // Instantiating only the specific fields for this case.

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

            // mEditTextEditField definition & settings.

            mEditTextEditField = new EditText(this);
            mEditTextEditField.setId(R.id.mEditTextEditField);
            mEditTextEditField.setText(mIntent.getStringExtra("editFieldContent"));
            mEditTextEditField.setEms(Integer.parseInt(mIntent.getStringExtra("editFieldLength")));

            mLayoutParamsEditTextEditField = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParamsEditTextEditField.addRule(RelativeLayout.ALIGN_LEFT, R.id.mTextViewEditField);
            mLayoutParamsEditTextEditField.addRule(RelativeLayout.BELOW, R.id.mTextViewEditField);

            // mKeyboardView definition & settings.

            // Debugging: KeyboardView Layout settings (begin).

            mKeyboardView = new CustomKeyboardView(getApplicationContext(), null);
            // mKeyboardView = new KeyboardView(getApplicationContext(), null);

            // Debugging: KeyboardView Layout settings (end).

            mKeyboardView.setId(R.id.mKeyboardView);
            mKeyboardView.setFocusable(true);
            mKeyboardView.setFocusableInTouchMode(true);

            mLayoutParamsKeyboardView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParamsKeyboardView.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mLayoutParamsKeyboardView.addRule(RelativeLayout.CENTER_HORIZONTAL);

            // Debugging: Building keyboard screen programmatically (end).

            // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end).

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

        }
        else {

            if (mIntent.getStringExtra("editFieldInputType").equals("T_BOOL")) {

                // Instantiating only the specific fields for this case.

                // mCheckBoxEditField definition & settings.

                mCheckBoxEditField = new CheckBox(this);
                mCheckBoxEditField.setId(R.id.mCheckBoxEditField);

                mLayoutParamsCheckBoxEditField = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mLayoutParamsCheckBoxEditField.addRule(RelativeLayout.ALIGN_LEFT, R.id.mTextViewEditField);
                mLayoutParamsCheckBoxEditField.addRule(RelativeLayout.BELOW, R.id.mTextViewEditField);

            }
            else if (mIntent.getStringExtra("editFieldInputType").equals("T_LISTA") ||
                    mIntent.getStringExtra("editFieldInputType").equals("T_TABELA")) {

                // Instantiating only the specific fields for this case.

                // mSpinnerEditField definition & settings.

                // Debugging (tipxxi): Spinner sliding problem (begin).

                mSpinnerEditField = new Spinner(this);
                // mSpinnerEditField = new Spinner(this, Spinner.MODE_DIALOG); // With sliding!
                // mSpinnerEditField = new Spinner(this, Spinner.MODE_DROPDOWN); // With sliding!

                // Debugging (tipxxi): Spinner sliding problem (end).

                mSpinnerEditField.setId(R.id.mSpinnerEditField);

                mSpinnerEditField.setOnItemSelectedListener(new CustomOnItemSelectedListener());

                mLayoutParamsSpinnerEditField = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mLayoutParamsSpinnerEditField.addRule(RelativeLayout.ALIGN_LEFT, R.id.mTextViewEditField);
                mLayoutParamsSpinnerEditField.addRule(RelativeLayout.BELOW, R.id.mTextViewEditField);

            }

            btnOnClickListenerImpl = new BtnOnClickListenerImpl();
            btnOnTouchListenerImpl = new BtnOnTouchListenerImpl();

            // mButtonDone definition & settings.

            mButtonDone = new Button(this);
            mButtonDone.setId(R.id.mButtonDone);
            mButtonDone.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
            mButtonDone.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonDone.setHeight(60);
            mButtonDone.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonDone.setTextColor(getResources().getColor(R.color.white));
            mButtonDone.setTypeface(Typeface.DEFAULT_BOLD);
            mButtonDone.setText(R.string.done);

            mButtonDone.setOnClickListener(btnOnClickListenerImpl);
            mButtonDone.setOnTouchListener(btnOnTouchListenerImpl);

            mLayoutParamsButtonDone = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParamsButtonDone.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mLayoutParamsButtonDone.width = 155;
            mLayoutParamsButtonDone.setMargins(5, 5, 0, 5);

            // mButtonCancel definition & settings.

            mButtonCancel = new Button(this);
            mButtonCancel.setId(R.id.mButtonCancel);
            mButtonCancel.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
            mButtonCancel.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            mButtonCancel.setHeight(60);
            mButtonCancel.setTextAppearance(this, android.R.style.TextAppearance_Medium_Inverse);
            mButtonCancel.setTextColor(getResources().getColor(R.color.white));
            mButtonCancel.setTypeface(Typeface.DEFAULT_BOLD);
            mButtonCancel.setText(R.string.cancel);

            mButtonCancel.setOnClickListener(btnOnClickListenerImpl);
            mButtonCancel.setOnTouchListener(btnOnTouchListenerImpl);

            mLayoutParamsButtonCancel = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLayoutParamsButtonCancel.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonDone);
            mLayoutParamsButtonCancel.width = 155;
            mLayoutParamsButtonCancel.setMargins(5, 5, 0, 5);

            // mRelativeLayoutFooter definition & settings.

            mRelativeLayoutFooter = new RelativeLayout(this);
            mRelativeLayoutFooter.setBackgroundColor(getResources().getColor(R.color.gray));

            mLayoutParamsRelativeLayoutFooter = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            mLayoutParamsRelativeLayoutFooter.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mLayoutParamsRelativeLayoutFooter.height = 60;

        }

        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

        // Debugging: Building keyboard screen programmatically (begin).

        // Adding views.

        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

        mRelativeLayout.addView(mTextViewBreadcrumb, mLayoutParamsTextViewBreadcrumb);
        mRelativeLayout.addView(mTextViewEditField, mLayoutParamsTextViewEditField);

        if (mIntent.getStringExtra("editFieldInputType").equals("T_ALFANUM") ||
                mIntent.getStringExtra("editFieldInputType").equals("T_NUMERICO")) {

            mRelativeLayout.addView(mEditTextEditField, mLayoutParamsEditTextEditField);
            mRelativeLayout.addView(mKeyboardView, mLayoutParamsKeyboardView);

        }
        else {

            if (mIntent.getStringExtra("editFieldInputType").equals("T_BOOL")) {

                mRelativeLayout.addView(mCheckBoxEditField, mLayoutParamsCheckBoxEditField);

            }
            else if (mIntent.getStringExtra("editFieldInputType").equals("T_LISTA") ||
                    mIntent.getStringExtra("editFieldInputType").equals("T_TABELA")) {

                mRelativeLayout.addView(mSpinnerEditField, mLayoutParamsSpinnerEditField);

            }

            mRelativeLayoutFooter.addView(mButtonDone, mLayoutParamsButtonDone);
            mRelativeLayoutFooter.addView(mButtonCancel, mLayoutParamsButtonCancel);

            mRelativeLayout.addView(mRelativeLayoutFooter, mLayoutParamsRelativeLayoutFooter);

        }

        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

        setContentView(mRelativeLayout);
        // setContentView(mKeyboardView);
        // setContentView(R.layout.keyboard);

        // Debugging: Building keyboard screen programmatically (end).

        // Debugging: BreadCrumb implementation: Activity setTitle() approach (begin).

        // setTitle("DynamicEditFieldSectionActivity"); // Works!
        // getActivity().setTitle("My Title");

        // setProgressBarVisibility(true);

        // setTheme(android.R.style.Theme_Black_NoTitleBar); // (for Android Built In Theme)

        // Debugging: BreadCrumb implementation: Activity setTitle() approach (end).

        // Debugging: BreadCrumb implementation: FragmentBreadCrumbs approach (begin).

//        mFragmentManager = getSupportFragmentManager();
//
//        mFragmentBreadCrumbs = (FragmentBreadCrumbs)findViewById(R.id.breadcrumbs);
//        mFragmentBreadCrumbs.setActivity(this);
//        // mFragmentManager.addOnBackStackChangedListener(this);
//        updateBreadCrumbs();

        // Debugging: BreadCrumb implementation: FragmentBreadCrumbs approach (end).

        // Debugging: Building keyboard screen programmatically.
//      mIntent = getIntent();

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (begin).

        // Debugging: Building keyboard screen programmatically (begin).

//        mTextViewBreadcrumb = (TextView) findViewById(R.id.mTextViewBreadcrumb);
//        mTextViewBreadcrumb.setText("Editing " + mIntent.getStringExtra("editFieldLabel"));

        // Debugging: Building keyboard screen programmatically (end).

        // Debugging: Removing & Customizing/Creating Title Bar (using any theme) (end).

        // Debugging: Building keyboard screen programmatically (begin).

//        mTextViewEditField = (TextView) findViewById(R.id.mTextViewEditField);
//        mTextViewEditField.setText(mIntent.getStringExtra("editFieldLabel"));
//
//        mEditTextEditField = (EditText) findViewById(R.id.mEditTextEditField);
//        mEditTextEditField.setText(mIntent.getStringExtra("editFieldContent"));
//        mEditTextEditField.setEms(Integer.parseInt(mIntent.getStringExtra("editFieldLength")));

        // Debugging: Building keyboard screen programmatically (end).

        // public static final int TYPE_CLASS_TEXT = 0x00000001;
        // public static final int TYPE_CLASS_NUMBER = 0x00000002;
        // public static final int TYPE_TEXT_VARIATION_PASSWORD = 0x00000080;

        // Debugging: startActivity() problem (begin).

//        buttonCancel = (Button) findViewById(R.id.buttonCancel);
//        buttonCancel.setOnClickListener(new BtnOnClickListenerImpl());

        // Debugging: startActivity() problem (end).

        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

        if (mIntent.getStringExtra("editFieldInputType").equals("T_ALFANUM") ||
            mIntent.getStringExtra("editFieldInputType").equals("T_NUMERICO")) {

        // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

            // TODO: Refactor! Don't works!

            // Debugging: Navigation flow control (begin).

            if (mIntent.getStringExtra("editFieldInputType").equals("T_ALFANUM")) {

            // Debugging: Removing editTextEditEntityFieldValueColumn.
//            if (mIntent.getStringExtra("editFieldInputType").equals(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
//            // if (mIntent.getStringExtra("editFieldInputType").equals(Integer.toString(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD))) {
//
//                // Debugging: Building keyboard screen programmatically.
//                mCustomKeyboard = new CustomKeyboard(this, R.id.mKeyboardView, R.xml.alphanumeric_keyboard);
//                mEditTextEditField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//
//            }
            // Debugging: Removing editTextEditEntityFieldValueColumn.
//            else if (mIntent.getStringExtra("editFieldInputType").equals("T_ALFANUM")) {
            // else if (mIntent.getStringExtra("editFieldInputType").equals(Integer.toString(InputType.TYPE_CLASS_TEXT))) {

            // Debugging: Navigation flow control (end).

                //  Alphanumeric keyboard.

                // Debugging: Building keyboard screen programmatically.
                mCustomKeyboard = new CustomKeyboard(this, R.id.mKeyboardView, R.xml.alphanumeric_keyboard);

                // Debugging: Navigation flow control (begin).

                if (mIntent.getStringExtra("editFieldIsPasswd") != null) {

                    if (mIntent.getStringExtra("editFieldIsPasswd").equals("true")) {

                        mEditTextEditField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        // mEditTextEditField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }

                }
                else {

                    mEditTextEditField.setInputType(InputType.TYPE_CLASS_TEXT);

                }

                // mEditTextEditField.setInputType(InputType.TYPE_CLASS_TEXT);

                // Debugging: Navigation flow control (end).

            }
            else {

                // Numeric keyboard.

                // Debugging: Building keyboard screen programmatically.
                mCustomKeyboard = new CustomKeyboard(this, R.id.mKeyboardView, R.xml.numeric_keyboard);

                // Debugging: Navigation flow control (begin).

                if (mIntent.getStringExtra("editFieldIsPasswd") != null) {

                    if (mIntent.getStringExtra("editFieldIsPasswd").equals("true")) {

                        mEditTextEditField.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        // mEditTextEditField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }

                }
                else {

                    mEditTextEditField.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                }

                // mEditTextEditField.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                // Debugging: Navigation flow control (end).

            }
            // mCustomKeyboard = new CustomKeyboard(this, R.id.mKeyboardView, R.xml.hexkbd);

            // Debugging: Building keyboard screen programmatically.
            mCustomKeyboard.registerEditText(R.id.mEditTextEditField);

        }
        else {

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

            if (mIntent.getStringExtra("editFieldInputType").equals("T_BOOL")) {

                if (mIntent.getStringExtra("editFieldContent").equals("0") ||
                        mIntent.getStringExtra("editFieldContent").equals("")) {
//                if (mIntent.getStringExtra("editFieldContent").equals("0")) {
                    mCheckBoxEditField.setChecked(false);
                }
                else {
                    mCheckBoxEditField.setChecked(true);
                }

            }

            else if (mIntent.getStringExtra("editFieldInputType").equals("T_LISTA") ||
                    mIntent.getStringExtra("editFieldInputType").equals("T_TABELA")) {

                updateMSpinnerEditFieldData(mIntent.getStringExtra("editFieldName"),
                        mIntent.getStringExtra("editFieldInputType"),
                        mIntent.getStringExtra("editFieldContent"));

            }

            // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

        }

    }

    // Debugging: BreadCrumb implementation: FragmentBreadCrumbs approach (begin).

//    private void updateBreadCrumbs() {
//
//        mFragmentBreadCrumbs.setParentTitle("DynamicEditSectionActivityA", "Activity", new BtnOnClickListenerImpl());
//        mFragmentBreadCrumbs.setTitle("DynamicEditFieldSectionActivity", "Activity");
//
//    }

//    void updateBreadCrumbs() {
//        mFragmentBreadCrumbs.setParentTitle(null, null, null);
//        mFragmentBreadCrumbs.setTitle(mSelectedRoomName, mSelectedRoomName);
//    }

//    private class BtnOnClickListenerImpl implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//
//            finish();
//            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
//
//        }
//
//    }

    // Debugging: BreadCrumb implementation: FragmentBreadCrumbs approach (end).

    // Debugging: startActivity() problem (begin).

//    private class BtnOnClickListenerImpl implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.buttonCancel:
//                    finish();
//                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
//                    break;
//            }
//
//        }
//    }

    // Debugging: startActivity() problem (end).

    // Debugging: Useless feature (begin).

//    @Override
//    public void onBackPressed() {
//
//        this.finish();
//        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
//
//        // NOTE: Trap the back key: when the CustomKeyboard is still visible hide it, only when it is invisible, finish activity
//        // if (mCustomKeyboard.isCustomKeyboardVisible()) {
//        //     mCustomKeyboard.hideCustomKeyboard();
//        // }
//        // else {
//        //     this.finish();
//        //}
//
//    }

    // Debugging: Useless feature (end).

    // Debugging: Building keyboard screen programmatically (begin).

    public class CustomKeyboardView extends KeyboardView {

        Context context;

        public CustomKeyboardView(Context context, AttributeSet attributeSet) {

            super(context, attributeSet);
            this.context = context ;

        }

        @Override
        public void onDraw(Canvas canvas) {

            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(25);
            paint.setColor(Color.RED);

            List<Keyboard.Key> keys;
            keys = getKeyboard().getKeys();

            for (Keyboard.Key key : keys) {

                if (key.pressed) {

                    // Debugging: Cast error (java.lang.ClassCastException: android.graphics.drawable.GradientDrawable cannot be cast to android.graphics.drawable.NinePatchDrawable) (begin).

                    if (context.getResources().getDrawable(R.drawable.button_pressed).getClass() != null) {

                        if (context.getResources().getDrawable(R.drawable.button_pressed).getClass().getName().equals(GradientDrawable.class.getName())) {

                            GradientDrawable gradientDrawable = (GradientDrawable) context.getResources().getDrawable(R.drawable.button_pressed);
                            gradientDrawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                            gradientDrawable.draw(canvas);

                        }
                        else if (context.getResources().getDrawable(R.drawable.button_pressed).getClass().getName().equals(NinePatchDrawable.class.getName())) {

                            NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) context.getResources().getDrawable(R.drawable.button_pressed);
                            ninePatchDrawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                            ninePatchDrawable.draw(canvas);

                        }

                    }

//                    NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) context.getResources().getDrawable(R.drawable.button_pressed);
//                    // NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) context.getResources().getDrawable(R.drawable.glow);
//                    ninePatchDrawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
//                    ninePatchDrawable.draw(canvas);

                    // Debugging: Cast error (java.lang.ClassCastException: android.graphics.drawable.GradientDrawable cannot be cast to android.graphics.drawable.NinePatchDrawable) (end).

                    if (key.label != null) {
                        canvas.drawText(key.label.toString(), key.x + (key.width / 2), key.y + 25, paint);
                    }
                }
//                else if (key.modifier) {  // boolean that defines key is function key
//                    NinePatchDrawable npd = (NinePatchDrawable) context.getResources().getDrawable(R.drawable.btn_keyboard_special);
//                    npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
//                    npd.draw(canvas);
//                    if (key.label != null) {
//                        canvas.drawText(key.label.toString(), key.x + (key.width / 2), key.y + 25, paint);
//                    }
//                }

                break;
            }
        }

    }

    // Debugging: Building keyboard screen programmatically (end).

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (begin).

    private class BtnOnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.mButtonDone:

                    if (mIntent.getStringExtra("editFieldInputType").equals("T_BOOL")) {

                        if (mCheckBoxEditField.isChecked()) {
                            mIntent.putExtra("editedFieldContent", "1");
                        }
                        else {
                            mIntent.putExtra("editedFieldContent", "0");
                        }

                        setResult(99, mIntent);
                        finish();
                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

                    }

                    else if (mIntent.getStringExtra("editFieldInputType").equals("T_LISTA") ||
                            mIntent.getStringExtra("editFieldInputType").equals("T_TABELA")) {

                        mIntent.putExtra("editedFieldContent", mSpinnerEditFieldIndexList.get(mSpinnerEditFieldValueList.indexOf(mSpinnerEditFieldItemSelected)).toString());

                        setResult(99, mIntent);
                        finish();
                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

                    }

                    break;

                case R.id.mButtonCancel:

                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

                    break;

            }
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

    // TODO: Duplicated code updateMSpinnerEditFieldData() = DynamicEditSectionActivity::updateSpinnerEditEntityFieldValueColumn().
    private void updateMSpinnerEditFieldData(String pField, String pSpinnerDataType, String pInitialSelection) {

        mSpinnerEditFieldValueList = new ArrayList<String>();
        // Debugging (tipxxi): "nome"[entity] = "id"[entity].
        mSpinnerEditFieldIndexList = new ArrayList<String>();
        // mSpinnerEditFieldIndexList = new ArrayList<Integer>();

        // Debugging (tipxxi): "nome"[entity] = "id"[entity].
        String lEntityName = pField.substring(2, pField.length());

        if (pSpinnerDataType.equals("T_TABELA")) {

            // Retrieving T_TABELA.

            // Debugging (tipxxi): "nome"[entity] = "id"[entity].
            ArrayList<Object> arrayListObject = mDatabaseHelper.getAllEntityInstances(lEntityName);
            // ArrayList<Object> arrayListObject = mDatabaseHelper.getAllEntityInstances(pField.substring(2, pField.length()));
            Object[] listObject = arrayListObject.toArray(new Object[arrayListObject.size()]);

            // Debugging (tipxxi): "nome"[entity] = "id"[entity].
            Field[] listField = mDatabaseHelper.getEntityFields(lEntityName);
            // Field[] listField = DatabaseHelper.getEntityFields(lEntityName);
            // Field[] listField = DatabaseHelper.getEntityFields(pField.substring(2, pField.length()));

            try {

                // Iterating over the entity instances.
                for (int i = 0; i < listObject.length; i++) {

                    // Iterating over the entity instance fields.
                    for (int j = 0; j < listField.length; j++) {

                        listField[j].setAccessible(true);

                        // Debugging (tipxxi): "nome"[entity] = "id"[entity] (begin).

                        // Getting the content of field with name: "id" + Entity name.
                        if (listField[j].getName().equals("id" + lEntityName)) {
                        // if (listField[j].getName().equals("id" + pField.substring(2, pField.length()))) {

                            mSpinnerEditFieldIndexList.add(listField[j].get(listObject[i]).toString());
                            // mSpinnerEditFieldIndexList.add(Integer.valueOf(listField[j].get(listObject[i]).toString()));

                            if (mDatabaseHelper.getEntityField(lEntityName, "nome" + lEntityName) == null) {
                            // if (DatabaseHelper.getEntityField(lEntityName, "nome" + lEntityName) == null) {
                                mSpinnerEditFieldValueList.add(listField[j].get(listObject[i]).toString());
                                // break;
                            }

                        }

                        // Getting the content of field with name: "id" + Entity name.
//                        if (listField[j].getName().equals("id" + pField.substring(2, pField.length()))) {
//                            mSpinnerEditFieldIndexList.add(listField[j].get(listObject[i]).toString());
//                            // mSpinnerEditFieldIndexList.add(Integer.valueOf(listField[j].get(listObject[i]).toString()));
//                        }

                        // Debugging (tipxxi): "nome"[entity] = "id"[entity] (end).

                        // Getting the content of field with name: "nome" + Entity name.
                        if (listField[j].getName().equals("nome" + lEntityName)) {
                        // if (listField[j].getName().equals("nome" + pField.substring(2, pField.length()))) {
                            mSpinnerEditFieldValueList.add(listField[j].get(listObject[i]).toString());
                        }

                    }

                }

            }
            catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
            }
            catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        else if (pSpinnerDataType.equals("T_LISTA")) {

            mSpinnerEditFieldValueList = Utils.loadTLista(pField);

            // Debugging: Fixing T_LISTA feature (begin).

            // Creating a index list for mSpinnerEditFieldValueList.

            for (int i = 0; i < mSpinnerEditFieldValueList.size(); i++) {
                // Debugging (tipxxi): "nome"[entity] = "id"[entity].
                mSpinnerEditFieldIndexList.add(Integer.toString(i));
                // mSpinnerEditFieldIndexList.add(i);
            }

            // Debugging: Fixing T_LISTA feature (end).

        }

        // Selecting a item in 'normal' mode (but little).
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mSpinnerEditFieldValueList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);

        mSpinnerEditField.setAdapter(dataAdapter);

        // Selecting the correct initial entity list element in Spinner.
        if (pInitialSelection.equals("")) {
            // For new registers, the default value of Spinner is the first.
            mSpinnerEditField.setSelection(0);
        }
        else {
            // Debugging (tipxxi): "nome"[entity] = "id"[entity].
            mSpinnerEditField.setSelection(mSpinnerEditFieldIndexList.indexOf(pInitialSelection));
            // mSpinnerEditField.setSelection(mSpinnerEditFieldIndexList.indexOf(Integer.valueOf(pInitialSelection)));
        }
        // mSpinnerEditField.setSelection(mSpinnerEditFieldIndexList.indexOf(Integer.valueOf(pInitialSelection)));

    }

    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        // Obs.: Works only when the Spinner item selected changes.

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            mSpinnerEditFieldItemSelected = parent.getItemAtPosition(pos).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }

    }

    // Debugging: Editing fields of type CheckBox and Spinner in DynamicEditFieldSectionActivity (end).

    // Debugging: Managing App's Memory (begin).

//      https://developer.android.com/reference/android/app/ActivityManager.html#getMemoryClass()
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
