package com.tcorredo.android.java.mvvm.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.tcorredo.android.java.mvvm.data.local.db.model.Gist;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
@Dao
public interface GistDao {

  @Query("SELECT * from gist ORDER BY updated_at ASC")
  List<Gist> getAllGists();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Gist gist);

  @Query("DELETE FROM gist WHERE id = :id")
  void deleteGist(String id);
}
