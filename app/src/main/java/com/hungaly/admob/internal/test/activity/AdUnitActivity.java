package com.hungaly.admob.internal.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.hungaly.admob.internal.test.R;
import com.hungaly.admob.internal.test.adController.*;

public class AdUnitActivity extends AppCompatActivity implements AdCustomListener, BannerController.BannerActivity,
		NativeAdvancedController.NativeAdvancedActivity{
	AdController adController;
	EditText text_adUnit;
	Button btn_load;
	Button btn_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad_unit);

		TextView textStatus = findViewById(R.id.text_adUnitStatus);
		TextView textFormat = findViewById(R.id.text_adFormat);
		text_adUnit = findViewById(R.id.input_adUnit);

		initAdController(textStatus, textFormat);
		initButtonListener();
	}

	private void initAdController(TextView textStatus, TextView textFormat) {
		int adFormat = getIntent().getIntExtra(getString(R.string.intent_ad_format_param), AdController.BANNER);
		switch (adFormat) {
			case AdController.BANNER:
				adController = new BannerController(this, textStatus, textFormat);
				((BannerController)adController).setBannerActivity(this);
				break;
			case AdController.INTERSTITIAL:
				adController = new InterstitialController(this, textStatus, textFormat);
				break;
			case AdController.REWARDED:
				adController = new RewardedController(this, textStatus, textFormat);
				break;
			case AdController.NATIVE:
				adController = new NativeAdvancedController(this, textStatus, textFormat);
			break;
		}
		adController.setAdListener(this);
	}

	private void initButtonListener() {
		btn_load = findViewById(R.id.btn_loadAdUnit);
		btn_show = findViewById(R.id.btn_showAdUnit);

		btn_load.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickLoadButton();
			}
		});

		btn_show.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickShowButton();
			}
		});
		btn_show.setEnabled(false);
	}

	private void onClickLoadButton() {
		adController.loadAd(text_adUnit.getText().toString());
		btn_load.setEnabled(false);
	}

	private void onClickShowButton() {
		adController.showAd();
	}

	@Override
	public void onAdFailedToLoad() {
		btn_load.setEnabled(true);
	}

	@Override
	public void onAdLoaded() {
		btn_show.setEnabled(true);
	}

	@Override
	public void onAdImpression() {
	}

	@Override
	public void onAdOpened() {
		btn_show.setEnabled(false);
		btn_load.setEnabled(true);
	}

	@Override
	public void addBannerView(AdView adView) {
		RelativeLayout bannerHolder = findViewById(R.id.banner_holder);
		bannerHolder.removeAllViews();
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
		bannerHolder.addView(adView);
	}

	@Override
	public void addNativeAd(UnifiedNativeAdView adView) {
		RelativeLayout nativeHolder = findViewById(R.id.native_holder);
		nativeHolder.removeAllViews();
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
		nativeHolder.addView(adView);
	}
}
