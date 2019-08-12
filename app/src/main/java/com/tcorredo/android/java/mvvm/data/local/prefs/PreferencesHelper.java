package com.tcorredo.android.java.mvvm.data.local.prefs;

import com.tcorredo.android.java.mvvm.data.local.db.model.Owner;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
public interface PreferencesHelper {
  boolean isFirstTime();

  void setFirstTime(boolean firstTime);

  Owner getUserLogged();

  void setUser(Owner user);
}
