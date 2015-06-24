package com.example.salonipracticedesign;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;



public class Activity_Update extends Activity {

	DBhelper mydb;
	private EditText edUsername, edLastname, edEmail, edBG, edPass, edPhone;
	Button btn_submit, btn_loadData;
	Spinner sp1;
	Cursor c;
	ArrayList<String> list = new ArrayList<String>();
	ListView l1;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		sp1 = (Spinner) findViewById(R.id.update_spinner);
		edLastname= (EditText)findViewById(R.id.txtlastname);
		edEmail= (EditText)findViewById(R.id.txtEmailId);
		edBG= (EditText)findViewById(R.id.txtbloodGroup);
		edPass= (EditText)findViewById(R.id.txtpassword);
		edPhone= (EditText)findViewById(R.id.txtContactNumber);
		btn_submit = (Button) findViewById(R.id.updt_submit);
		btn_loadData = (Button) findViewById(R.id.Load_record);

		mydb = new DBhelper(this);
		loadSpinnerData();
		edLastname.setVisibility(View.INVISIBLE);
		edEmail.setVisibility(View.INVISIBLE);
		edBG.setVisibility(View.INVISIBLE);
		edPass.setVisibility(View.INVISIBLE);
		edPhone.setVisibility(View.INVISIBLE);
		
		btn_submit.setEnabled(false);
		btn_loadData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				c = mydb.getAllContacts();
				String getName = sp1.getSelectedItem().toString();

				c = mydb.searchContacts(getName);

				edLastname.setVisibility(View.VISIBLE);
				edEmail.setVisibility(View.VISIBLE);
				edBG.setVisibility(View.VISIBLE);
				edPass.setVisibility(View.VISIBLE);
				edPhone.setVisibility(View.VISIBLE);
				
				c.moveToFirst();
				if (c != null) {

					String getname = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_NAME));
					String getLastname = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_LASTNAME));
					String getEmail = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_EMAIL));
					String getBG = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_BG));
					String getpass = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_PWD));
					String getNumber = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_PHONE));
					edLastname.setText(getLastname);
					edEmail.setText(getEmail);
					edBG.setText(getBG);
					edPass.setText(getpass);
					edPhone.setText(getNumber);
					
					btn_submit.setEnabled(true);
				}

			}
		});

		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String getName = sp1.getSelectedItem().toString();
				
				String getLastname = edLastname.getText().toString();
				String getEmail = edEmail.getText().toString();
				String getBG = edBG.getText().toString();
				String getpass = edPass.getText().toString();
				String getNumber = edPhone.getText().toString();
				mydb.updateContacts(getName, getLastname, getEmail, getBG, getpass,getNumber);

				edLastname.setText(" ");
				edEmail.setText(" ");
				edBG.setText(" ");
				edPass.setText(" ");
				edPhone.setText(" ");
				
				
				
				Toast.makeText(getApplicationContext(),
						"Record is updated....", Toast.LENGTH_SHORT).show();
				edLastname.setVisibility(View.INVISIBLE);
				edEmail.setVisibility(View.INVISIBLE);
				edBG.setVisibility(View.INVISIBLE);
				edPass.setVisibility(View.INVISIBLE);
				edPhone.setVisibility(View.INVISIBLE);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_record, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void loadSpinnerData() {

		DBhelper db = new DBhelper(getApplicationContext());
		List<String> labels = db.getAllLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, labels);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp1.setAdapter(dataAdapter);

		if (sp1.getAdapter().getCount() == 0) {
			btn_submit.setEnabled(false);
			Toast.makeText(getApplicationContext(),
					"Sorry, No records found..", Toast.LENGTH_SHORT).show();
		}
	}
}
