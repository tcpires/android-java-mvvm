package com.tcorredo.android.java.mvvm.di.module;

import com.tcorredo.android.java.mvvm.ui.gist.GistAdapter;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public class GistFactoryModule {

  @Provides
  GistAdapter provideGistAdapter() {
    return new GistAdapter(new ArrayList<>());
  }
}
