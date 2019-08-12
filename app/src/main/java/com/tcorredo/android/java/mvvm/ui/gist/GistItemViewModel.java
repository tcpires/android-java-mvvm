package com.tcorredo.android.java.mvvm.ui.gist;

import android.content.Context;
import android.text.format.DateUtils;
import androidx.databinding.ObservableField;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.data.local.db.model.Gist;
import java.util.Calendar;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public class GistItemViewModel {
  public ObservableField<String> imageUrl;
  public ObservableField<String> userName;
  public ObservableField<String> gistTitle;
  public ObservableField<String> gistCreatedTime;
  public ObservableField<String> gistDescription;
  public ObservableField<String> filesNumber;
  public ObservableField<String> commentsNumber;

  private Gist gist;
  private GistItemViewModelListener listener;

  public GistItemViewModel(Context context, Gist gist, GistItemViewModelListener listener) {
    this.gist = gist;
    this.listener = listener;
    imageUrl = new ObservableField<>(gist.getOwner().getAvatarUrl());
    userName = new ObservableField<>(gist.getOwner().getLogin());

    String prefix = "";
    StringBuilder stringBuilder = new StringBuilder();
    for (String fileName : gist.getFiles().keySet()) {
      stringBuilder.append(prefix);
      prefix = ", ";
      stringBuilder.append(fileName);
    }
    gistTitle = new ObservableField<>(stringBuilder.toString());
    if (gist.getDescription() != null && !gist.getDescription().isEmpty()) {
      gistDescription = new ObservableField<>(gist.getDescription());
    } else {
      gistDescription =
          new ObservableField<>(context.getResources().getString(R.string.no_description));
    }

    String createdDateString = String.valueOf(
        DateUtils.getRelativeTimeSpanString(gist.getCreatedAt().getTime(),
            Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS));
    gistCreatedTime = new ObservableField<>(
        context.getResources().getString(R.string.gist_created, createdDateString));
    filesNumber = new ObservableField<>(String.valueOf(gist.getFiles().size()));
    commentsNumber = new ObservableField<>(gist.getComments().toString());
  }

  public void onItemClick() {
    listener.onItemClick(gist);
  }

  public interface GistItemViewModelListener {
    void onItemClick(Gist gist);
  }
}
