package com.tcorredo.android.java.mvvm.ui.repository;

import androidx.databinding.ObservableField;
import com.tcorredo.android.java.mvvm.data.model.Repository;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryItemViewModel {

  public ObservableField<String> title;
  public ObservableField<String> content;
  public ObservableField<String> imageUrl;
  public ObservableField<String> userName;
  public ObservableField<String> forkNumber;
  public ObservableField<String> starNumber;

  public RepositoryItemViewModel(Repository repository) {
    title = new ObservableField<>(repository.getName());
    content = new ObservableField<>(repository.getDescription());
    imageUrl = new ObservableField<>(repository.getOwner().getAvatarUrl());
    userName = new ObservableField<>(repository.getOwner().getLogin());
    forkNumber = new ObservableField<>(repository.getForks().toString());
    starNumber = new ObservableField<>(repository.getStargazersCount().toString());
  }
}
