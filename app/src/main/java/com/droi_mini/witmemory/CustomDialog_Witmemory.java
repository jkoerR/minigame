package com.droi_mini.witmemory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droi_mini.R;


public class CustomDialog_Witmemory extends Dialog {

//    private ImageView iv_custom_dialog_witmemory_img;
    private ImageView iv_custom_dialog_witmemory_can;
    private ImageView iv_custom_dialog_witmemory_con;
//    private ImageView iv_custom_dialog_witmemory_can_flag;
//    private ImageView iv_custom_dialog_witmemory_ad_flag_con;
//    private ImageView iv_custom_dialog_witmemory_ad_flag_can;
//    private ImageView iv_custom_dialog_witmemory_con_flag;
//    private TextView tv_custom_dialog_witmemory;
//    private TextView tv_custom_dialog_witmemory_notice;
    private RelativeLayout tv_custom_dialog_witmemory_no;
    private LinearLayout ll_custom_dialog_witmemory;
    private View.OnClickListener onClickListener;
    private Activity mContext;
//    String mango;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        wm.dimAmount = 0.8f;
        getWindow().setAttributes(wm);
        setContentView(R.layout.custom_dialog_witmemory);
        setLayout();

    }

    public CustomDialog_Witmemory(Activity context, View.OnClickListener onClickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
        this.onClickListener = onClickListener;
//        this.mango = mango;
    }

    private void setLayout() {

//        iv_custom_dialog_witmemory_img = (ImageView) findViewById(R.id.iv_custom_dialog_witmemory_img);
        iv_custom_dialog_witmemory_can = (ImageView) findViewById(R.id.iv_custom_dialog_witmemory_can);
        iv_custom_dialog_witmemory_con = (ImageView) findViewById(R.id.iv_custom_dialog_witmemory_con);
//        iv_custom_dialog_witmemory_ad_flag_con = (ImageView) findViewById(R.id.iv_custom_dialog_witmemory_ad_flag_con);
//        iv_custom_dialog_witmemory_ad_flag_can = (ImageView) findViewById(R.id.iv_custom_dialog_witmemory_ad_flag_can);
//        iv_custom_dialog_witmemory_can_flag = (ImageView) findViewById(R.id.iv_custom_dialog_witmemory_can_flag);
//        iv_custom_dialog_witmemory_con_flag = (ImageView) findViewById(R.id.iv_custom_dialog_witmemory_con_flag);
        ll_custom_dialog_witmemory = (LinearLayout) findViewById(R.id.ll_custom_dialog_witmemory);
//        tv_custom_dialog_witmemory = (TextView) findViewById(R.id.tv_custom_dialog_witmemory);
//        tv_custom_dialog_witmemory_notice = (TextView) findViewById(R.id.tv_custom_dialog_witmemory_notice);
//        tv_custom_dialog_witmemory_no = (RelativeLayout) findViewById(R.id.tv_custom_dialog_witmemory_no);
//        lav_event_fire = (LottieAnimationView) findViewById(R.id.lav_event_fire);

        iv_custom_dialog_witmemory_can.setOnClickListener(onClickListener);
        iv_custom_dialog_witmemory_con.setOnClickListener(onClickListener);
//        if (mango.equals("")) {
//            iv_custom_dialog_witmemory_img.setImageResource(R.drawable.popup_title_reward);
//            iv_custom_dialog_witmemory_can.setVisibility(View.GONE);
//            iv_custom_dialog_witmemory_con.setVisibility(View.GONE);
////            iv_custom_dialog_witmemory_can_flag.setVisibility(View.VISIBLE);
//            iv_custom_dialog_witmemory_con_flag.setVisibility(View.VISIBLE);
////            iv_custom_dialog_witmemory_can_flag.setOnClickListener(onClickListener);
//            iv_custom_dialog_witmemory_con_flag.setOnClickListener(onClickListener);
//            tv_custom_dialog_witmemory.setVisibility(View.GONE);
//            tv_custom_dialog_witmemory_notice.setVisibility(View.VISIBLE);
////            tv_custom_dialog_witmemory_notice.setText(mContext.getString(R.string.witmemorypop));
//            tv_custom_dialog_witmemory_no.setVisibility(View.VISIBLE);
//            tv_custom_dialog_witmemory_no.setOnClickListener(onClickListener);
//
//            iv_custom_dialog_witmemory_ad_flag_con.setVisibility(View.GONE);
//            iv_custom_dialog_witmemory_ad_flag_can.setVisibility(View.GONE);
//        }else if (mango.equals("AD")) {//TODO 광고 시청할래?
//
//            iv_custom_dialog_witmemory_can.setVisibility(View.GONE);
//            iv_custom_dialog_witmemory_con.setVisibility(View.GONE);
//            tv_custom_dialog_witmemory.setVisibility(View.VISIBLE);
////            tv_custom_dialog_witmemory.setText(mContext.getString(R.string.game_AD));
//            iv_custom_dialog_witmemory_ad_flag_con.setVisibility(View.VISIBLE);
//            iv_custom_dialog_witmemory_ad_flag_can.setVisibility(View.VISIBLE);
//            iv_custom_dialog_witmemory_ad_flag_con.setOnClickListener(onClickListener);
//            iv_custom_dialog_witmemory_ad_flag_can.setOnClickListener(onClickListener);
//
//        }else {
//            iv_custom_dialog_witmemory_ad_flag_con.setVisibility(View.GONE);
//            iv_custom_dialog_witmemory_ad_flag_can.setVisibility(View.GONE);
//            if (mango.equals("0")){
//                tv_custom_dialog_witmemory.setText("수고하셨습니다!");
//            }else{
//                tv_custom_dialog_witmemory.setText(mango + "망고 획득!");
//            }
//        }
        setGradient(mContext,ll_custom_dialog_witmemory,R.drawable.shape_f5f4ed_ra15_sto);
    }

    @Override
    public void show() {
        if (isShowing() || mContext == null || mContext.isFinishing()) return;
        super.show();
    }
    void setGradient(Context context, View view, int so) {
        GradientDrawable drawable2 = (GradientDrawable) context.getDrawable(so);
        view.setBackground(drawable2);
        view.setClipToOutline(true);
    }
}
