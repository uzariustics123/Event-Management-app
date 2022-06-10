// Generated by view binder compiler. Do not edit!
package com.macas.nmsc.sict.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.macas.nmsc.sict.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOnboardBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final CardView carditem;

  @NonNull
  public final CoordinatorLayout coordinator;

  @NonNull
  public final DrawerLayout drawer;

  @NonNull
  public final ImageView imgProfile;

  @NonNull
  public final FrameLayout layoutSwap;

  @NonNull
  public final ImageButton menuBtn;

  @NonNull
  public final NavigationView navigation;

  @NonNull
  public final TextInputEditText searchedtxt;

  @NonNull
  public final TextInputLayout tilsearch;

  @NonNull
  public final FrameLayout toolbarframe;

  private ActivityOnboardBinding(@NonNull DrawerLayout rootView, @NonNull AppBarLayout appbar,
      @NonNull CardView carditem, @NonNull CoordinatorLayout coordinator,
      @NonNull DrawerLayout drawer, @NonNull ImageView imgProfile, @NonNull FrameLayout layoutSwap,
      @NonNull ImageButton menuBtn, @NonNull NavigationView navigation,
      @NonNull TextInputEditText searchedtxt, @NonNull TextInputLayout tilsearch,
      @NonNull FrameLayout toolbarframe) {
    this.rootView = rootView;
    this.appbar = appbar;
    this.carditem = carditem;
    this.coordinator = coordinator;
    this.drawer = drawer;
    this.imgProfile = imgProfile;
    this.layoutSwap = layoutSwap;
    this.menuBtn = menuBtn;
    this.navigation = navigation;
    this.searchedtxt = searchedtxt;
    this.tilsearch = tilsearch;
    this.toolbarframe = toolbarframe;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOnboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOnboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_onboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOnboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar;
      AppBarLayout appbar = ViewBindings.findChildViewById(rootView, id);
      if (appbar == null) {
        break missingId;
      }

      id = R.id.carditem;
      CardView carditem = ViewBindings.findChildViewById(rootView, id);
      if (carditem == null) {
        break missingId;
      }

      id = R.id.coordinator;
      CoordinatorLayout coordinator = ViewBindings.findChildViewById(rootView, id);
      if (coordinator == null) {
        break missingId;
      }

      DrawerLayout drawer = (DrawerLayout) rootView;

      id = R.id.img_profile;
      ImageView imgProfile = ViewBindings.findChildViewById(rootView, id);
      if (imgProfile == null) {
        break missingId;
      }

      id = R.id.layout_swap;
      FrameLayout layoutSwap = ViewBindings.findChildViewById(rootView, id);
      if (layoutSwap == null) {
        break missingId;
      }

      id = R.id.menu_btn;
      ImageButton menuBtn = ViewBindings.findChildViewById(rootView, id);
      if (menuBtn == null) {
        break missingId;
      }

      id = R.id.navigation;
      NavigationView navigation = ViewBindings.findChildViewById(rootView, id);
      if (navigation == null) {
        break missingId;
      }

      id = R.id.searchedtxt;
      TextInputEditText searchedtxt = ViewBindings.findChildViewById(rootView, id);
      if (searchedtxt == null) {
        break missingId;
      }

      id = R.id.tilsearch;
      TextInputLayout tilsearch = ViewBindings.findChildViewById(rootView, id);
      if (tilsearch == null) {
        break missingId;
      }

      id = R.id.toolbarframe;
      FrameLayout toolbarframe = ViewBindings.findChildViewById(rootView, id);
      if (toolbarframe == null) {
        break missingId;
      }

      return new ActivityOnboardBinding((DrawerLayout) rootView, appbar, carditem, coordinator,
          drawer, imgProfile, layoutSwap, menuBtn, navigation, searchedtxt, tilsearch,
          toolbarframe);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}