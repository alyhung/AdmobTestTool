package com.hungaly.admob.internal.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.ads.mediation.testsuite.TestSuite;
import com.hungaly.admob.internal.test.R;

public class MediationActivity extends AppCompatActivity {
	private static final String TAG = "hungaly";
	TextView text_status;
	EditText input_appId;
	Button btn_open;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mediation);
		text_status = findViewById(R.id.text_mStatus);
		input_appId = findViewById(R.id.input_appId);
		btn_open = findViewById(R.id.btn_openTestSuite);

		btn_open.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickButtonOpen();
			}
		});
	}

	private void onClickButtonOpen() {
		TestSuite.launch(MediationActivity.this, input_appId.getText().toString());
	}
}
