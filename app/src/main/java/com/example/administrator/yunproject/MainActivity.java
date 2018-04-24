package com.example.administrator.yunproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.yunproject.Fragment.DynamicFragment;
import com.example.administrator.yunproject.Fragment.FindFragment;
import com.example.administrator.yunproject.Fragment.MyFragment;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.namee.permissiongen.PermissionGen;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class MainActivity extends BaseAc {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.b_anew_upload)
    Button bAnewUpload;
    @BindView(R.id.layout_error)
    LinearLayout layoutError;
    private MainActivity instance;
    private final int PERMISSION_REQUESTCODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_base_main);
        instance = this;
        ButterKnife.bind(instance);
        initView();




    }




    private void initView() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            //没有授权
            //一次申请多个权限
//            PermissionUtils.requestMultiPermissions(this, mPermissionGrant);
            init();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_REQUESTCODE);
        }
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainAdapter);
        viewPager.setOffscreenPageLimit(3);
        alphaIndicator.setViewPager(viewPager);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(mainAdapter);

        alphaIndicator.getTabView(0).removeShow();
        alphaIndicator.getTabView(1).removeShow();
        alphaIndicator.getTabView(2).removeShow();
    }


    public void init() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        //电话通讯录
                        Manifest.permission.GET_ACCOUNTS,
                        Manifest.permission.READ_PHONE_STATE,
                        //位置
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        //相机、麦克风
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        //存储空间
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_SETTINGS
                )
                .request();

    }

    @Override
    public void errnet() {
        Toast.makeText(MainActivity.this, "网络断开", Toast.LENGTH_SHORT).show();
        llRoot.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);


    }

    @Override
    protected void sucnet() {
        llRoot.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);


    }


    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();
        private String[] titles = {"动态", "发现", "我"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(DynamicFragment.newInstance(titles[0]));
            fragments.add(FindFragment.newInstance(titles[1]));
            fragments.add(MyFragment.newInstance(titles[2]));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (0 == position) {
                alphaIndicator.getCurrentItemView().removeShow();
            } else if (1 == position) {
                alphaIndicator.getCurrentItemView().removeShow();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }


    private long timeMillis;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                timeMillis = System.currentTimeMillis();
            } else {
                BaseActivity.finishAllActivity();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
