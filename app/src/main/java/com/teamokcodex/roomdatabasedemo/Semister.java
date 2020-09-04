package com.teamokcodex.roomdatabasedemo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Semister {
    @PrimaryKey(autoGenerate = true)
    int id;
    String semister_name;
    String semister_credit;
    String semister_cgpa;

    public Semister() {

    }

    public Semister(String semister_name, String semister_credit, String semister_cgpa) {
        this.semister_name = semister_name;
        this.semister_credit = semister_credit;
        this.semister_cgpa = semister_cgpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemister_name() {
        return semister_name;
    }

    public void setSemister_name(String semister_name) {
        this.semister_name = semister_name;
    }

    public String getSemister_credit() {
        return semister_credit;
    }

    public void setSemister_credit(String semister_credit) {
        this.semister_credit = semister_credit;
    }

    public String getSemister_cgpa() {
        return semister_cgpa;
    }

    public void setSemister_cgpa(String semister_cgpa) {
        this.semister_cgpa = semister_cgpa;
    }
}
