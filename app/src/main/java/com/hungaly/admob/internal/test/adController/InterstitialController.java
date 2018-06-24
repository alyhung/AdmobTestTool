package com.hungaly.admob.internal.test.adController;

import android.app.Activity;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.hungaly.admob.internal.test.R;

/**
 * Created by hungaly on 6/24/18.
 */

public class InterstitialController extends AdController{
	InterstitialAd interstitialAd;

	public InterstitialController(Activity activity, TextView status, TextView format) {
		super(activity, status, format);
		format.setText(activity.getString(R.string.interstitial_format));
	}

	@Override
	public void loadAd(String adUnit) {
		interstitialAd = new InterstitialAd(activity);
		interstitialAd.setAdUnitId(adUnit);
		interstitialAd.setAdListener(new AdListener(){
			@Override
			public void onAdClosed() {
				InterstitialController.this.onAdClose();
			}

			@Override
			public void onAdFailedToLoad(int i) {
				InterstitialController.this.onAdFailedToLoad(i);
			}

			@Override
			public void onAdLeftApplication() {
				InterstitialController.this.onAdLeftApplication();
			}

			@Override
			public void onAdOpened() {
				InterstitialController.this.onAdOpened();
			}

			@Override
			public void onAdLoaded() {
				InterstitialController.this.onAdLoaded();
			}

			@Override
			public void onAdClicked() {
				InterstitialController.this.onAdClicked();
			}

			@Override
			public void onAdImpression() {
				InterstitialController.this.onAdImpression();
			}
		});
		interstitialAd.loadAd(new AdRequest.Builder().build());
	}

	@Override
	public void showAd() {
		interstitialAd.show();
	}
}
