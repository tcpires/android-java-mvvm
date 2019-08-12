package com.tcorredo.android.java.mvvm.ui.splash;

import android.os.Handler;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

  public SplashViewModel(AppDataManager appDataManager,
      SchedulerProvider schedulerProvider) {
    super(appDataManager, schedulerProvider);

    new Handler().postDelayed(this::decideNextActivity, 5000);
  }

  private void decideNextActivity() {
    if (getAppDataManager().getUserLogged().getId() == null) {
      getNavigator().openLoginActivity();
    } else {
      getNavigator().openMainActivity();
    }
  }
}
