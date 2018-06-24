package com.hungaly.admob.internal.test;

import android.app.Activity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by hungaly on 6/22/18.
 */

public class AdMobAdUtils implements AdUtils, RewardedVideoAdListener {
	private static AdMobAdUtils sharedInstance = new AdMobAdUtils();

	private InterstitialAd interstitialAd;
	private final String appId = "ca-app-pub-3345124885896772~4865820662";
	private final String interstitialAdUnit = "ca-app-pub-3345124885896772/9735003963";
	private final String rewardedAdUnit = "ca-app-pub-3345124885896772/1856513947";
	private final String TAG = "hungaly";

	private GeneralInterstitialListener interstitialListener;
	private GeneralRewardedListener rewardedListener;
	private RewardedVideoAd rewardedVideoAd;

	public static AdMobAdUtils getSharedInstance(){
		return sharedInstance;
	}

	@Override
	public void init(Activity activity) {
		MobileAds.initialize(activity, appId);
		interstitialAd = new InterstitialAd(activity);
		interstitialAd.setAdUnitId(interstitialAdUnit);
		interstitialListener = new GeneralInterstitialListener() {
			@Override
			public void onAdClosed() {
			}
		};
		rewardedListener = new GeneralRewardedListener() {
			@Override
			public void onRewardedVideoAdLeftApplication() {
			}

			@Override
			public void onRewardedVideoAdClosed() {

			}

			@Override
			public void onRewardedVideoAdFailedToLoad(int errorCode) {

			}

			@Override
			public void onRewardedVideoAdLoaded() {

			}

			@Override
			public void onRewardedVideoAdOpened() {

			}

			@Override
			public void onRewarded(String name, int amount) {

			}

			@Override
			public void onRewardedVideoStarted() {

			}

			@Override
			public void onRewardedVideoCompleted() {

			}
		};
		interstitialAd.setAdListener(new AdListener(){
			@Override
			public void onAdClosed() {
				super.onAdClosed();
				interstitialListener.onAdClosed();
			}
		});

		rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
		rewardedVideoAd.setRewardedVideoAdListener(this);
	}

	@Override
	public void loadInterstitial() {
		if (interstitialAd.isLoaded() || interstitialAd.isLoading()){
			return;
		}
		interstitialAd.loadAd(new AdRequest.Builder().build());
	}

	@Override
	public void showInterstitial() {
		interstitialAd.show();
	}

	@Override
	public void setInterstitialListener(GeneralInterstitialListener interstitialListener) {
		this.interstitialListener = interstitialListener;
	}

	@Override
	public boolean canShowInterstitial() {
		return interstitialAd.isLoaded();
	}

	@Override
	public void loadRewarded() {
		if(!rewardedVideoAd.isLoaded()){
			rewardedVideoAd.loadAd(rewardedAdUnit, new AdRequest.Builder().build());
		}
	}

	@Override
	public void showRewarded() {
		rewardedVideoAd.show();
	}

	@Override
	public void setRewardedListener(GeneralRewardedListener rewardedListener) {
		this.rewardedListener = rewardedListener;
	}

	@Override
	public boolean canShowRewarded() {
		return rewardedVideoAd.isLoaded();
	}

	@Override
	public void onRewardedVideoAdLoaded() {
		rewardedListener.onRewardedVideoAdLoaded();
	}

	@Override
	public void onRewardedVideoAdOpened() {
		rewardedListener.onRewardedVideoAdOpened();
	}

	@Override
	public void onRewardedVideoStarted() {
		rewardedListener.onRewardedVideoStarted();
	}

	@Override
	public void onRewardedVideoAdClosed() {
		rewardedListener.onRewardedVideoAdClosed();
	}

	@Override
	public void onRewarded(RewardItem rewardItem) {
		rewardedListener.onRewarded(rewardItem.getType(), rewardItem.getAmount());
	}

	@Override
	public void onRewardedVideoAdLeftApplication() {
		rewardedListener.onRewardedVideoAdLeftApplication();
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int i) {
		rewardedListener.onRewardedVideoAdFailedToLoad(i);
	}

	@Override
	public void onRewardedVideoCompleted() {
		rewardedListener.onRewardedVideoCompleted();
	}
}
