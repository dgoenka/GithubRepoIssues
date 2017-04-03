package com.divyanshgoenka.omdbsearch.provider;


import android.support.annotation.NonNull;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Updatable;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by divyanshgoenka on 25/03/17.
 */

public class ResultObservable implements MutableRepository<JsonObject> {

    private static ResultObservable instance;
    private final HashMap<String, JsonObject> resultMap = new HashMap<>();
    private final HashMap<String, List<ResultUpdateable>> updateables = new HashMap<>();

    private ResultObservable() {
    }

    public static synchronized ResultObservable getInstance() {
        if (instance == null)
            instance = new ResultObservable();

        return instance;
    }

    @Override
    public void addUpdatable(@NonNull Updatable updatable) {
    }

    @Override
    public void removeUpdatable(@NonNull Updatable updatable) {
        Iterator it = updateables.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<ResultUpdateable>> pair = (Map.Entry) it.next();
            List<ResultUpdateable> resultUpdateables = pair.getValue();
            if (resultUpdateables != null)
                resultUpdateables.remove(updatable);
            it.remove(); // avoids a ConcurrentModificationException
        }
    }


    @NonNull
    @Override
    public JsonObject get() {
        return null;
    }

    public JsonObject get(final String arg, ResultUpdateable updatable) {
        JsonObject result = resultMap.get(arg.toLowerCase());
        if (result == null) {
            List<ResultUpdateable> resultUpdateables = updateables.get(arg);
            if (resultUpdateables == null) {
                resultUpdateables = new ArrayList<>();
                updateables.put(arg, resultUpdateables);
            }
            resultUpdateables.add(updatable);
            OMDbFetcher.get(arg, new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                    accept(arg, result);
                }
            });
        }
        return result;
    }

    private void accept(String arg, JsonObject result) {
        resultMap.put(arg, result);
        List<ResultUpdateable> listToNotify = updateables.get(arg);
        if (listToNotify != null) {
            for (ResultUpdateable updateable : listToNotify)
                updateable.update(result);
        }
    }

    @Override
    public void accept(@NonNull JsonObject value) {

    }
}
