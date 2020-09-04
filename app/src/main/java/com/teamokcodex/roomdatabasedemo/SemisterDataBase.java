package com.teamokcodex.roomdatabasedemo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RemoteCallbackList;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Semister.class,version = 1,exportSchema = false)
public abstract class SemisterDataBase extends RoomDatabase {
    public abstract SemisterDao semisterDao();

    private static volatile SemisterDataBase INSTENCE;

    static SemisterDataBase getdatabase(Context context)
    {
        if(INSTENCE==null)
        {
            synchronized (SemisterDataBase.class)
            {
                if(INSTENCE==null)
                {
                    INSTENCE= Room.databaseBuilder(context.getApplicationContext()
                    ,SemisterDataBase.class,"bal").fallbackToDestructiveMigration()
                            .addCallback(roomcall)
                            .build();
                }
            }
        }
        return INSTENCE;

    }

    private static RoomDatabase.Callback roomcall= new RoomDatabase.Callback()
    {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            new DbTask(INSTENCE).execute();
        }
    };
    private static class DbTask extends AsyncTask<Void,Void,Void>
    {
        private SemisterDao semisterDao;

        private DbTask(SemisterDataBase dataBase)
        {
            semisterDao=dataBase.semisterDao();
        }


        @Override
        protected Void doInBackground(Void... voids)
        {

//            semisterDao.insertdata(new Semister("first semister","10","3.45"));
//            semisterDao.insertdata(new Semister("second semister","15","3.45"));
//            semisterDao.insertdata(new Semister("third semister","35","3.45"));

            return null;
        }
    }


}
