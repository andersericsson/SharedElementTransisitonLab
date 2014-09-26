package com.jayway.heroapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends Activity {

    public static final String ACTIVITY_INFO_EXTRA = "activity_info_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActivityInfo info = getIntent().getParcelableExtra(ACTIVITY_INFO_EXTRA);

        ImageView iconView = (ImageView) findViewById(R.id.app_icon);
        iconView.setImageDrawable(info.loadIcon(getPackageManager()));

        TextView labelView = (TextView) findViewById(R.id.app_label);
        labelView.setText(info.loadLabel(getPackageManager()));
    }
}
