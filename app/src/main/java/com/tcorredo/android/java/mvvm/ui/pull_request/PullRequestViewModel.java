package com.tcorredo.android.java.mvvm.ui.pull_request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.data.local.db.model.PullRequest;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import java.util.List;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class PullRequestViewModel extends BaseViewModel<PullRequestNavigator> {

  private final MutableLiveData<List<PullRequest>> pullRequestListLiveData;

  public PullRequestViewModel(AppDataManager appDataManager,
      SchedulerProvider schedulerProvider) {
    super(appDataManager, schedulerProvider);
    pullRequestListLiveData = new MutableLiveData<>();
  }

  public void getPullRequests(String userName, String projectName, String state, int page) {
    setIsLoading(true);
    getCompositeDisposable().add(
        getAppDataManager().getPullRequests(userName, projectName, state, page)
            .compose(throwOnHttpException())
            .map(response -> {
              getNavigator().setGithubPaging(findPaging(response.headers()));
              return response;
            })
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(response -> {
              if (response.body() != null) {
                pullRequestListLiveData.setValue(response.body());
                getNavigator().updateTotalOpenedAndClosed(response.body());
              }
              setIsLoading(false);
              getNavigator().onSuccess();
              getNavigator().setAllowLoadMore(true);
            }, throwable -> {
              setIsLoading(false);
              Timber.e(throwable);
              getNavigator().handleError(throwable);
              getNavigator().setAllowLoadMore(true);
            }));
  }

  public LiveData<List<PullRequest>> getPullRequestListLiveData() {
    return pullRequestListLiveData;
  }
}
