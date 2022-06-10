// Generated by view binder compiler. Do not edit!
package com.macas.nmsc.sict.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.macas.nmsc.sict.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class YourOrgsBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final FloatingActionButton actionFab;

  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final BottomAppBar bottomAppBar;

  @NonNull
  public final CoordinatorLayout coordinator;

  @NonNull
  public final DrawerLayout drawer;

  @NonNull
  public final LottieAnimationView lottie1;

  @NonNull
  public final ImageButton menuBtn;

  @NonNull
  public final LinearLayout missingLayout;

  @NonNull
  public final NavigationView navigation;

  @NonNull
  public final NestedScrollView nestedScrollView;

  @NonNull
  public final ImageButton night;

  @NonNull
  public final ImageButton notifs;

  @NonNull
  public final RecyclerView orgrecyc;

  @NonNull
  public final FrameLayout toolbarframe;

  private YourOrgsBinding(@NonNull DrawerLayout rootView, @NonNull FloatingActionButton actionFab,
      @NonNull AppBarLayout appbar, @NonNull BottomAppBar bottomAppBar,
      @NonNull CoordinatorLayout coordinator, @NonNull DrawerLayout drawer,
      @NonNull LottieAnimationView lottie1, @NonNull ImageButton menuBtn,
      @NonNull LinearLayout missingLayout, @NonNull NavigationView navigation,
      @NonNull NestedScrollView nestedScrollView, @NonNull ImageButton night,
      @NonNull ImageButton notifs, @NonNull RecyclerView orgrecyc,
      @NonNull FrameLayout toolbarframe) {
    this.rootView = rootView;
    this.actionFab = actionFab;
    this.appbar = appbar;
    this.bottomAppBar = bottomAppBar;
    this.coordinator = coordinator;
    this.drawer = drawer;
    this.lottie1 = lottie1;
    this.menuBtn = menuBtn;
    this.missingLayout = missingLayout;
    this.navigation = navigation;
    this.nestedScrollView = nestedScrollView;
    this.night = night;
    this.notifs = notifs;
    this.orgrecyc = orgrecyc;
    this.toolbarframe = toolbarframe;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static YourOrgsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static YourOrgsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.your_orgs, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static YourOrgsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.actionFab;
      FloatingActionButton actionFab = ViewBindings.findChildViewById(rootView, id);
      if (actionFab == null) {
        break missingId;
      }

      id = R.id.appbar;
      AppBarLayout appbar = ViewBindings.findChildViewById(rootView, id);
      if (appbar == null) {
        break missingId;
      }

      id = R.id.bottomAppBar;
      BottomAppBar bottomAppBar = ViewBindings.findChildViewById(rootView, id);
      if (bottomAppBar == null) {
        break missingId;
      }

      id = R.id.coordinator;
      CoordinatorLayout coordinator = ViewBindings.findChildViewById(rootView, id);
      if (coordinator == null) {
        break missingId;
      }

      DrawerLayout drawer = (DrawerLayout) rootView;

      id = R.id.lottie1;
      LottieAnimationView lottie1 = ViewBindings.findChildViewById(rootView, id);
      if (lottie1 == null) {
        break missingId;
      }

      id = R.id.menu_btn;
      ImageButton menuBtn = ViewBindings.findChildViewById(rootView, id);
      if (menuBtn == null) {
        break missingId;
      }

      id = R.id.missingLayout;
      LinearLayout missingLayout = ViewBindings.findChildViewById(rootView, id);
      if (missingLayout == null) {
        break missingId;
      }

      id = R.id.navigation;
      NavigationView navigation = ViewBindings.findChildViewById(rootView, id);
      if (navigation == null) {
        break missingId;
      }

      id = R.id.nested_scroll_view;
      NestedScrollView nestedScrollView = ViewBindings.findChildViewById(rootView, id);
      if (nestedScrollView == null) {
        break missingId;
      }

      id = R.id.night;
      ImageButton night = ViewBindings.findChildViewById(rootView, id);
      if (night == null) {
        break missingId;
      }

      id = R.id.notifs;
      ImageButton notifs = ViewBindings.findChildViewById(rootView, id);
      if (notifs == null) {
        break missingId;
      }

      id = R.id.orgrecyc;
      RecyclerView orgrecyc = ViewBindings.findChildViewById(rootView, id);
      if (orgrecyc == null) {
        break missingId;
      }

      id = R.id.toolbarframe;
      FrameLayout toolbarframe = ViewBindings.findChildViewById(rootView, id);
      if (toolbarframe == null) {
        break missingId;
      }

      return new YourOrgsBinding((DrawerLayout) rootView, actionFab, appbar, bottomAppBar,
          coordinator, drawer, lottie1, menuBtn, missingLayout, navigation, nestedScrollView, night,
          notifs, orgrecyc, toolbarframe);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
