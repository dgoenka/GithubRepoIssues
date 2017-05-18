package com.divyanshgoenka.omdbsearch.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.divyanshgoenka.omdbsearch.R;
import com.divyanshgoenka.omdbsearch.model.Issue;
import com.divyanshgoenka.omdbsearch.presenter.IssuesObservable;
import com.divyanshgoenka.omdbsearch.util.Validations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class SearchActivity extends AppCompatActivity {

    public static final String ARG_SEARCH_TERM = "search_term";

    EditText searchField;
    ProgressBar progressBar;
    CompositeDisposable mCompositeDisposable;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final View goButton = findViewById(R.id.go_button);
        searchField = (EditText) findViewById(R.id.search_field);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        if (savedInstanceState != null)
            searchField.setText(savedInstanceState.getString(ARG_SEARCH_TERM, ""));

        searchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchClick();
                    return true;
                }
                return false;
            }
        });

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                goButton.setEnabled(!TextUtils.isEmpty(s));
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClick();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_SEARCH_TERM, searchField.getText().toString());
    }

    public void searchClick() {
        final String searchTerm = searchField.getText().toString();
        if (!TextUtils.isEmpty(searchTerm)) {
            hideKeyboard();
            setLoadingMode();
            mCompositeDisposable = IssuesObservable.loadJSON(searchTerm, 1, this::update, this::handleError);
        }
    }

    private void handleError(Throwable throwable) {
        new AlertDialog.Builder(this).setMessage(R.string.no_result).show();
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissAndUnregister();
    }

    public void dismissAndUnregister() {
        if (progressDialog != null)
            progressDialog.dismiss();
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    private void setLoadingMode() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.searching));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchField.getWindowToken(), 0);
    }


    public void update(List<Issue> result) {
        if (!Validations.isEmptyOrNull(result)) {
            Intent intent = new Intent(this, IssuesActivity.class);
            intent.putExtra(IssuesActivity.RESULT_JSON, (ArrayList<Issue>) result);
            startActivity(intent);
        }
        dismissAndUnregister();
    }

}
