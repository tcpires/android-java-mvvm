package com.tcorredo.android.java.mvvm.ui.repository;

import com.tcorredo.android.java.mvvm.data.model.Repository;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public interface RepositoryNavigator {

  void onSuccess();

  void handleError(Throwable throwable);

  void updateRepository(List<Repository> repositories);
}
