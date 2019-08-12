package com.tcorredo.android.java.mvvm.ui.repository;

import com.tcorredo.android.java.mvvm.data.remote.response.paging.GithubPaging;
import com.tcorredo.android.java.mvvm.ui.base.BaseNavigator;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public interface RepositoryNavigator extends BaseNavigator {

  void setGithubPaging(GithubPaging pageArrayList);

  void setAllowLoadMore(boolean allowLoadMore);
}
