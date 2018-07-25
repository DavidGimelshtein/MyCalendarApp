package com.example.david.mycalendarapp;

import android.app.Activity;
import android.content.Context;

import android.graphics.Color;
import android.graphics.Point;
import android.icu.util.Calendar;
import android.icu.util.ULocale;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

/**
 * Created by P.D.A.C. Technologies LTD.
 * 3/11/18
 *
 * @author david
 */

public class GridAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener{

	private static final String TAG = GridAdapter.class.getSimpleName();
	private LayoutInflater mInflater;
	private List<Date> monthlyDates;
	private Calendar currentDate;
	private Context context;
	//private List<EventObjects> allEvents;

	public GridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate) {
		super(context, R.layout.single_cell_layout);
		this.monthlyDates = monthlyDates;
		this.currentDate = currentDate;
		this.context = context;
		//this.allEvents = allEvents;
		mInflater = LayoutInflater.from(context);
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Date mDate = monthlyDates.get(position);
		ULocale locale = new ULocale( "@calendar=hebrew");
		Calendar hebrewCalender = Calendar.getInstance(locale);
		hebrewCalender.setTime(mDate);
		int dayValue = hebrewCalender.get(Calendar.DAY_OF_MONTH);
		int displayMonth = hebrewCalender.get(Calendar.MONTH);
		int displayYear = hebrewCalender.get(Calendar.YEAR);
		int currentMonth = currentDate.get(Calendar.MONTH);
		int currentYear = currentDate.get(Calendar.YEAR);
		View view = convertView;
		if(view == null){
			view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
		}
		Point point = new Point();
		((Activity) context).getWindowManager()
				.getDefaultDisplay().getSize(point);
		int screenHeitght = point.y;
		view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeitght/9	));
		if(displayMonth == currentMonth && displayYear == currentYear){
			view.setBackgroundColor(Color.parseColor("#FF5733"));
		}else{
			view.setBackgroundColor(Color.parseColor("#cccccc"));
		}
		//Add day to calendar
		TextView cellNumber = (TextView)view.findViewById(R.id.calendar_date_id);
		cellNumber.setText(String.valueOf(dayValue));
		//Add events to the calendar
		TextView eventIndicator = (TextView)view.findViewById(R.id.event_id);
		Calendar eventCalendar = Calendar.getInstance();
		/*for(int i = 0; i < allEvents.size(); i++){
			eventCalendar.setTime(allEvents.get(i).getDate());
			if(dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
					&& displayYear == eventCalendar.get(Calendar.YEAR)){
				eventIndicator.setBackgroundColor(Color.parseColor("#FF4081"));
			}
		}*/
		return view;
	}
	@Override
	public int getCount() {
		return monthlyDates.size();
	}
	@Nullable
	@Override
	public Object getItem(int position) {
		return monthlyDates.get(position);
	}
	@Override
	public int getPosition(Object item) {
		return monthlyDates.indexOf(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(context, "Clicked " + monthlyDates.get(position), Toast.LENGTH_LONG).show();
	}
}
