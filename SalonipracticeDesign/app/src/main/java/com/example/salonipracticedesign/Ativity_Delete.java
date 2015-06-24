package com.example.salonipracticedesign;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Ativity_Delete extends Activity {

	DBhelper mydb;
	EditText name, number;
	Button btn_delete, btn_Reset;
	Spinner sp1;
	String getName;
	Cursor cursor;
	ArrayList<String> list = new ArrayList<String>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);

		btn_delete = (Button) findViewById(R.id.del_submit);

		mydb = new DBhelper(this);
		sp1 = (Spinner) findViewById(R.id.spinnerName);

		loadSpinnerData();

		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (sp1.getAdapter().getCount() == 0) {

					Toast.makeText(getApplicationContext(),
							"Sorry, No records found..", Toast.LENGTH_SHORT)
							.show();
					btn_delete.setEnabled(false);

				}

				else {
					String getName = sp1.getSelectedItem().toString();

					mydb.deleteContacts(getName);
					Toast.makeText(getApplicationContext(),
							"Record is deleted", Toast.LENGTH_SHORT).show();
					loadSpinnerData();
				}
			}
		});

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
			btn_delete.setEnabled(false);
			Toast.makeText(getApplicationContext(),
					"Sorry, No records found..", Toast.LENGTH_SHORT).show();
		}
	}

}