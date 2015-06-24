package com.example.salonipracticedesign;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {

	private Context mContext;
	ArrayList<MyModel> arrayModel;
	private LayoutInflater inflater;

	public MyListAdapter(Context context, ArrayList<MyModel> arrayModel) {
		// TODO Auto-generated constructor stub
		mContext = context;
		this.arrayModel = arrayModel;
		inflater= (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayModel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrayModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rowView =inflater.inflate(R.layout.row_list_item,null);

		TextView tvFirstname = (TextView) rowView
				.findViewById(R.id.textViewName);
		tvFirstname.setText(arrayModel.get(position).getName());

		TextView tvLastName = (TextView) rowView
				.findViewById(R.id.textViewlastName);
		tvLastName.setText(arrayModel.get(position).getLastName());

		TextView tvEmail = (TextView) rowView
				.findViewById(R.id.textViewEmailId);
		tvEmail.setText(arrayModel.get(position).getMail());

		TextView tvBlood = (TextView) rowView
				.findViewById(R.id.textViewBlood);
		tvBlood.setText(arrayModel.get(position).getBlood());

		TextView tvpassword = (TextView) rowView
				.findViewById(R.id.textViewPassword);
		tvpassword.setText(arrayModel.get(position).getPassword());

		TextView tvPhoneNo = (TextView) rowView
				.findViewById(R.id.textViewphoneNo);
		tvPhoneNo.setText(arrayModel.get(position).getPhone());

		return rowView;
	}
}
