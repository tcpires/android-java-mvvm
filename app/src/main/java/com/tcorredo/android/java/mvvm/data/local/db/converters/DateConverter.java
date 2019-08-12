package com.tcorredo.android.java.mvvm.data.local.db.converters;

import androidx.room.TypeConverter;
import java.util.Date;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
public class DateConverter {
  @TypeConverter
  public Date fromTimestamp(Long value) {
    return value == null ? null : new Date(value);
  }

  @TypeConverter
  public static Long dateToTimestamp(Date value) {
    return value == null ? null : value.getTime();
  }
}
