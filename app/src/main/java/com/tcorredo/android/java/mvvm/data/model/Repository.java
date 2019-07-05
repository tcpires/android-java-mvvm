package com.tcorredo.android.java.mvvm.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class Repository implements Parcelable {

  public static final Creator<Repository> CREATOR = new Creator<Repository>() {
    @Override public Repository createFromParcel(Parcel source) {
      return new Repository(source);
    }

    @Override public Repository[] newArray(int size) {
      return new Repository[size];
    }
  };

  private static final Comparator<Repository> NAME =
      (o1, o2) -> o1.getName().compareTo(o2.getName());

  @Json(name = "id") private Long id;
  @Json(name = "node_id") private String nodeId;
  @Json(name = "name") private String name;
  @Json(name = "full_name") private String fullName;
  @Json(name = "private") private Boolean _private;
  @Json(name = "owner") private Owner owner;
  @Json(name = "html_url") private String htmlUrl;
  @Json(name = "description") private String description;
  @Json(name = "url") private String url;
  @Json(name = "created_at") private Date createdAt;
  @Json(name = "updated_at") private Date updatedAt;
  @Json(name = "pushed_at") private String pushedAt;
  @Json(name = "clone_url") private String cloneUrl;
  @Json(name = "homepage") private String homepage;
  @Json(name = "size") private Long size;
  @Json(name = "stargazers_count") private Long stargazersCount;
  @Json(name = "watchers_count") private Long watchersCount;
  @Json(name = "language") private String language;
  @Json(name = "has_issues") private Boolean hasIssues;
  @Json(name = "has_projects") private Boolean hasProjects;
  @Json(name = "has_downloads") private Boolean hasDownloads;
  @Json(name = "has_wiki") private Boolean hasWiki;
  @Json(name = "has_pages") private Boolean hasPages;
  @Json(name = "forks_count") private Long forksCount;
  @Json(name = "mirror_url") private String mirrorUrl;
  @Json(name = "archived") private Boolean archived;
  @Json(name = "disabled") private Boolean disabled;
  @Json(name = "open_issues_count") private Long openIssuesCount;
  @Json(name = "forks") private Long forks;
  @Json(name = "open_issues") private Long openIssues;
  @Json(name = "watchers") private Long watchers;
  @Json(name = "default_branch") private String defaultBranch;
  @Json(name = "score") private Float score;

  public Repository() {
  }

  public Repository(Long id, String nodeId, String name, String fullName, Boolean _private,
      Owner owner, String htmlUrl, String description, String url, Date createdAt,
      Date updatedAt, String pushedAt, String cloneUrl, String homepage, Long size,
      Long stargazersCount, Long watchersCount, String language, Boolean hasIssues,
      Boolean hasProjects, Boolean hasDownloads, Boolean hasWiki, Boolean hasPages,
      Long forksCount, String mirrorUrl, Boolean archived, Boolean disabled,
      Long openIssuesCount, Long forks, Long openIssues, Long watchers,
      String defaultBranch, Float score) {
    this.id = id;
    this.nodeId = nodeId;
    this.name = name;
    this.fullName = fullName;
    this._private = _private;
    this.owner = owner;
    this.htmlUrl = htmlUrl;
    this.description = description;
    this.url = url;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.pushedAt = pushedAt;
    this.cloneUrl = cloneUrl;
    this.homepage = homepage;
    this.size = size;
    this.stargazersCount = stargazersCount;
    this.watchersCount = watchersCount;
    this.language = language;
    this.hasIssues = hasIssues;
    this.hasProjects = hasProjects;
    this.hasDownloads = hasDownloads;
    this.hasWiki = hasWiki;
    this.hasPages = hasPages;
    this.forksCount = forksCount;
    this.mirrorUrl = mirrorUrl;
    this.archived = archived;
    this.disabled = disabled;
    this.openIssuesCount = openIssuesCount;
    this.forks = forks;
    this.openIssues = openIssues;
    this.watchers = watchers;
    this.defaultBranch = defaultBranch;
    this.score = score;
  }

  protected Repository(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.nodeId = in.readString();
    this.name = in.readString();
    this.fullName = in.readString();
    this._private = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.owner = in.readParcelable(Owner.class.getClassLoader());
    this.htmlUrl = in.readString();
    this.description = in.readString();
    this.url = in.readString();
    long tmpCreationDate = in.readLong();
    this.createdAt = tmpCreationDate == -1 ? null : new Date(tmpCreationDate);
    long tmpUpdateDate = in.readLong();
    this.updatedAt = tmpUpdateDate == -1 ? null : new Date(tmpUpdateDate);
    this.pushedAt = in.readString();
    this.cloneUrl = in.readString();
    this.homepage = in.readString();
    this.size = (Long) in.readValue(Long.class.getClassLoader());
    this.stargazersCount = (Long) in.readValue(Long.class.getClassLoader());
    this.watchersCount = (Long) in.readValue(Long.class.getClassLoader());
    this.language = in.readString();
    this.hasIssues = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.hasProjects = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.hasDownloads = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.hasWiki = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.hasPages = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.forksCount = (Long) in.readValue(Long.class.getClassLoader());
    this.mirrorUrl = in.readString();
    this.archived = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.disabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
    this.openIssuesCount = (Long) in.readValue(Long.class.getClassLoader());
    this.forks = (Long) in.readValue(Long.class.getClassLoader());
    this.openIssues = (Long) in.readValue(Long.class.getClassLoader());
    this.watchers = (Long) in.readValue(Long.class.getClassLoader());
    this.defaultBranch = in.readString();
    this.score = (Float) in.readValue(Float.class.getClassLoader());
  }

  public static Comparator<Repository> getNameComparator() {
    return NAME;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Boolean get_private() {
    return _private;
  }

  public void set_private(Boolean _private) {
    this._private = _private;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public String getPushedAt() {
    return pushedAt;
  }

  public void setPushedAt(String pushedAt) {
    this.pushedAt = pushedAt;
  }

  public String getCloneUrl() {
    return cloneUrl;
  }

  public void setCloneUrl(String cloneUrl) {
    this.cloneUrl = cloneUrl;
  }

  public String getHomepage() {
    return homepage;
  }

  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public Long getStargazersCount() {
    return stargazersCount;
  }

  public void setStargazersCount(Long stargazersCount) {
    this.stargazersCount = stargazersCount;
  }

  public Long getWatchersCount() {
    return watchersCount;
  }

  public void setWatchersCount(Long watchersCount) {
    this.watchersCount = watchersCount;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Boolean getHasIssues() {
    return hasIssues;
  }

  public void setHasIssues(Boolean hasIssues) {
    this.hasIssues = hasIssues;
  }

  public Boolean getHasProjects() {
    return hasProjects;
  }

  public void setHasProjects(Boolean hasProjects) {
    this.hasProjects = hasProjects;
  }

  public Boolean getHasDownloads() {
    return hasDownloads;
  }

  public void setHasDownloads(Boolean hasDownloads) {
    this.hasDownloads = hasDownloads;
  }

  public Boolean getHasWiki() {
    return hasWiki;
  }

  public void setHasWiki(Boolean hasWiki) {
    this.hasWiki = hasWiki;
  }

  public Boolean getHasPages() {
    return hasPages;
  }

  public void setHasPages(Boolean hasPages) {
    this.hasPages = hasPages;
  }

  public Long getForksCount() {
    return forksCount;
  }

  public void setForksCount(Long forksCount) {
    this.forksCount = forksCount;
  }

  public String getMirrorUrl() {
    return mirrorUrl;
  }

  public void setMirrorUrl(String mirrorUrl) {
    this.mirrorUrl = mirrorUrl;
  }

  public Boolean getArchived() {
    return archived;
  }

  public void setArchived(Boolean archived) {
    this.archived = archived;
  }

  public Boolean getDisabled() {
    return disabled;
  }

  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }

  public Long getOpenIssuesCount() {
    return openIssuesCount;
  }

  public void setOpenIssuesCount(Long openIssuesCount) {
    this.openIssuesCount = openIssuesCount;
  }

  public Long getForks() {
    return forks;
  }

  public void setForks(Long forks) {
    this.forks = forks;
  }

  public Long getOpenIssues() {
    return openIssues;
  }

  public void setOpenIssues(Long openIssues) {
    this.openIssues = openIssues;
  }

  public Long getWatchers() {
    return watchers;
  }

  public void setWatchers(Long watchers) {
    this.watchers = watchers;
  }

  public String getDefaultBranch() {
    return defaultBranch;
  }

  public void setDefaultBranch(String defaultBranch) {
    this.defaultBranch = defaultBranch;
  }

  public Float getScore() {
    return score;
  }

  public void setScore(Float score) {
    this.score = score;
  }

  @Override public String toString() {
    return "Repository{" +
        "id=" + id +
        ", nodeId='" + nodeId + '\'' +
        ", name='" + name + '\'' +
        ", fullName='" + fullName + '\'' +
        ", _private=" + _private +
        ", owner=" + owner +
        ", htmlUrl='" + htmlUrl + '\'' +
        ", description='" + description + '\'' +
        ", url='" + url + '\'' +
        ", createdAt='" + createdAt + '\'' +
        ", updatedAt='" + updatedAt + '\'' +
        ", pushedAt='" + pushedAt + '\'' +
        ", cloneUrl='" + cloneUrl + '\'' +
        ", homepage='" + homepage + '\'' +
        ", size=" + size +
        ", stargazersCount=" + stargazersCount +
        ", watchersCount=" + watchersCount +
        ", language='" + language + '\'' +
        ", hasIssues=" + hasIssues +
        ", hasProjects=" + hasProjects +
        ", hasDownloads=" + hasDownloads +
        ", hasWiki=" + hasWiki +
        ", hasPages=" + hasPages +
        ", forksCount=" + forksCount +
        ", mirrorUrl='" + mirrorUrl + '\'' +
        ", archived=" + archived +
        ", disabled=" + disabled +
        ", openIssuesCount=" + openIssuesCount +
        ", forks=" + forks +
        ", openIssues=" + openIssues +
        ", watchers=" + watchers +
        ", defaultBranch='" + defaultBranch + '\'' +
        ", score=" + score +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.nodeId);
    dest.writeString(this.name);
    dest.writeString(this.fullName);
    dest.writeValue(this._private);
    dest.writeParcelable(this.owner, flags);
    dest.writeString(this.htmlUrl);
    dest.writeString(this.description);
    dest.writeString(this.url);
    dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
    dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
    dest.writeString(this.pushedAt);
    dest.writeString(this.cloneUrl);
    dest.writeString(this.homepage);
    dest.writeValue(this.size);
    dest.writeValue(this.stargazersCount);
    dest.writeValue(this.watchersCount);
    dest.writeString(this.language);
    dest.writeValue(this.hasIssues);
    dest.writeValue(this.hasProjects);
    dest.writeValue(this.hasDownloads);
    dest.writeValue(this.hasWiki);
    dest.writeValue(this.hasPages);
    dest.writeValue(this.forksCount);
    dest.writeString(this.mirrorUrl);
    dest.writeValue(this.archived);
    dest.writeValue(this.disabled);
    dest.writeValue(this.openIssuesCount);
    dest.writeValue(this.forks);
    dest.writeValue(this.openIssues);
    dest.writeValue(this.watchers);
    dest.writeString(this.defaultBranch);
    dest.writeValue(this.score);
  }

  @Override public int describeContents() {
    return 0;
  }
}
