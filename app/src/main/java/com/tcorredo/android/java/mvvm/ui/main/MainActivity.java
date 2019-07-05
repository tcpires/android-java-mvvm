package com.tcorredo.android.java.mvvm.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.navigation.NavigationView;
import com.tcorredo.android.java.mvvm.BR;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.databinding.ActivityMainBinding;
import com.tcorredo.android.java.mvvm.databinding.NavHeaderBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseActivity;
import com.tcorredo.android.java.mvvm.ui.repository.RepositoryFragment;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
    implements MainNavigator {

  private ActivityMainBinding mActivityMainBinding;
  private MainViewModel mMainViewModel;
  private DrawerLayout mDrawer;
  private NavigationView mNavigationView;

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

  @Override public void handleError(Throwable throwable) {

  }

  private void setUp() {
    mDrawer = mActivityMainBinding.drawerView;
    Toolbar mToolbar = mActivityMainBinding.toolbar;
    mNavigationView = mActivityMainBinding.navigationView;

    mToolbar.setTitle("Github API");
    mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    setSupportActionBar(mToolbar);
    ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
        this,
        mDrawer,
        mToolbar,
        R.string.open_drawer,
        R.string.close_drawer) {
      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        hideKeyboard();
      }
    };
    mDrawer.addDrawerListener(mDrawerToggle);
    mDrawerToggle.syncState();
    setupNavMenu();
    mMainViewModel.onNavMenuCreated();
    loadFragment(RepositoryFragment.newInstance());
  }

  private void setupNavMenu() {
    NavHeaderBinding navHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(),
        R.layout.nav_header, mActivityMainBinding.navigationView, false);
    mActivityMainBinding.navigationView.addHeaderView(navHeaderBinding.getRoot());
    navHeaderBinding.setViewModel(mMainViewModel);

    mNavigationView.setNavigationItemSelectedListener(
        item -> {
          mDrawer.closeDrawer(GravityCompat.START);
          switch (item.getItemId()) {
            case R.id.nav_repositories:
              loadFragment(RepositoryFragment.newInstance());
              return true;
            default:
              return false;
          }
        });
  }

  private void loadFragment(Fragment fragment) {
    if (fragment != null) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.fragment_container, fragment)
          .commit();
    }
  }
}
