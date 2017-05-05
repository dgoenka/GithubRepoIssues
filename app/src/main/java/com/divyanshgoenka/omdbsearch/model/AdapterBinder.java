package com.divyanshgoenka.omdbsearch.model;

/**
 * Created by divyanshgoenka on 04/05/17.
 */

public interface AdapterBinder<T> {
    public void onBind(T arg);

    public void onUnbind();

}
