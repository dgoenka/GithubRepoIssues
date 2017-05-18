package com.divyanshgoenka.omdbsearch.network;

import com.divyanshgoenka.omdbsearch.model.Issue;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by divyanshgoenka on 17/05/17.
 */

public interface RequestInterface {
    @GET("repos/{repo}/issues?page={pageno}")
    Observable<List<Issue>> listIssues(@Path("repo") String repo, @Path("pageno") int pageNumber);
}
