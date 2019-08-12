package com.tcorredo.android.java.mvvm.ui.gist;

import com.tcorredo.android.java.mvvm.data.remote.response.paging.GithubPaging;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public interface GistNavigator {

  void onSuccess();

  void handleError(Throwable throwable);

  void setGithubPaging(GithubPaging pageArrayList);

  void setAllowLoadMore(boolean allowLoadMore);
}
