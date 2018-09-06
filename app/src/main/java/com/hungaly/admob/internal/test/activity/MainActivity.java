package com.hungaly.admob.internal.test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hungaly.admob.internal.test.R;
import com.hungaly.admob.internal.test.adController.AdController;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn_mediation = findViewById(R.id.btn_mediation);
		btn_mediation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MediationActivity.class);
				startActivity(intent);
			}
		});

		Button btnBanner = findViewById(R.id.btn_banner);
		btnBanner.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openTestAdUnitActivity(AdController.BANNER);
			}
		});

		Button btnInterstitial = findViewById(R.id.btn_interstitial);
		btnInterstitial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openTestAdUnitActivity(AdController.INTERSTITIAL);
			}
		});

		Button btnRewarded = findViewById(R.id.btn_rewarded);
		btnRewarded.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openTestAdUnitActivity(AdController.REWARDED);
			}
		});

		Button btnNative = findViewById(R.id.btn_native);
		btnNative.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openTestAdUnitActivity(AdController.NATIVE);
			}
		});
	}

	private void openTestAdUnitActivity(int adFormat) {
		final Intent intent = new Intent(MainActivity.this, AdUnitActivity.class);
		intent.putExtra(getString(R.string.intent_ad_format_param), adFormat);
		startActivity(intent);
	}
}
