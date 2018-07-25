package com.example.david.mycalendarapp;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.icu.util.HebrewCalendar;
import android.icu.util.ULocale;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//import com.inducesmile.androidcustomcalendar.database.DatabaseQuery;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by P.D.A.C. Technologies LTD.
 * 3/11/18
 *
 * @author david
 */


@RequiresApi(api = Build.VERSION_CODES.N)
public class CalendarCustomView extends LinearLayout{

	private static final String TAG = CalendarCustomView.class.getSimpleName();
	private ImageView previousButton, nextButton;
	private TextView currentDate;
	private GridView calendarGridView;
	private Button addEventButton;
	private static final int MAX_CALENDAR_COLUMN = 42;
	private int month, year;
	private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
	ULocale locale = new ULocale( "@calendar=hebrew");
	Calendar hebrewCalender = Calendar.getInstance(locale);
	private Context context;
	private GridAdapter mAdapter;
	//private DatabaseQuery mQuery;


	public CalendarCustomView(Context context) {
		super(context);
	}

	public CalendarCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initializeUILayout();
		setUpCalendarAdapter();
		setPreviousButtonClickEvent();
		setNextButtonClickEvent();
		setGridCellClickEvents();
		Log.d(TAG, "I need to call this method");
	}

	public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private void initializeUILayout(){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_calendar_layout, this);
		previousButton = view.findViewById(R.id.previous_month);
		nextButton = view.findViewById(R.id.next_month);
		currentDate = view.findViewById(R.id.display_current_date);
		addEventButton = view.findViewById(R.id.add_calendar_event);
		calendarGridView = view.findViewById(R.id.calendar_grid);
	}

	private void setPreviousButtonClickEvent(){
		previousButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hebrewCalender.add(Calendar.MONTH, -1);
				setUpCalendarAdapter();
			}
		});
	}

	private void setNextButtonClickEvent(){
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hebrewCalender.add(Calendar.MONTH, 1);
				setUpCalendarAdapter();
			}
		});
	}

	private void setGridCellClickEvents(){
		calendarGridView.setOnItemClickListener(mAdapter);
	}

	private void setUpCalendarAdapter(){
		List<Date> dayValueInCells = new ArrayList<Date>();
		//mQuery = new DatabaseQuery(context);
		//List<EventObjects> mEvents = mQuery.getAllFutureEvents();
		Calendar mCal = (HebrewCalendar)hebrewCalender.clone();
		mCal.set(Calendar.DAY_OF_MONTH, 1);
		int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
		mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
		while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
			dayValueInCells.add(mCal.getTime());
			mCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		Log.d(TAG, "Number of date " + dayValueInCells.size());
		DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL, locale);
		String sDate = dt.format(hebrewCalender.getTime());
		currentDate.setText(sDate);
		mAdapter = new GridAdapter(context, dayValueInCells, hebrewCalender);
		calendarGridView.setAdapter(mAdapter);
	}
}
