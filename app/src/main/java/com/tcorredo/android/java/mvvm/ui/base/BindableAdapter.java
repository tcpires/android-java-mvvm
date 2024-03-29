package com.tcorredo.android.java.mvvm.ui.base;

import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public interface BindableAdapter<T> {

  void setItems(List<T> items);
}
