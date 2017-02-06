/*
 * Copyright (c) 2017. Assignment1 CMPUT301, university of alberta. All Rights Reserved.
 * You May use, distribute or modify this code unders terms and conditions of code of student
 *    behavior at University of Alberta. But cannot be assignment solution directly
 * You can find a copy a file license in this project. otherwise please contact xhuang2@ualberta.ca
 */

package com.example.xin.testapp;

import java.io.Serializable;

/**
 * Created by xin on 2017/2/1.
 */


/**
 * This class is the Person class
 * return the user's input
 *
 * @author Xin Huang
 * @version  1.0
 * @since 1.0
 */

/**
 * get the user input and return
 */
public class Person implements Serializable {

    /** Want to send the ArrayList between activities
     * implements Serializable and set serialVersionUID and set noSerializableString
     * Taken from http://blog.csdn.net/wzy_1988/article/details/35571359
     * 2017.02.04 21:43
     */
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

    /**
     * return date
     * @return date
     */
    public String getNeck() {
        return neck;
    }

    /**
     * @param neck
     */

    public void setNeck(String neck) {
        this.neck = neck;
    }

    /**
     * @return bust
     */
    public String getBust() {
        return bust;
    }

    /**
     *
     * @param bust
     */
    public void setBust(String bust) {
        this.bust = bust;
    }

    /**
     * @return chest
     */
    public String getChest() {
        return chest;
    }

    /**
     * @param chest
     */
    public void setChest(String chest) {
        this.chest = chest;
    }

    /**
     * @return waist
     */
    public String getWaist() {
        return waist;
    }

    /**
     * @param waist
     */
    public void setWaist(String waist) {
        this.waist = waist;
    }

    /**
     * @return hip
     */
    public String getHip() {
        return hip;
    }

    /**
     * @param hip
     */
    public void setHip(String hip) {
        this.hip = hip;
    }

    /**
     * @return inseam
     */
    public String getInseam() {
        return inseam;
    }

    /**
     * @param inseam
     */
    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }


    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the string format for display to textview
     */
    public String toString(){
        return "Name: " + name + "\n" + "Bust: " + bust + "\n" + "Chest: " + chest + "\n" +
                "Waist: " + waist + "\n"  + "Inseam: " + inseam + "\n" + "Neck: " + neck + "\n" +
                "Hip: " + hip + "\n" + "Date: " + date + "\n" + "Comment: " + comment;
    }

    /**
     * Want to use bundle and intent to send ArrayList between Activities
     * getNoSerialiZableString and setNoSerializableString got from internet
     * Taken from http://blog.csdn.net/wzy_1988/article/details/35571359
     *  2017.02.04 21:43
     *
     * @return ""
     */
    public String getNoSerializableString() {
        if (noSerializableString != null) {
            return noSerializableString;
        } else {
            return "";
        }
    }

    /**
     * @param noSerializableString
     */
    public void setNoSerializableString(String noSerializableString) {
        this.noSerializableString = noSerializableString;
    }

}



