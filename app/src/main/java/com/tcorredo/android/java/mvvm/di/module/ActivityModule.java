package com.tcorredo.android.java.mvvm.di.module;

import com.tcorredo.android.java.mvvm.ui.login.LoginActivity;
import com.tcorredo.android.java.mvvm.ui.main.MainActivity;
import com.tcorredo.android.java.mvvm.ui.pull_request.PullRequestActivity;
import com.tcorredo.android.java.mvvm.ui.splash.SplashActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract SplashActivity bindSplashActivity();

  @ContributesAndroidInjector
  abstract LoginActivity bindLoginActivity();

  @ContributesAndroidInjector
  abstract MainActivity bindMainActivity();

  @ContributesAndroidInjector(modules = PullRequestFactoryModule.class)
  abstract PullRequestActivity bindPullRequestActivity();
}
