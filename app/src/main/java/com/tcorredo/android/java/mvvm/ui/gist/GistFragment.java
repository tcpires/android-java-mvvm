package com.tcorredo.android.java.mvvm.ui.gist;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tcorredo.android.java.mvvm.BR;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.data.local.db.model.Gist;
import com.tcorredo.android.java.mvvm.data.remote.response.paging.GithubPaging;
import com.tcorredo.android.java.mvvm.databinding.FragmentGistBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseFragment;
import javax.inject.Inject;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public class GistFragment extends BaseFragment<FragmentGistBinding, GistViewModel>
    implements GistNavigator, GistItemViewModel.GistItemViewModelListener {

  private FragmentGistBinding fragmentRepositoryBinding;
  private GistViewModel repositoryViewModel;

  @Inject
  GistAdapter adapter;

  private GithubPaging githubPaging;
  private boolean allowLoadMore = true;

  public static GistFragment newInstance() {
    Bundle args = new Bundle();
    GistFragment fragment = new GistFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_gist;
  }

  @Override
  public GistViewModel getViewModel() {
    repositoryViewModel = ViewModelProviders.of(this, factory).get(GistViewModel.class);
    return repositoryViewModel;
  }

  @Override public void onSuccess() {
    if (fragmentRepositoryBinding.refreshList.isRefreshing()) {
      fragmentRepositoryBinding.refreshList.setRefreshing(false);
    }
  }

  @Override
  public void handleError(Throwable throwable) {
    // handle error
  }

  @Override public void setGithubPaging(GithubPaging githubPaging) {
    this.githubPaging = githubPaging;
  }

  @Override public void setAllowLoadMore(boolean allowLoadMore) {
    this.allowLoadMore = allowLoadMore;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repositoryViewModel.setNavigator(this);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    fragmentRepositoryBinding = getViewDataBinding();

    adapter.setContext(getActivity());
    adapter.setListener(this);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
    fragmentRepositoryBinding.repositoryRecyclerView.setLayoutManager(gridLayoutManager);
    fragmentRepositoryBinding.repositoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
    fragmentRepositoryBinding.repositoryRecyclerView.setAdapter(adapter);

    fragmentRepositoryBinding.repositoryRecyclerView.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (gridLayoutManager.getItemCount() - 2 <=
                gridLayoutManager.getChildCount()
                    + gridLayoutManager.findFirstVisibleItemPosition()) {
              if (githubPaging.hasMore() && allowLoadMore) {
                allowLoadMore = false;
                repositoryViewModel.getGists(githubPaging.nextPage());
              }
            }
          }
        });

    fragmentRepositoryBinding.refreshList.setOnRefreshListener(
        () -> {
          adapter.clearItems();
          repositoryViewModel.refreshGistList();
        });
  }

  @Override public void onItemClick(Gist gist) {
  }
}
