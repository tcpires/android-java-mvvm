package com.tcorredo.android.java.mvvm.utils;

import android.content.Context;
import androidx.databinding.BindingAdapter;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tcorredo.android.java.mvvm.data.model.Repository;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryAdapter;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class BindingUtils {

  public BindingUtils() {
  }

  @BindingAdapter({ "adapter" })
  public static void addRepositoryItems(RecyclerView recyclerView, List<Repository> repositories) {
    RepositoryAdapter adapter = (RepositoryAdapter) recyclerView.getAdapter();
    if (adapter != null) {
      adapter.clearItems();
      adapter.addItems(repositories);
    }
  }

  @BindingAdapter("imageUrl")
  public static void setImageUrl(ImageView imageView, String url) {
    Context context = imageView.getContext();
    GlideApp.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView);
  }
}
