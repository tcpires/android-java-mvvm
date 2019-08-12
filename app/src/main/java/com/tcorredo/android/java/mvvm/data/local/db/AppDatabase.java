package com.tcorredo.android.java.mvvm.data.local.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.tcorredo.android.java.mvvm.data.local.db.converters.DateConverter;
import com.tcorredo.android.java.mvvm.data.local.db.converters.GistFileConverter;
import com.tcorredo.android.java.mvvm.data.local.db.converters.OwnerConverter;
import com.tcorredo.android.java.mvvm.data.local.db.dao.GistDao;
import com.tcorredo.android.java.mvvm.data.local.db.model.Gist;

/**
 * @author Thiago Corredo
 * @since 2019-08-09
 */
@Database(entities = { Gist.class }, version = 1, exportSchema = false)
@TypeConverters({ OwnerConverter.class, DateConverter.class, GistFileConverter.class })
public abstract class AppDatabase extends RoomDatabase {

  private static AppDatabase INSTANCE;

  public static AppDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (AppDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              AppDatabase.class, "github_database")
              .build();
        }
      }
    }
    return INSTANCE;
  }

  public abstract GistDao gistDao();
}
