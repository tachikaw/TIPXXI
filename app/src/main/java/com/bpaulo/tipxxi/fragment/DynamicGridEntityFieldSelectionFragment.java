package com.bpaulo.tipxxi.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.database.DatabaseHelper;
import com.bpaulo.tipxxi.util.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DynamicGridEntityFieldSelectionFragment extends Fragment {

    private static final String TAG = "DynamicGridEntityFieldSelectionFragment";

    private String mEditFieldName;
    private String mEntityName;
    private String mEditFieldInputType;

    private String[][] mEntityFieldParameters;

    // UI definitions.

    private FrameLayout mFrameLayout;

    private RelativeLayout mRelativeLayout;

    private LinearLayout mLinearLayout, mLinearLayoutListRow;

    private ListView mListView;

    private TextView[] mTextViewEntityColumnHeader, mTextViewEntityColumn;

    private int[][] mTextViewEntityColumnHeaderWidth;

    private TextView[] mTextViewEntityColumnDivider;

    private ViewGroup.LayoutParams mViewGroupLayoutParams;
    private DatabaseHelper mDatabaseHelper;

    private Field[] mFieldList;

    private RelativeLayout mRelativeLayoutFooter;
    private RelativeLayout.LayoutParams mLayoutParamsRelativeLayoutFooter;

    private Button mButtonPageDown;
    private RelativeLayout.LayoutParams mLayoutParamsButtonPageDown;

    private Button mButtonPageUp;
    private RelativeLayout.LayoutParams mLayoutParamsButtonPageUp;

    private Button mButtonCancel;
    private RelativeLayout.LayoutParams mLayoutParamsButtonCancel;

    private ArrayList<Object> mArrayListObject;

    private Object[] mListObject;

    private EntityListAdapter mEntityListAdapter;
    private int mPageNumber = 1;
    private int mMaxPageNumber = 1;

    private TextView mTextViewPageInformation;

    private RelativeLayout.LayoutParams mLayoutParamsTextViewPageInformation;
    private RelativeLayout mRelativeLayoutListHeader;

    private RelativeLayout.LayoutParams mLayoutParamsRelativeLayoutListHeader;

    private BtnOnClickListenerImpl btnOnClickListenerImpl;
    private BtnOnTouchListenerImpl btnOnTouchListenerImpl;

    private Intent mIntent;

    public DynamicGridEntityFieldSelectionFragment() {

        super();

    }

    /*
        Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead less... (Ctrl+F1)

        From the Fragment documentation:

        Every fragment must have an empty constructor, so it can be instantiated when restoring its
        activity's state. It is strongly recommended that subclasses do not have other constructors with
        parameters, since these constructors will not be called when the fragment is re-instantiated;
        instead, arguments can be supplied by the caller with setArguments(Bundle) and later retrieved
        by the Fragment with getArguments().

    */
    public static final DynamicGridEntityFieldSelectionFragment createDynamicGridEntityFieldSelectionFragment(String pEditFieldName, String pEditFieldEntityName, String pEditFieldInputType) {

        DynamicGridEntityFieldSelectionFragment dynamicGridEntityFieldSelectionFragment = new DynamicGridEntityFieldSelectionFragment();

        dynamicGridEntityFieldSelectionFragment.mEditFieldName = pEditFieldName;
        dynamicGridEntityFieldSelectionFragment.mEntityName = pEditFieldEntityName;
        dynamicGridEntityFieldSelectionFragment.mEditFieldInputType = pEditFieldInputType;

        return dynamicGridEntityFieldSelectionFragment;

    }
    /*
    public DynamicGridEntityFieldSelectionFragment(String pEntity) {

        super();
        this.mEntityName = pEntity;

    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mDatabaseHelper = DatabaseHelper.getInstance(getActivity());

        if (mEditFieldInputType.equals("T_TABELA")) {

            mFieldList = mDatabaseHelper.getEntityFields(mEntityName);

            mTextViewEntityColumnHeader = new TextView[mFieldList.length];
            mTextViewEntityColumnHeaderWidth = new int[mFieldList.length][2];

            mTextViewEntityColumnDivider = new TextView[mFieldList.length];

            mEntityFieldParameters = new String[mFieldList.length][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

        }
        else {

            mTextViewEntityColumnHeader = new TextView[1];
            mTextViewEntityColumnHeaderWidth = new int[1][2];

            mTextViewEntityColumnDivider = new TextView[1];

            mEntityFieldParameters = new String[1][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

        }

        btnOnClickListenerImpl = new BtnOnClickListenerImpl();
        btnOnTouchListenerImpl = new BtnOnTouchListenerImpl();

        // mFrameLayout definition & settings.

        mFrameLayout = new FrameLayout(getActivity());
        mFrameLayout.setPadding(0, 30, 0, 0); // Works!

        mViewGroupLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mFrameLayout.setLayoutParams(mViewGroupLayoutParams);

        // mRelativeLayout definition & settings.

        mRelativeLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // mLinearLayout definition & settings.

        mLinearLayout = new LinearLayout(getActivity());
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mViewGroupLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mViewGroupLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLinearLayout.setLayoutParams(mViewGroupLayoutParams);

        // mListView definition & settings.

        mListView = new ListView(getActivity());

        mViewGroupLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mViewGroupLayoutParams.height = 480; // No more needed?

        mListView.setLayoutParams(mViewGroupLayoutParams);
        mListView.setDividerHeight(3);

        mListView.setVerticalScrollBarEnabled(true); // Works fine!
        mListView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mListView.setHeaderDividersEnabled(true);
        mListView.setFooterDividersEnabled(true);

        int[] colors = {0, 0xFFFFFFFF, 0};
        mListView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        mListView.setDividerHeight(1);

        // mLinearLayoutListHeader definition & settings.

        mRelativeLayoutListHeader = new RelativeLayout(getActivity());
        mRelativeLayoutListHeader.setLayoutParams(new android.widget.AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        // mTextViewPageInformation definition & settings.

        mTextViewPageInformation = new TextView(getActivity());
        mTextViewPageInformation.setId(R.id.mTextViewPageInformation);
        mTextViewPageInformation.setText("(" + Integer.toString(mPageNumber) + "/" + mMaxPageNumber + ")");
        mTextViewPageInformation.setGravity(Gravity.CENTER_VERTICAL);
        mTextViewPageInformation.setTextColor(getResources().getColor(R.color.black));
        mTextViewPageInformation.setTextSize(24);
        mTextViewPageInformation.setTypeface(Typeface.DEFAULT_BOLD);

        mLayoutParamsTextViewPageInformation = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsTextViewPageInformation.rightMargin = 10; // Works fine in this context! (Edited).
        mLayoutParamsTextViewPageInformation.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        mRelativeLayoutListHeader.addView(mTextViewPageInformation, mLayoutParamsTextViewPageInformation);

        // Filling LinearLayout mLinearLayoutListHeader with a set empty TextView mTextViewEntityColumnHeader
        // to further add views to LinearLayout with index (avoid out of bounds error).
        if (mEditFieldInputType.equals("T_TABELA")) {

            for (int i = 0, j = 0; i < mFieldList.length; i++) {

                // Loading the parameters for each field.
                mEntityFieldParameters[i] = (String[]) DatabaseHelper.invokeMethod(
                        Utils.ENTITY_PACKAGE_NAME + mEntityName,
                        "getAttributeParameters",
                        mFieldList[i].getName(),
                        String.class
                );

                // TODO: If I change this parameter in field, this feature stops!
                if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {

                    mTextViewEntityColumnHeader[j] = new TextView(getActivity());

                    mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[j], j);

                    mTextViewEntityColumnHeaderWidth[j][0] = Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]);
                    mTextViewEntityColumnHeaderWidth[j][1] = Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]);

                    j++;

                }

            }

        }
        else {

            mEntityFieldParameters[0] = (String[]) DatabaseHelper.invokeMethod(
                    Utils.ENTITY_PACKAGE_NAME + mEntityName,
                    "getAttributeParameters",
                    mEditFieldName,
                    String.class
            );

            mTextViewEntityColumnHeader[0] = new TextView(getActivity());

            mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[0], 0);

            mTextViewEntityColumnHeaderWidth[0][0] = Integer.valueOf(mEntityFieldParameters[0][Utils.COL_INDEX]);
            mTextViewEntityColumnHeaderWidth[0][1] = Integer.valueOf(mEntityFieldParameters[0][Utils.GRID_LARGURA]);

        }

        // Populating the grid's header with entity's fields.
        if (mEditFieldInputType.equals("T_TABELA")) {

            for (int i = 0, j = 0, mRelativeLayoutListHeaderIndex; i < mFieldList.length; i++) {

                // Getting the parameters for each field.
                mEntityFieldParameters[i] = (String[]) DatabaseHelper.invokeMethod(
                        Utils.ENTITY_PACKAGE_NAME + mEntityName,
                        "getAttributeParameters",
                        mFieldList[i].getName(),
                        String.class
                );

                // TODO: If I change this parameter in field, this feature stops!
                if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {

                    mTextViewEntityColumnHeader[j] = new TextView(getActivity());
                    mTextViewEntityColumnHeader[j].setWidth(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_LARGURA]));
                    mTextViewEntityColumnHeader[j].setPadding(10, 10, 10, 10);
                    mTextViewEntityColumnHeader[j].setGravity(Gravity.CENTER_VERTICAL);
                    mTextViewEntityColumnHeader[j].setText(mEntityFieldParameters[i][Utils.CAPTION]);
                    mTextViewEntityColumnHeader[j].setTextColor(getResources().getColor(R.color.black));
                    mTextViewEntityColumnHeader[j].setTextSize(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_TAMANHO_LETRA]));
                    mTextViewEntityColumnHeader[j].setTypeface(Typeface.DEFAULT_BOLD);

                    mLayoutParamsRelativeLayoutListHeader = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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

                }

            }

        }
        else {

            // Getting the parameters for each field.
            mEntityFieldParameters[0] = (String[]) DatabaseHelper.invokeMethod(
                    Utils.ENTITY_PACKAGE_NAME + mEntityName,
                    "getAttributeParameters",
                    mEditFieldName,
                    String.class
            );

            mTextViewEntityColumnHeader[0] = new TextView(getActivity());
            mTextViewEntityColumnHeader[0].setWidth(Integer.valueOf(mEntityFieldParameters[0][Utils.GRID_LARGURA]) * 2);
            mTextViewEntityColumnHeader[0].setPadding(10, 10, 10, 10);
            mTextViewEntityColumnHeader[0].setGravity(Gravity.CENTER_VERTICAL);
            mTextViewEntityColumnHeader[0].setText(mEntityFieldParameters[0][Utils.CAPTION]);
            mTextViewEntityColumnHeader[0].setTextColor(getResources().getColor(R.color.black));
            mTextViewEntityColumnHeader[0].setTextSize(Integer.valueOf(mEntityFieldParameters[0][Utils.GRID_TAMANHO_LETRA]));
            mTextViewEntityColumnHeader[0].setTypeface(Typeface.DEFAULT_BOLD);

            mLayoutParamsRelativeLayoutListHeader = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // Setting the position of header element using a shift based on the width of the previous header element inserted.
            // RelativeLayout.RIGHT_OF option don't works! RelativeLayout.BELOW works fine (but RelativeLayout.RIGHT_OF,.ABOVE,.LEFT_OF,.ALIGN_RIGHT not).
            mLayoutParamsRelativeLayoutListHeader.addRule(RelativeLayout.BELOW, R.id.mTextViewPageInformation);

            // Adding filled TextView mTextViewEntityColumnHeader.
            mRelativeLayoutListHeader.addView(mTextViewEntityColumnHeader[0],
                    0,
                    mLayoutParamsRelativeLayoutListHeader);

        }

        mListView.addHeaderView(mRelativeLayoutListHeader, null, false);

        // Setting the item click listener.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mIntent = new Intent();

                if (mEditFieldInputType.equals("T_TABELA")) {

                    TextView textViewSelectedRow = (TextView) view.findViewById(R.id.mTextViewEntityColumn);

                    EntityValueHolder entityValueHolder = (EntityValueHolder) textViewSelectedRow.getTag();

                    mIntent.putExtra("editedFieldContent", "");

                    for (int i = 0; i < entityValueHolder.name.length; i++) {

                        if (entityValueHolder.name[i] != null &&
                                entityValueHolder.value[i] != null) {

                            if (entityValueHolder.name[i].equals("id" + mEntityName)) {

                                mIntent.putExtra("editedFieldContent", entityValueHolder.value[i]);

                            }

                        }

                    }

                }
                else {

                    mIntent.putExtra("editedFieldContent", String.valueOf(id + (mPageNumber - 1) * EntityListAdapter.MAX_ROW_DISPLAY));

                }

                getActivity().setResult(99, mIntent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

            }

        });

        // mButtonPageDown definition & settings.

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

        mButtonPageDown.setOnClickListener(btnOnClickListenerImpl);
        mButtonPageDown.setOnTouchListener(btnOnTouchListenerImpl);

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

        mButtonPageUp.setOnClickListener(btnOnClickListenerImpl);
        mButtonPageUp.setOnTouchListener(btnOnTouchListenerImpl);

        // mButtonCancel definition & settings.

        mButtonCancel = new Button(getActivity());
        mButtonCancel.setId(R.id.mButtonCancel);
        mButtonCancel.setBackground(getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
        mButtonCancel.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        mButtonCancel.setHeight(60);
        mButtonCancel.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium_Inverse);
        mButtonCancel.setTextColor(getResources().getColor(R.color.white));
        mButtonCancel.setTypeface(Typeface.DEFAULT_BOLD);
        mButtonCancel.setText(R.string.cancel);

        mLayoutParamsButtonCancel = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsButtonCancel.addRule(RelativeLayout.RIGHT_OF, R.id.mButtonPageUp);
        mLayoutParamsButtonCancel.width = 155;
        mLayoutParamsButtonCancel.setMargins(5, 5, 0, 5);

        mButtonCancel.setOnClickListener(btnOnClickListenerImpl);
        mButtonCancel.setOnTouchListener(btnOnTouchListenerImpl);

        if (mEditFieldInputType.equals("T_TABELA")) {
            mArrayListObject = mDatabaseHelper.getAllEntityInstances(mEntityName);
        }
        else {
            mArrayListObject = Utils.loadTListaObject(mEditFieldName);
        }

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

        // Adding views.

        mLinearLayout.addView(mListView);
        mRelativeLayout.addView(mLinearLayout);

        // mRelativeLayoutFooter definition & settings.

        mRelativeLayoutFooter = new RelativeLayout(getActivity());
        mRelativeLayoutFooter.setBackgroundColor(getResources().getColor(R.color.gray));

        mLayoutParamsRelativeLayoutFooter = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        mLayoutParamsRelativeLayoutFooter.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mLayoutParamsRelativeLayoutFooter.height = 60;

        mRelativeLayoutFooter.addView(mButtonPageDown, mLayoutParamsButtonPageDown);
        mRelativeLayoutFooter.addView(mButtonPageUp, mLayoutParamsButtonPageUp);
        mRelativeLayoutFooter.addView(mButtonCancel, mLayoutParamsButtonCancel);

        mRelativeLayout.addView(mRelativeLayoutFooter, mLayoutParamsRelativeLayoutFooter);

        mFrameLayout.addView(mRelativeLayout, relativeLayoutParams);

        return mFrameLayout;

    }

    private class BtnOnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.mButtonPageDown:

                    refreshEntityListPageDown();

                    break;

                case R.id.mButtonPageUp:

                    refreshEntityListPageUp();

                    break;

                case R.id.mButtonCancel:

                    getActivity().finish();
                    getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

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

    private void refreshEntityListPageDown() {

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

        mListView.setAdapter(mEntityListAdapter);

        // Updating the page number.
        mPageNumber++;

        // Setting the page number.
        updatePageInformation();

        // Disabling/enabling "Page Down" and "Page Up" buttons.

        if (mPageNumber == mMaxPageNumber) {
            mButtonPageDown.setEnabled(false);
            mButtonPageDown.setBackground(getResources().getDrawable(R.drawable.button_disabled_shape));
        }

        mButtonPageUp.setEnabled(true);
        mButtonPageUp.setBackground(getResources().getDrawable(R.drawable.button_shape));

    }

    private void refreshEntityListPageUp() {

        ArrayList<Object> newArrayListObject = new ArrayList<Object>();

        // Filling the grid.
        for (int i = (mPageNumber - 2) * EntityListAdapter.MAX_ROW_DISPLAY; i < (mPageNumber - 1) * EntityListAdapter.MAX_ROW_DISPLAY; i++) {
            newArrayListObject.add(mArrayListObject.get(i));
        }

        mListObject = newArrayListObject.toArray(new Object[newArrayListObject.size()]);

        mEntityListAdapter = new EntityListAdapter(getActivity()
                .getApplicationContext(), R.layout.fragment_list_row,
                mListObject);

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

    public class EntityListAdapter extends ArrayAdapter<Object> {

        Context context;
        int layoutResourceId;
        Object data[] = null;

        private static final int MAX_ROW_DISPLAY = 8; // Min. value = 1; Max. value = 6 (for 800 x 400 screen layout).
        // private static final int MAX_ROW_DISPLAY = 6; // Min. value = 1; Max. value = 6 (for 800 x 400 screen layout).

        public EntityListAdapter(Context context, int layoutResourceId,
                                 Object[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        @Override
        public void notifyDataSetChanged() {
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Field[] lFieldList = null;
            EntityValueHolder entityValueHolder = null;

            // mLinearLayoutListRow definition & settings.

            mLinearLayoutListRow = new LinearLayout(getActivity());
            mLinearLayoutListRow.setOrientation(LinearLayout.HORIZONTAL);
            mLinearLayoutListRow.setLayoutParams(new android.widget.AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
            mLinearLayoutListRow.setPadding(10, 10, 10, 10);

            // mTextViewEntityColumn definition & settings.

            if (mEditFieldInputType.equals("T_TABELA")) {

                lFieldList = mDatabaseHelper.getEntityFields(mEntityName);

                mTextViewEntityColumn = new TextView[lFieldList.length];

                // Filling LinearLayout mLinearLayoutListHeader with a set empty TextView mTextViewEntityColumnHeader
                // to further add views to LinearLayout with index (avoid out of bounds error).
                for (int i = 0; i < lFieldList.length; i++) {

                    mTextViewEntityColumn[i] = new TextView(getActivity());
                    mTextViewEntityColumnDivider[i] = new TextView(getActivity());
                    mLinearLayoutListRow.addView(mTextViewEntityColumn[i], i);
                    mLinearLayoutListRow.addView(mTextViewEntityColumnDivider[i], i + 1);

                }

                for (int i = 0; i < lFieldList.length; i++) {

                    mTextViewEntityColumn[i] = new TextView(getActivity());
                    mTextViewEntityColumn[i].setId(R.id.mTextViewEntityColumn);
                    mTextViewEntityColumn[i].setGravity(Gravity.CENTER_VERTICAL);
                    mTextViewEntityColumn[i].setTextColor(0xff000000);
                    mTextViewEntityColumn[i].setTextSize(Integer.valueOf(mEntityFieldParameters[i][Utils.GRID_TAMANHO_LETRA]));

                    // TODO: If I change this parameter in field, this feature stops!
                    if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {
                        mLinearLayoutListRow.addView(mTextViewEntityColumn[i], Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2);
                        mTextViewEntityColumnDivider[i] = new TextView(getActivity());
                        mTextViewEntityColumnDivider[i].setWidth(5);
                        mLinearLayoutListRow.addView(mTextViewEntityColumnDivider[i],
                                (Integer.valueOf(mEntityFieldParameters[i][Utils.COL_INDEX]) * 2) + 1);
                    }

                }

                convertView = mLinearLayoutListRow;

                entityValueHolder = new EntityValueHolder();

                entityValueHolder.name = new String[lFieldList.length];
                entityValueHolder.value = new String[lFieldList.length];

                for (int i = 0; i < lFieldList.length; i++) {

                    lFieldList[i].setAccessible(true);

                    try {

                        // Returns the value of the field in the specified object.
                        if (lFieldList[i].get(data[position]) != null) {

                            // TODO: If I change this parameter in field, this feature stops!
                            if (mEntityFieldParameters[i][Utils.IS_IMPRIMIR].equals("true")) {

                                entityValueHolder.name[i] = lFieldList[i].getName();

                                if (mEntityFieldParameters[i][Utils.IS_PASSWD].equals("true")) {

                                    String passwordField = new String("");

                                    for (int j = 0; j < lFieldList[i].get(data[position]).toString().length(); j++) {
                                        passwordField = passwordField + "*";
                                    }

                                    mTextViewEntityColumn[i].setText(passwordField);
                                    entityValueHolder.value[i] = lFieldList[i].get(data[position]).toString();

                                }
                                else {

                                    mTextViewEntityColumn[i].setText(lFieldList[i].get(data[position]).toString());
                                    entityValueHolder.value[i] = lFieldList[i].get(data[position]).toString();

                                }

                                mTextViewEntityColumn[i].setTag(entityValueHolder);

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

                            }

                        }

                    }
                    catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
            else {

                mTextViewEntityColumn = new TextView[1];

                mTextViewEntityColumn[0] = new TextView(getActivity());
                mTextViewEntityColumn[0].setId(R.id.mTextViewEntityColumn);
                mTextViewEntityColumn[0].setGravity(Gravity.CENTER_VERTICAL);
                mTextViewEntityColumn[0].setTextColor(0xff000000);
                mTextViewEntityColumn[0].setTextSize(Integer.valueOf(mEntityFieldParameters[0][Utils.GRID_TAMANHO_LETRA]));

                mLinearLayoutListRow.addView(mTextViewEntityColumn[0]);

                convertView = mLinearLayoutListRow;

                entityValueHolder = new EntityValueHolder();

                entityValueHolder.name = new String[1];
                entityValueHolder.value = new String[1];

                mTextViewEntityColumn[0].setText(data[position].toString());

                entityValueHolder.name[0] = data[position].toString();
                entityValueHolder.value[0] = String.valueOf(position);

                // Setting width.
                mTextViewEntityColumn[0].setWidth(Integer.valueOf(mEntityFieldParameters[0][Utils.GRID_LARGURA]) * 2);

                // Setting text alignment.
                switch (Integer.valueOf(mEntityFieldParameters[0][Utils.GRID_ALINHAMENTO])) {

                    case 0: // Left.
                        mTextViewEntityColumn[0].setGravity(Gravity.LEFT);
                        break;

                    case 1: // Center.
                        mTextViewEntityColumn[0].setGravity(Gravity.CENTER);
                        break;

                    case 2: // Right.
                        mTextViewEntityColumn[0].setGravity(Gravity.RIGHT);
                        break;

                }

            }

            return convertView;

        }

        @Override
        public int getCount() {

            if (data == null) {
                return 0;
            }
            return Math.min(MAX_ROW_DISPLAY, data.length);

        }

    }

    static class EntityValueHolder {
        String[] name;
        String[] value;
    }

    // Managing app's memory.
    @Override
    public void onStop() {

        super.onStop();
        Log.i(TAG, "onStop();"); // Works fine!

    }

    // Managing app's memory.
    @Override
    public void onLowMemory() {

        super.onLowMemory();
        Log.i(TAG, "onLowMemory();"); // Don't called!

    }

}