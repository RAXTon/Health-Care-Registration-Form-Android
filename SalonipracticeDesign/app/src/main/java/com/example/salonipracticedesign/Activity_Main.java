package com.example.salonipracticedesign;

import java.util.Calendar;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_Main extends Activity implements OnClickListener {

	private int year;
	private int month;
	private int day;
	final Calendar c = Calendar.getInstance();
	private CheckBox diabetes, BP, thyroid;
	String dis;
	String selectdieases;
	String selectcountry;
	EditText edUsername, edLastname, edEmail, edBG, edPass, edPhone;
	Button btnInsert, btnUpdate,btndelete,btndisplay;	
	DBhelper dbHelper;
	ProgressDialog pd;

	private static final Context Context = null;
	private EditText edtName;
	private EditText edtLastName;
	private EditText edtPhone;
	private EditText edtEmail;
	private EditText edtDOB;
	private EditText edtBloodGroup;
	private EditText edtpassword;
	private EditText edtcopassword;
	private Button btnSubmit;
	private Button btnCancel;
	private RadioGroup rg;
	String gender;
	private String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String phonePattern = "^[+][0-9]{10,13}$";
	Resources res;
	CalendarView calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		res = getResources();
		// String a1 = res.getString(R.string.alertusername);

		ImageView hosImage = (ImageView) findViewById(R.id.home_banner);
		hosImage.setBackgroundResource(R.drawable.animationdemo1);
		AnimationDrawable anim = (AnimationDrawable) hosImage.getBackground();
		anim.start();
		init();
		radioButton();
		btnInsert.setOnClickListener(this);
		btnUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Activity_Main.this,
						Activity_Update.class);
				startActivity(i);

			}
		});

		btndelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Activity_Main.this,
						Ativity_Delete.class);
				startActivity(i);

			}
		});
		
		/*btndisplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Activity_Main.this,
						Activity_Display.class);
				startActivity(i);

			}
		});*/
	}

	public void init() {

		edtName = (EditText) findViewById(R.id.username);
		edtLastName = (EditText) findViewById(R.id.lastname);
		edtPhone = (EditText) findViewById(R.id.ContactNumber);
		edtEmail = (EditText) findViewById(R.id.EmailId);
		edtDOB = (EditText) findViewById(R.id.BirthDate);
		edtBloodGroup = (EditText) findViewById(R.id.bloodGroup);
		edtpassword = (EditText) findViewById(R.id.password);
		edtcopassword = (EditText) findViewById(R.id.Confirmpassword);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(Activity_Main.this);
		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(Activity_Main.this);
		rg = (RadioGroup) findViewById(R.id.rggender);
		diabetes = (CheckBox) findViewById(R.id.checkbox_Diabetes);
		BP = (CheckBox) findViewById(R.id.checkbox_BP);
		thyroid = (CheckBox) findViewById(R.id.checkbox_Thyroid);
		
		edUsername = (EditText) findViewById(R.id.username);
		edLastname = (EditText) findViewById(R.id.lastname);
		edEmail = (EditText) findViewById(R.id.EmailId);
		edBG = (EditText) findViewById(R.id.bloodGroup);
		edPass = (EditText) findViewById(R.id.password);
		edPhone = (EditText) findViewById(R.id.ContactNumber);
		btnInsert = (Button) findViewById(R.id.btnSubmit);
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btndelete= (Button) findViewById(R.id.btnDelete);
		btndisplay= (Button) findViewById(R.id.btnDisplay);
		dbHelper = new DBhelper(this);


		btnUpdate.setOnClickListener(this);
		btndelete.setOnClickListener(this);
		btndisplay.setOnClickListener(this);
		pd=new ProgressDialog(Activity_Main.this);
		
		
		//------------------------Date of birth---------------------

		edtDOB.setOnClickListener(this);
		// final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		edtDOB.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(day).append("-").append(month + 1).append("-")
				.append(year).append(" "));

		
		// ---------------Spinner for country---------------
		final Spinner Country = (Spinner) findViewById(R.id.country);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.country_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		Country.setAdapter(adapter);

		Country.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				selectcountry = (String) Country.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

	}

	// ---------------------Method for datepicker---------------------
	protected Dialog onCreateDialog(int id) {

		return new DatePickerDialog(this, pickerListener, year, month, day);

	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// Show selected date
			edtDOB.setText(new StringBuilder().append(day).append("-")
					.append(month + 1).append("-").append(year).append(" "));

		}
	};

	// ----------------------Checkbox-------------------------------------------
	public void onCheckboxClicked(View view) {
		// Is the view now checked?
		boolean checked = ((CheckBox) view).isChecked();

		if (diabetes.isChecked() && BP.isChecked() && thyroid.isChecked()) {
			dis = "Diabetes, Bloodpressure and thyroid";
		}

		else if (diabetes.isChecked() && BP.isChecked()) {
			dis = "diabetes & BP";
		}

		else if (diabetes.isChecked() && thyroid.isChecked()) {
			dis = "diabetes & Thyroid";
		} else if (thyroid.isChecked() && BP.isChecked()) {
			dis = "Thyroid & BP";
		} else if (diabetes.isChecked()) {
			dis = "diabetes";
		} else if (BP.isChecked()) {
			dis = "Bloodpressure";
		}

		else if (thyroid.isChecked()) {
			dis = "Thyroid";
		}

		else {
			dis = null;
		}
	}

	public void radioButton() {
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (checkedId == R.id.radioButtonMale) {
					gender = "Male";
				} else {
					gender = "Female";
				}
			}
		});
	}

	// Onclick method

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnSubmit:
			dbHelper.insertContacts(edUsername.getText().toString(), edLastname
					.getText().toString(), edEmail.getText().toString(), edBG
					.getText().toString(), edPass.getText().toString(), edPhone
					.getText().toString());
			Toast.makeText(Activity_Main.this, "Data Inseted",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnDisplay:
			//disp();
			new demo().execute();
//		case R.id.btnDelete:
//			dbHelper.deleteContacts(edUsername.getText().toString());
//			Toast.makeText(Activity_Main.this, "Data Deleted",
//					Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.btnUpdate:
//			dbHelper.updateContacts(edUsername.getText().toString(), edLastname
//					.getText().toString(), edEmail.getText().toString(), edBG
//					.getText().toString(), edPass.getText().toString(), edPhone
//					.getText().toString());
//			Toast.makeText(Activity_Main.this, "Data Updated",
//					Toast.LENGTH_SHORT).show();
//			break;

		default:
			break;
		}
	}

	// alert box
	public void alertBox() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Context);
		alertDialogBuilder.setTitle("Submit your Data");
		alertDialogBuilder.setMessage("please click ok to save your data");
		alertDialogBuilder.setIcon(R.drawable.ic_launcher);

		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog closed
						submitData();

					}
				});

		alertDialogBuilder.show();
	}

	// alert box for cancel button
	public void cancelAlertBox() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Context);
		alertDialogBuilder.setTitle("Are you sure to cancel???");
		alertDialogBuilder.setMessage("please click oK");
		alertDialogBuilder.setIcon(R.drawable.ic_launcher);

		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						onClear(); // call onClear method
						Toast.makeText(getApplicationContext(),
								"You data is canceled", Toast.LENGTH_SHORT)
								.show();
					}
				});

		alertDialogBuilder.show();
	}

	// Submitting Data

	public void submitData() {
		if (edtName.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertusername),
					Toast.LENGTH_SHORT).show();
		} else if (edtLastName.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertlastname),
					Toast.LENGTH_SHORT).show();
		} else if (edtPhone.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertphone),
					Toast.LENGTH_SHORT).show();
		} else if (edtEmail.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertemail),
					Toast.LENGTH_SHORT).show();
		} else if (edtBloodGroup.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertbloodgroup),
					Toast.LENGTH_SHORT).show();
		} else if (!edtEmail.getText().toString().matches(emailPattern)) {
			Toast.makeText(this, "InValid Email", Toast.LENGTH_SHORT).show();

		} else if (!edtPhone.getText().toString().matches(phonePattern)) {
			Toast.makeText(this, "InValid Phone Number", Toast.LENGTH_SHORT)
					.show();
		} else if (edtDOB.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertDOB),
					Toast.LENGTH_SHORT).show();
		} else if (edtpassword.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertpassword),
					Toast.LENGTH_SHORT).show();
		} else if (edtcopassword.getText().toString().trim().length() < 1) {
			Toast.makeText(this, res.getString(R.string.alertcopassword),
					Toast.LENGTH_SHORT).show();
		} else if (!edtpassword.getText().toString()
				.equals(edtcopassword.getText().toString())) {
			Toast.makeText(this, "your Password does not match",
					Toast.LENGTH_SHORT).show();

		} else {
			Intent myIntent = new Intent(Activity_Main.this,
					Activity_Details.class);
			myIntent.putExtra("USERNAME", edtName.getText().toString());
			myIntent.putExtra("LASTNAME", edtLastName.getText().toString());
			myIntent.putExtra("PHONE", edtPhone.getText().toString());
			myIntent.putExtra("EMAILID", edtEmail.getText().toString());
			myIntent.putExtra("DOB", edtDOB.getText().toString());
			myIntent.putExtra("BLOODGROUP", edtBloodGroup.getText().toString());
			myIntent.putExtra("PASSWORD", edtBloodGroup.getText().toString());
			myIntent.putExtra("CONFIRM_PASSWORD", edtBloodGroup.getText()
					.toString());
			myIntent.putExtra("GENDER", gender);
			myIntent.putExtra("COUNTRY", selectcountry);
			myIntent.putExtra("DIEASES", dis);
			startActivity(myIntent);
			Toast.makeText(getApplicationContext(),
					"You data is successfully submited", Toast.LENGTH_SHORT)
					.show();
		}
	}

	// clear data of form

	public void onClear() {
		if (edtName != null)
			edtName.setText("");
		if (edtLastName != null)
			edtLastName.setText("");
		if (edtPhone != null)
			edtPhone.setText("");
		if (edtEmail != null)
			edtEmail.setText("");
		if (edtDOB != null)
			edtDOB.setText("");
		if (edtBloodGroup != null)
			edtBloodGroup.setText("");
		if (edtpassword != null)
			edtpassword.setText("");
		if (edtcopassword != null)
			edtcopassword.setText("");
		rg.clearCheck();// for clear radio button
	}
	public void disp() {
		// TODO Auto-generated method stub
		Intent i = new Intent(Activity_Main.this,
				Activity_Display.class);
		startActivity(i);
	}

	public class demo extends AsyncTask<Void, Void, Void>
	{
	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd.setTitle("Loading...");
			pd.setMessage("Data is Loading.....");
			pd.setCancelable(false);
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			disp();
			return null;
			
			
			
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if(pd.isShowing())
				pd.dismiss();
			super.onPostExecute(result);
			
		}
	}
	

}