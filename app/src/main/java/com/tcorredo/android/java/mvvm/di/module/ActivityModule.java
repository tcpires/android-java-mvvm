package com.tcorredo.android.java.mvvm.di.module;

import com.tcorredo.android.java.mvvm.ui.main.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract MainActivity bindMainActivity();
}
