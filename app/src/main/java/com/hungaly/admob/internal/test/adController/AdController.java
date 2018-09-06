package com.hungaly.admob.internal.test.adController;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by hungaly on 6/24/18.
 */

public abstract class AdController {
	public static final int BANNER = 0;
	public static final int INTERSTITIAL = 1;
	public static final int REWARDED = 2;
	public static final int NATIVE = 3;

	protected TextView textStatus;
	protected TextView adFormat;
	protected Activity activity;
	protected AdCustomListener adListener;

	public AdController(Activity activity, TextView status, TextView adFormat){
		this.activity = activity;
		textStatus = status;
		this.adFormat = adFormat;
	}

	public void setAdListener(AdCustomListener adCustomListener){
		this.adListener = adCustomListener;
	}

	protected void onAdClose() {
	}
	protected void onAdFailedToLoad(int i) {
		textStatus.setText("Ad failed to load ERROR_CODE = " + i);
		adListener.onAdFailedToLoad();
	}
	protected void onAdLeftApplication() {}
	protected void onAdOpened() {
		adListener.onAdOpened();
	}
	protected void onAdLoaded() {
		adListener.onAdLoaded();
	}
	protected void onAdClicked() {}
	protected void onAdImpression() {
		adListener.onAdImpression();
	}

	public abstract void loadAd(String adId);
	public abstract void showAd();
}
