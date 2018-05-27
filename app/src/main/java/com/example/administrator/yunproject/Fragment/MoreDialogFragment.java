package com.example.administrator.yunproject.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.yunproject.Activity.BaseWebActivity;
import com.example.administrator.yunproject.Activity.DiscussActivity;
import com.example.administrator.yunproject.Activity.InterlocutionActivity;
import com.example.administrator.yunproject.Activity.QuestionsActivity;
import com.example.administrator.yunproject.R;
import com.example.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/13.
 */

@SuppressLint("ValidFragment")
public class MoreDialogFragment extends DialogFragment {
    @BindView(R.id.iamge)
    ImageView iamge;
    @BindView(R.id.chat_popup_photo)
    RelativeLayout chatPopupPhoto;
    @BindView(R.id.photograph)
    ImageView photograph;
    @BindView(R.id.chat_popup_photograph)
    RelativeLayout chatPopupPhotograph;
    @BindView(R.id.voice)
    ImageView voice;
    @BindView(R.id.chat_function_voice)
    RelativeLayout chatFunctionVoice;
    @BindView(R.id.voice_popup)
    LinearLayout voicePopup;
    @BindView(R.id.hongbao)
    ImageView hongbao;
    @BindView(R.id.chat_function_hongbao)
    RelativeLayout chatFunctionHongbao;
    @BindView(R.id.tuwen)
    ImageView tuwen;
    @BindView(R.id.chat_function_tuwen)
    RelativeLayout chatFunctionTuwen;
    @BindView(R.id.red_popuplinear)
    LinearLayout redPopuplinear;
    @BindView(R.id.closea)
    LinearLayout closea;
    Unbinder unbinder;
    private Activity instance;
    private Dialog dialog;
    private int videoId;
    StandardGSYVideoPlayer listItemVideoPlayer;

    @SuppressLint("ValidFragment")
    public MoreDialogFragment(StandardGSYVideoPlayer listItemVideoPlayer) {
        this.listItemVideoPlayer = listItemVideoPlayer;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        instance = getActivity();
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.more_dialog_fragment);
        dialog.setCanceledOnTouchOutside(true); // 外部点击打开
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 底部显示
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);

        ButterKnife.bind(this, dialog); // Dialog即View
        initWight();
        return dialog;
    }


    private void initWight() {
        videoId = getArguments().getInt("videoId");
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
////        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @OnClick({R.id.chat_function_voice, R.id.chat_function_hongbao, R.id.chat_popup_photo, R.id.chat_popup_photograph
            , R.id.chat_function_tuwen, R.id.closea})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chat_function_voice:  //分享
                Toast.makeText(instance, "暂未开放此功能", Toast.LENGTH_SHORT).show();

                break;
            case R.id.chat_function_hongbao:

                listItemVideoPlayer.onVideoPause();
                listItemVideoPlayer.onVideoReset();
                Intent interlocutionIntent = new Intent(instance, InterlocutionActivity.class);
                interlocutionIntent.putExtra("videoId", videoId);
                startActivity(interlocutionIntent);
                dialog.cancel();
                break;
            case R.id.chat_popup_photo:  //简介
                listItemVideoPlayer.onVideoPause();
                listItemVideoPlayer.onVideoReset();
                Intent interlocu = new Intent(instance, BaseWebActivity.class);
                interlocu.putExtra("videoId", videoId);
                interlocu.putExtra("tag", 106);
                startActivity(interlocu);
                dialog.cancel();

                break;
            case R.id.chat_popup_photograph:  //问答
                listItemVideoPlayer.onVideoPause();
                listItemVideoPlayer.onVideoReset();
                Intent mQuestionIntent = new Intent(instance, QuestionsActivity.class);
                mQuestionIntent.putExtra("videoId", videoId);
                startActivity(mQuestionIntent);
                dialog.cancel();
                break;
            case R.id.chat_function_tuwen:  //评价
                listItemVideoPlayer.onVideoPause();
                listItemVideoPlayer.onVideoReset();
                Intent DiscussIntent = new Intent(instance, DiscussActivity.class);
                DiscussIntent.putExtra("videoId", videoId);
                startActivity(DiscussIntent);
                dialog.cancel();

                break;
            case R.id.closea:  //关闭
                dialog.cancel();
                break;
        }
    }
}
