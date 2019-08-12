package com.tcorredo.android.java.mvvm.ui.gist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tcorredo.android.java.mvvm.data.local.db.model.Gist;
import com.tcorredo.android.java.mvvm.databinding.ItemGistBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewHolder;
import com.tcorredo.android.java.mvvm.ui.base.BindableAdapter;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public class GistAdapter extends RecyclerView.Adapter<BaseViewHolder> implements
    BindableAdapter<Gist> {

  private Context context;
  private List<Gist> gistList;
  private GistItemViewModel.GistItemViewModelListener listener;

  public GistAdapter() {
  }

  public GistAdapter(List<Gist> gistList) {
    this.gistList = gistList;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemGistBinding itemRepositoryBinding =
        ItemGistBinding.inflate(LayoutInflater.from(parent.getContext()),
            parent, false);
    return new GistViewHolder(context, itemRepositoryBinding);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    holder.onBind(position);
  }

  @Override public int getItemCount() {
    return gistList.size();
  }

  public void add(List<Gist> gists) {
    gistList.addAll(gists);
    notifyDataSetChanged();
  }

  public void clearItems() {
    gistList.clear();
    notifyDataSetChanged();
  }

  @Override public void setItems(List<Gist> gists) {
    gistList.addAll(gists);
    notifyDataSetChanged();
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public void setListener(
      GistItemViewModel.GistItemViewModelListener listener) {
    this.listener = listener;
  }

  public class GistViewHolder extends BaseViewHolder {

    private Context context;
    private ItemGistBinding gistBinding;
    private GistItemViewModel itemViewModel;

    public GistViewHolder(Context context, ItemGistBinding gistBinding) {
      super(gistBinding.getRoot());
      this.context = context;
      this.gistBinding = gistBinding;
    }

    @Override public void onBind(int position) {
      Gist repository = gistList.get(position);
      itemViewModel = new GistItemViewModel(context, repository, listener);
      gistBinding.setViewModel(itemViewModel);
      gistBinding.executePendingBindings();
    }
  }
}
