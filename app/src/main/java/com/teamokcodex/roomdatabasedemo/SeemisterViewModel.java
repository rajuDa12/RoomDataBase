package com.teamokcodex.roomdatabasedemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SeemisterViewModel extends AndroidViewModel {

    private SemisterReporsotory semisterReporsotory;
    private LiveData<List<Semister>> allsemisters;

    public SeemisterViewModel(@NonNull Application application)
    {
        super(application);
        semisterReporsotory=new SemisterReporsotory(application);
        allsemisters=semisterReporsotory.alldatalist();

    }
    public  void Insert(Semister semister)
    {
        semisterReporsotory.Insert(semister);
    }


    public  void Update(Semister semister)
    {
        semisterReporsotory.Update(semister);
    }

    public  void delete(Semister semister)
    {
        semisterReporsotory.delete(semister);
    }
    public  void deleteall(Semister semister)
    {
        semisterReporsotory.deleteall(semister);
    }

    public LiveData<List<Semister>>getAllsemisters()
    {
        return allsemisters;
    }

}
