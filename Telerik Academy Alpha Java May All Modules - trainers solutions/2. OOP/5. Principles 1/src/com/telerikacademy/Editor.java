package com.telerikacademy;

public class Editor extends Staff {
    public void approveReview() {
        System.out.println("Approve review");
    }

    public void rejectReview() {
        System.out.println("Reject review");
    }

    @Override
    public void postAReview() {
        super.postAReview();
        System.out.println("Editor post a review");
    }
}
