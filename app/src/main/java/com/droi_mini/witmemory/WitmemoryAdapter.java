package com.droi_mini.witmemory;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.droi_mini.R;
import com.droi_mini.dto.DTO_Witmemory;

import java.util.ArrayList;



public class WitmemoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mContext;
    //	private ArrayList<DTO_Practice> arrayList;
    private ArrayList<DTO_Witmemory> arrayList;
    public boolean isAd = false;
    public String firstNum = "";

    public WitmemoryAdapter(Activity context, ArrayList<DTO_Witmemory> arrayList, View.OnClickListener clickListener) {
        this.mContext = context;
        this.arrayList = arrayList;
        this.clickListener = clickListener;
    }

    private View.OnClickListener clickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
//        if (viewType == 1) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainevent, parent, false);
//            return new ViewHolder_mainEvent(view);
//        }
//        //        else if (viewType == 2) {
////            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice_title, parent, false);
////            return new ViewHolder_title_none(view);
////        }
//        else {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_witmemory, parent, false);
        return new ViewHolder(view);
//        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder recyclerViewHolder, int position) {
        int aPosition = recyclerViewHolder.getAdapterPosition();
        DTO_Witmemory dto = arrayList.get(aPosition);
        ViewHolder viewHolder = (ViewHolder) recyclerViewHolder;
        if (dto.getIsSuc().equals("Y")) {
            viewHolder.rl_witmemory_root.setVisibility(View.GONE);
            viewHolder.rl_witmemory_root.setEnabled(false);
//            viewHolder.rl_witmemory_root.setOnClickListener(null);
        } else {
            viewHolder.rl_witmemory_root.setVisibility(View.VISIBLE);
            viewHolder.rl_witmemory_root.setEnabled(true);
//            viewHolder.rl_witmemory_root.setOnClickListener(clickListener);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int displaywidth = metrics.widthPixels;
        int displayheight = metrics.heightPixels;

        float widthrate = displaywidth / 720f;
        float heightrate = displayheight / 1280f;
        float drawrate = (widthrate + heightrate) / 1.25f;
        viewHolder.rl_witmemory_root.getLayoutParams().width = (int) (100 * drawrate);
        viewHolder.rl_witmemory_root.getLayoutParams().height = (int) (100 * drawrate);

        viewHolder.rl_witmemory_root.setOnClickListener(clickListener);
        viewHolder.rl_witmemory_root.setTag(aPosition);
        viewHolder.tv_item_witmemory.setText(dto.getNum() + "");

        if (dto.getIsFail().equals("Y")) {
            viewHolder.rl_witmemory_root.setBackgroundResource(R.drawable.game_card_fail_bg);
        } else {
//            Logger.loge("isAd   :  "   +    isAd   + "     firstNum   : " + firstNum  +   "      dto.getNum() : "    +  dto.getNum());
            if (isAd) {
                if (firstNum.equals(dto.getNum()+"")) {
                    viewHolder.rl_witmemory_root.setBackgroundResource(R.drawable.game_card_first_bg);
                }else{
                    viewHolder.rl_witmemory_root.setBackgroundResource(R.drawable.game_card_bg);
                }
            } else {
                viewHolder.rl_witmemory_root.setBackgroundResource(R.drawable.game_card_bg);
            }

        }
        Animation anim;
        float alp = 1;
        viewHolder.tv_item_witmemory.setAlpha(alp);
//        if (dto.getNum()<11){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_10);
//            alp = (float) 0.9;
//        }else if (dto.getNum()<21){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_20);
//            alp = (float) 0.8;
//        }else if (dto.getNum()<31){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_30);
//            alp = (float) 0.7;
//        }else if (dto.getNum()<41){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_40);
//            alp = (float) 0.6;
//        }else if (dto.getNum()<51){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_50);
//            alp = (float) 0.5;
//        }else if (dto.getNum()<61){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_60);
//            alp = (float) 0.4;
//        }else if (dto.getNum()<71){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_70);
//            alp = (float) 0.3;
//        }else if (dto.getNum()<81){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_80);
//            alp = (float) 0.2;
//        }else if (dto.getNum()<91){
//            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_90);
//            alp = (float) 0.1;
//        }else{
        anim = AnimationUtils.loadAnimation(mContext, R.anim.alpha_100);
        alp = (float) 0.0;
        if (dto.isAlpah()) {
            viewHolder.tv_item_witmemory.startAnimation(anim);
            float finalAlp = alp;
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    viewHolder.tv_item_witmemory.setAlpha(finalAlp);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_witmemory;
        RelativeLayout rl_witmemory_root;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_item_witmemory = itemView.findViewById(R.id.tv_item_witmemory);
            rl_witmemory_root = itemView.findViewById(R.id.rl_witmemory_root);
        }
    }
}
