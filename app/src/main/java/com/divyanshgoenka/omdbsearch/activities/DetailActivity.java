package com.divyanshgoenka.omdbsearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.divyanshgoenka.omdbsearch.R;
import com.divyanshgoenka.omdbsearch.model.Detail;
import com.divyanshgoenka.omdbsearch.model.DetailRecyclerAdapter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DetailActivity extends AppCompatActivity {

    public static final String RESULT_JSON = "RESULT_JSON";
    private static final String POSTER_URL = "POSTER_URL";
    private final ArrayList<Detail> details = new ArrayList<>();
    ImageView poster;
    RecyclerView recyclerView;
    String resultJsonStr;
    String posterUrl;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        poster = (ImageView) findViewById(R.id.poster);
        recyclerView = (RecyclerView) findViewById(R.id.detail_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        details.clear();

        if (savedInstanceState != null) {
            resultJsonStr = savedInstanceState.getString(RESULT_JSON);
            posterUrl = savedInstanceState.getString(POSTER_URL);
            setData();
        } else {
            resultJsonStr = getIntent().getStringExtra(RESULT_JSON);
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
        outState.putString(RESULT_JSON, resultJsonStr);
        outState.putString(POSTER_URL, posterUrl);
    }

    public void setData() {
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(resultJsonStr).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = result.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> entry = iterator.next();
            switch (entry.getKey().toLowerCase()) {
                case ("response"): {
                    break;
                }
                case ("poster"): {
                    posterUrl = entry.getValue().getAsString();
                    break;
                }
                default: {
                    if (entry.getValue() instanceof JsonPrimitive)
                        details.add(new Detail(entry.getKey(), entry.getValue().getAsString()));
                }
            }
        }
    }

    public void setViews() {
        Picasso.with(this).load(posterUrl).into(poster);
        recyclerView.setAdapter(new DetailRecyclerAdapter(details));
    }
}
