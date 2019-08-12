package com.tcorredo.android.java.mvvm.di.module;

import com.tcorredo.android.java.mvvm.ui.gist.GistFragment;
import com.tcorredo.android.java.mvvm.ui.profile.ProfileFragment;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class FragmentModule {

  @ContributesAndroidInjector(modules = GistFactoryModule.class)
  abstract GistFragment bindGist();

  @ContributesAndroidInjector(modules = RepositoryFactoryModule.class)
  abstract RepositoryFragment bindRepository();

  @ContributesAndroidInjector()
  abstract ProfileFragment bindProfile();
}
