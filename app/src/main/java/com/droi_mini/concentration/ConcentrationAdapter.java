//package com.droi_mini.concentration;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.droi_mini.R;
//import com.droi_mini.dto.DTO_Concentration;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class ConcentrationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Context mContext;
//    //	private ArrayList<DTO_Practice> arrayList;
//    private ArrayList<DTO_Concentration> arrayList;
//
//    public ConcentrationAdapter(Context context, ArrayList<DTO_Concentration> arrayList, View.OnClickListener clickListener) {
//        this.mContext = context;
//        this.arrayList = arrayList;
//        this.clickListener = clickListener;
//    }
//
//    private View.OnClickListener clickListener;
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view;
////        if (viewType == 1) {
////            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainevent, parent, false);
////            return new ViewHolder_mainEvent(view);
////        }
////        //        else if (viewType == 2) {
//////            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice_title, parent, false);
//////            return new ViewHolder_title_none(view);
//////        }
////        else {
//        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_concentration, parent, false);
//        return new ViewHolder(view);
////        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder recyclerViewHolder, int position) {
//        int aPosition = recyclerViewHolder.getAdapterPosition();
//        DTO_Concentration dto = arrayList.get(aPosition);
//        ViewHolder viewHolder = (ViewHolder) recyclerViewHolder;
//
//        viewHolder.rl_witmemory_root.setOnClickListener(clickListener);
//        viewHolder.rl_witmemory_root.setTag(aPosition);
////        viewHolder.tv_item_witmemory.setText(dto.getNum() + "");
//
////        if (dto.getIsSuc().equals("Y")) {
////            viewHolder.rl_witmemory_root.setVisibility(View.INVISIBLE);
////        } else {
////            viewHolder.rl_witmemory_root.setVisibility(View.VISIBLE);
////        }
////
////        Animation anim;
////        float alp = 1;
////        viewHolder.tv_item_witmemory.setAlpha(alp);
//////        if (dto.getNum()<11){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_10);
//////            alp = (float) 0.9;
//////        }else if (dto.getNum()<21){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_20);
//////            alp = (float) 0.8;
//////        }else if (dto.getNum()<31){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_30);
//////            alp = (float) 0.7;
//////        }else if (dto.getNum()<41){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_40);
//////            alp = (float) 0.6;
//////        }else if (dto.getNum()<51){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_50);
//////            alp = (float) 0.5;
//////        }else if (dto.getNum()<61){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_60);
//////            alp = (float) 0.4;
//////        }else if (dto.getNum()<71){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_70);
//////            alp = (float) 0.3;
//////        }else if (dto.getNum()<81){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_80);
//////            alp = (float) 0.2;
//////        }else if (dto.getNum()<91){
//////            anim = AnimationUtils.loadAnimation(mContext,R.anim.alpha_90);
//////            alp = (float) 0.1;
//////        }else{
////        anim = AnimationUtils.loadAnimation(mContext, R.anim.alpha_100);
////        alp = (float) 0.0;
//////        }
////
////        if (dto.isAlpah()) {
////            viewHolder.tv_item_witmemory.startAnimation(anim);
////            float finalAlp = alp;
////            anim.setAnimationListener(new Animation.AnimationListener() {
////                @Override
////                public void onAnimationStart(Animation animation) {
////
////                }
////
////                @Override
////                public void onAnimationEnd(Animation animation) {
////                    viewHolder.tv_item_witmemory.setAlpha(finalAlp);
////                }
////
////                @Override
////                public void onAnimationRepeat(Animation animation) {
////
////                }
////            });
////        }
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    protected class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tv_item_witmemory)
//        TextView tv_item_witmemory;
//        @BindView(R.id.rl_witmemory_root)
//        RelativeLayout rl_witmemory_root;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//}
