package com.tcorredo.android.java.mvvm.ui.gist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.data.local.db.model.Gist;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import java.util.List;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public class GistViewModel extends BaseViewModel<GistNavigator> {

  private final MutableLiveData<List<Gist>> gistListLiveData;

  public GistViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
    super(appDataManager, schedulerProvider);
    gistListLiveData = new MutableLiveData<>();
    getGists(0);
  }

  public void getGists(int page) {
    setIsLoading(true);
    getCompositeDisposable().add(
        getAppDataManager().getGists(page)
            .compose(throwOnHttpException())
            .map(response -> {
              getNavigator().setGithubPaging(findPaging(response.headers()));
              return response;
            })
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(response -> {
              if (response.body() != null) {
                gistListLiveData.setValue(response.body());
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

  public void refreshGistList() {
    getGists(0);
  }

  public LiveData<List<Gist>> getGistListLiveData() {
    return gistListLiveData;
  }
}
