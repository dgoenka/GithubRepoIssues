package com.divyanshgoenka.omdbsearch.presenter;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by divyanshgoenka on 25/03/17.
 */

public class IssuesObservable {

    public static Observable<List<String>> paginatedThings(final Observable<Void> onNextObservable) {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<String>> emitter) throws Exception {

                onNextObservable.subscribe(new Observer<Void>() {
                    int latestPage = -1;


                    @Override
                    public void onError(Throwable e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        emitter.onComplete();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        emitter.setDisposable(d);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        latestPage++;
                        List<String> pageItems = new ArrayList<String>();
                        for (int i = 0; i < 10; i++) {
                            pageItems.add("page " + latestPage + " item " + i);
                        }
                        emitter.onNext(pageItems);
                    }
                });
            }
        });
    }
}
