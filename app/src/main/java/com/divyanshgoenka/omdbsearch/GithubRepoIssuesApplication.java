package com.divyanshgoenka.omdbsearch;


import android.app.Application;


/**
 * Created by divyanshgoenka on 25/03/17.
 */

public class GithubRepoIssuesApplication extends Application {
    private static GithubRepoIssuesApplication instance;

    public static GithubRepoIssuesApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
