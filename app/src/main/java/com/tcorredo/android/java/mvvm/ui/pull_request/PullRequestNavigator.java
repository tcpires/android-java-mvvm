package com.tcorredo.android.java.mvvm.ui.pull_request;

import com.tcorredo.android.java.mvvm.data.local.db.model.PullRequest;
import com.tcorredo.android.java.mvvm.data.remote.response.paging.GithubPaging;
import com.tcorredo.android.java.mvvm.ui.base.BaseNavigator;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public interface PullRequestNavigator extends BaseNavigator {

  void setGithubPaging(GithubPaging pageArrayList);

  void updateTotalOpenedAndClosed(List<PullRequest> pullRequests);

  void setAllowLoadMore(boolean allowLoadMore);
}
