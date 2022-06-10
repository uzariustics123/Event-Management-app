// Generated by view binder compiler. Do not edit!
package com.macas.nmsc.sict.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.macas.nmsc.sict.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AnimPager1Binding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final CoordinatorLayout coordinator;

  @NonNull
  public final LottieAnimationView lottie1;

  private AnimPager1Binding(@NonNull CoordinatorLayout rootView,
      @NonNull CoordinatorLayout coordinator, @NonNull LottieAnimationView lottie1) {
    this.rootView = rootView;
    this.coordinator = coordinator;
    this.lottie1 = lottie1;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AnimPager1Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AnimPager1Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.anim_pager1, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AnimPager1Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CoordinatorLayout coordinator = (CoordinatorLayout) rootView;

      id = R.id.lottie1;
      LottieAnimationView lottie1 = ViewBindings.findChildViewById(rootView, id);
      if (lottie1 == null) {
        break missingId;
      }

      return new AnimPager1Binding((CoordinatorLayout) rootView, coordinator, lottie1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
