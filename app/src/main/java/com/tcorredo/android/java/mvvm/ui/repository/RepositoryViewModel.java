package com.tcorredo.android.java.mvvm.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.tcorredo.android.java.mvvm.data.model.Repository;
import com.tcorredo.android.java.mvvm.data.remote.GithubService;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import java.util.List;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryViewModel extends BaseViewModel<RepositoryNavigator> {

  private final MutableLiveData<List<Repository>> repositoryListLiveData;

  public RepositoryViewModel(GithubService service,
      SchedulerProvider schedulerProvider) {
    super(service, schedulerProvider);
    repositoryListLiveData = new MutableLiveData<>();
    getRepositories("language:Java", "stars", 1);
  }

  public void getRepositories(String query, String sort, int page) {
    setIsLoading(true);
    getCompositeDisposable().add(
        getService().getRepositories(query, sort, page)
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(repositoryResponse -> {
              if (repositoryResponse != null && repositoryResponse.getItems() != null) {
                repositoryListLiveData.setValue(repositoryResponse.getItems());
              }
              setIsLoading(false);
              getNavigator().onSuccess();
            }, throwable -> {
              setIsLoading(false);
              Timber.e(throwable);
              getNavigator().handleError(throwable);
            }));
  }

  public void refreshRepositoriesList() {
    getRepositories("language:Java", "stars", 1);
  }

  public LiveData<List<Repository>> getRepositoryListLiveData() {
    return repositoryListLiveData;
  }
}
