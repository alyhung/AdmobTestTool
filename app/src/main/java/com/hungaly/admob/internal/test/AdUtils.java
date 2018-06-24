package com.hungaly.admob.internal.test;

import android.app.Activity;

/**
 * Created by hungaly on 6/21/18.
 */

interface AdUtils {
	void init(Activity activity);
	void loadInterstitial();
	void showInterstitial();
	void setInterstitialListener(GeneralInterstitialListener interstitialListener);
	boolean canShowInterstitial();

	void loadRewarded();
	void showRewarded();
	void setRewardedListener(GeneralRewardedListener rewardedListener);
	boolean canShowRewarded();
}

interface GeneralInterstitialListener{
	void onAdClosed();
}

interface GeneralRewardedListener{
	void onRewardedVideoAdLeftApplication();
	void onRewardedVideoAdClosed();
	void onRewardedVideoAdFailedToLoad(int errorCode);
	void onRewardedVideoAdLoaded();
	void onRewardedVideoAdOpened();
	void onRewarded(String name, int amount);
	void onRewardedVideoStarted();
	void onRewardedVideoCompleted();
}
