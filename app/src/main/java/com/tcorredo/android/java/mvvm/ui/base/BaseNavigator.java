package com.tcorredo.android.java.mvvm.ui.base;

import androidx.annotation.StringRes;

/**
 * @author Thiago Corredo
 * @since 2019-08-12
 */
public interface BaseNavigator {

  void onSuccess();

  void handleError(Throwable throwable);

  void showMessage(String title, String content, @StringRes int buttonName);
}
