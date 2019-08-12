package com.tcorredo.android.java.mvvm.ui.login;

import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.data.AppDataManager;
import com.tcorredo.android.java.mvvm.ui.base.BaseViewModel;
import com.tcorredo.android.java.mvvm.utils.rx.SchedulerProvider;

/**
 * @author Thiago Corredo
 * @since 2019-08-11
 */
public class LoginViewModel extends BaseViewModel<LoginNavigator> {

  public LoginViewModel(AppDataManager appDataManager,
      SchedulerProvider schedulerProvider) {
    super(appDataManager, schedulerProvider);
  }

  public void login(String userName) {
    getCompositeDisposable().add(getAppDataManager().getUser(userName)
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(ownerResult -> {
          getAppDataManager().setUser(ownerResult);
          getNavigator().openMainActivity();
        }, error -> {
          getNavigator().handleError(error);
          getNavigator().showMessage("Ops...",
              "Não foi encontrado nenhum usuário com esse nome, por favor tente novamente ou se cadastre no site do github.",
              R.string.ok);
        }));
  }

  public void onLoginClick() {
    getNavigator().onLogin();
  }
}
