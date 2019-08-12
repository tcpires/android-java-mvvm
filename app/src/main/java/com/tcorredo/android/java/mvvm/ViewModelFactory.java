package com.tcorredo.android.java.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.ui.gist.GistViewModel;
import com.tcorredo.android.java.mvvm.ui.login.LoginViewModel;
import com.tcorredo.android.java.mvvm.ui.main.MainViewModel;
import com.tcorredo.android.java.mvvm.ui.profile.ProfileViewModel;
import com.tcorredo.android.java.mvvm.ui.pull_request.PullRequestViewModel;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryViewModel;
import com.tcorredo.android.java.mvvm.ui.splash.SplashViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

  private AppDataManager appDataManager;
  private SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelFactory(AppDataManager appDataManager,
      SchedulerProvider schedulerProvider) {
    this.appDataManager = appDataManager;
    this.schedulerProvider = schedulerProvider;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
      //noinspection unchecked
      return (T) new ProfileViewModel(appDataManager, schedulerProvider);
    }if (modelClass.isAssignableFrom(GistViewModel.class)) {
      //noinspection unchecked
      return (T) new GistViewModel(appDataManager, schedulerProvider);
    } else if (modelClass.isAssignableFrom(RepositoryViewModel.class)) {
      //noinspection unchecked
      return (T) new RepositoryViewModel(appDataManager, schedulerProvider);
    } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(appDataManager, schedulerProvider);
    } else if (modelClass.isAssignableFrom(PullRequestViewModel.class)) {
      //noinspection unchecked
      return (T) new PullRequestViewModel(appDataManager, schedulerProvider);
    } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
      //noinspection unchecked
      return (T) new LoginViewModel(appDataManager, schedulerProvider);
    } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
      //noinspection unchecked
      return (T) new SplashViewModel(appDataManager, schedulerProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}
