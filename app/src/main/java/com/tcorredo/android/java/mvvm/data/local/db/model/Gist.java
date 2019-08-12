package com.tcorredo.android.java.mvvm.data.local.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.squareup.moshi.Json;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * @author Thiago Corredo
 * @since 2019-08-09
 */
@Entity(tableName = "gist")
public class Gist implements Parcelable {

  public static final Creator<Gist> CREATOR = new Creator<Gist>() {
    @Override public Gist createFromParcel(Parcel source) {
      return new Gist(source);
    }

    @Override public Gist[] newArray(int size) {
      return new Gist[size];
    }
  };

  @Json(name = "id")
  @PrimaryKey
  @ColumnInfo(name = "id")
  @NonNull
  private String id;

  @Json(name = "html_url")
  @ColumnInfo(name = "html_url")
  private String htmlUrl;

  @Json(name = "files")
  @ColumnInfo(name = "files")
  private Map<String, GistFile> files;

  @Json(name = "created_at")
  @ColumnInfo(name = "created_at")
  private Date createdAt;

  @Json(name = "updated_at")
  @ColumnInfo(name = "updated_at")
  private Date updatedAt;

  @Json(name = "description")
  @ColumnInfo(name = "description")
  private String description;

  @Json(name = "comments")
  @ColumnInfo(name = "comments")
  private Integer comments;

  @Json(name = "owner")
  @ColumnInfo(name = "owner")
  private Owner owner;

  @Ignore
  public Gist() {
  }

  public Gist(@NotNull String id, String htmlUrl, Map<String, GistFile> files, Date createdAt,
      Date updatedAt, String description, Integer comments, Owner owner) {
    this.id = id;
    this.htmlUrl = htmlUrl;
    this.files = files;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.description = description;
    this.comments = comments;
    this.owner = owner;
  }

  protected Gist(Parcel in) {
    this.id = Objects.requireNonNull(in.readString());
    this.htmlUrl = in.readString();
    int filesSize = in.readInt();
    this.files = new HashMap<>(filesSize);
    for (int i = 0; i < filesSize; i++) {
      String key = in.readString();
      GistFile value = in.readParcelable(GistFile.class.getClassLoader());
      this.files.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
    }
    long tmpCreatedAt = in.readLong();
    this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
    long tmpUpdatedAt = in.readLong();
    this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
    this.description = in.readString();
    this.comments = (Integer) in.readValue(Integer.class.getClassLoader());
    this.owner = in.readParcelable(Owner.class.getClassLoader());
  }

  @NotNull public String getId() {
    return id;
  }

  public void setId(@NotNull String id) {
    this.id = id;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public Map<String, GistFile> getFiles() {
    return files;
  }

  public void setFiles(
      Map<String, GistFile> files) {
    this.files = files;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getComments() {
    return comments;
  }

  public void setComments(Integer comments) {
    this.comments = comments;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  @Override public String toString() {
    return "Gist{" +
        "id='" + id + '\'' +
        ", htmlUrl='" + htmlUrl + '\'' +
        ", files=" + files +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", description='" + description + '\'' +
        ", owner=" + owner +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.htmlUrl);
    dest.writeInt(this.files.size());
    for (Map.Entry<String, GistFile> entry : this.files.entrySet()) {
      dest.writeString(entry.getKey());
      dest.writeParcelable(entry.getValue(), flags);
    }
    dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
    dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
    dest.writeString(this.description);
    dest.writeValue(this.comments);
    dest.writeParcelable(this.owner, flags);
  }

  @Override public int describeContents() {
    return 0;
  }
}
