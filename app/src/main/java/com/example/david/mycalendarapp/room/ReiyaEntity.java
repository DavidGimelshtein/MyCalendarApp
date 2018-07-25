package com.example.david.mycalendarapp.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by P.D.A.C. Technologies LTD.
 * 3/28/18
 *
 * @author david
 */

@Entity
public class ReiyaEntity {

	@PrimaryKey
	private int id;
	private int year;
	private int month;
	private int day;
	private int dayOrNight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDayOrNight() {
		return dayOrNight;
	}

	public void setDayOrNight(int dayOrNight) {
		this.dayOrNight = dayOrNight;
	}
}
