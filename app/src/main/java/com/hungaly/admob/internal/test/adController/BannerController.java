package com.hungaly.admob.internal.test.adController;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.hungaly.admob.internal.test.R;

/**
 * Created by hungaly on 6/24/18.
 */

public class BannerController extends AdController {
	AdView adView;
	BannerActivity bannerActivity;

	public BannerController(Activity activity, TextView status, TextView format) {
		super(activity, status, format);
		format.setText(activity.getString(R.string.banner_format));
	}

	public void setBannerActivity(BannerActivity target){
		bannerActivity = target;
	}

	@Override
	public void loadAd(String adId) {
		if (adView != null && adView.isShown()) {
			adView.destroy();
		}
		adView = new AdView(activity);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(adId);
		adView.setVisibility(View.INVISIBLE);
		adView.setAdListener(new AdListener(){
			@Override
			public void onAdClosed() {
				BannerController.this.onAdClose();
			}

			@Override
			public void onAdFailedToLoad(int i) {
				BannerController.this.onAdFailedToLoad(i);
			}

			@Override
			public void onAdLeftApplication() {
				BannerController.this.onAdLeftApplication();
			}

			@Override
			public void onAdOpened() {
				BannerController.this.onAdOpened();
			}

			@Override
			public void onAdLoaded() {
				BannerController.this.onAdLoaded();
			}

			@Override
			public void onAdClicked() {
				BannerController.this.onAdClicked();
			}

			@Override
			public void onAdImpression() {
				BannerController.this.onAdImpression();
			}
		});
		adView.loadAd(new AdRequest.Builder().build());
		bannerActivity.addBannerView(adView);
	}

	@Override
	public void showAd() {
		adView.setVisibility(View.VISIBLE);
		onAdOpened();
	}

	public interface BannerActivity{
		public void addBannerView(AdView adview);
	}
}
