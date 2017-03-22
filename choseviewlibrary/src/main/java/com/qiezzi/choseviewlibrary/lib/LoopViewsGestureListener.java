package com.qiezzi.choseviewlibrary.lib;


import android.view.MotionEvent;

final class LoopViewsGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final AddressView loopView;

    LoopViewsGestureListener(AddressView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
