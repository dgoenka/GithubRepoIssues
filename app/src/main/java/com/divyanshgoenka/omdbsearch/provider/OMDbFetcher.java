package com.divyanshgoenka.omdbsearch.provider;

import com.divyanshgoenka.omdbsearch.OMDbApplication;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by divyanshgoenka on 30/03/17.
 */

class OMDbFetcher {
    public static void get(String arg, FutureCallback<JsonObject> callback) {
        Ion.with(OMDbApplication.getInstance().getApplicationContext()).load("http://www.omdbapi.com/").addQuery("t", arg).asJsonObject().setCallback(callback);
    }

}
