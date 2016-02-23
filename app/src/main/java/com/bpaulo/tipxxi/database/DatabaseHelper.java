package com.bpaulo.tipxxi.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.bpaulo.tipxxi.menuprincipal.MenuPrincipalActivity;
import com.bpaulo.tipxxi.util.Utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import dalvik.system.DexFile;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "BP.db";

	// Debugging: Singleton SQLiteOpenHelper (begin).

	private static final int DATABASE_VERSION = 1;
	@SuppressLint("SdCardPath")
	// private static final String PATH = "/data/data/com.bpaulo.tipxxi/databases/" + DATABASE_NAME;

	// Debugging: Singleton SQLiteOpenHelper (end).

	private static final String TAG = "DatabaseHelper";

	public static final int NUMBER_OF_ATTRIBUTE_PARAMETERS = 16;

	// Debugging: Singleton SQLiteOpenHelper.
	private static DatabaseHelper sInstance;

	public DatabaseHelper(Context context) {

		// Debugging: Singleton SQLiteOpenHelper.
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//		super(context, DATABASE_NAME, null, 1);

	}

	// Debugging: Singleton SQLiteOpenHelper (begin).

	public static synchronized DatabaseHelper getInstance(Context context) {

		// Use the application context, which will ensure that you don't accidentally leak an Activity's context.
		// See this article for more information: http://bit.ly/6LRzfx
		if (sInstance == null) {
			sInstance = new DatabaseHelper(context.getApplicationContext());
			sInstance.getWritableDatabase(); // Needed to create correctly the database (in the first time).
		}

		return sInstance;

	}

	// Debugging: Singleton SQLiteOpenHelper (end).

	@Override
	public void onCreate(SQLiteDatabase db) {

		Field[] lFieldList;
		ArrayList<String> arrayListEntitiesNames = getAllEntitiesNames();
		String[] arrayEntitiesNames = arrayListEntitiesNames.toArray(new String[arrayListEntitiesNames.size()]);
		String query = "";
		int primaryKeyIndex = 0;
		List<String>[] entityFKList = new List[arrayEntitiesNames.length];

        db.setForeignKeyConstraintsEnabled(true);

        // Debugging.
		// http://developer.android.com/training/basics/data-storage/databases.html
		// http://stackoverflow.com/questions/1942586/comparison-of-database-column-types-in-mysql-postgresql-and-sqlite-cross-map

		// Getting some system information.
// 		int sdkVersion = Integer.parseInt(Build.VERSION.SDK);
//		if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
//			className = "com.example.android.businesscard.ContactAccessorSdk3_4";
//		}
//		else {
//			className = "com.example.android.businesscard.ContactAccessorSdk5";
//		}

		// Works! But retrieve useless information about the package.
//		Log.i(TAG, "getActivity().getApplicationContext().getDatabasePath('BP.db') = " + ArranqueActivity.getAppApplicationContext().getDatabasePath("BP.db"));
		// Log.i(TAG, "getActivity().getApplicationContext().getDatabasePath('BP.db') = " + getActivity().getApplicationContext().getDatabasePath("BP.db"));
//		Package lPackage = Package.getPackage("com.bpaulo.comunicaxxilight.entity");
//		Log.i(TAG, "lPackage.getName() = " + lPackage.getName());
		// Package[] packageList = lPackage.getPackages();

		// dalvik.system.DexClassLoader dexClassLoader;
		// dalvik.system.PathClassLoader pathClassLoader;

		/*

		  	Setting SQLLite PRAGMA.

			-- PRAGMA auto_vacuum; -- Current setting.
			-- PRAGMA database.auto_vacuum = 0 | NONE | 1 | FULL | 2 | INCREMENTAL;
			-- Current: 1 | FULL
			PRAGMA auto_vacuum = 1; -- 0 option don't works.

			-- PRAGMA automatic_index; -- Current setting.
			-- PRAGMA automatic_index = boolean;
			-- Current: true (changed)
			PRAGMA automatic_index = false;

			-- PRAGMA checkpoint_fullfsync -- Current setting.
			-- PRAGMA checkpoint_fullfsync = boolean;
			-- Current: false
			PRAGMA checkpoint_fullfsync = false;

			-- http://stackoverflow.com/questions/4254371/enabling-foreign-key-constraints-in-sqlite
			-- Obs: Setted for each transaction.
			-- PRAGMA foreign_keys; -- Current setting.
			-- PRAGMA foreign_keys = boolean;
			-- Current: OFF (changed)
			PRAGMA foreign_keys = true;

			-- PRAGMA fullfsync; -- Current setting.
			-- PRAGMA fullfsync = boolean;
			-- Current: false
			PRAGMA fullfsync = false;

			-- PRAGMA ignore_check_constraints; -- Current setting.
			-- PRAGMA ignore_check_constraints = boolean;
			-- Current: false
			PRAGMA ignore_check_constraints = false;

			-- PRAGMA database.journal_mode; -- Current setting.
			-- PRAGMA database.journal_mode = DELETE | TRUNCATE | PERSIST | MEMORY | WAL | OFF
			-- Current: DELETE
			PRAGMA journal_mode = DELETE;

			-- PRAGMA database.journal_size_limit; -- Current setting.
			-- PRAGMA database.journal_size_limit = N;
			-- Current: -1
			PRAGMA journal_size_limit = -1;

			-- PRAGMA database.locking_mode; -- Current setting.
			-- PRAGMA database.locking_mode = NORMAL | EXCLUSIVE
			-- Current: NORMAL
			PRAGMA locking_mode = NORMAL;

			-- Don't works!
			-- PRAGMA database.max_page_count; -- Current setting.
			-- PRAGMA database.max_page_count = N;
			-- Current: 1073741823
			PRAGMA max_page_count;

			-- Don't works!
			-- PRAGMA database.page_size; -- Current setting.
			-- PRAGMA database.page_size = bytes;
			-- Current: 4096
			PRAGMA page_size;

			-- PRAGMA recursive_triggers; -- Current setting.
			-- PRAGMA recursive_triggers = boolean;
			-- Current: false
			PRAGMA recursive_triggers = false;

			-- PRAGMA database.secure_delete; -- Current setting.
			-- PRAGMA database.secure_delete = boolean;
			-- Current: false (changed)
			PRAGMA secure_delete = true;

			-- Don't works!
			-- Safety level may not be changed inside a transaction: PRAGMA synchronous = OFF;
			-- Safety level may not be changed inside a transaction: PRAGMA synchronous = NORMAL;
			-- PRAGMA database.synchronous;
			-- PRAGMA database.synchronous = 0 | OFF | 1 | NORMAL | 2 | FULL;
			-- Current: 2 | FULL
			PRAGMA synchronous;

			-- Don't works!
			-- temporary storage cannot be changed from within a transaction: PRAGMA temp_store = MEMORY;
			-- temporary storage cannot be changed from within a transaction: PRAGMA temp_store = FILE;
			-- PRAGMA temp_store;
			-- PRAGMA temp_store = 0 | DEFAULT | 1 | FILE | 2 | MEMORY;
			-- Current: 0 | DEFAULT
			PRAGMA temp_store;

			-- PRAGMA database.schema_version; -- Current setting.
			-- PRAGMA database.schema_version = integer;
			-- PRAGMA database.user_version; -- Current setting.
			-- PRAGMA database.user_version = integer;
			-- Current: 1
			PRAGMA user_version = 1;

			-- PRAGMA database.wal_checkpoint; -- Current setting.
			-- PRAGMA database.wal_checkpoint(PASSIVE);
			-- PRAGMA database.wal_checkpoint(FULL);
			-- PRAGMA database.wal_checkpoint(RESTART);
			-- PRAGMA database.wal_checkpoint(TRUNCATE);
			PRAGMA wal_checkpoint;

		 */

		// db.rawQuery("PRAGMA secure_delete = true;", null); // Don't works!

		// db.execSQL("PRAGMA secure_delete = true;");
//		04-16 12:04:50.555: E/AndroidRuntime(3186): FATAL EXCEPTION: main
//		04-16 12:04:50.555: E/AndroidRuntime(3186): android.database.sqlite.SQLiteException: unknown error (code 0): Queries can be performed using SQLiteDatabase query or rawQuery methods only.
//		04-16 12:04:50.555: E/AndroidRuntime(3186): 	at android.database.sqlite.SQLiteConnection.nativeExecuteForChangedRowCount(Native Method)

		// Creating table with PK for each entity.

		for (int i = 0; i < arrayEntitiesNames.length; i++) {

			query = "CREATE TABLE IF NOT EXISTS tb" + arrayEntitiesNames[i] + " (";
			lFieldList = getEntityFields(arrayEntitiesNames[i]);
			entityFKList[i] = new ArrayList<String>();
			entityFKList[i].add(arrayEntitiesNames[i]);

			for (int j = 0; j < lFieldList.length; j++) {

				if (query.equals("CREATE TABLE IF NOT EXISTS tb" + arrayEntitiesNames[i] + " (")) {
					query += lFieldList[j].getName();
				}
				else {
					query += ", " + lFieldList[j].getName();
				}

				// Setting field type.
				if (lFieldList[j].getType().equals(Integer.class)) {
					query += " INTEGER";
				}
				else if (lFieldList[j].getType().equals(Float.class)) {
					query += " REAL";
				}
				else if (lFieldList[j].getType().equals(Long.class)) {
					query += " REAL";
				}
				else if (lFieldList[j].getType().equals(String.class)) {
					query += " TEXT";
				}

				if (lFieldList[j].getName().startsWith("id")){
					// Checking if the field is the ID of entity.
					if (lFieldList[j].getName().equals("id" + arrayEntitiesNames[i])) {
						primaryKeyIndex = j;
					}
					// Checking if the field is a ID of another entity/table (FK).
					else if (arrayListEntitiesNames.contains(lFieldList[j].getName().substring(2, lFieldList[j].getName().length()))) {
						entityFKList[i].add(lFieldList[j].getName());
					}
				}

			}

			// PRIMARY KEY definition.
			query += ", PRIMARY KEY (" + lFieldList[primaryKeyIndex].getName() + "));";

			db.execSQL(query);
		}

		for (int i = 0; i < entityFKList.length; i++) {
			// Checking if the entity have FKs.
			if (entityFKList[i].size() > 1) {

				lFieldList = getEntityFields(entityFKList[i].get(0));

				// Path 1: Creating a clone table.

				primaryKeyIndex = 0;

				query = "CREATE TABLE IF NOT EXISTS tb" + entityFKList[i].get(0) + "Aux (";

				for (int j = 0; j < lFieldList.length; j++) {

					if (query.equals("CREATE TABLE IF NOT EXISTS tb" + entityFKList[i].get(0) + "Aux (")) {
						query += lFieldList[j].getName();
					}
					else {
						query += ", " + lFieldList[j].getName();
					}

					// Setting field type.
					if (lFieldList[j].getType().equals(Integer.class)) {
						query += " INTEGER";
					}
					else if (lFieldList[j].getType().equals(Float.class)) {
						query += " REAL";
					}
					else if (lFieldList[j].getType().equals(Long.class)) {
						query += " REAL";
					}
					else if (lFieldList[j].getType().equals(String.class)) {
						query += " TEXT";
					}

					if (lFieldList[j].getName().startsWith("id")){
						// Checking if the field is the ID of entity.
						if (lFieldList[j].getName().equals("id" + entityFKList[i].get(0))) {
							primaryKeyIndex = j;
						}
					}
				}

				// PRIMARY KEY definition.
				query += ", PRIMARY KEY (" + lFieldList[primaryKeyIndex].getName() + ")";

				// FOREIGN KEY definition(s).
				for (int k = 1; k < entityFKList[i].size(); k++) {

					query += ", FOREIGN KEY (" + entityFKList[i].get(k) +
							") REFERENCES tb" + entityFKList[i].get(k).substring(2, entityFKList[i].get(k).length()) +
							" (" + entityFKList[i].get(k) + ")";

				}

				query += ");";

				db.execSQL(query);

				// Path 2: Insert original table content in clone table.

				query = "INSERT INTO tb" + entityFKList[i].get(0) + "Aux SELECT ";

				for (int j = 0; j < lFieldList.length; j++) {

					if (query.equals("INSERT INTO tb" + entityFKList[i].get(0) + "Aux SELECT ")) {
						query += lFieldList[j].getName();
					}
					else {
						query += ", " + lFieldList[j].getName();
					}

				}

				query += " FROM tb" + entityFKList[i].get(0) + ";";

				db.execSQL(query);

				// Path 3: Drop original table.

				query = "DROP TABLE tb" + entityFKList[i].get(0) + ";";

				db.execSQL(query);

				// Path 4: Rename clone table with original table name.

				query = "ALTER TABLE tb" + entityFKList[i].get(0) + "Aux RENAME TO tb" + entityFKList[i].get(0) + ";";

				db.execSQL(query);

			}
		}

		// TABLE tbOperadores
//		db.execSQL("CREATE TABLE IF NOT EXISTS tbOperadores "
//				+ "(IDOperador INTEGER, " + "NomeOperador CHAR(50))");

		// TABLE tbParametros
//		db.execSQL("CREATE TABLE IF NOT EXISTS tbParametros "
//				+ "(ImprimeTalaoParcial INTEGER, "
//				+ "ImprimeTalaoTotal INTEGER, " + "NumLinhasCabec INTEGER, "
//				+ "TextoCabec1 CHAR(80), " + "TextoCabec2 CHAR(80), "
//				+ "TextoCabec3 CHAR(80), " + "TextoCabec4 CHAR(80), "
//				+ "TextoCabec5 CHAR(80), " + "TextoCabec6 CHAR(80), "
//				+ "TextoCabec7 CHAR(80), " + "TextoCabec8 CHAR(80), "
//				+ "NumLinhasRodape INTEGER, " + "TextoRodape1 CHAR(80), "
//				+ "TextoRodape2 CHAR(80), " + "TextoRodape3 CHAR(80), "
//				+ "NumLinhasAvancoRodape INTEGER, "
//				+ "AdminPassword CHAR(10), " + "Idioma CHAR(02), "
//				+ "CodArtigoDiversos CHAR(10), "
//				+ "CodEmpresaDiversos CHAR(10), "
//				+ "ReiniciaNumTalaoAno INTEGER, " + "LabelEmpresaTP CHAR(10), "
//				+ "LabelReferenciaTP CHAR(10), " + "AssumirTaraVisor INTEGER, "
//				+ "TempoMostragemPeso INTEGER, " + "TimeOutPeso INTEGER, "
//				+ "ModoAcessoPesagem CHAR(20), " + "FormatoTP CHAR(20), "
//				+ "NumViasTP INTEGER, " + "MostraPesoVia CHAR(10), "
//				+ "EmiteFR INTEGER, " + "TimeOutNIF INTEGER, "
//				+ "PermitirParque INTEGER)");

		// TABLE tbVisores
//		db.execSQL("CREATE TABLE IF NOT EXISTS tbVisores "
//				+ "(IDVisor CHAR(10), " + "DesignacaoVisor CHAR(50), "
//				+ "FimTrama INTEGER, " + "InicioPeso INTEGER, "
//				+ "InicioTrama INTEGER, " + "TamanhoBufferEnvio INTEGER, "
//				+ "TamanhoBufferRecepcao INTEGER, " + "TamanhoPeso INTEGER)");

		// TABLE tbVisorPosto
//		db.execSQL("CREATE TABLE IF NOT EXISTS tbVisorPosto "
//				+ "(IDPosto CHAR(10), " + "IDVisorPosto CHAR(10), "
//				+ "Alcance REAL, " + "DivisaoPeso INTEGER, "
//				+ "DivisoesEstabilidade INTEGER, " + "PesoMinimo REAL, "
//				+ "UnidadePeso CHAR(10), " + "EfectuaPedido INTEGER, "
//				+ "IntervaloPedidos INTEGER, " + "PortaComVisor CHAR(10), "
//				+ "RemoteIP CHAR(20), " + "RemoteIPPorta CHAR(10), "
//				+ "SettingsComVisor CHAR(50), " + "Rs485NumVisores INTEGER)");

	}

    // Debugging: setForeignKeyConstraintsEnabled approach (begin).

    @Override
    public void onConfigure(SQLiteDatabase db) {

        db.setForeignKeyConstraintsEnabled(true);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            database.setForeignKeyConstraintsEnabled(true);
//        } else {
//            database.execSQL("PRAGMA foreign_keys=ON");
//        }

    }

    // Debugging: setForeignKeyConstraintsEnabled approach (end).

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        ArrayList<String> listEntitiesNames = getAllEntitiesNames();
        Iterator<String> itListEntitiesNames = listEntitiesNames.iterator();
        String query = "";
        while (itListEntitiesNames.hasNext()) {
            String entityName = itListEntitiesNames.next();
            query = "DROP TABLE IF EXISTS tb" + entityName;
            // db.execSQL(query);
        }

//		db.execSQL("DROP TABLE IF EXISTS tbParametros");
//		db.execSQL("DROP TABLE IF EXISTS tbVisores");
//		db.execSQL("DROP TABLE IF EXISTS tbVisorPosto");
//		db.execSQL("DROP TABLE IF EXISTS tbOperadores");

        onCreate(db);
    }

    // Debugging: setForeignKeyConstraintsEnabled approach (end).

	// Methods that uses Android & Java reflection.

	// Debugging (tipxxi): ID/Code alphanumeric.
	public int createEntity (Object pObjectToWrite) {
	// public boolean createEntity (Object pObject) {

		String lClassName;
		Field[] lFieldList;

		// Debugging (tipxxi): ID/Code alphanumeric.
		SQLiteDatabase db;
		this.getReadableDatabase();
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues lContentValues = new ContentValues();

		// Debugging (tipxxi): ID/Code alphanumeric (begin).

		Object lObjectToRead;
		Field lField;
		Method lGetIdMethod, lSetIdMethod;
		Object lIdObject;

		// Debugging (tipxxi): ID/Code alphanumeric (end).

		lClassName = pObjectToWrite.getClass().getSimpleName();
		lFieldList = getEntityFields(lClassName);

		// Debugging (tipxxi): ID/Code alphanumeric (begin).

		// Checking if new register already exists.
		try {

			lObjectToRead = pObjectToWrite.getClass().newInstance();

			lField = pObjectToWrite.getClass().getDeclaredField("id" + lClassName);
			lField.setAccessible(true);

			// Getting pObjectToWrite id.
			lGetIdMethod = pObjectToWrite.getClass().getMethod("getId" + lClassName);
			lIdObject = lGetIdMethod.invoke(pObjectToWrite);

			// Setting lObjectToRead id with pObjectToWrite id.
			lSetIdMethod = pObjectToWrite.getClass().getMethod("setId" + lClassName, lField.getType());
			lSetIdMethod.invoke(lObjectToRead, lIdObject);

			if (readEntity(lObjectToRead) != null) { // DatabaseHelper::readEntity(): Call OK.
				return 98; // Duplicated id
			}

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
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// Debugging (tipxxi): ID/Code alphanumeric (end).

		try {
			for (int i = 0; i < lFieldList.length; i++) {

				// Debugging (tipxxi): ID/Code alphanumeric (begin).

				lFieldList[i].setAccessible(true);

				if (lFieldList[i].get(pObjectToWrite) != null) {
					if (lFieldList[i].get(pObjectToWrite).getClass().equals(Integer.class)) {
						lContentValues.put(lFieldList[i].getName(), (Integer) lFieldList[i].get(pObjectToWrite));
					}
					else if (lFieldList[i].get(pObjectToWrite).getClass().equals(Float.class)) {
						lContentValues.put(lFieldList[i].getName(), (Float) lFieldList[i].get(pObjectToWrite));
					}
					else if (lFieldList[i].get(pObjectToWrite).getClass().equals(Long.class)) {
						lContentValues.put(lFieldList[i].getName(), (Long) lFieldList[i].get(pObjectToWrite));
					}
					else if (lFieldList[i].get(pObjectToWrite).getClass().equals(String.class)) {
						lContentValues.put(lFieldList[i].getName(), lFieldList[i].get(pObjectToWrite).toString());
					}
				}

//				if (lFieldList[i].getName().equals("id" + lClassName)) {
//					// ID entity field.
//					contentValues.put(lFieldList[i].getName(), getHighestID(lClassName) + 1);
//				}
//				else {
//					lFieldList[i].setAccessible(true);
//					// http://stackoverflow.com/questions/37628/what-is-reflection-and-why-is-it-useful
//					if (lFieldList[i].get(pObjectToWrite) != null) {
//						if (lFieldList[i].get(pObjectToWrite).getClass().equals(Integer.class)) {
//						// if (lObject instanceof Integer) {
//							contentValues.put(lFieldList[i].getName(), (Integer) lFieldList[i].get(pObjectToWrite));
//						}
//						else if (lFieldList[i].get(pObjectToWrite).getClass().equals(Float.class)) {
//						// else if (lObject instanceof Float) {
//							contentValues.put(lFieldList[i].getName(), (Float) lFieldList[i].get(pObjectToWrite));
//						}
//						else if (lFieldList[i].get(pObject).getClass().equals(Long.class)) {
//						// else if (lObject instanceof Long)) {
//							contentValues.put(lFieldList[i].getName(), (Long) lFieldList[i].get(pObjectToWrite));
//						}
//						else if (lFieldList[i].get(pObject).getClass().equals(String.class)) {
//						// else if (lObject instanceof String)) {
//							contentValues.put(lFieldList[i].getName(), lFieldList[i].get(pObjectToWrite).toString());
//						}
//					}
//				}

				// Debugging (tipxxi): ID/Code alphanumeric (end).

			}
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// Debugging: SQLiteConnection object for database leaked (begin).

//		if (!db.isOpen()) {
//			db.openDatabase(PATH ,null, 0);
//		}

		// Debugging: SQLiteConnection object for database leaked (end).

		// Debugging (tipxxi): ID/Code alphanumeric.
		db = this.getWritableDatabase();

		// Debugging (tipxxi): SQLiteException (begin).

		try {

			db.insertOrThrow("tb" + lClassName, null, lContentValues);
			// db.insert("tb" + lClassName, null, lContentValues);

		}
		catch (SQLiteException e) {
		// catch (SQLiteConstraintException e) {

			e.printStackTrace();
			// SQLiteConstraintException.printStackTrace();

			return 97; // Foreign key (FK) constraint failed.

		}

		// db.insert("tb" + lClassName, null, lContentValues);

		// Debugging (tipxxi): SQLiteException (end).

		// Debugging: SQLiteConnection object for database leaked.
		db.close();

		// Debugging (tipxxi): ID/Code alphanumeric.
		return 0; // OK
		// return true;

	}

	public Object readEntity (Object pObject) {

		String lClassName;
		Field[] lFieldList;
		// Debugging (tipxxi): ID/Code alphanumeric (begin).
		Field lIdField = null;
		Cursor lCursor = null;
		// Field lField;
		// Integer lId = 0;
		// Debugging (tipxxi): ID/Code alphanumeric (end).
		int lColumnIndex = 0;

		SQLiteDatabase db = this.getReadableDatabase();

		lClassName = pObject.getClass().getSimpleName();
		lFieldList = pObject.getClass().getDeclaredFields();
		try {
			lIdField = pObject.getClass().getDeclaredField("id" + lClassName);
			lIdField.setAccessible(true);
			// Debugging (tipxxi): ID/Code alphanumeric.
			// lId = Integer.valueOf(lField.get(pObject).toString());
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		// Debugging (tipxxi): ID/Code alphanumeric (begin).
		try {
			lCursor = db.rawQuery("SELECT * FROM " + "tb" + lClassName + " WHERE " + lIdField.getName() + " = '" + lIdField.get(pObject).toString() + "'", null);
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		// Cursor lCursor = db.rawQuery("SELECT * FROM " + "tb" + lClassName + " WHERE rowid = " + lId + "", null);
		// Debugging (tipxxi): ID/Code alphanumeric (end).

		// Move the lCursor to the first row. This method will return false if the lCursor is empty.
		if (lCursor.moveToFirst()) {

			for (int i = 0; i < lFieldList.length; i++) {

				lFieldList[i].setAccessible(true);
				lColumnIndex = lCursor.getColumnIndex(lFieldList[i].getName());

				try {

					// Debugging (tipxxi): Refactoring entity attributes checking (begin).

					if (!isEntityAttribute(lFieldList[i].getName())) {

					/*
					if (!lFieldList[i].getName().equals("NUMBER_OF_ATTRIBUTES") &&
							!lFieldList[i].getName().equals("attributeParameters") &&
							!lFieldList[i].getName().equals("ONE_REGISTER") &&
							// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
							// !lFieldList[i].getName().equals("ENTITY_CAPTION")) {
							// !lFieldList[i].getName().equals("CAPTION")) {
							// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).
							!lFieldList[i].getName().equals("CREATE") &&
							!lFieldList[i].getName().equals("READ") &&
							!lFieldList[i].getName().equals("UPDATE") &&
							!lFieldList[i].getName().equals("DELETE") &&
							!lFieldList[i].getName().equals("ENTITY_CAPTION")) {
							// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).
					*/

					// Debugging (tipxxi): Refactoring entity attributes checking (end).

						if (lFieldList[i].getType().equals(Integer.class)) {
							lFieldList[i].set(pObject, lCursor.getInt(lColumnIndex));
						}
						else if (lFieldList[i].getType().equals(Float.class)) {
							lFieldList[i].set(pObject, lCursor.getFloat(lColumnIndex));
						}
						else if (lFieldList[i].getType().equals(Long.class)) {
							lFieldList[i].set(pObject, lCursor.getLong(lColumnIndex));
						}
						else if (lFieldList[i].getType().equals(String.class)) {
							lFieldList[i].set(pObject, lCursor.getString(lColumnIndex));
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
		// Debugging (tipxxi): ID/Code alphanumeric (begin).
		else {
			pObject = null;
		}
		// Debugging (tipxxi): ID/Code alphanumeric (end).
		lCursor.close();

		// Debugging: SQLiteConnection object for database leaked.
		db.close();

		return pObject;

	}

	// Debugging (tipxxi): ID/Code alphanumeric.
	public int updateEntity(Object pObjectToUpdate, String pLoadedObjectId) {
	// public boolean updateEntity(Object pObjectToUpdate) {

		String lClassName;
		Field[] lFieldList;
		// Debugging (tipxxi): ID/Code alphanumeric.
		String lId = "";
		// Integer lId = 0;

		// Debugging (tipxxi): ID/Code alphanumeric.
		SQLiteDatabase db;
		this.getReadableDatabase();
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues lContentValues = new ContentValues();

		// Debugging (tipxxi): ID/Code alphanumeric (begin).

		Object lObjectToDelete;
		Field lIdField;
		Method lSetIdMethod;
		Object lObjectToUpdateId;

		// Debugging (tipxxi): ID/Code alphanumeric (end).

		lClassName = pObjectToUpdate.getClass().getSimpleName();
		lFieldList = getEntityFields(lClassName);

		// Debugging (tipxxi): ID/Code alphanumeric (begin).

		// Checking if register exists.
		try {

			lIdField = pObjectToUpdate.getClass().getDeclaredField("id" + lClassName);
			lIdField.setAccessible(true);

			// Getting pObjectToUpdate id (using Field get method).
			lObjectToUpdateId = lIdField.get(pObjectToUpdate);

			if (!pLoadedObjectId.equals(lObjectToUpdateId.toString())) {

				// ID changed: Create a new one, checking if ID already exists, if no, delete
				// the original register (I cannot update a register with a unknown ID).

				lObjectToDelete = pObjectToUpdate.getClass().newInstance();
				lSetIdMethod = lObjectToDelete.getClass().getMethod("setId" + lClassName, lIdField.getType());
				if (lIdField.getType().equals(Integer.class)) {
					lSetIdMethod.invoke(lObjectToDelete, Integer.valueOf(pLoadedObjectId));
				}
				else {
					lSetIdMethod.invoke(lObjectToDelete, pLoadedObjectId);
				}

				switch (createEntity(pObjectToUpdate)) {

					case 0: // OK.

						if (deleteEntity(lObjectToDelete) != 0) {
							return 97;
						}

						return 0;

					case 98: // Duplicated ID.

						return 98;

					case 99: // Unexpected error.

						return 99;

				}

			}

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
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// Debugging (tipxxi): ID/Code alphanumeric (end).

		try {
			for (int i = 0; i < lFieldList.length; i++) {
				lFieldList[i].setAccessible(true);
				if (lFieldList[i].get(pObjectToUpdate) != null) {
					if (lFieldList[i].getName().equals("id" + lClassName)) {

						// Debugging (tipxxi): ID/Code alphanumeric (begin).

						lId = (String) lFieldList[i].get(pObjectToUpdate);

//						if (lFieldList[i].get(pObjectToUpdate).getClass().equals(Integer.class)) {
//							lId = (Integer) lFieldList[i].get(pObjectToUpdate);
//						}
//						else if (lFieldList[i].get(pObjectToUpdate).getClass().equals(String.class)) {
//							lId = Integer.valueOf((String) lFieldList[i].get(pObjectToUpdate));
//						}

						// Debugging (tipxxi): ID/Code alphanumeric (end).

					}
					else {
						if (lFieldList[i].get(pObjectToUpdate).getClass().equals(Integer.class)) {
							lContentValues.put(lFieldList[i].getName(), (Integer) lFieldList[i].get(pObjectToUpdate));
						}
						else if (lFieldList[i].get(pObjectToUpdate).getClass().equals(Float.class)) {
							lContentValues.put(lFieldList[i].getName(), (Float) lFieldList[i].get(pObjectToUpdate));
						}
						else if (lFieldList[i].get(pObjectToUpdate).getClass().equals(Long.class)) {
							lContentValues.put(lFieldList[i].getName(), (Long) lFieldList[i].get(pObjectToUpdate));
						}
						else if (lFieldList[i].get(pObjectToUpdate).getClass().equals(String.class)) {
							lContentValues.put(lFieldList[i].getName(), lFieldList[i].get(pObjectToUpdate).toString());
						}
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

		// Debugging (tipxxi): ID/Code alphanumeric (begin).

		db = this.getWritableDatabase();

		// Debugging (tipxxi): SQLiteException (begin).

		try {

			// Debugging (tipxxi): ID/Code alphanumeric.
			db.update("tb" + lClassName, lContentValues, "id" + lClassName + "= ? ", new String[]{lId});
			// db.update("tb" + lClassName, lContentValues, "id" + lClassName + "= ? ", new String[]{Integer.toString(lId)});

		}
		catch (SQLiteException e) {

			e.printStackTrace();
			// SQLiteConstraintException.printStackTrace();

			return 99; // TODO: Define the exact error.

		}

		// db.update("tb" + lClassName, lContentValues, "id" + lClassName + "= ? ", new String[]{Integer.toString(lId)});

		// Debugging (tipxxi): SQLiteException (end).

		// Debugging (tipxxi): ID/Code alphanumeric (end).

		// Debugging: SQLiteConnection object for database leaked.
		db.close();

		// Debugging (tipxxi): ID/Code alphanumeric.
		return 0;
		// return true;

	}

	public int deleteEntity(Object pObjectToDelete) {
	// public boolean deleteEntity(Object pObjectToDelete) {

		String lClassName;
		Field lIdField;
		Integer lId = 0;

		// Debugging (tipxxi): ID/Code alphanumeric.
		Object lIdObject = null;

		SQLiteDatabase db = this.getWritableDatabase();
		lClassName = pObjectToDelete.getClass().getSimpleName();

		try {
			lIdField = pObjectToDelete.getClass().getDeclaredField("id" + lClassName);
			lIdField.setAccessible(true);
			// Debugging (tipxxi): ID/Code alphanumeric.
			lIdObject = lIdField.get(pObjectToDelete);
			// lId = (Integer) lField.get(pObjectToId);
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		/*
			https://code.google.com/p/android/issues/detail?id=11607

			try {
				db.beginTransaction();
				db.execSQL("PRAGMA foreign_keys=ON;");
				db.execSQL("DELETE FROM "+tabela+" WHERE "+campo+" ="+id);
				db.execSQL("PRAGMA foreign_keys=OFF;");
				db.setTransactionSuccessful();
			}
			catch (Exception e) {
				e.printStackTrace();
				db.endTransaction();
			}

		 */
//		try {
//			db.beginTransaction();
//			db.rawQuery("PRAGMA foreign_keys = ON;", null);
//			db.delete("tb" + lClassName, "id" + lClassName + "= ? ", new String[] { Integer.toString(lId) });
//			db.rawQuery("PRAGMA foreign_keys = OFF;", null);
//			db.endTransaction(); // TODO: Looks successfully (, but don't delete!
//			// db.setTransactionSuccessful();
//		}
//		catch (Exception e) {
//			Log.i(TAG, "public boolean deleteEntity(Object pObject): Exception!");
//			e.printStackTrace();
//			db.endTransaction();
//		}

        // Debugging: setForeignKeyConstraintsEnabled approach (begin).

        try {

			// Debugging (tipxxi): ID/Code alphanumeric.
            db.delete("tb" + lClassName, "id" + lClassName + "= ? ", new String[]{lIdObject.toString()});
//            db.delete("tb" + lClassName, "id" + lClassName + "= ? ", new String[]{Integer.toString(lId)});

        }
		catch (SQLiteException e) {
		// catch (SQLiteConstraintException e) {
		// catch (Exception SQLiteConstraintException) {

			e.printStackTrace();
            // SQLiteConstraintException.printStackTrace();

			return 98;

        }
//        db.delete("tb" + lClassName, "id" + lClassName + "= ? ",
//				new String[] { Integer.toString(lId) });

        // Debugging: setForeignKeyConstraintsEnabled approach (end).

		// Debugging: SQLiteConnection object for database leaked.
		db.close();

		return 0;
		// return true;

	}

	// Debugging (tipxxi): Scrap code (begin).

	/*

	public Integer getHighestID(String pEntity) {
		// private Integer getHighestID(String pEntity) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT MAX(ID" + pEntity + ") FROM " + "tb" + pEntity, null);
		cursor.moveToFirst();
		Integer id = cursor.getInt(0);
		cursor.close();

		// Debugging: SQLiteConnection object for database leaked.
		// db.close();

		return id;

	}

	// Debugging: Page Up / Down feature (begin).

	public ArrayList<Object> getFirstOfAllEntityInstances(String pEntity) {

		Field[] lFieldList = null;
		ArrayList<Object> lArrayListObject = new ArrayList<Object>();
		// Object[] lListObject;
		// int index = 0;
		Class<?> clazz = null;
		Object lObject;
		int lColumnIndex = 0;

		SQLiteDatabase db = this.getReadableDatabase();

		try {
			clazz = Class.forName("com.bpaulo.tipxxi.entity." + pEntity);
			// clazz = Class.forName("com.bpaulo.comunicaxxilight.entity." + pEntity);
			lFieldList = clazz.getDeclaredFields();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Cursor cursor = db.rawQuery("SELECT * FROM " + "tb" + pEntity, null);
		cursor.moveToFirst();
		// lListObject = new Object[cursor.getCount()];

		while (cursor.isAfterLast() == false) {
			try {
				lObject = clazz.newInstance();
				for (int i = 0; i < lFieldList.length; i++) {
					lFieldList[i].setAccessible(true);
					lColumnIndex = cursor.getColumnIndex(lFieldList[i].getName());
					try {

						// Debugging: Adding "CAPTION" class variable (begin).

						if (!lFieldList[i].getName().equals("NUMBER_OF_ATTRIBUTES") &&
								!lFieldList[i].getName().equals("attributeParameters") &&
								!lFieldList[i].getName().equals("ONE_REGISTER") &&
								// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
								// !lFieldList[i].getName().equals("ENTITY_CAPTION")) {
								// !lFieldList[i].getName().equals("CAPTION")) {
								// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).
								!lFieldList[i].getName().equals("CREATE") &&
								!lFieldList[i].getName().equals("READ") &&
								!lFieldList[i].getName().equals("UPDATE") &&
								!lFieldList[i].getName().equals("DELETE") &&
								!lFieldList[i].getName().equals("ENTITY_CAPTION")) {
								// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

							if (lFieldList[i].getType().equals(Integer.class)) {
								lFieldList[i].set(lObject, cursor.getInt(lColumnIndex));
							}
							else if (lFieldList[i].getType().equals(Float.class)) {
								lFieldList[i].set(lObject, cursor.getFloat(lColumnIndex));
							}
							else if (lFieldList[i].getType().equals(Long.class)) {
								lFieldList[i].set(lObject, cursor.getLong(lColumnIndex));
							}
							else if (lFieldList[i].getType().equals(String.class)) {
								lFieldList[i].set(lObject, cursor.getString(lColumnIndex));
							}

						}

//						if (lFieldList[i].getType().equals(java.lang.Integer.class)) {
//							lFieldList[i].set(lObject, cursor.getInt(lColumnIndex));
//						}
//						else if (lFieldList[i].getType().equals(java.lang.Float.class)) {
//							lFieldList[i].set(lObject, cursor.getFloat(lColumnIndex));
//						}
//						else if (lFieldList[i].getType().equals(java.lang.Long.class)) {
//							lFieldList[i].set(lObject, cursor.getLong(lColumnIndex));
//						}
//						else if (lFieldList[i].getType().equals(java.lang.String.class)) {
//							lFieldList[i].set(lObject, cursor.getString(lColumnIndex));
//						}

						// Debugging: Adding "CAPTION" class variable (end).

					}
					catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
					catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				if (lArrayListObject.isEmpty()){
					lArrayListObject.add(lObject);
				}
			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			cursor.moveToNext();
		}
		cursor.close();

		// Debugging: SQLiteConnection object for database leaked.
		db.close();

		return lArrayListObject;
		// return lListObject;

	}

	// Debugging: Page Up / Down feature (end).

	*/

	// Debugging (tipxxi): Scrap code (end).

	// Debugging: Return ArrayList<Object> option.
	public ArrayList<Object> getAllEntityInstances(String pEntityName) {
	// Debugging: Return Object[] option.
	// public Object[] getAllEntityInstances(String pEntityName) {

		Field[] lFieldList = null;

		// Debugging: Return ArrayList<Object> option.
		ArrayList<Object> lArrayListObject = new ArrayList<Object>();

		// Debugging: Return Object[] option (begin).

//		Object[] lListObject;
//		int index = 0;

		// Debugging: Return Object[] option (end).
		Class<?> clazz = null;
		Object lObject;
		int lColumnIndex = 0;

		SQLiteDatabase db = this.getReadableDatabase();

		try {
			clazz = Class.forName(Utils.ENTITY_PACKAGE_NAME + pEntityName);
			lFieldList = clazz.getDeclaredFields();
		}
		catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		}

		// Sorting the data in grid (ascending ID).
		Cursor cursor = db.rawQuery("SELECT * FROM tb" + pEntityName + " ORDER BY id" + pEntityName + " ASC", null);
		// Cursor cursor = db.rawQuery("SELECT * FROM tb" + pEntityName + " ORDER BY id" + pEntityName + " DESC", null);
		cursor.moveToFirst();
		// Debugging: Return Object[] option.
//		lListObject = new Object[cursor.getCount()];

		while (cursor.isAfterLast() == false) {

			try {

				lObject = clazz.newInstance();
				for (int i = 0; i < lFieldList.length; i++) {

					lFieldList[i].setAccessible(true);
					lColumnIndex = cursor.getColumnIndex(lFieldList[i].getName());

					try {

						// Debugging (tipxxi): Refactoring entity attributes checking (begin).

						if (!isEntityAttribute(lFieldList[i].getName())) {

						/*
						if (!lFieldList[i].getName().equals("NUMBER_OF_ATTRIBUTES") &&
								!lFieldList[i].getName().equals("attributeParameters") &&
								!lFieldList[i].getName().equals("ONE_REGISTER") &&
								// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
								// !lFieldList[i].getName().equals("ENTITY_CAPTION")) {
								// !lFieldList[i].getName().equals("CAPTION")) {
								// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).
								!lFieldList[i].getName().equals("CREATE") &&
								!lFieldList[i].getName().equals("READ") &&
								!lFieldList[i].getName().equals("UPDATE") &&
								!lFieldList[i].getName().equals("DELETE") &&
								!lFieldList[i].getName().equals("ENTITY_CAPTION")) {
								// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).
						*/

						// Debugging (tipxxi): Refactoring entity attributes checking (end).

							if (lFieldList[i].getType().equals(Integer.class)) {
								lFieldList[i].set(lObject, cursor.getInt(lColumnIndex));
							}
							else if (lFieldList[i].getType().equals(Float.class)) {
								lFieldList[i].set(lObject, cursor.getFloat(lColumnIndex));
							}
							else if (lFieldList[i].getType().equals(Long.class)) {
								lFieldList[i].set(lObject, cursor.getLong(lColumnIndex));
							}
							else if (lFieldList[i].getType().equals(String.class)) {
								lFieldList[i].set(lObject, cursor.getString(lColumnIndex));
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
				// Debugging: Return ArrayList<Object> option.
				lArrayListObject.add(lObject);

				// Debugging: Return Object[] option.
//				lListObject[index++] = lObject;

			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			cursor.moveToNext();
		}
		cursor.close();

		// Debugging: SQLiteConnection object for database leaked.
		db.close();

		// Debugging: Return ArrayList<Object> option.
		return lArrayListObject;

		// Debugging: Return Object[] option.
//		return lListObject;

	}

	public static ArrayList<String> getAllEntitiesNames() {

		ArrayList<String> lArrayListEntitiesNames = new ArrayList<String>();

		// http://stackoverflow.com/questions/15446036/find-all-classes-in-a-package-in-android
		try {

			// DexFile dexFile = new DexFile(MoedeiroActivity.getAppApplicationContext().getPackageCodePath());
			DexFile dexFile = new DexFile(MenuPrincipalActivity.getAppApplicationContext().getPackageCodePath());

			// Debugging: Removing ArranqueActivity.getAppApplicationContext() (change for getApplicationContext()).
			// DexFile dexFile = new DexFile(context.getApplicationContext().getPackageCodePath());
			// DexFile dexFile = new DexFile(ArranqueActivity.getAppApplicationContext().getPackageCodePath());

			for (Enumeration<String> iter = dexFile.entries(); iter.hasMoreElements();) {
				String entityName = iter.nextElement();
				if (entityName.startsWith(Utils.ENTITY_PACKAGE_NAME)) {
					lArrayListEntitiesNames.add(entityName.replace(Utils.ENTITY_PACKAGE_NAME, ""));
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return lArrayListEntitiesNames;

	}

	// TODO: The place of this method isn't here because don't have DB operations (maybe Utils.java)!

	// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete.
	public Field[] getEntityFields(String pEntityName) {
	// public static Field[] getEntityFields(String pEntityName) {

		Field[] lFieldList = null;
		Field[] lEntityFieldsList = null;

		// Debugging: Order of fields listed in Edit screen.
		String[] lEntityFieldParameters;

		try {

			// Debugging: Order of fields listed in Edit screen.
			lFieldList = Class.forName(Utils.ENTITY_PACKAGE_NAME + pEntityName).getDeclaredFields();
			// lFieldList = Class.forName("com.bpaulo.tipxxi.entity." + pEntityName).getFields(); // lFieldList = null.

			// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete.
			// Debugging: Order of fields listed in Edit screen.
			lEntityFieldParameters = new String[(Integer)getEntityFieldAttributeValue(pEntityName, new String("NUMBER_OF_ATTRIBUTES"), null)];
			// lEntityFieldParameters = new String[lFieldList.length - 4];

			// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete.
			// Debugging: Adding "CAPTION" class variable.
			lEntityFieldsList = new Field[(Integer)getEntityFieldAttributeValue(pEntityName, new String("NUMBER_OF_ATTRIBUTES"), null)];
			// lEntityFieldsList = new Field[lFieldList.length - 4];
			// Debugging: Grid/Edit option.
			// lEntityFieldsList = new Field[lFieldList.length - 3];
			// lEntityFieldsList = new Field[lFieldList.length - 2];

			for (int i = 0; i < lFieldList.length; i++) {
			// for (int i = 0, j = 0; i < lFieldList.length; i++) {

				// Debugging (tipxxi): Refactoring entity attributes checking (begin).

				if (!isEntityAttribute(lFieldList[i].getName())) {

				/*
				// Debugging: Adding "CAPTION" class variable.
				if (!lFieldList[i].getName().equals("NUMBER_OF_ATTRIBUTES") &&
						!lFieldList[i].getName().equals("attributeParameters") &&
						!lFieldList[i].getName().equals("ONE_REGISTER") &&
						// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
						// !lFieldList[i].getName().equals("ENTITY_CAPTION")) {
						// !lFieldList[i].getName().equals("CAPTION")) {
						// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).
						!lFieldList[i].getName().equals("CREATE") &&
						!lFieldList[i].getName().equals("READ") &&
						!lFieldList[i].getName().equals("UPDATE") &&
						!lFieldList[i].getName().equals("DELETE") &&
						!lFieldList[i].getName().equals("ENTITY_CAPTION")) {
						// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).
				*/

				// Debugging (tipxxi): Refactoring entity attributes checking (end).

					// Debugging: Order of fields listed in Edit screen (begin).

					lEntityFieldParameters = (String[]) DatabaseHelper.invokeMethod(
							Utils.ENTITY_PACKAGE_NAME + pEntityName,
							"getAttributeParameters",
							lFieldList[i].getName(),
							String.class
					);

					// Setting the lEntityFieldsList index = lEntityFieldParameters[15] = attributeParameters[n][15] = "n"; // ColIndex: int.
					lEntityFieldsList[Integer.valueOf(lEntityFieldParameters[15])] = lFieldList[i];

					// lEntityFieldsList[j] = lFieldList[i];
					// j++;

					// Debugging: Order of fields listed in Edit screen (end).

				}
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return lEntityFieldsList;

	}

	// Debugging: Navigation flow control (begin).

	// TODO: The place of this method isn't here because don't have DB operations (maybe Utils.java)!

	public Field getEntityField(String pEntityName, String pEntityFieldName) {
	// public static Field getEntityField(String pEntityName, String pEntityFieldName) {

		Field lField = null;
		Field[] lFieldList;

		try {

			lFieldList = Class.forName(Utils.ENTITY_PACKAGE_NAME + pEntityName).getDeclaredFields();

			for (int i = 0; i < lFieldList.length; i++) {

				// Debugging (tipxxi): Refactoring entity attributes checking (begin).

				if (!isEntityAttribute(lFieldList[i].getName()) &&
						lFieldList[i].getName().equals(pEntityFieldName)) {
					lField = lFieldList[i];
				}

				/*
				if (!lFieldList[i].getName().equals("NUMBER_OF_ATTRIBUTES") &&
						!lFieldList[i].getName().equals("attributeParameters") &&
						!lFieldList[i].getName().equals("ONE_REGISTER") &&
						// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
						// !lFieldList[i].getName().equals("ENTITY_CAPTION") &&
						// !lFieldList[i].getName().equals("CAPTION") &&
						// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).
						!lFieldList[i].getName().equals("CREATE") &&
						!lFieldList[i].getName().equals("READ") &&
						!lFieldList[i].getName().equals("UPDATE") &&
						!lFieldList[i].getName().equals("DELETE") &&
						!lFieldList[i].getName().equals("ENTITY_CAPTION") &&
						// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).
						lFieldList[i].getName().equals(pEntityFieldName)) {

					lField = lFieldList[i];

				}
				*/

				// Debugging (tipxxi): Refactoring entity attributes checking (end).

			}

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return lField;

	}

	// TODO: The place of this method isn't here because don't have DB operations (maybe Utils.java)!

	// Debugging (tipxxi): Scrap code (begin).

	/*
	// Retrieving the value of Class's field.
	public static Object getEntityFieldValue(String pClassName, String pFieldName) {

		Class<?> clazz;
		Field field;
		Object returnObject = null;

		try {

			clazz = Class.forName(pClassName);
			field = clazz.getDeclaredField(pFieldName);
			field.setAccessible(true);
			returnObject = field.get(clazz.newInstance());

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return returnObject;

	}
	*/

	// Debugging (tipxxi): Scrap code (end).

	// Debugging: Navigation flow control (end).

	// Debugging: Grid/Edit option (begin).

	// TODO: The place of this method isn't here because don't have DB operations (maybe Utils.java)!

	// Debugging: Retrieve entity field value with any attribute.
	public Object getEntityFieldAttributeValue(String pEntityName, String pEntityFieldName, String pEntityFieldId) {
	// public static Object getEntityFieldAttributeValue(String pEntityName, String pEntityFieldName, String pEntityFieldId) {
	// public static Object getEntityFieldAttributeValue(String pEntityName, String pEntityFieldName) {
	// public static Field getEntityFieldAttributeValue(String pEntityName, String pEntityFieldName) {

		Field[] lFieldList = null;
		Field lEntityField = null;
		Class<?> clazz;
		Object object, returnObject = null;
		// Debugging: Retrieve entity field value with any attribute.
		Method method;
		// Debugging (tipxxi): ID/Code alphanumeric.
		Field lIdField;

		try {

			clazz = Class.forName(Utils.ENTITY_PACKAGE_NAME + pEntityName);
			object = clazz.newInstance();
			lFieldList = Class.forName(Utils.ENTITY_PACKAGE_NAME + pEntityName).getDeclaredFields();

			// Debugging (tipxxi): ID/Code alphanumeric.
			lIdField = clazz.getDeclaredField("id" + pEntityName);

			for (int i = 0; i < lFieldList.length; i++) {
				if (lFieldList[i].getName().equals(pEntityFieldName)) {

					// Debugging: Retrieve entity field value with any attribute (begin).

					// Checking if entity field attributes have 'static final'.
					if (lFieldList[i].toString().contains("static final")) {

						lEntityField = lFieldList[i];
						lEntityField.setAccessible(true);

//						returnObject = lEntityField.get(object);

					}
					else {

//						clazz = Class.forName("com.bpaulo.comunicaxxilight.entity." + pFieldName.substring(2, pFieldName.length()));
//						object = clazz.newInstance();

						// Debugging (tipxxi): ID/Code alphanumeric.
						method = clazz.getMethod("setId" + pEntityName, lIdField.getType());
						// method = clazz.getMethod("setId" + pEntityName, lFieldList[i].getType());
//						method = clazz.getMethod("setId" + pEntityName, Integer.class);
//						method = clazz.getMethod("setId" + pFieldName.substring(2, pFieldName.length()), Integer.class);

						// Debugging (tipxxi): ID/Code alphanumeric (begin).

						if (lIdField.getType().equals(Integer.class)) {
						// if (lFieldList[i].getType().equals(Integer.class)) {
							method.invoke(object, Integer.valueOf(pEntityFieldId));
						}
						else {
							method.invoke(object, pEntityFieldId);
						}

//						method.invoke(object, Integer.valueOf(pEntityFieldId));

						// Debugging (tipxxi): ID/Code alphanumeric (end).

						readEntity(object); // DatabaseHelper::readEntity(): getEntityFieldAttributeValue() call from DynamicGridSectionFragment::retrieveFieldValue() not used!
//						mDBHelper.readEntity(object);

						lEntityField = object.getClass().getDeclaredField(pEntityFieldName);
						lEntityField.setAccessible(true);
//						field = object.getClass().getDeclaredField("nome" + pFieldName.substring(2, pFieldName.length()));
//						field.setAccessible(true);

//						fieldValue = field.get(object).toString();

					}

					// Debugging: Retrieve entity field value with any attribute (end).

					returnObject = lEntityField.get(object);

				}
			}
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
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return returnObject;
		// return lEntityField;

	}

	// Debugging: Grid/Edit option (end).

	// TODO: The place of this method isn't here because don't have DB operations (maybe Utils.java)!

	public static Object invokeMethod(String pClassName, String pMethod, Object pParameter, Class<?> pParameterType) {

		Class<?> clazz;
		Method method;
		Object returnObject = null;

		try {

			clazz = Class.forName(pClassName);

			if (pParameter != null &&
					pParameterType != null) {

				method = clazz.getMethod(pMethod, pParameterType);
				returnObject = method.invoke(clazz.newInstance(), pParameter);

			}
			else {

				method = clazz.getMethod(pMethod, (Class[]) null);
				returnObject = method.invoke(clazz.newInstance());

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

		return returnObject;

	}

	// Debugging (tipxxi): Scrap code (begin).

	/*
	// Debugging: Transient entities (begin).
	public static boolean isDatabaseEntity(String pEntityName) {

		Class<?> clazz;

		try {
			clazz = Class.forName(Utils.ENTITY_PACKAGE_NAME + pEntityName);
		}
		catch (ClassNotFoundException e) {
			return false;
			// e.printStackTrace();
		}

		return true;

	}
	*/

	// Debugging: Transient entities (end).

	// Debugging (tipxxi): Scrap code (end).

	// Debugging (tipxxi): Refactoring entity attributes checking (begin).

	private boolean isEntityAttribute(String pFieldName) {

		if (pFieldName.equals("NUMBER_OF_ATTRIBUTES") ||
				pFieldName.equals("attributeParameters") ||
				pFieldName.equals("UNIQUE_REGISTER") ||
				pFieldName.equals("CREATE") ||
				pFieldName.equals("READ") ||
				pFieldName.equals("UPDATE") ||
				pFieldName.equals("DELETE") ||
				pFieldName.equals("ENTITY_CAPTION")) {
			return true;
		}
		else {
			return false;
		}

	}

	// Debugging (tipxxi): Refactoring entity attributes checking (end).

}