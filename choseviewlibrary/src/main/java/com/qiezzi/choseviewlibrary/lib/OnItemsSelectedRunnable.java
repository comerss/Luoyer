package com.qiezzi.choseviewlibrary.lib;


final class OnItemsSelectedRunnable implements Runnable {
    final AddressView loopView;

    OnItemsSelectedRunnable(AddressView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
