package com.telerikacademy.cache;

import com.telerikacademy.models.Bookmark;

import java.util.List;

public interface PopularBookmark {
    public Bookmark getMostPopular(Bookmark[] items);
}
