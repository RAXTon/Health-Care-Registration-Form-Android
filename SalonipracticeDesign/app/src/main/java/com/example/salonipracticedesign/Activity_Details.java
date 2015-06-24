package com.example.salonipracticedesign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_Details extends Activity {
	private String name;
	private String lastName;
	private String Phone;
	private String Email;
	private String DOB;
	private String bloodgroup;
	private String gender;
	private TextView tview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_details);
		tview = (TextView) findViewById(R.id.tview);
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.GREEN);

		Intent extra = getIntent();
		if (extra != null) {
			name = extra.getStringExtra("USERNAME");
			lastName = extra.getStringExtra("LASTNAME");
			Phone = extra.getStringExtra("PHONE");
			Email = extra.getStringExtra("EMAILID");
			DOB = extra.getStringExtra("DOB");
			bloodgroup = extra.getStringExtra("BLOODGROUP");
			gender = extra.getStringExtra("GENDER");
		}
		tview.setText("Your name is " + name + " " + lastName
				+ "\nYour Phone number is" + " " + Phone
				+ "\nYour email address is " + "" + Email
				+ "\nYour Birth Date is" + "" + DOB + "\nYour BloodGroup is "
				+ " " + bloodgroup + "\nGender is " + "" + gender);

	}

}

