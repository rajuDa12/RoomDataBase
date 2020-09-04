package com.teamokcodex.roomdatabasedemo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SemisterReporsotory {

    private  SemisterDao semisterDao;
    private LiveData<List<Semister>>allsemister;

    public SemisterReporsotory(Application application)
    {
        SemisterDataBase semisterDataBase=SemisterDataBase.getdatabase(application);
        semisterDao=semisterDataBase.semisterDao();
        allsemister=semisterDao.alldatalist();
    }

    public  void Insert(Semister semister)
    {
        new InsertTask(semisterDao).execute(semister);

    }


    public  void Update(Semister semister)
    {

        new UpdateTask(semisterDao).execute(semister);

    }


    public  void delete(Semister semister)
    {

        new DeleteTask(semisterDao).execute(semister);

    }


    public  void deleteall(Semister semister)
    {

        new deleteallTask(semisterDao).execute(semister);


    }


    public LiveData<List<Semister>>alldatalist(){

        return allsemister;
    }





    private static class InsertTask extends AsyncTask<Semister,Void,Void>
    {
        private SemisterDao semisterDao;

        private InsertTask(SemisterDao semisterDao)
        {
            this.semisterDao=semisterDao;
        }

        @Override
        protected Void doInBackground(Semister... semisters) {

            semisterDao.insertdata(semisters[0]);
            return null;
        }
    }


    private static class UpdateTask extends AsyncTask<Semister,Void,Void>
    {
        private SemisterDao semisterDao;

        private UpdateTask(SemisterDao semisterDao)
        {
            this.semisterDao=semisterDao;
        }

        @Override
        protected Void doInBackground(Semister... semisters) {

            semisterDao.updatedata(semisters[0]);
            return null;
        }
    }


    private static class DeleteTask extends AsyncTask<Semister,Void,Void>
    {
        private SemisterDao semisterDao;

        private DeleteTask(SemisterDao semisterDao)
        {
            this.semisterDao=semisterDao;
        }

        @Override
        protected Void doInBackground(Semister... semisters) {

            semisterDao.deletedate(semisters[0]);
            return null;
        }
    }



    private static class deleteallTask extends AsyncTask<Semister,Void,Void>
    {
        private SemisterDao semisterDao;

        private deleteallTask(SemisterDao semisterDao)
        {
            this.semisterDao=semisterDao;
        }

        @Override
        protected Void doInBackground(Semister... semisters) {

            semisterDao.deleteall();
            return null;
        }
    }

}
