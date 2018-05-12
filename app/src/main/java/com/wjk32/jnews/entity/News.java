package com.wjk32.jnews.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wjk32 on 12/15/2017.
 */

public class News implements Serializable{

    private String status;
    private int totalResults;
    private ArrayList<Artical> articles;

    public News(String status, int totalResults, ArrayList<Artical> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Artical{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", newsList=" + articles +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<Artical> getArticlest() {
        return articles;
    }
}


