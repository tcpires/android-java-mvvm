package com.tcorredo.android.java.mvvm.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tcorredo.android.java.mvvm.BR;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.databinding.ActivityMainBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseActivity;
import com.tcorredo.android.java.mvvm.ui.gist.GistFragment;
import com.tcorredo.android.java.mvvm.ui.profile.ProfileFragment;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryFragment;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
    implements MainNavigator, BottomNavigationView.OnNavigationItemSelectedListener {

  private ActivityMainBinding mActivityMainBinding;
  private MainViewModel mMainViewModel;
  private FragmentManager fragmentManager;

  public static Intent newIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  public MainViewModel getViewModel() {
    mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
    return mMainViewModel;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityMainBinding = getViewDataBinding();
    mMainViewModel.setNavigator(this);
    setUp();
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nav_repositories: {
        loadFragment(RepositoryFragment.newInstance());
        break;
      }
      case R.id.nav_gists: {
        loadFragment(GistFragment.newInstance());
        break;
      }
      case R.id.nav_profile: {
        loadFragment(ProfileFragment.newInstance());
        break;
      }
    }
    return true;
  }

  private void setUp() {
    fragmentManager = getSupportFragmentManager();

    Toolbar mToolbar = mActivityMainBinding.toolbar;

    mToolbar.setTitle("Github API");
    mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    setSupportActionBar(mToolbar);

    mActivityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(this);
    mActivityMainBinding.bottomNavigation.setSelectedItemId(R.id.nav_repositories);
  }

  private void loadFragment(Fragment fragment) {
    if (fragment != null) {
      String fragmentTag = fragment.getClass().getName();

      FragmentTransaction transaction = fragmentManager.beginTransaction();
      transaction.replace(R.id.fragment_container, fragment, fragmentTag);
      transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

      if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
        transaction.addToBackStack(fragmentTag);
      }
      transaction.commit();
    }
  }
}
