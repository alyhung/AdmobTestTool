package com.hungaly.admob.internal.test.adController;

import android.app.Activity;
import android.app.NativeActivity;
import android.view.View;
import android.widget.*;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.formats.*;
import com.hungaly.admob.internal.test.R;

public class NativeAdvancedController  extends AdController{
	private NativeAdvancedActivity nativeActivity;
	private UnifiedNativeAd unifiedNativeAd;

	public NativeAdvancedController(Activity activity, TextView status, TextView format) {
		super(activity, status, format);
		nativeActivity = (NativeAdvancedActivity) activity;
		format.setText(activity.getString(R.string.native_format));
	}

	@Override
	public void loadAd(String adId) {
		AdLoader.Builder builder = new AdLoader.Builder(activity, adId);
		builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
			// OnUnifiedNativeAdLoadedListener implementation.
			@Override
			public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
				NativeAdvancedController.this.onAdLoaded();
				NativeAdvancedController.this.unifiedNativeAd = unifiedNativeAd;
			}
		});

		builder.withAdListener(new AdListener(){
			@Override
			public void onAdFailedToLoad(int i) {
				NativeAdvancedController.this.onAdFailedToLoad(i);
			}

			@Override
			public void onAdLoaded() {
				NativeAdvancedController.this.onAdLoaded();
			}
		});

		NativeAdOptions adOptions = new NativeAdOptions.Builder()
				.build();

		builder.withNativeAdOptions(adOptions);

		AdLoader adLoader = builder.build();

		adLoader.loadAd(new AdRequest.Builder().build());
	}

	@Override
	public void showAd() {
		UnifiedNativeAdView adView = (UnifiedNativeAdView) activity.getLayoutInflater()
				.inflate(R.layout.ad_unified, null);
		populateUnifiedNativeAdView(unifiedNativeAd, adView);
		nativeActivity.addNativeAd(adView);
		onAdOpened();
	}

	private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
		// Get the video controller for the ad. One will always be provided, even if the ad doesn't
		// have a video asset.
		VideoController vc = nativeAd.getVideoController();

		// Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
		// VideoController will call methods on this object when events occur in the video
		// lifecycle.
		vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
			public void onVideoEnd() {
				// Publishers should allow native ads to complete video playback before refreshing
				// or replacing them with another ad in the same UI location.
				super.onVideoEnd();
			}
		});

		MediaView mediaView = adView.findViewById(R.id.ad_media);
		adView.setMediaView(mediaView);

		adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
		adView.setBodyView(adView.findViewById(R.id.ad_body));
		adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
		adView.setIconView(adView.findViewById(R.id.ad_app_icon));
		adView.setPriceView(adView.findViewById(R.id.ad_price));
		adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
		adView.setStoreView(adView.findViewById(R.id.ad_store));
		adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

		// Some assets are guaranteed to be in every UnifiedNativeAd.
		((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
		((TextView) adView.getBodyView()).setText(nativeAd.getBody());
		((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

		// These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
		// check before trying to display them.
		if (nativeAd.getIcon() == null) {
			adView.getIconView().setVisibility(View.GONE);
		} else {
			((ImageView) adView.getIconView()).setImageDrawable(
					nativeAd.getIcon().getDrawable());
			adView.getIconView().setVisibility(View.VISIBLE);
		}

		if (nativeAd.getPrice() == null) {
			adView.getPriceView().setVisibility(View.INVISIBLE);
		} else {
			adView.getPriceView().setVisibility(View.VISIBLE);
			((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
		}

		if (nativeAd.getStore() == null) {
			adView.getStoreView().setVisibility(View.INVISIBLE);
		} else {
			adView.getStoreView().setVisibility(View.VISIBLE);
			((TextView) adView.getStoreView()).setText(nativeAd.getStore());
		}

		if (nativeAd.getStarRating() == null) {
			adView.getStarRatingView().setVisibility(View.INVISIBLE);
		} else {
			((RatingBar) adView.getStarRatingView())
					.setRating(nativeAd.getStarRating().floatValue());
			adView.getStarRatingView().setVisibility(View.VISIBLE);
		}

		if (nativeAd.getAdvertiser() == null) {
			adView.getAdvertiserView().setVisibility(View.INVISIBLE);
		} else {
			((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
			adView.getAdvertiserView().setVisibility(View.VISIBLE);
		}

		adView.setNativeAd(nativeAd);
	}

	public interface NativeAdvancedActivity {
		public void addNativeAd(UnifiedNativeAdView adView);
	}
}
