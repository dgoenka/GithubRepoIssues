package com.divyanshgoenka.omdbsearch.presenter;

import com.google.android.agera.Updatable;
import com.google.gson.JsonObject;

/**
 * Created by divyanshgoenka on 02/04/17.
 */

public interface ResultUpdateable extends Updatable {
    void update(JsonObject result);
}
