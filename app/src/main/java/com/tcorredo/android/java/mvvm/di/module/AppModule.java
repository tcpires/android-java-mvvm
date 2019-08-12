package com.tcorredo.android.java.mvvm.di.module;

import android.content.Context;
import com.squareup.moshi.Moshi;
import com.tcorredo.android.java.mvvm.MyAppAplication;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.data.DataManager;
import com.tcorredo.android.java.mvvm.data.local.db.AppDatabase;
import com.tcorredo.android.java.mvvm.data.local.prefs.PreferencesHelper;
import com.tcorredo.android.java.mvvm.data.local.prefs.PreferencesManager;
import com.tcorredo.android.java.mvvm.data.remote.GithubService;
import com.tcorredo.android.java.mvvm.data.remote.MoshiFactory;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProviderImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.tcorredo.android.java.mvvm.BuildConfig.BASE_URL;
import static com.tcorredo.android.java.mvvm.utils.Constants.PREF_NAME;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

  @Provides Context provideContext(MyAppAplication application) {
    return application;
  }

  @Provides @Singleton SchedulerProvider provideSchedulerProvider() {
    return new SchedulerProviderImpl();
  }

  @Provides @Singleton Moshi provideMoshi() {
    return MoshiFactory.get();
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    okHttpClient.connectTimeout(30000, TimeUnit.MILLISECONDS);
    okHttpClient.readTimeout(30000, TimeUnit.MILLISECONDS);
    okHttpClient.writeTimeout(30000, TimeUnit.MILLISECONDS);
    okHttpClient.addInterceptor(
        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
    return okHttpClient.build();
  }

  @Provides @Singleton Retrofit.Builder provideRetrofitBuilder(Moshi moshi,
      OkHttpClient okHttpClient, SchedulerProvider schedulerProvider) {
    Scheduler ioScheduler = schedulerProvider.io();
    return new Retrofit.Builder().addConverterFactory(
        MoshiConverterFactory.create(moshi).asLenient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(ioScheduler))
        .client(okHttpClient);
  }

  @Provides @Singleton GithubService provideGithubService(
      Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder.baseUrl(BASE_URL).build().create(GithubService.class);
  }

  @Provides
  @Singleton
  PreferencesHelper providePreferencesHelper(Context context, Moshi moshi) {
    return new PreferencesManager(context, PREF_NAME, moshi);
  }

  @Provides
  @Singleton
  AppDatabase provideAppDatabase(Context context) {
    return AppDatabase.getDatabase(context);
  }

  @Provides
  @Singleton
  DataManager provideDataManager(Context context, PreferencesHelper preferencesHelper,
      AppDatabase appDatabase, GithubService service) {
    return new AppDataManager(context, preferencesHelper, appDatabase, service);
  }
}
