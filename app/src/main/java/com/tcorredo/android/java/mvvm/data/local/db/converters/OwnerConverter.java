package com.tcorredo.android.java.mvvm.data.local.db.converters;

import androidx.room.TypeConverter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.tcorredo.android.java.mvvm.data.local.db.model.Owner;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
public class OwnerConverter {
  @TypeConverter
  public String fromOwnerToJson(@NotNull Owner owner) {
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<Owner> jsonAdapter = moshi.adapter(Owner.class);
    return jsonAdapter.toJson(owner);
  }

  @TypeConverter
  public Owner fromJsonToOwner(@NotNull String owner) {
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<Owner> jsonAdapter = moshi.adapter(Owner.class);
    try {
      return jsonAdapter.fromJson(owner);
    } catch (IOException e) {
      Timber.e(e);
      return new Owner();
    }
  }
}
