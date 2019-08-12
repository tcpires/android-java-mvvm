package com.tcorredo.android.java.mvvm.di.module;

import androidx.lifecycle.ViewModelProvider;
import com.tcorredo.android.java.mvvm.ViewModelFactory;
import dagger.Binds;
import dagger.Module;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class ViewModelModule {

  @Binds
  abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
