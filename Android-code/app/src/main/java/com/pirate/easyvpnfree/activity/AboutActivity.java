package com.pirate.easyvpnfree.activity;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pirate.easyvpnfree.R;

public class AboutActivity extends BaseActivity {

    private AdView mAdView;
    private Button mbutton;
    private ImageButton mPornGirl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        try {
            PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionNumber = pinfo.versionCode;
            String versionName = pinfo.versionName;
            String appName =  AboutActivity.this.getString(R.string.app_name);
            TextView versionText = (TextView)findViewById(R.id.appVersion);
            versionText.setText(
                    String.format("%s version %s build %d",
                            appName,
                            versionName,
                            versionNumber
                            )
            );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mPornGirl = (ImageButton) findViewById(R.id.pornGirl);
        mPornGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = getString(R.string.pornAppDownloadAddress);
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(address);
                Toast.makeText(getApplicationContext(), "复制成功，请打开浏览器粘贴地址下载",Toast.LENGTH_SHORT).show();
            }
        });
        mbutton = (Button) findViewById(R.id.copyAddress);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = getString(R.string.downloadAddress);
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(address);
                Toast.makeText(getApplicationContext(), "复制成功，请打开浏览器粘贴地址下载",Toast.LENGTH_SHORT).show();
            }
        });
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.e("xxxxx", "已关闭: ");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e("xxxxx", "错误加载: "+ i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.e("xxxxx", " onAdLeftApplication" );
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e("xxxxx", "广告已打开 " );
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e("xxxxx", "广告已加载: ");
            }
        });
    }
}
