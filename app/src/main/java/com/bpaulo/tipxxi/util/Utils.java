package com.bpaulo.tipxxi.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bpaulo.tipxxi.R;
import com.bpaulo.tipxxi.menuprincipal.MenuPrincipalActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    private static final String TAG = "Utils";

    // displayAlertDialog() calls levels.
    public static final int CONFIRMATION_LEVEL = 0;
    public static final int WARNING_LEVEL = 1;
    public static final int ERROR_LEVEL = 2;

    public static final int CAMPO = 0;
    public static final int CAPTION = 1;
    public static final int TIPO = 2;
    public static final int IS_RODAPE = 3;
    public static final int GRID_LARGURA = 4;
    public static final int GRID_ALINHAMENTO = 5;
    public static final int GRID_TIPO_LETRA = 6;
    public static final int GRID_TAMANHO_LETRA = 7;
    public static final int IS_IMPRIMIR = 8;
    public static final int IS_EDITAVEL = 9;
    public static final int IS_PASSWD = 10;
    public static final int CS_VALORES_AUTORIZADOS = 11;
    public static final int IS_NOT_NULL = 12;
    public static final int CS_VALORES_PROIBIDOS = 13;
    public static final int MAX_LENGTH = 14;
    public static final int COL_INDEX = 15;

    public static final String ENTITY_PACKAGE_NAME = "com.bpaulo.tipxxi.entity.";

    // Debugging (tipxxi): Parameterizing screen size (begin).

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    // Debugging (tipxxi): Parameterizing screen size (end).

    public static final String[] CARTAOTIPOOPERACAO = {"Livre",
            "2 Pesagens + Bruto",
            "Pesagem Completa (1+2)",
            "Entrada/Saída com Tara",
            "Entrada com Tara",
            "Saída com Tara",
            // Debugging (tipxxi).
            "Pesagem Única",
            "Cartao Tipo Operação 1",
            "Cartao Tipo Operação 2",
            "Cartao Tipo Operação 3",
            "Cartao Tipo Operação 4"};
            // "Pesagem Única"};

    public static final String[] MODOEMMOEDEIRO = {"Gratuito",
            "Saldo/Plafond"};

    public static Object getEntityFieldFromClass(String pEntityFieldName, String pEntityFieldId) {

        String fieldValue = "";
        Class<?> clazz;
        Object object;
        String[] lList;
        Field field;

        try {

            clazz = Class.forName("com.bpaulo.tipxxi.util.Utils");
            object = clazz.newInstance();
            field = clazz.getDeclaredField(pEntityFieldName.toUpperCase(Locale.getDefault()));
            field.setAccessible(true);
            lList = (String[]) field.get(object);

            fieldValue = lList[Integer.valueOf(pEntityFieldId)];

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return fieldValue;

    }

    // Debugging (tipxxi): T_TABELA & T_LISTA new selection screen (begin).
    public static ArrayList<Object> loadTListaObject(String pFieldName) {
    // public static ArrayList<String> loadTLista(String pFieldName) {

        Class<?> clazz;
        Object object = null;
        Field lField;
        Object[] lLista;
        // String[] lLista;
        ArrayList<Object> lTLista = new ArrayList<Object>();
        // ArrayList<String> lTLista = new ArrayList<String>();

        try {
            clazz = Class.forName("com.bpaulo.tipxxi.util.Utils");
            object = clazz.newInstance();
            lField = clazz.getDeclaredField(pFieldName.toUpperCase(Locale.getDefault()));
            lField.setAccessible(true);
            lLista = (Object[]) lField.get(object);
            // lLista = (String[]) lField.get(object);
            if (lLista.length > 0) {
                for (int i = 0; i < lLista.length; i++) {
                    lTLista.add(lLista[i]);
                }
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
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return lTLista;

        // Reading T_LISTA from file approach.

//		ArrayList<String> lTLista = new ArrayList<String>();
//		BufferedReader bufferedReader;
//		String readString;
//		StringBuffer stringBuffer = new StringBuffer("");
//		String[] listData;
//
//		try {
//			bufferedReader = readFile("T_LISTA");
//			readString = bufferedReader.readLine();
//			while (readString != null) {
//				stringBuffer.append(readString);
//				listData = readString.split(",");
//				if (listData[0].equals(pCampo)){
//					for (int i = 1; i < listData.length; i++) {
//						lTLista.add(listData[i]);
//					}
//					break;
//				}
//				readString = bufferedReader.readLine();
//			}
//		}
//		catch (IOException e) {
//			Log.i(TAG, "loadTLista(String pCampo): IOException.");
//			e.printStackTrace();
//		}
//
//		return lTLista;

    }

    // Debugging (tipxxi): T_TABELA & T_LISTA new selection screen (end).

    public static ArrayList<String> loadTLista(String pFieldName) {

        Class<?> clazz;
        Object object = null;
        Field lField;
        String[] lLista;
        ArrayList<String> lTLista = new ArrayList<String>();

        try {
            clazz = Class.forName("com.bpaulo.tipxxi.util.Utils");
            object = clazz.newInstance();
            lField = clazz.getDeclaredField(pFieldName.toUpperCase(Locale.getDefault()));
            lField.setAccessible(true);
            lLista = (String[]) lField.get(object);
            if (lLista.length > 0) {
                for (int i = 0; i < lLista.length; i++) {
                    lTLista.add(lLista[i]);
                }
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
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return lTLista;

        // Reading T_LISTA from file approach.

//		ArrayList<String> lTLista = new ArrayList<String>();
//		BufferedReader bufferedReader;
//		String readString;
//		StringBuffer stringBuffer = new StringBuffer("");
//		String[] listData;
//
//		try {
//			bufferedReader = readFile("T_LISTA");
//			readString = bufferedReader.readLine();
//			while (readString != null) {
//				stringBuffer.append(readString);
//				listData = readString.split(",");
//				if (listData[0].equals(pCampo)){
//					for (int i = 1; i < listData.length; i++) {
//						lTLista.add(listData[i]);
//					}
//					break;
//				}
//				readString = bufferedReader.readLine();
//			}
//		}
//		catch (IOException e) {
//			Log.i(TAG, "loadTLista(String pCampo): IOException.");
//			e.printStackTrace();
//		}
//
//		return lTLista;

    }

    public static void displayToast(String pMessage, int pDuration) {

        Toast toast = Toast.makeText(MenuPrincipalActivity.getAppApplicationContext(),
                (CharSequence) pMessage,
                pDuration);

        LinearLayout linearLayoutToast = (LinearLayout) toast.getView();
        TextView textViewToast = (TextView) linearLayoutToast.getChildAt(0);
        textViewToast.setTextSize(24);

        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);

        toast.show();

    }

    public static void displayAlertDialog(Context pContext, int pLevel, String pTitle, String pMessage) {

        RelativeLayout relativeLayout;

        TextView textViewTitle;
        RelativeLayout.LayoutParams layoutParamsTextViewTitle;
        RelativeLayout relativeLayoutTitle;
        RelativeLayout.LayoutParams relativeLayoutTitleParams;

        TextView textViewMessage;
        RelativeLayout.LayoutParams layoutParamsTextViewMessage;
        RelativeLayout relativeLayoutMessage;
        RelativeLayout.LayoutParams relativeLayoutMessageParams;

        Button buttonOK;
        RelativeLayout.LayoutParams layoutParamsButtonOK;
        RelativeLayout relativeLayoutButtonOK;
        RelativeLayout.LayoutParams relativeLayoutButtonOKParams;

        // The approaches below don't works.
//		FrameLayout frameLayout;
//		LinearLayout linearLayout;

        // Defining the border of RelativeLayouts.

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(1, pContext.getResources().getColor(R.color.white));
        // gradientDrawable.setCornerRadius(1);

//		ShapeDrawable shapeDrawable = new ShapeDrawable();
//		Paint paint = shapeDrawable.getPaint();
//		paint.setColor(Color.GRAY); // Defines the color of the border. Breaks "relativeLayoutTitle.setBackgroundColor(pContext.getResources().getColor(R.color.red));".
//		// paint.setStyle(Paint.Style.FILL); // Set to define the color of the element and the border.
//		// paint.setStyle(Paint.Style.STROKE); // Set to define only the color of the border.
//		paint.setStyle(Paint.Style.FILL_AND_STROKE); // ? Same effect of Paint.Style.FILL.
//		paint.setStrokeWidth(1);
//		shapeDrawable.setBounds(20, 20, 20, 20);

        // relativeLayout definition & settings.

        relativeLayout = new RelativeLayout(pContext);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // The approaches below don't works.

        // frameLayout definition & settings.

//		frameLayout = new FrameLayout(pContext);
//		frameLayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));

        // linearLayout definition & settings.

//		linearLayout = new LinearLayout(pContext);
//		linearLayout.setOrientation(LinearLayout.);
//		linearLayout.setPadding(0, 30, 0, 0);
//		linearLayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));

        // textViewTitle definition & settings.

        textViewTitle = new TextView(pContext);
        // textViewTitle.setId(R.id.AlertDialogTitle);
        textViewTitle.setText(pTitle);
        textViewTitle.setTextAppearance(pContext, android.R.style.TextAppearance_Large);
        if (pLevel == CONFIRMATION_LEVEL) {
            textViewTitle.setTextColor(pContext.getResources().getColor(R.color.white));
        }
        else if (pLevel == WARNING_LEVEL) {
            textViewTitle.setTextColor(pContext.getResources().getColor(R.color.blue));
        }
        else if (pLevel == ERROR_LEVEL) {
            textViewTitle.setTextColor(pContext.getResources().getColor(R.color.black));
        }
        textViewTitle.setTextSize(36);
        textViewTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL); // Sets the position of textViewTitle inside of RelativeLayout.
        textViewTitle.setPadding(10, 10, 10, 10);

        layoutParamsTextViewTitle = new RelativeLayout.LayoutParams(400, 100); // Works fine! (with "relativeLayoutTitleParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);")
        // layoutParamsTextViewTitle = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//		layoutParamsTextViewTitle.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); // Causes layout error.
//		layoutParamsTextViewTitle.topMargin = 30;
//		layoutParamsTextViewTitle.leftMargin = 30;
//		layoutParamsTextViewTitle.width = 200; // Define the width of the text (compress the message).

        relativeLayoutTitle = new RelativeLayout(pContext);
        relativeLayoutTitle.setId(R.id.AlertDialogTitle);
        // relativeLayoutTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL); // Don't works!
        // relativeLayoutTitle.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL); // Don't works!
        if (pLevel == CONFIRMATION_LEVEL) {
            gradientDrawable.setColor(pContext.getResources().getColor(R.color.gray));
            // relativeLayoutTitle.setBackgroundColor(pContext.getResources().getColor(R.color.gray));
        }
        else if (pLevel == WARNING_LEVEL) {
            gradientDrawable.setColor(pContext.getResources().getColor(R.color.yellow));
            // relativeLayoutTitle.setBackgroundColor(pContext.getResources().getColor(R.color.yellow));
        }
        else if (pLevel == ERROR_LEVEL) {
            gradientDrawable.setColor(pContext.getResources().getColor(R.color.red));
            // relativeLayoutTitle.setBackgroundColor(pContext.getResources().getColor(R.color.red));
        }
        // RelativeLayout borders definition.
        relativeLayoutTitle.setBackground(gradientDrawable);
        // relativeLayoutTitle.setBackgroundDrawable(shapeDrawable);
        // relativeLayoutTitle.setBackground(pContext.getResources().getDrawable(R.drawable.alertdialogtitle_shape));

        relativeLayoutTitleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // relativeLayoutTitleParams = new RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        // relativeLayoutTitleParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); // Works fine! Position inside main RelativeLayout.
        // relativeLayoutTitleParams.addRule(RelativeLayout.CENTER_HORIZONTAL); // Works fine! Position inside main RelativeLayout.
        // relativeLayoutTitleParams.addRule(RelativeLayout.CENTER_VERTICAL); // Works fine! Position inside main RelativeLayout.
        relativeLayoutTitleParams.topMargin = 60;
        relativeLayoutTitleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        // relativeLayoutTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        relativeLayoutTitle.addView(textViewTitle, layoutParamsTextViewTitle);

        // textViewMessage definition & settings.

        textViewMessage = new TextView(pContext);
        // textViewMessage.setId(R.id.AlertDialogMessage);
        textViewMessage.setText(pMessage);
        textViewMessage.setTextAppearance(pContext, android.R.style.TextAppearance_Large);
        if (pLevel == CONFIRMATION_LEVEL) {
            textViewMessage.setTextColor(pContext.getResources().getColor(R.color.white));
        }
        else if (pLevel == WARNING_LEVEL) {
            textViewMessage.setTextColor(pContext.getResources().getColor(R.color.blue));
        }
        else if (pLevel == ERROR_LEVEL) {
            textViewMessage.setTextColor(pContext.getResources().getColor(R.color.black));
        }
        textViewMessage.setTextSize(24);
        textViewMessage.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textViewMessage.setPadding(10, 10, 10, 10);

        layoutParamsTextViewMessage = new RelativeLayout.LayoutParams(400, 200);
        // layoutParamsTextViewMessage = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//		layoutParamsTextViewMessage.bottomMargin = 75;
//		layoutParamsTextViewMessage.leftMargin = 30;
//		layoutParamsTextViewMessage.addRule(RelativeLayout.BELOW, R.id.AlertDialogTitle);

        relativeLayoutMessage = new RelativeLayout(pContext);
        relativeLayoutMessage.setId(R.id.AlertDialogMessage);
        // RelativeLayout borders definition.
        relativeLayoutMessage.setBackground(gradientDrawable);
        // relativeLayoutMessage.setBackgroundDrawable(shapeDrawable);

        relativeLayoutMessageParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // relativeLayoutMessageParams = new RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        relativeLayoutMessageParams.topMargin = 10; // Spacing between this and above element.
        relativeLayoutMessageParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        relativeLayoutMessageParams.addRule(RelativeLayout.BELOW, R.id.AlertDialogTitle);

        relativeLayoutMessage.addView(textViewMessage, layoutParamsTextViewMessage);

        // buttonOK definition & settings.

        buttonOK = new Button(pContext);
        // buttonOK.setId(R.id.AlertDialogButtonOK);
        buttonOK.setBackground(pContext.getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
        buttonOK.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        // buttonOK.setHeight(50);
        buttonOK.setTextAppearance(pContext, android.R.style.TextAppearance_Medium_Inverse);
        buttonOK.setTextColor(pContext.getResources().getColor(R.color.white));
        buttonOK.setTypeface(Typeface.DEFAULT_BOLD);
        buttonOK.setText(R.string.OK);

        layoutParamsButtonOK = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // layoutParamsButtonOK = new RelativeLayout.LayoutParams(400, 100);

        layoutParamsButtonOK.height = 50; // Defines the height of buttonOK. Breaks "buttonOK.setHeight(50);".
        layoutParamsButtonOK.width = 160; // Defines the width of buttonOK.
        // Discarding relativeLayoutButtonOK (trouble with the insertion of buttonOK in RelativeLayout (Inserting textViewMessage instead, works fine).
        layoutParamsButtonOK.topMargin = 10; // Spacing between this and above element.
        layoutParamsButtonOK.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParamsButtonOK.addRule(RelativeLayout.BELOW, R.id.AlertDialogMessage);

        relativeLayoutButtonOK = new RelativeLayout(pContext);
        // relativeLayoutButtonOK.setBackgroundDrawable(shapeDrawable);

        relativeLayoutButtonOKParams = new RelativeLayout.LayoutParams(400, 70);
        // relativeLayoutButtonOKParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        relativeLayoutButtonOKParams.topMargin = 10; // Spacing between this and above element.
        relativeLayoutButtonOKParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        relativeLayoutButtonOKParams.addRule(RelativeLayout.BELOW, R.id.AlertDialogMessage);

        // relativeLayoutButtonOK.addView(buttonOK, layoutParamsButtonOK);
        // relativeLayoutButtonOK.addView(textViewMessage, layoutParamsTextViewMessage);

        // Adding views.

        relativeLayout.addView(relativeLayoutTitle, relativeLayoutTitleParams);
        // relativeLayout.addView(textViewTitle, layoutParamsTextViewTitle);
        // The approaches below don't works.
        // linearLayout.addView(relativeLayoutTitle, relativeLayoutTitleParams);
        // frameLayout.addView(relativeLayoutTitle, relativeLayoutTitleParams);

        relativeLayout.addView(relativeLayoutMessage, relativeLayoutMessageParams);
        // relativeLayout.addView(textViewMessage, layoutParamsTextViewMessage);
        // The approaches below don't works.
        // linearLayout.addView(relativeLayoutMessage, relativeLayoutMessageParams);
        // frameLayout.addView(relativeLayoutMessage, relativeLayoutMessageParams);

        relativeLayout.addView(buttonOK, layoutParamsButtonOK);
        // relativeLayout.addView(relativeLayoutButtonOK, relativeLayoutButtonOKParams);
        // The approaches below don't works.
        // linearLayout.addView(relativeLayoutButtonOK, relativeLayoutButtonOKParams);
        // frameLayout.addView(relativeLayoutButtonOK, relativeLayoutButtonOKParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(pContext);
        // AlertDialog.Builder adb = new AlertDialog.Builder(this);

        final Dialog dialog = alertDialogBuilder.setView(new View(pContext)).create();
        // Dialog dialog = alertDialogBuilder.setView(new View(pContext)).create();
        // Dialog d = adb.setView(new View(this)).create();
        // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)

        WindowManager.LayoutParams windowManagerLayoutParams = new WindowManager.LayoutParams();
        windowManagerLayoutParams.copyFrom(dialog.getWindow().getAttributes());
        windowManagerLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowManagerLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.show();
        dialog.getWindow().setAttributes(windowManagerLayoutParams);
        dialog.setContentView(relativeLayout);
        // The approaches below don't works.
        // dialog.setContentView(linearLayout);
        // dialog.setContentView(frameLayout);

        // buttonOK = (Button) dialog.findViewById(R.id.AlertDialogButtonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
                // dialog.cancel();
            }

        });
        buttonOK.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View pView, MotionEvent pMotionEvent) {

                switch (pMotionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        pView.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        pView.invalidate();

                        break;

                    }
                    case MotionEvent.ACTION_UP: {

                        pView.getBackground().clearColorFilter();
                        pView.invalidate();

                        break;

                    }
                }

                return false;

            }

        });

    }

    // Debugging (tipxxi): Creating a Calendar (begin).

    public static void showCalendar(Context pContext) {

        RelativeLayout relativeLayout;

        GradientDrawable gradientDrawable;

        RelativeLayout relativeLayoutCalendar;
        RelativeLayout.LayoutParams relativeLayoutCalendarParams;

        CalendarView calendarView;

        Button buttonOK;
        RelativeLayout.LayoutParams layoutParamsButtonOK;

        final Dialog dialog;

        gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(1, pContext.getResources().getColor(R.color.white));

        // relativeLayout definition & settings.

        relativeLayout = new RelativeLayout(pContext);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // relativeLayoutCalendar definition & settings.

        relativeLayoutCalendar = new RelativeLayout(pContext);
        relativeLayoutCalendar.setId(R.id.relativeLayoutCalendar);
        gradientDrawable.setColor(pContext.getResources().getColor(R.color.gray));
        relativeLayoutCalendar.setBackground(gradientDrawable);

        relativeLayoutCalendarParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        relativeLayoutCalendarParams.width = 700;
        relativeLayoutCalendarParams.height = 400;
        relativeLayoutCalendarParams.topMargin = 10;

        relativeLayoutCalendarParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        // calendarView definition & settings.

        calendarView = new CalendarView(pContext);

        // calendarView.setEnabled(false);

        /**
         * Sets the text appearance for the week day abbreviation of the calendar header.
         *
         * @param resourceId The text appearance resource id.
         *
         * @attr ref android.R.styleable#CalendarView_weekDayTextAppearance
         */
        // calendarView.setDateTextAppearance();

        /**
         * Check if this view can be scrolled horizontally in a certain direction.
         *
         * @param direction Negative to check scrolling left, positive to check scrolling right.
         * @return true if this view can be scrolled in the specified direction, false otherwise.
         */
        // calendarView.canScrollHorizontally();

        /**
         * Check if this view can be scrolled vertically in a certain direction.
         *
         * @param direction Negative to check scrolling up, positive to check scrolling down.
         * @return true if this view can be scrolled in the specified direction, false otherwise.
         */
        // calendarView.canScrollVertically();

//        calendarView.setId(R.id.calendarView);

        /**
         * Sets whether to show the week number.
         *
         * @param showWeekNumber True to show the week number.
         *
         * @attr ref android.R.styleable#CalendarView_showWeekNumber
         */
        // Sets whether to show the week number.
        calendarView.setShowWeekNumber(false);

        /**
         * Sets the first day of week.
         *
         * @param firstDayOfWeek The first day of the week conforming to the
         *            {@link CalendarView} APIs.
         * @see Calendar#MONDAY
         * @see Calendar#TUESDAY
         * @see Calendar#WEDNESDAY
         * @see Calendar#THURSDAY
         * @see Calendar#FRIDAY
         * @see Calendar#SATURDAY
         * @see Calendar#SUNDAY
         *
         * @attr ref android.R.styleable#CalendarView_firstDayOfWeek
         */
        // Sets the first day of week according to Calendar. Here we set Monday as the first day of the Calendar.
        calendarView.setFirstDayOfWeek(2);

        /**
         * Sets the background color for the selected week.
         *
         * @param color The week background color.
         *
         * @attr ref android.R.styleable#CalendarView_selectedWeekBackgroundColor
         */
        // The background color for the selected week.
        calendarView.setSelectedWeekBackgroundColor(pContext.getResources().getColor(R.color.green));

        /**
         * Sets the color for the dates of a not focused month.
         *
         * @param color A not focused month date color.
         *
         * @attr ref android.R.styleable#CalendarView_unfocusedMonthDateColor
         */
        // Sets the color for the dates of an unfocused month.
        calendarView.setUnfocusedMonthDateColor(pContext.getResources().getColor(R.color.white));
        // calendarView.setUnfocusedMonthDateColor(pContext.getResources().getColor(R.color.transparent));

        /**
         * Sets the color for the separator line between weeks.
         *
         * @param color The week separator color.
         *
         * @attr ref android.R.styleable#CalendarView_weekSeparatorLineColor
         */
        // Sets the color for the separator line between weeks.
        calendarView.setWeekSeparatorLineColor(pContext.getResources().getColor(R.color.white));
        // calendarView.setWeekSeparatorLineColor(pContext.getResources().getColor(R.color.transparent));

        /**
         * Sets the drawable for the vertical bar shown at the beginning and at
         * the end of the selected date.
         *
         * @param resourceId The vertical bar drawable resource id.
         *
         * @attr ref android.R.styleable#CalendarView_selectedDateVerticalBar
         */
        // Sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        calendarView.setSelectedDateVerticalBar(R.color.darkorange);
        // calendarView.setSelectedDateVerticalBar(R.color.darkgreen);

        /**
         * Sets the listener to be notified upon selected date change.
         *
         * @param listener The listener to be notified.
         */
        // Sets the listener to be notified upon selected date change.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            // Show the selected date as a toast.
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                displayToast(day + "/" + month + "/" + year, Toast.LENGTH_SHORT);
                // Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

            }

        });

        RelativeLayout.LayoutParams layoutParamsCalendarView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // RelativeLayout.LayoutParams layoutParamsCalendarView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // layoutParamsCalendarView.height = 600;
        // layoutParamsCalendarView.width = 300;

        // buttonOK definition & settings.

        buttonOK = new Button(pContext);
        buttonOK.setBackground(pContext.getResources().getDrawable(R.drawable.button_shape)); // Related with .setBackgroundColor().
        buttonOK.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        buttonOK.setTextAppearance(pContext, android.R.style.TextAppearance_Medium_Inverse);
        buttonOK.setTextColor(pContext.getResources().getColor(R.color.white));
        buttonOK.setTypeface(Typeface.DEFAULT_BOLD);
        buttonOK.setText(R.string.OK);

        layoutParamsButtonOK = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParamsButtonOK.height = 50; // Defines the height of buttonOK. Breaks "buttonOK.setHeight(50);".
        layoutParamsButtonOK.width = 160; // Defines the width of buttonOK.
        // Discarding relativeLayoutButtonOK (trouble with the insertion of buttonOK in RelativeLayout (Inserting textViewMessage instead, works fine).
        layoutParamsButtonOK.topMargin = 10; // Spacing between this and above element.
        layoutParamsButtonOK.bottomMargin = 10; // Spacing between this and above element.
        layoutParamsButtonOK.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParamsButtonOK.addRule(RelativeLayout.BELOW, R.id.relativeLayoutCalendar);

        // Adding views.

        relativeLayoutCalendar.addView(calendarView, layoutParamsCalendarView);
        relativeLayout.addView(relativeLayoutCalendar, relativeLayoutCalendarParams);
        relativeLayout.addView(buttonOK, layoutParamsButtonOK);

        // relativeLayout.addView(calendarView, layoutParamsCalendarView);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(pContext);
        dialog = alertDialogBuilder.setView(new View(pContext)).create();

        WindowManager.LayoutParams windowManagerLayoutParams = new WindowManager.LayoutParams();
        windowManagerLayoutParams.copyFrom(dialog.getWindow().getAttributes());
        windowManagerLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowManagerLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.show();
        dialog.getWindow().setAttributes(windowManagerLayoutParams);
        dialog.setContentView(relativeLayout);

        // Setting the listeners.

        buttonOK.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }

        });

        buttonOK.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View pView, MotionEvent pMotionEvent) {

                switch (pMotionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        pView.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        pView.invalidate();

                        break;

                    }
                    case MotionEvent.ACTION_UP: {

                        pView.getBackground().clearColorFilter();
                        pView.invalidate();

                        break;

                    }
                }

                return false;

            }

        });

    }

    // Debugging (tipxxi): Creating a Calendar (end).

}
