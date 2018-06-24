package com.hungaly.admob.internal.test.adController;

import android.app.Activity;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.hungaly.admob.internal.test.R;

/**
 * Created by hungaly on 6/24/18.
 */

public class RewardedController extends AdController implements RewardedVideoAdListener {
	RewardedVideoAd rewardedVideoAd;
	public RewardedController(Activity activity, TextView status, TextView format) {
		super(activity, status, format);
		format.setText(activity.getString(R.string.rewarded_format));
	}

	@Override
	public void loadAd(String adId) {
		rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
		rewardedVideoAd.setRewardedVideoAdListener(this);
		rewardedVideoAd.loadAd(adId, new AdRequest.Builder().build());
	}

	@Override
	public void showAd() {
		rewardedVideoAd.show();
	}

	@Override
	public void onRewardedVideoAdLoaded() {
		onAdLoaded();
	}

	@Override
	public void onRewardedVideoAdOpened() {
		onAdOpened();
	}

	@Override
	public void onRewardedVideoStarted() {
		onAdImpression();
	}

	@Override
	public void onRewardedVideoAdClosed() {
		onAdClose();
	}

	@Override
	public void onRewarded(RewardItem rewardItem) {
	}

	@Override
	public void onRewardedVideoAdLeftApplication() {
		onAdLeftApplication();
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int i) {
		onAdFailedToLoad(i);
	}

	@Override
	public void onRewardedVideoCompleted() {
	}
}
