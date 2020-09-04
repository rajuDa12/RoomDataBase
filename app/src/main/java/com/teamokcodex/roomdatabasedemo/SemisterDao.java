package com.teamokcodex.roomdatabasedemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SemisterDao {

    @Insert
    void insertdata(Semister semister);

    @Update
    void updatedata(Semister semister);

   @Delete
    void deletedate(Semister semister);

   @Query("SELECT *FROM SEMISTER ORDER BY id")
    LiveData<List<Semister>>alldatalist();

   @Query("DELETE FROM SEMISTER")
   void deleteall();

}
