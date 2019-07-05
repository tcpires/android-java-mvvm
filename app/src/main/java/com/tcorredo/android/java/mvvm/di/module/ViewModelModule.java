package com.tcorredo.android.java.mvvm.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.tcorredo.android.java.mvvm.ViewModelFactory;
import com.tcorredo.android.java.mvvm.ui.main.MainViewModel;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(RepositoryViewModel.class)
  abstract ViewModel bindRepositoryViewModel(RepositoryViewModel repositoryViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel.class)
  abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
