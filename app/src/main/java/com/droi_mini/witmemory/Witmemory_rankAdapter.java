//package com.droi_mini.witmemory;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.dreamsolutech.openingbox.R;
//import com.dreamsolutech.openingbox.dto.DTO_WM_Award;
//import com.dreamsolutech.openingbox.util.Util;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class Witmemory_rankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Context mContext;
//    //	private ArrayList<DTO_Practice> arrayList;
//    private ArrayList<DTO_WM_Award> arrayList;
//    boolean flag = true;
//    ArrayList<Integer> ints = new ArrayList<Integer>();
//
//    public Witmemory_rankAdapter(Context context, ArrayList<DTO_WM_Award> arrayList, View.OnClickListener clickListener) {
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
//        if (viewType == 1) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_witmemory_rewaded, parent, false);
//            return new ViewHolder_rewaded(view);
//        }else {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_witmemory_rank, parent, false);
//            return new ViewHolder(view);
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder recyclerViewHolder, int position) {
//        int aPosition = recyclerViewHolder.getAdapterPosition();
//        DTO_WM_Award dto = arrayList.get(aPosition);
//        if (recyclerViewHolder instanceof ViewHolder) {
//            ViewHolder viewHolder = (ViewHolder) recyclerViewHolder;
//            viewHolder.rl_item_witmemory_rank_root.setTag(aPosition);
//            viewHolder.rl_item_witmemory_rank_root.setOnClickListener(clickListener);
//
//            if (aPosition % 2 == 0) {
//                viewHolder.rl_item_witmemory_rank_root.setBackgroundColor(Color.parseColor("#faf7e4"));
//            } else {
//                viewHolder.rl_item_witmemory_rank_root.setBackgroundColor(Color.parseColor("#fffdee"));
//            }
//
//            if (dto.getRank().equals("1")) {
//                viewHolder.iv_item_witmemory_rank.setImageResource(R.drawable.icon_ranking_num1);
////                viewHolder.iv_item_witmemory_rank.setImageResource(R.drawable.icon_ranking_win);
//                viewHolder.iv_item_witmemory_rank.setVisibility(View.VISIBLE);
//                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            } else if (dto.getRank().equals("2")) {
//                viewHolder.iv_item_witmemory_rank.setImageResource(R.drawable.icon_ranking_num2);
////                viewHolder.iv_item_witmemory_rank.setImageResource(R.drawable.icon_ranking_win);
//                viewHolder.iv_item_witmemory_rank.setVisibility(View.VISIBLE);
//                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            } else if (dto.getRank().equals("3")) {
//                viewHolder.iv_item_witmemory_rank.setImageResource(R.drawable.icon_ranking_num3);
////                viewHolder.iv_item_witmemory_rank.setImageResource(R.drawable.icon_ranking_win);
//                viewHolder.iv_item_witmemory_rank.setVisibility(View.VISIBLE);
//                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            } else {
//                viewHolder.iv_item_witmemory_rank.setVisibility(View.INVISIBLE);
//                viewHolder.iv_item_witmemory_rank.setImageResource(R.drawable.icon_ranking_win);
////            viewHolder.tv_item_witmemory_rank_rank.setText((aPosition+1)+"등");
//                viewHolder.tv_item_witmemory_rank_rank.setText(dto.getRank() + "등");
////                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            }
//            viewHolder.tv_item_witmemory_rank_maxnum.setText(dto.getGame_step());
//            viewHolder.tv_item_witmemory_rank_nick.setText(dto.getUsr_nick());
//        }else if (recyclerViewHolder instanceof ViewHolder_rewaded) {
//            ViewHolder_rewaded viewHolder = (ViewHolder_rewaded) recyclerViewHolder;
//
//            if (dto.getRank().equals("1")) {
//                viewHolder.iv_item_witmemory_rewarded_rank.setImageResource(R.drawable.icon_ranking_win);
//                viewHolder.iv_item_witmemory_rewarded_rank.setVisibility(View.VISIBLE);
//                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            } else if (dto.getRank().equals("2")) {
//                viewHolder.iv_item_witmemory_rewarded_rank.setImageResource(R.drawable.icon_ranking_win);
//                viewHolder.iv_item_witmemory_rewarded_rank.setVisibility(View.VISIBLE);
//                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            } else if (dto.getRank().equals("3")) {
//                viewHolder.iv_item_witmemory_rewarded_rank.setImageResource(R.drawable.icon_ranking_win);
//                viewHolder.iv_item_witmemory_rewarded_rank.setVisibility(View.VISIBLE);
//                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            }else {
////                viewHolder.iv_item_witmemory_rewarded_rank.setVisibility(View.INVISIBLE);
//////            viewHolder.tv_item_witmemory_rank_rank.setText((aPosition+1)+"등");
////                viewHolder.tv_item_witmemory_rank_rank.setText(dto.getRank() + "등");
//                viewHolder.iv_item_witmemory_rewarded_rank.setImageResource(R.drawable.icon_ranking_win);
//                viewHolder.iv_item_witmemory_rewarded_rank.setVisibility(View.VISIBLE);
//                viewHolder.tv_item_witmemory_rank_rank.setText("");
//            }
//
//            viewHolder.tv_item_witmemory_rewarded_cash.setText(Util.myFormatter(dto.getTot_cash())+"망고");
//            if (dto.getEvent_flag().equals("Y")){
//                viewHolder.iv_item_witmemory_rewarded_event.setVisibility(View.VISIBLE);
//            }else{
//                viewHolder.iv_item_witmemory_rewarded_event.setVisibility(View.GONE);
//            }
//        }
//
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return arrayList.get(position).getItemViewType();
////        return super.getItemViewType(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    protected class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.rl_item_witmemory_rank_root)
//        RelativeLayout rl_item_witmemory_rank_root;
//        @BindView(R.id.tv_item_witmemory_rank_rank)
//        TextView tv_item_witmemory_rank_rank;
//        @BindView(R.id.tv_item_witmemory_rank_nick)
//        TextView tv_item_witmemory_rank_nick;
//        @BindView(R.id.tv_item_witmemory_rank_maxnum)
//        TextView tv_item_witmemory_rank_maxnum;
//        @BindView(R.id.iv_item_witmemory_rank)
//        ImageView iv_item_witmemory_rank;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//    protected class ViewHolder_rewaded extends RecyclerView.ViewHolder {
////        @BindView(R.id.rl_item_witmemory_rank_root)
////        RelativeLayout rl_item_witmemory_rank_root;
//        @BindView(R.id.iv_item_witmemory_rewarded_rank)
//        ImageView iv_item_witmemory_rewarded_rank;
//        @BindView(R.id.tv_item_witmemory_rank_rank)
//        TextView tv_item_witmemory_rank_rank;
//        @BindView(R.id.tv_item_witmemory_rewarded_cash)
//        TextView tv_item_witmemory_rewarded_cash;
//        @BindView(R.id.iv_item_witmemory_rewarded_event)
//        ImageView iv_item_witmemory_rewarded_event;
//
//        public ViewHolder_rewaded(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//}
