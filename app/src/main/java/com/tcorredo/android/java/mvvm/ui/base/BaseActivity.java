package com.tcorredo.android.java.mvvm.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.button.MaterialButton;
import com.tcorredo.android.java.mvvm.R;
import com.tcorredo.android.java.mvvm.ViewModelFactory;
import dagger.android.AndroidInjection;
import java.util.Objects;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends
    AppCompatActivity implements BaseNavigator {

  @Inject
  public ViewModelFactory factory;

  private T mViewDataBinding;
  private V mViewModel;

  /**
   * Override for set view model
   *
   * @return view model instance
   */
  public abstract V getViewModel();

  /**
   * Override for set binding variable
   *
   * @return variable id
   */
  public abstract int getBindingVariable();

  /**
   * @return layout resource id
   */
  public abstract @LayoutRes int getLayoutId();

  public void performDependencyInjection() {
    AndroidInjection.inject(this);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    performDependencyInjection();
    super.onCreate(savedInstanceState);
    performDataBinding();
  }

  private void performDataBinding() {
    mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
    mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
    mViewDataBinding.setLifecycleOwner(this);
    mViewDataBinding.executePendingBindings();
  }

  public T getViewDataBinding() {
    return mViewDataBinding;
  }

  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager)
          getSystemService(Context.INPUT_METHOD_SERVICE);

      if (imm != null) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      }
    }
  }

  protected void setActionbarNavigationAsBack(Toolbar toolbar) {
    if (toolbar == null) {
      return;
    }

    toolbar.setNavigationIcon(getVectorDrawable(R.drawable.ic_arrow_back_white_24dp));
    toolbar.setNavigationOnClickListener(view -> onBackPressed());
  }

  protected Drawable getVectorDrawable(int drawable) {
    return VectorDrawableCompat.create(getResources(), drawable, getTheme());
  }

  @Override public void onSuccess() {

  }

  @Override public void handleError(Throwable throwable) {
    Timber.e(throwable);
  }

  @Override public void showMessage(String title, String content, int buttonName) {
    Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.custom_dialog_message);
    dialog.setCancelable(false);
    dialog.setCanceledOnTouchOutside(false);
    Objects.requireNonNull(dialog.getWindow())
        .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

    TextView titleText = dialog.findViewById(R.id.title);
    TextView contentText = dialog.findViewById(R.id.content);
    MaterialButton buttonPositive = dialog.findViewById(R.id.button_positive);

    titleText.setText(title);
    contentText.setText(content);

    buttonPositive.setText(buttonName);
    buttonPositive.setOnClickListener(view -> dialog.dismiss());

    dialog.show();
    dialog.getWindow().setAttributes(lp);
  }
}
