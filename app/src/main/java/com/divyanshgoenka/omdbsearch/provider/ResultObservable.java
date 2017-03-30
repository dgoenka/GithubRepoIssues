package com.divyanshgoenka.omdbsearch.provider;


import android.support.annotation.NonNull;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Updatable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by divyanshgoenka on 25/03/17.
 */

public class ResultObservable implements MutableRepository<JSONObject> {

    private static ResultObservable instance;
    private static HashMap<String, JSONObject> jsonsMap;
    private HashMap<String, JSONObject> resultMap = new HashMap<>();
    private List<Updatable> updateables = new ArrayList<>();

    private ResultObservable() {
    }

    public static synchronized ResultObservable getInstance() {
        if (instance != null)
            instance = new ResultObservable();

        return instance;
    }

    @Override
    public void addUpdatable(@NonNull Updatable updatable) {
        updateables.add(updatable);
    }

    @Override
    public void removeUpdatable(@NonNull Updatable updatable) {
        updateables.remove(updatable);
    }

    @NonNull
    @Override
    public JSONObject get() {
        return null;
    }

    public JSONObject get(String arg) {
        JSONObject result = resultMap.get(arg.toLowerCase());
        if (result == null) {
            OMDbFetcher.get(arg, new OMDbFetcher.Callback() {

            });
        }
        return result;
    }

    @Override
    public void accept(@NonNull JSONObject value) {

    }
}
