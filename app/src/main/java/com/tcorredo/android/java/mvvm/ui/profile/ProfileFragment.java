package com.tcorredo.android.java.mvvm.ui.profile;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.tcorredo.android.java.mvvm.BR;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.databinding.FragmentProfileBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseFragment;

/**
 * @author Thiago Corredo
 * @since 2019-08-12
 */
public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel>
    implements ProfileNavigator {

  private FragmentProfileBinding fragmentRepositoryBinding;
  private ProfileViewModel repositoryViewModel;

  public static ProfileFragment newInstance() {
    Bundle args = new Bundle();
    ProfileFragment fragment = new ProfileFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_profile;
  }

  @Override
  public ProfileViewModel getViewModel() {
    repositoryViewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
    return repositoryViewModel;
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
  }
}
