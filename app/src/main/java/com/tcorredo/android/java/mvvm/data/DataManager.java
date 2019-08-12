package com.tcorredo.android.java.mvvm.data;

import com.tcorredo.android.java.mvvm.data.local.db.DbHelper;
import com.tcorredo.android.java.mvvm.data.local.prefs.PreferencesHelper;
import com.tcorredo.android.java.mvvm.data.remote.GithubService;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
public interface DataManager extends PreferencesHelper, DbHelper, GithubService {
}
