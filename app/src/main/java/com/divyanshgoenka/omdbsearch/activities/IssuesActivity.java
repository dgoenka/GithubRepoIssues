package com.divyanshgoenka.omdbsearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.divyanshgoenka.omdbsearch.R;
import com.divyanshgoenka.omdbsearch.model.Issue;
import com.divyanshgoenka.omdbsearch.model.IssueRecyclerAdapter;

import java.util.ArrayList;

public class IssuesActivity extends AppCompatActivity {

    public static final String RESULT_JSON = "RESULT_JSON";
    ImageView poster;
    RecyclerView recyclerView;
    private ArrayList<Issue> issues;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        poster = (ImageView) findViewById(R.id.poster);
        recyclerView = (RecyclerView) findViewById(R.id.detail_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        issues.clear();

        if (savedInstanceState != null) {
            issues = (ArrayList<Issue>) savedInstanceState.getSerializable(RESULT_JSON);
            setData();
        } else {
            issues = (ArrayList<Issue>) getIntent().getSerializableExtra(RESULT_JSON);
            setData();
        }

        setViews();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RESULT_JSON, issues);
    }

    public void setData() {

    }

    public void setViews() {
        recyclerView.setAdapter(new IssueRecyclerAdapter(issues));
    }
}