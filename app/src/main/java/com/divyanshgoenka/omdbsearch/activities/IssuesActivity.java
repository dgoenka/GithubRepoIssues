package com.divyanshgoenka.omdbsearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.divyanshgoenka.omdbsearch.R;
import com.divyanshgoenka.omdbsearch.model.Issue;
import com.divyanshgoenka.omdbsearch.model.IssueRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IssuesActivity extends AppCompatActivity {

    public static final String RESULT_JSON = "RESULT_JSON";
    ImageView poster;
    @BindView(R.id.issue_recycler)
    RecyclerView recyclerView;
    LinearLayoutManager llm;
    private ArrayList<Issue> issues;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issues_list);
        ButterKnife.bind(this);

        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
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

        toolbar.setNavigationOnClickListener(view -> finish());

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
