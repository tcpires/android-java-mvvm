package com.tcorredo.android.java.mvvm.ui.profile;

import androidx.databinding.ObservableField;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.data.local.db.model.Owner;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;

/**
 * @author Thiago Corredo
 * @since 2019-08-12
 */
public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {

  public ObservableField<String> imageUrl;
  public ObservableField<String> userName;
  public ObservableField<String> repositoriesNumber;
  public ObservableField<String> gistsNumber;

  public ProfileViewModel(AppDataManager appDataManager,
      SchedulerProvider schedulerProvider) {
    super(appDataManager, schedulerProvider);

    getUser();
  }

  public void getUser() {
    setIsLoading(true);
    if (getAppDataManager().getUserLogged().getId() != null) {
      Owner owner = getAppDataManager().getUserLogged();
      imageUrl = new ObservableField<>(owner.getAvatarUrl());
      userName = new ObservableField<>(owner.getLogin());
      repositoriesNumber = new ObservableField<>(owner.getPublicRepos().toString());
      gistsNumber = new ObservableField<>(owner.getPublicGists().toString());
    }
  }
}
