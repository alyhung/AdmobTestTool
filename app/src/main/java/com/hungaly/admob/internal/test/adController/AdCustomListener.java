package com.hungaly.admob.internal.test.adController;

/**
 * Created by hungaly on 6/24/18.
 */

public interface AdCustomListener {
	public void onAdFailedToLoad();
	public void onAdLoaded();
	public void onAdImpression();
	public void onAdOpened();
}
