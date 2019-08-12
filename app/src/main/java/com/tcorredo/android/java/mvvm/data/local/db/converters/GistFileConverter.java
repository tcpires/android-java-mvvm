package com.tcorredo.android.java.mvvm.data.local.db.converters;

import androidx.room.TypeConverter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.tcorredo.android.java.mvvm.data.local.db.model.GistFile;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
public class GistFileConverter {
  @TypeConverter
  public final String fromGistFileToJson(@NotNull Map<String, GistFile> gistFiles) {
    Moshi moshi = new Moshi.Builder().build();
    Type map = Types.newParameterizedType(Map.class, String.class, GistFile.class);
    JsonAdapter<Map<String, GistFile>> adapter = moshi.adapter(map);
    return adapter.toJson(gistFiles);
  }

  @TypeConverter
  public final Map<String, GistFile> fromJsonToGistFile(@NotNull String gistFiles) {
    Moshi moshi = new Moshi.Builder().build();
    Type map = Types.newParameterizedType(Map.class, String.class, GistFile.class);
    JsonAdapter<Map<String, GistFile>> adapter = moshi.adapter(map);
    try {
      return adapter.fromJson(gistFiles);
    } catch (IOException e) {
      Timber.e(e);
      return new HashMap<>();
    }
  }
}
