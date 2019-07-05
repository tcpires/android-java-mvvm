package com.tcorredo.android.java.mvvm.ui.base;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;
import com.tcorredo.android.java.mvvm.data.remote.GithubService;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import java.lang.ref.WeakReference;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public abstract class BaseViewModel<N> extends ViewModel {

  private GithubService service;
  private SchedulerProvider mSchedulerProvider;

  private final ObservableBoolean isLoading = new ObservableBoolean();

  private CompositeDisposable mCompositeDisposable;

  private WeakReference<N> mNavigator;

  public BaseViewModel() {
  }

  public BaseViewModel(GithubService service,
      SchedulerProvider schedulerProvider) {
    this.service = service;
    this.mSchedulerProvider = schedulerProvider;
    this.mCompositeDisposable = new CompositeDisposable();
  }

  public void setService(GithubService service) {
    this.service = service;
  }

  public void setmSchedulerProvider(SchedulerProvider mSchedulerProvider) {
    this.mSchedulerProvider = mSchedulerProvider;
  }

  public GithubService getService() {
    return service;
  }

  public SchedulerProvider getSchedulerProvider() {
    return mSchedulerProvider;
  }

  public CompositeDisposable getCompositeDisposable() {
    return mCompositeDisposable;
  }

  public ObservableBoolean getIsLoading() {
    return isLoading;
  }

  public void setIsLoading(boolean isLoading) {
    this.isLoading.set(isLoading);
  }

  public N getNavigator() {
    return mNavigator.get();
  }

  public void setNavigator(N navigator) {
    this.mNavigator = new WeakReference<>(navigator);
  }

  @Override
  protected void onCleared() {
    mCompositeDisposable.dispose();
    super.onCleared();
  }
}
