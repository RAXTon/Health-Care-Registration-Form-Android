package com.example.salonipracticedesign;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Activity_Display extends Activity {
	private ListView obj;
	DBhelper mydb;
	Cursor c;
	ProgressDialog pd;
	ArrayList<String> array_list = new ArrayList<String>();

	private ListView lv;

	private ArrayList<MyModel> arrayModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		obj = (ListView) findViewById(R.id.lstview);
		arrayModel = new ArrayList<MyModel>();
		mydb = new DBhelper(this);
		/*
		 * c = mydb.getAllCotacts(); c.moveToFirst(); if (c != null) { do {
		 * 
		 * String getFname = c.getString(c
		 * .getColumnIndex(DBhelper.CONTACT_FNAME)); String getLname =
		 * c.getString(c .getColumnIndex(DBhelper.CONTACT_LNAME)); String
		 * getEmail = c.getString(c .getColumnIndex(DBhelper.CONTACT_EMAIL));
		 * String getPhone = c.getString(c
		 * .getColumnIndex(DBhelper.CONTACT_PHONE)); String getPwd =
		 * c.getString(c .getColumnIndex(DBhelper.CONTACT_PWD)); String
		 * getRecord = getFname + " " + getLname + " " +getEmail + " " +getPhone
		 * + " " +getPwd; array_list.add(getRecord); } while (c.moveToNext()); }
		 */

		/*
		 * ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_single_choice, array_list);
		 * 
		 * // adding it to the list view. obj.setAdapter(arrayAdapter);
		 * 
		 * obj.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { // TODO Auto-generated method stub
		 * 
		 * Toast.makeText(getApplicationContext(), " " +
		 * obj.getSelectedItemPosition(), Toast.LENGTH_SHORT);
		 * 
		 * } });
		 */

		c = mydb.getAllContacts();
		c.moveToFirst();
		if (c != null) {
			do {
				MyModel dModel = new MyModel();

				dModel.setName(c.getString(c
						.getColumnIndex(DBhelper. CONTACTS_COLUMN_NAME )));

				dModel.setLastName(c.getString(c
						.getColumnIndex(DBhelper.CONTACTS_COLUMN_LASTNAME)));

				dModel.setMail(c.getString(c
						.getColumnIndex(DBhelper.CONTACTS_COLUMN_EMAIL)));
				dModel.setBlood(c.getString(c
						.getColumnIndex(DBhelper.CONTACTS_COLUMN_BG)));
				dModel.setPassword(c.getString(c
						.getColumnIndex(DBhelper.CONTACTS_COLUMN_PWD)));

				dModel.setPhone(c.getString(c
						.getColumnIndex(DBhelper.CONTACTS_COLUMN_PHONE)));

				arrayModel.add(dModel);
			} while (c.moveToNext());
		}

		lv = (ListView) findViewById(R.id.lstview);
		MyListAdapter adapter = new MyListAdapter(this, arrayModel);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Toast.makeText(Activity_Display.this,
						"Row Item Clicked! on Position = " + position,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}