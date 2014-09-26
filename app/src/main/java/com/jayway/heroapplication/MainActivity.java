package com.jayway.heroapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> items = getPackageManager().queryIntentActivities(intent, 0);
        final AppAdapter adapter = new AppAdapter(this, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.ACTIVITY_INFO_EXTRA, adapter.getItem(position).activityInfo);
                startActivity(intent);
            }
        });
    }

    private class AppAdapter extends ArrayAdapter<ResolveInfo> {

        public AppAdapter(final Context context, final List<ResolveInfo> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
            }

            final PackageManager packageManager = getContext().getPackageManager();
            final ResolveInfo item = getItem(position);

            ImageView iconView = (ImageView) view.findViewById(R.id.icon);
            iconView.setImageDrawable(item.activityInfo.loadIcon(packageManager));
            iconView.setViewName("icon" + position);

            TextView labelView = (TextView) view.findViewById(R.id.label);
            labelView.setText(item.activityInfo.loadLabel(packageManager));
            labelView.setViewName("label" + position);

            return view;
        }
    }
}
