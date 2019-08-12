package com.tcorredo.android.java.mvvm.ui.main;

import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {
  public MainViewModel(AppDataManager appDataManager,
      SchedulerProvider schedulerProvider) {
    super(appDataManager, schedulerProvider);
  }
}
