package com.divyanshgoenka.omdbsearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.divyanshgoenka.omdbsearch.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DetailActivity extends AppCompatActivity {

    public static final String RESULT_JSON = "RESULT_JSON";
    ImageView poster;
    TextView textView;
    String resultJsonStr;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        poster = (ImageView) findViewById(R.id.poster);
        textView = (TextView) findViewById(R.id.text);

        if (savedInstanceState != null) {
            resultJsonStr = savedInstanceState.getString(RESULT_JSON);
        } else {
            resultJsonStr = getIntent().getStringExtra(RESULT_JSON);
        }

        setData();

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
    }

    public void setData() {
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(resultJsonStr).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = result.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iterator = entrySet.iterator();
        StringBuffer resultStr = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> entry = iterator.next();
            switch (entry.getKey().toLowerCase()) {
                case ("response"): {
                    break;
                }
                case ("poster"): {
                    Picasso.with(this).load(entry.getValue().getAsString()).into(poster);
                    break;
                }
                default: {
                    if (entry.getValue() instanceof JsonPrimitive)
                        resultStr.append("<b>" + entry.getKey() + "</b><br>" + entry.getValue().getAsString() + "<br><br>");
                }
            }
        }
        textView.setText(Html.fromHtml(resultStr.toString()));
    }
}
