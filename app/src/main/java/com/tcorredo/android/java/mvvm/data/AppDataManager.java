package com.tcorredo.android.java.mvvm.data;

import android.content.Context;
import com.tcorredo.android.java.mvvm.data.local.db.AppDatabase;
import com.tcorredo.android.java.mvvm.data.local.db.model.Gist;
import com.tcorredo.android.java.mvvm.data.local.db.model.Owner;
import com.tcorredo.android.java.mvvm.data.local.db.model.PullRequest;
import com.tcorredo.android.java.mvvm.data.local.prefs.PreferencesHelper;
import com.tcorredo.android.java.mvvm.data.remote.GithubService;
import com.tcorredo.android.java.mvvm.data.remote.response.RepositoryResponse;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import retrofit2.adapter.rxjava2.Result;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
@Singleton public class AppDataManager implements DataManager {

  private final Context context;
  private final PreferencesHelper mPreferencesHelper;
  private final AppDatabase mDatabase;
  private final GithubService service;

  @Inject public AppDataManager(Context context, PreferencesHelper preferencesHelper,
      AppDatabase mDatabase, GithubService service) {
    this.context = context;
    this.mPreferencesHelper = preferencesHelper;
    this.mDatabase = mDatabase;
    this.service = service;
  }

  @Override public List<Gist> getAllGists() {
    return mDatabase.gistDao().getAllGists();
  }

  @Override public void insert(Gist gist) {
    mDatabase.gistDao().insert(gist);
  }

  @Override public void deleteGist(String id) {
    mDatabase.gistDao().deleteGist(id);
  }

  @Override public boolean isFirstTime() {
    return mPreferencesHelper.isFirstTime();
  }

  @Override public void setFirstTime(boolean firstTime) {
    mPreferencesHelper.setFirstTime(firstTime);
  }

  @Override public Owner getUserLogged() {
    return mPreferencesHelper.getUserLogged();
  }

  @Override public void setUser(Owner user) {
    mPreferencesHelper.setUser(user);
  }

  @Override
  public Single<Result<RepositoryResponse>> getRepositories(@NotNull String query, String sort,
      int page) {
    return service.getRepositories(query, sort, page);
  }

  @Override
  public Single<Result<List<PullRequest>>> getPullRequests(String userName, String projectName,
      String state, int page) {
    return service.getPullRequests(userName, projectName, state, page);
  }

  @Override public Single<Result<List<Gist>>> getGists(int page) {
    return service.getGists(page);
  }

  @Override public Single<Owner> getUser(String userName) {
    return service.getUser(userName);
  }
}
