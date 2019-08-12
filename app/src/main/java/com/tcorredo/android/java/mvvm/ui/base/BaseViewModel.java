package com.tcorredo.android.java.mvvm.ui.base;

import android.net.Uri;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.data.remote.GithubService;
import com.tcorredo.android.java.mvvm.data.remote.response.paging.GithubPaging;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.CompositeDisposable;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Headers;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public abstract class BaseViewModel<N> extends ViewModel {

  private static final Pattern NEXT_PAGE_LINK = Pattern.compile(".*<(.+)>; rel=\"next\".*");
  private static final Pattern LAST_PAGE_LINK = Pattern.compile(".*<(.+)>; rel=\"last\".*");

  private AppDataManager appDataManager;
  private SchedulerProvider mSchedulerProvider;

  private final ObservableBoolean isLoading = new ObservableBoolean();

  private CompositeDisposable mCompositeDisposable;

  private WeakReference<N> mNavigator;

  public BaseViewModel() {
  }

  public BaseViewModel(AppDataManager appDataManager,
      SchedulerProvider schedulerProvider) {
    this.appDataManager = appDataManager;
    this.mSchedulerProvider = schedulerProvider;
    this.mCompositeDisposable = new CompositeDisposable();
  }

  public AppDataManager getAppDataManager() {
    return appDataManager;
  }

  public SchedulerProvider getSchedulerProvider() {
    return mSchedulerProvider;
  }

  public CompositeDisposable getCompositeDisposable() {
    return mCompositeDisposable;
  }

  public ObservableBoolean getIsLoading() {
    return isLoading;
  }

  public void setIsLoading(boolean isLoading) {
    this.isLoading.set(isLoading);
  }

  public N getNavigator() {
    return mNavigator.get();
  }

  public void setNavigator(N navigator) {
    this.mNavigator = new WeakReference<>(navigator);
  }

  @Override
  protected void onCleared() {
    mCompositeDisposable.dispose();
    super.onCleared();
  }

  protected static <T> SingleTransformer<Result<T>, Response<T>> throwOnHttpException() {
    return upstream -> upstream.flatMap(result -> {
      if (result.isError()) {
        if (result.error() != null) {
          return Single.error(result.error());
        }
      }

      Response<T> response = result.response();
      if (response != null && !response.isSuccessful()) {
        return Single.error(new HttpException(response));
      }

      return Single.just(Objects.requireNonNull(response));
    });
  }

  protected static GithubPaging findPaging(Headers headers) {
    int nextPage = Integer.MAX_VALUE;
    int lastPage = Integer.MIN_VALUE;

    String link = headers.get("Link");
    if (link == null) {
      return new GithubPaging(nextPage, lastPage);
    }

    Matcher nextMatcher = NEXT_PAGE_LINK.matcher(link);
    if (nextMatcher.matches()) {
      Uri nextUri = Uri.parse(nextMatcher.group(nextMatcher.groupCount()));
      String next = nextUri.getQueryParameter("page");
      nextPage = (next != null) ? Integer.parseInt(next) : nextPage;
    }

    Matcher lastMatcher = LAST_PAGE_LINK.matcher(link);
    if (lastMatcher.matches()) {
      Uri lastUri = Uri.parse(lastMatcher.group(lastMatcher.groupCount()));
      String last = lastUri.getQueryParameter("page");
      lastPage = (last != null) ? Integer.parseInt(last) : lastPage;
    }

    return new GithubPaging(nextPage, lastPage);
  }
}
