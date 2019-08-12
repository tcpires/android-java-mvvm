package com.tcorredo.android.java.mvvm.data.local.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

/**
 * @author Thiago Corredo
 * @since 2019-08-10
 */
public class GistFile implements Parcelable {

  public static final Creator<GistFile> CREATOR = new Creator<GistFile>() {
    @Override public GistFile createFromParcel(Parcel source) {
      return new GistFile(source);
    }

    @Override public GistFile[] newArray(int size) {
      return new GistFile[size];
    }
  };

  @Json(name = "filename")
  private String filename;

  @Json(name = "type")
  private String type;

  @Json(name = "language")
  private String language;

  @Json(name = "rawUrl")
  private String rawUrl;

  @Json(name = "size")
  private Integer size;

  public GistFile() {
  }

  public GistFile(String filename, String type, String language, String rawUrl,
      Integer size) {
    this.filename = filename;
    this.type = type;
    this.language = language;
    this.rawUrl = rawUrl;
    this.size = size;
  }

  protected GistFile(Parcel in) {
    this.filename = in.readString();
    this.type = in.readString();
    this.language = in.readString();
    this.rawUrl = in.readString();
    this.size = (Integer) in.readValue(Integer.class.getClassLoader());
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getRawUrl() {
    return rawUrl;
  }

  public void setRawUrl(String rawUrl) {
    this.rawUrl = rawUrl;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  @Override public String toString() {
    return "GistFile{" +
        "filename='" + filename + '\'' +
        ", type='" + type + '\'' +
        ", language='" + language + '\'' +
        ", rawUrl='" + rawUrl + '\'' +
        ", size=" + size +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.filename);
    dest.writeString(this.type);
    dest.writeString(this.language);
    dest.writeString(this.rawUrl);
    dest.writeValue(this.size);
  }

  @Override public int describeContents() {
    return 0;
  }
}
