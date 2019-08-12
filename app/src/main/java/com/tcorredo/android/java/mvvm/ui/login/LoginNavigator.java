package com.tcorredo.android.java.mvvm.ui.login;

import com.tcorredo.android.java.mvvm.ui.base.BaseNavigator;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public interface LoginNavigator extends BaseNavigator {

  void onLogin();

  void openMainActivity();
}
