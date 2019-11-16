package com.telerikacademy;

public class Main {

    public static void main(String[] args) {
	    ChiefEditor chiefEditor = new ChiefEditor();

	    // call method from the base class


	    // call rate with different derived types to show pure polymorphism


	    // call approve with basic type & derived type


        // use postAReview to show overriding - extend & change behaviour

        // show hidden fields & super - use id from User / Staff
    }

    public static void rate(User user) {
        user.rateBookmark();
    }

    public static void approve(Editor editor) {
        editor.approveReview();
    }
}
