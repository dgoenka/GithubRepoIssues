package com.divyanshgoenka.omdbsearch.presenter;


import com.divyanshgoenka.omdbsearch.model.Issue;

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

    public static Observable<List<Issue>> paginatedIssues(final Observable<Void> onNextObservable) {
        return Observable.create(new ObservableOnSubscribe<List<Issue>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Issue>> emitter) throws Exception {

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

                        //emitter.onNext(pageItems);
                    }
                });
            }
        });
    }
}
