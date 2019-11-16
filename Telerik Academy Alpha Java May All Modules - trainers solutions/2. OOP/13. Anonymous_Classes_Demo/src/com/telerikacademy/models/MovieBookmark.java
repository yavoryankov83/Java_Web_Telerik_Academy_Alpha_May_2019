package com.telerikacademy.models;

public class MovieBookmark extends Bookmark{
    private int releaseYear;

    public MovieBookmark(int releaseYear) {
        setReleaseYear(releaseYear);
    }

    private void setReleaseYear(int releaseYear) {
        // validate!!!!
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
