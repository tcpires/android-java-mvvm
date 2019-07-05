package com.tcorredo.android.java.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.tcorredo.android.java.mvvm.data.remote.GithubService;
import com.tcorredo.android.java.mvvm.ui.main.MainViewModel;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

  private GithubService service;
  private SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelFactory(GithubService service,
      SchedulerProvider schedulerProvider) {
    this.service = service;
    this.schedulerProvider = schedulerProvider;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(RepositoryViewModel.class)) {
      //noinspection unchecked
      return (T) new RepositoryViewModel(service, schedulerProvider);
    } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(service, schedulerProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}
