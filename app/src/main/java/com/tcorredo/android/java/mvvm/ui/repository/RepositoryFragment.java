package com.tcorredo.android.java.mvvm.ui.repository;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.tcorredo.android.java.mvvm.BR;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.data.model.Repository;
import com.tcorredo.android.java.mvvm.databinding.FragmentRepositoryBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseFragment;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryFragment extends BaseFragment<FragmentRepositoryBinding, RepositoryViewModel>
    implements RepositoryNavigator {

  private FragmentRepositoryBinding fragmentRepositoryBinding;
  private RepositoryViewModel repositoryViewModel;

  @Inject
  RepositoryAdapter adapter;

  private String query;

  public static RepositoryFragment newInstance() {
    Bundle args = new Bundle();
    RepositoryFragment fragment = new RepositoryFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_repository;
  }

  @Override
  public RepositoryViewModel getViewModel() {
    repositoryViewModel = ViewModelProviders.of(this, factory).get(RepositoryViewModel.class);
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

  @Override public void updateRepository(List<Repository> repositories) {
    adapter.addItems(repositories);
  }

  @Override public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_search, menu);

    MenuItem mSearch = menu.findItem(R.id.action_search);

    SearchView mSearchView = (SearchView) mSearch.getActionView();

    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        setQuery(query);
        repositoryViewModel.getRepositories(query, "stars", 1);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty()) {
          setQuery("");
        }
        return true;
      }
    });
  }

  @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_order_by_name:
        Collections.sort(repositoryViewModel.getRepositoryListLiveData().getValue(),
            Repository.getNameComparator());
        adapter.clearItems();
        adapter.addItems(repositoryViewModel.getRepositoryListLiveData().getValue());
        break;
      case R.id.action_order_by_stars:
        adapter.clearItems();
        repositoryViewModel.getRepositories("language:Java", "stars", 1);
        break;
    }
    return super.onOptionsItemSelected(item);
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

    fragmentRepositoryBinding.repositoryRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext()));
    fragmentRepositoryBinding.repositoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
    fragmentRepositoryBinding.repositoryRecyclerView.setAdapter(adapter);

    fragmentRepositoryBinding.refreshList.setOnRefreshListener(
        () -> {
          adapter.clearItems();
          repositoryViewModel.refreshRepositoriesList();
        });
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
