package com.tcorredo.android.java.mvvm.di.module;

import com.tcorredo.android.java.mvvm.ui.pull_request.PullRequestAdapter;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryAdapter;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module
public class PullRequestFactoryModule {

  @Provides
  PullRequestAdapter providePullRequestAdapter() {
    return new PullRequestAdapter(new ArrayList<>());
  }
}
