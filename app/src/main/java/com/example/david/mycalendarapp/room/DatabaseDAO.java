package com.example.david.mycalendarapp.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by P.D.A.C. Technologies LTD.
 * 3/28/18
 *
 * @author david
 */

@Dao
interface DatabaseDAO {
	@Query("SELECT * FROM reiyaentity")
	List<ReiyaEntity> getAll();

	@Query("SELECT * FROM user WHERE uid IN (:userIds)")
	List<ReiyaEntity> loadAllByIds(int[] userIds);

	@Query("SELECT * FROM user WHERE first_name LIKE :first AND "
			+ "last_name LIKE :last LIMIT 1")
	ReiyaEntity findByName(String first, String last);

	@Insert
	void insertAll(ReiyaEntity... users);

	@Delete
	void delete(ReiyaEntity user);
}
