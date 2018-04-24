package com.example.administrator.yunproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {


    @BindView(R.id.tv_help)
    TextView tvHelp;
    @BindView(R.id.tv_ly)
    TextView tvLy;
    @BindView(R.id.tv_myly)
    TextView tvMyly;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_tilte)
    TextView tvTilte;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.titleBar)
    AppBarLayout titleBar;
    @BindView(R.id.iv_help)
    ImageView ivHelp;
    @BindView(R.id.iv_ly)
    ImageView ivLy;
    @BindView(R.id.iv_myly)
    ImageView ivMyly;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    private SetActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        activity = this;
        ButterKnife.bind(activity);

        iniView();
    }

    private void iniView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.GONE);
        tvTilte.setText("设置");
        tvTilte.setTextSize(19);

    }

    @OnClick({R.id.tv_help, R.id.tv_ly, R.id.tv_myly,R.id.iv_help, R.id.iv_ly, R.id.iv_myly, R.id.tv_exit,R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_help:
            case R.id.iv_help:
                Intent mHelpIntent = new Intent(activity, HelpActivity.class);
                startActivity(mHelpIntent);

                break;
            case R.id.tv_ly:
            case R.id.iv_ly:
                Intent intent = new Intent(activity, BaseWebActivity.class);
                intent.putExtra("tag", 101);
                startActivity(intent);

                break;
            case R.id.tv_myly:
            case R.id.iv_myly:
                Intent intent2 = new Intent(activity, BaseWebActivity.class);
                intent2.putExtra("tag", 102);
                startActivity(intent2);
                break;
            case R.id.tv_exit:
                SharedPreferencesUtility.setLogin(activity, false);
                SharedPreferencesUtility.clearUserId(activity);
                finish();
                break;

            case R.id.iv_left:
                finish();
                break;
        }
    }

}
