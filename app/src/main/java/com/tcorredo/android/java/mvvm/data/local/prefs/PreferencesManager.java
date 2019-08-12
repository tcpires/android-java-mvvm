package com.tcorredo.android.java.mvvm.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.squareup.moshi.Moshi;
import com.tcorredo.android.java.mvvm.data.local.db.model.Owner;
import java.io.IOException;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
@Singleton public class PreferencesManager implements PreferencesHelper {

  private static final String PREF_KEY_FIRST_TIME = "pref_key_first_time";
  private static final String PREF_KEY_USER = "pref_key_user";

  private final SharedPreferences sharedPreferences;
  private Moshi moshi;

  @Inject public PreferencesManager(Context context, String prefFileName, Moshi moshi) {
    sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    this.moshi = moshi;
  }

  @Override public boolean isFirstTime() {
    return sharedPreferences.getBoolean(PREF_KEY_FIRST_TIME, false);
  }

  @Override public void setFirstTime(boolean firstTime) {
    sharedPreferences.edit().putBoolean(PREF_KEY_FIRST_TIME, firstTime).apply();
  }

  @Override public Owner getUserLogged() {
    String userAsJson = sharedPreferences.getString(PREF_KEY_FIRST_TIME, "{}");
    try {
      return moshi.adapter(Owner.class).fromJson(Objects.requireNonNull(userAsJson));
    } catch (IOException e) {
      return new Owner();
    }
  }

  @Override public void setUser(Owner user) {
    String userAsJson = moshi.adapter(Owner.class).toJson(user);

    sharedPreferences.edit().putString(PREF_KEY_FIRST_TIME, userAsJson).apply();
  }
}
