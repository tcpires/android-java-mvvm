package com.tcorredo.android.java.mvvm.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.tcorredo.android.java.mvvm.BR;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.databinding.ActivityLoginBinding;
import com.tcorredo.android.java.mvvm.ui.base.BaseActivity;
import com.tcorredo.android.java.mvvm.ui.main.MainActivity;
import java.util.Objects;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>
    implements LoginNavigator {

  private ActivityLoginBinding activityLoginBinding;
  private LoginViewModel loginViewModel;

  public static Intent newIntent(Context context) {
    return new Intent(context, LoginActivity.class);
  }

  @Override public LoginViewModel getViewModel() {
    loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    return loginViewModel;
  }

  @Override public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override public int getLayoutId() {
    return R.layout.activity_login;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityLoginBinding = getViewDataBinding();
    loginViewModel.setNavigator(this);
  }

  @Override public void onLogin() {
    hideKeyboard();
    loginViewModel.login(
        Objects.requireNonNull(activityLoginBinding.inputUserName.getText()).toString());
  }

  @Override public void openMainActivity() {
    Intent intent = MainActivity.newIntent(this);
    startActivity(intent);
  }
}
