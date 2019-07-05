package com.tcorredo.android.java.mvvm.data.remote;

import com.tcorredo.android.java.mvvm.data.remote.response.RepositoryResponse;
import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public interface GithubService {

  @GET("search/repositories")
  Single<RepositoryResponse> getRepositories(@NotNull @Query("q") String query,
      @Query("sort") String sort, @Query("page") int page);
}
