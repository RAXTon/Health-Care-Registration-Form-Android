package com.example.salonipracticedesign;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "myApp.db";
	public static final int DATABASE_VERSION = 1;
	public static final String CONTACTS_TABLE_NAME = "registration";
	public static final String CONTACTS_COLUMN_ID = "_id";
	public static final String CONTACTS_COLUMN_NAME = "name";
	public static final String CONTACTS_COLUMN_LASTNAME = "lastname";
	public static final String CONTACTS_COLUMN_EMAIL = "email";
	public static final String CONTACTS_COLUMN_BG = "blood";
	public static final String CONTACTS_COLUMN_PWD = "password";
	public static final String CONTACTS_COLUMN_PHONE = "phone";

	// private SQLiteDatabase myDatabase;

	public DBhelper(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_TABLE_REG = "CREATE TABLE " + CONTACTS_TABLE_NAME + "("
				+ CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY,"
				+ CONTACTS_COLUMN_NAME + " VARCHAR," + CONTACTS_COLUMN_LASTNAME
				+ " VARCHAR," + CONTACTS_COLUMN_EMAIL + " VARCHAR,"
				+ CONTACTS_COLUMN_BG + " VARCHAR," + CONTACTS_COLUMN_PHONE
				+ " INTEGER," + CONTACTS_COLUMN_PWD + " VARCHAR)";
		db.execSQL(CREATE_TABLE_REG);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void insertContacts(String name, String lastname, String email,
			String blood, String password, String phone) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(CONTACTS_COLUMN_NAME, name);
		cv.put(CONTACTS_COLUMN_LASTNAME, lastname);
		cv.put(CONTACTS_COLUMN_EMAIL, email);
		cv.put(CONTACTS_COLUMN_BG, blood);
		cv.put(CONTACTS_COLUMN_PWD, password);
		cv.put(CONTACTS_COLUMN_PHONE, phone);
		db.insert(CONTACTS_TABLE_NAME, null, cv);
		db.close();

	}

	public void deleteContacts(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("registration", "name=?", new String[] { name });
	}

	public void updateContacts(String name, String lastname, String email,
			String blood, String password, String phone) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put("phone", phone);
		cv.put("lastname", lastname);
		cv.put("email", email);
		cv.put("blood", blood);
		cv.put("password", password);

		db.update(CONTACTS_TABLE_NAME, cv, "name=?", new String[] { name });
	}

	public Cursor getAllContacts() {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from registration",null);

		return res;
	}

	public Cursor getText() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select name from registration", null);
		return c;
	}

	// public String getName(String s) throws SQLException {
	// // TODO Auto-generated method stub
	// String[] columns = new
	// String[]{CONTACTS_COLUMN_ID,CONTACTS_COLUMN_NAME,CONTACTS_COLUMN_LASTNAME,CONTACTS_COLUMN_EMAIL,CONTACTS_COLUMN_BG,CONTACTS_COLUMN_PWD,CONTACTS_COLUMN_PHONE};
	// Cursor c = myDatabase.query(CONTACTS_TABLE_NAME, columns,
	// CONTACTS_COLUMN_ID + "=" + s, null, null, null, null);// change this line
	// in this method
	// if(c!=null)
	// {
	// c.moveToFirst();
	// String name = c.getString(1);
	// return name;
	// }
	// return null;
	//
	// }

	public List<String> getAllLabels() {
		List<String> list = new ArrayList<String>();
		String selectQuery = "select * from registration";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));

			} while (cursor.moveToNext());

		}
		cursor.close();
		db.close();
		return list;
	}

	public Cursor searchContacts(String name) {

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select name,phone,lastname,email,blood,password from registration where name='"
								+ name + "'", null);
		return cursor;
	}
}
