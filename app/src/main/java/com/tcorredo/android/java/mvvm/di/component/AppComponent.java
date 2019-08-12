package com.tcorredo.android.java.mvvm.di.component;

import com.tcorredo.android.java.mvvm.MyAppAplication;
import com.tcorredo.android.java.mvvm.di.module.ActivityModule;
import com.tcorredo.android.java.mvvm.di.module.AppModule;
import com.tcorredo.android.java.mvvm.di.module.FragmentModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Singleton
@Component(modules = {
    AndroidInjectionModule.class, AppModule.class, ActivityModule.class, FragmentModule.class
})
public interface AppComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(MyAppAplication application);

    AppComponent build();
  }

  void inject(MyAppAplication application);
}
