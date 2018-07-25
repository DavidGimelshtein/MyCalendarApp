package com.example.david.mycalendarapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by P.D.A.C. Technologies LTD.
 * 3/28/18
 *
 * @author david
 */

@Database(entities = {ReiyaEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	public abstract DatabaseDAO databaseDAO();
}

