package com.divyanshgoenka.omdbsearch.presenter;


import com.divyanshgoenka.omdbsearch.model.Issue;
import com.divyanshgoenka.omdbsearch.network.RequestInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by divyanshgoenka on 25/03/17.
 */

public class IssuesObservable {

    public static final String BASE_URL = "https://api.github.com/";

    public static CompositeDisposable loadJSON(String query, int pageNumber, Consumer<List<Issue>> onNext, Consumer<Throwable> onError) {

        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);
        CompositeDisposable mCompositeDisposable = new CompositeDisposable();

        mCompositeDisposable.add(requestInterface.listIssues(query, pageNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onNext, onError));

        return mCompositeDisposable;
    }
}
