package com.tcorredo.android.java.mvvm.ui.repository;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tcorredo.android.java.mvvm.data.model.Repository;
import com.tcorredo.android.java.mvvm.databinding.ItemRepositoryBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewHolder;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

  private List<Repository> repositoryList;

  public RepositoryAdapter() {
  }

  public RepositoryAdapter(List<Repository> repositoryList) {
    this.repositoryList = repositoryList;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemRepositoryBinding blogViewBinding =
        ItemRepositoryBinding.inflate(LayoutInflater.from(parent.getContext()),
            parent, false);
    return new RepositoryViewHolder(blogViewBinding);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    holder.onBind(position);
  }

  @Override public int getItemCount() {
    return repositoryList.size();
  }

  public void addItems(List<Repository> blogList) {
    repositoryList.addAll(blogList);
    notifyDataSetChanged();
  }

  public void clearItems() {
    repositoryList.clear();
    notifyDataSetChanged();
  }

  public class RepositoryViewHolder extends BaseViewHolder {

    private ItemRepositoryBinding repositoryBinding;

    private RepositoryItemViewModel itemViewModel;

    public RepositoryViewHolder(ItemRepositoryBinding repositoryBinding) {
      super(repositoryBinding.getRoot());
      this.repositoryBinding = repositoryBinding;
    }

    @Override public void onBind(int position) {
      Repository repository = repositoryList.get(position);
      itemViewModel = new RepositoryItemViewModel(repository);
      repositoryBinding.setViewModel(itemViewModel);
      repositoryBinding.executePendingBindings();
    }
  }
}
