package com.example.xin.testapp;

import java.io.Serializable;

/**
 * Created by xin on 2017/2/1.
 */

public class Person implements Serializable {
    //Taken from http://blog.csdn.net/wzy_1988/article/details/35571359
    //2017.02.04 21:43
    private static final long serialVersionUID = -6470574927973900913L;
    private String name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;
    private transient String noSerializableString;

    public Person(String name, String date, String neck, String bust, String chest,
                  String waist, String hip, String inseam, String comment) {
        super();
        this.name = name;
        this.date = date;
        this.neck = neck;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String toString(){
        return "Name: " + name + "\n" + "Bust: " + bust + "\n" + "Chest: " + chest + "\n" +
                "Waist: " + waist + "\n"  + "Inseam: " + inseam + "\n" + "Neck: " + neck + "\n" +
                "Hip: " + hip + "\n" + "Date: " + date + "\n" + "Comment: " + comment;
    }

     //Taken from http://blog.csdn.net/wzy_1988/article/details/35571359
    //2017.02.04 21:43
    public String getNoSerializableString() {
        if (noSerializableString != null) {
            return noSerializableString;
        } else {
            return "";
        }
    }

    public void setNoSerializableString(String noSerializableString) {
        this.noSerializableString = noSerializableString;
    }

}
