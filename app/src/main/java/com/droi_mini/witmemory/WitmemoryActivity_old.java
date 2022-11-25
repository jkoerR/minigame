//package com.droi_mini.witmemory;
//
//import android.content.Context;
//import android.graphics.drawable.GradientDrawable;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.os.Handler;
//import android.os.SystemClock;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.droi_mini.BaseActivity;
//import com.droi_mini.CustomRecyclerDecoration_Ve;
//import com.droi_mini.R;
//import com.droi_mini.dto.DTO_Witmemory;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class WitmemoryActivity_old extends BaseActivity {
//
//    /*ui*/
//    @BindView(R.id.rl_witmemory_start)
//    RelativeLayout rl_witmemory_start;
//    @BindView(R.id.tv_witmemory_title)
//    TextView tv_witmemory_title;
//    @BindView(R.id.tv_witmemory_title_minus)
//    TextView tv_witmemory_title_minus;
//    @BindView(R.id.rl_witmemory_back)
//    RelativeLayout rl_witmemory_back;
//    //    @BindView(R.id.rl_witmemory_rank)
////    RelativeLayout rl_witmemory_rank;
//    @BindView(R.id.rv_witmemory)
//    RecyclerView rv_witmemory;
//    @BindView(R.id.rl_witmemory_intro)
//    RelativeLayout rl_witmemory_intro;
//    @BindView(R.id.iv_witmemory_intro_start)
//    ImageView iv_witmemory_intro_start;
//    @BindView(R.id.iv_witmemory_intro_ranking)
//    ImageView iv_witmemory_intro_ranking;
//
////    @BindView(R.id.tv_witmemory_usr_cash)
////    TextView tv_witmemory_usr_cash;
////    @BindView(R.id.tv_witmemory_maxnum)
////    TextView tv_witmemory_maxnum;
//////    @BindView(R.id.rl_witmemory_rank_root)
//////    RelativeLayout rl_witmemory_rank_root;
////    @BindView(R.id.rl_witmemory_rank_root_sub)
////    RelativeLayout rl_witmemory_rank_root_sub;
////    @BindView(R.id.rv_witmemory_ranking)
////    RecyclerView rv_witmemory_ranking;
////    @BindView(R.id.rl_witmemory_top10)
////    RelativeLayout rl_witmemory_top10;
////    @BindView(R.id.tv_witmemory_top10)
////    TextView tv_witmemory_top10;
////    @BindView(R.id.rl_witmemory_cur10)
////    RelativeLayout rl_witmemory_cur10;
////    @BindView(R.id.tv_witmemory_cur10)
////    TextView tv_witmemory_cur10;
////    @BindView(R.id.iv_witmemory_game_close)
////    ImageView iv_witmemory_game_close;
////    @BindView(R.id.tv_witmemory_myrank)
////    TextView tv_witmemory_myrank;
////    @BindView(R.id.tv_witmemory_mynick)
////    TextView tv_witmemory_mynick;
////    @BindView(R.id.tv_witmemory_mymaxnum)
////    TextView tv_witmemory_mymaxnum;
//    @BindView(R.id.iv_witmemory_intro)
//    ImageView iv_witmemory_intro;
////    @BindView(R.id.rl_witmemory_rewarded)
////    RelativeLayout rl_witmemory_rewarded;
////    @BindView(R.id.tv_witmemory_rewarded)
////    TextView tv_witmemory_rewarded;
////    @BindView(R.id.tv_witmemory_rank_none)
////    TextView tv_witmemory_rank_none;
////    @BindView(R.id.ll_witmemory_ranking_reborn)
////    LinearLayout ll_witmemory_ranking_reborn;
////    @BindView(R.id.rl_witmemory_ranking_my_in)
////    RelativeLayout rl_witmemory_ranking_my_in;
////    @BindView(R.id.ll_witmemory_ranking_rewarded)
////    LinearLayout ll_witmemory_ranking_rewarded;
//    @BindView(R.id.rv_witmemory_fake)
//    RecyclerView rv_witmemory_fake;
//
//    /*adapter*/
////    Main_Presenter main_presenter;
//    Handler handler = new Handler();
//    CountDownTimer countDownTimer;
//    WitmemoryAdapter witmemoryAdapter;
//    WitmemoryAdapter witmemoryAdapter_fake;
////    Witmemory_rankAdapter witmemory_rankAdapter;
////    Gson gson = new Gson();
//    CustomDialog_Witmemory customDialog_witmemory;
//
//    /*data*/
//    public static Context mContext;
//    ArrayList<DTO_Witmemory> dto_witmemories = new ArrayList<>();
//    ArrayList<DTO_Witmemory> dto_witmemories_fake = new ArrayList<>();
//
//    //받아올 데이터 start
//    int startNum = 1; //시작시 처음 수
//    int endNum = 12; //시작시 마지막 수
//    int addNum = 12; //다음 라운드 추가되는 수
//    long countDown = 15100; //처음 남은 시간
//    int plus_time = 3000; //라운드별 추가 시간
//    int difficulty = 2;  //라운드마다 사라지는 숫자 갯수 늘어나는 폭
//    int continue_time = 5100;  //라운드마다 사라지는 숫자 갯수 늘어나는 폭
//    //받아올 데이터 end
//    int maxNum = 1; //기록 숫자
//
////    int revive_limit_cnt = 5;   //이어하기 숫자
//    double penalty = 500;   //패널티 숫자
//
//
//    boolean isAd = false;  //광고본 후 알려주기
////    boolean isContinue = false; //이어하기 할래?
////    boolean isRevive = false; //이어하기 했니?
//
//
////    ArrayList<DTO_WM_Award> dto_wm_awards_top10 = new ArrayList<>();
////    ArrayList<DTO_WM_Award> dto_wm_awards_cur10 = new ArrayList<>();
////    ArrayList<DTO_WM_Award> dto_wm_awards_list_cash = new ArrayList<>();
////    ArrayList<DTO_WM_Award> dto_rank_base = new ArrayList<>();
//
//    boolean isFail = false;
//    boolean isTouch = false;
//    boolean isDema = false;
//    long remaining = 0; //남은 시간
//    int r_Count = 0; //라운드별 총 사라지는 갯수
////    int revive_ad = 0;
//
//    boolean isReady = false;
//
//    String usr_cash = "";
//    String my_tot_rank = "";
//    String my_cur_rank = "";
//    String my_tot_step = "";
//    String my_cur_step = "";
//    String usr_nick = "";
//    String today_get_price_flag = "N";
//
////    String key_sq = "";
//
//    int ADMOBCALLBACK = 1537;
//
//    private static final long MIN_CLICK_INTERVAL = 0;
//    private long mLastClickTime = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_witmemory);
//        ButterKnife.bind(this);
////        main_presenter = new Main_Presenter(this, this);
//        mContext = this;
//        init();
//        isReady = true;
////        request_maxnum_enter();
//
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onBackPressed() {
//
////        if (rl_witmemory_rank_root.getVisibility() == View.VISIBLE) {
////            rl_witmemory_rank_root.setVisibility(View.GONE);
////            if (isFail) {
////                if (MainActivity.mContext != null)
////                    ((MainActivity) MainActivity.mContext).ref_Gameroom();
////                super.onBackPressed();
////            }
////        } else
////            if (countDownTimer != null) {
////            isContinue = true;
////            countDownTimer.onFinish();
////            countDownTimer.cancel();
////            countDownTimer = null;
////        } else {
////            if (MainActivity.mContext != null)
////                ((MainActivity) MainActivity.mContext).ref_Gameroom();
////            super.onBackPressed();
////        }
//        finish();
//    }
//
//    private void init() {
//
//        RequestOptions options = new RequestOptions();
//        options.centerCrop();
//        Glide.with(this).load(R.drawable.game_intro_bg).apply(options).into(iv_witmemory_intro);
//
//
////        rl_witmemory_rank_root.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View view, MotionEvent motionEvent) {
////                return true;
////            }
////        });
//
//        rl_witmemory_back.setOnClickListener(onClickListener);
//        rl_witmemory_start.setOnClickListener(onClickListener);
//        iv_witmemory_intro_start.setOnClickListener(onClickListener);
////        rl_witmemory_top10.setOnClickListener(onClickListener);
////        rl_witmemory_cur10.setOnClickListener(onClickListener);
////        iv_witmemory_game_close.setOnClickListener(onClickListener);
//        iv_witmemory_intro_ranking.setOnClickListener(onClickListener);
////        rl_witmemory_rewarded.setOnClickListener(onClickListener);
//
//
//        GridLayoutManager gridLayout = new GridLayoutManager(WitmemoryActivity_old.this, 3);
//        rv_witmemory.setLayoutManager(gridLayout);
////        rv_witmemory.addItemDecoration(new CustomRecyclerDecoration_Ho(7));
//        rv_witmemory.addItemDecoration(new CustomRecyclerDecoration_Ve(7));
//        witmemoryAdapter = new WitmemoryAdapter(WitmemoryActivity_old.this, dto_witmemories, onClickListener);
//        rv_witmemory.setAdapter(witmemoryAdapter);
//
//        rl_witmemory_intro.setVisibility(View.VISIBLE);
////        rl_witmemory_rank_root.setVisibility(View.GONE);
//
//        GridLayoutManager gridLayout_fake = new GridLayoutManager(WitmemoryActivity_old.this, 3);
//        rv_witmemory_fake.setLayoutManager(gridLayout_fake);
////        rv_witmemory.addItemDecoration(new CustomRecyclerDecoration_Ho(7));
//        rv_witmemory_fake.addItemDecoration(new CustomRecyclerDecoration_Ve(7));
//
//        for (int i = 1; i < 13 + 1; i++) {
//            dto_witmemories_fake.add(new DTO_Witmemory(i, "N", "N", false));
//        }
//        witmemoryAdapter_fake = new WitmemoryAdapter(WitmemoryActivity_old.this, dto_witmemories_fake, onClickListener);
//        rv_witmemory_fake.setAdapter(witmemoryAdapter_fake);
//
////        setGradient(WitmemoryActivity.this, rl_witmemory_rank_root_sub, R.drawable.shape_ffffff_ra38_sto);
//
//
////        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
////        rv_witmemory_ranking.setLayoutManager(LinearLayoutManager);
//////        practice_rentAdapter = new Practice_RentAdapter(this, dto_practices, onClickListener);
////        witmemory_rankAdapter = new Witmemory_rankAdapter(this, dto_rank_base, onClickListener);
////        rv_witmemory_ranking.setAdapter(witmemory_rankAdapter);
//    }
//    void setGradient(Context context, View view, int so) {
//        GradientDrawable drawable2 = (GradientDrawable) context.getDrawable(so);
//        view.setBackground(drawable2);
//        view.setClipToOutline(true);
//    }
//
////    private void request_maxnum_enter() {
////        JsonObject base = Util.baseJson();
////        JsonObject jsonObject = new JsonObject();
//////        jsonObject.addProperty("quiz_sq", quiz_sq);
//////        Logger.loge("jsonObject   :  " + jsonObject);
////        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
////        base.addProperty("box_req", box_req);
//////        Logger.loge("base  :  " + base);
////        main_presenter.maxnum_enter(base);
////    }
//
////    private void request_maxnum_rst(String maxNum) {
////        JsonObject base = Util.baseJson();
////        JsonObject jsonObject = new JsonObject();
////        jsonObject.addProperty("maxNum", maxNum);
////        jsonObject.addProperty("key_sq", key_sq);
////        Logger.loge("jsonObject   :  " + jsonObject);
////        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
////        base.addProperty("box_req", box_req);
//////        Logger.loge("base  :  " + base);
////        main_presenter.maxnum_rst(base);
////    }
////
////    private void request_maxnum_start() {
////        JsonObject base = Util.baseJson();
////        JsonObject jsonObject = new JsonObject();
//////        jsonObject.addProperty("maxNum", maxNum);
//////        Logger.loge("jsonObject   :  " + jsonObject);
////        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
////        base.addProperty("box_req", box_req);
//////        Logger.loge("base  :  " + base);
////        main_presenter.maxnum_start(base);
////    }
//
//    private void startGame() {
////        if (key_sq.equals("")) {
//////            request_maxnum_start();
////        }
//
//        if (!isReady) return;
//        countDownTimer = new CountDownTimer(countDown, 1000) {
//            public void onTick(long millisUntilFinished) {
//                remaining = millisUntilFinished;
//                tv_witmemory_title.setText("" + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                isFail = true;
////                tv_witmemory_title.setText("실패!");
////                if (!isContinue && (revive_ad < revive_limit_cnt)) {
//                    if (customDialog_witmemory != null && customDialog_witmemory.isShowing())
//                        return;
//                    customDialog_witmemory = new CustomDialog_Witmemory(WitmemoryActivity_old.this, onClickListener);
//                    customDialog_witmemory.setCancelable(false);
//                    customDialog_witmemory.show();
////                } else {
////                    request_maxnum_rst(maxNum + "");
////                }
////                countDownTimer = null;
//            }
//        };
////        if (!isRevive) {
//            dto_witmemories.clear();
//            for (int i = startNum; i < endNum + 1; i++) {
//                dto_witmemories.add(new DTO_Witmemory(i, "N", "N", false));
//            }
//            Collections.shuffle(dto_witmemories);
//            Random random = new Random();
//            for (int i = 0; i < r_Count; i++) {
//                int randomValue = random.nextInt(10);
//                DTO_Witmemory dto_witmemory = dto_witmemories.get(randomValue);
//                dto_witmemory.setAlpah(true);
//                dto_witmemories.set(randomValue, dto_witmemory);
//            }
//            r_Count = r_Count + difficulty;
//            witmemoryAdapter.isAd = this.isAd;
//            witmemoryAdapter.firstNum = startNum + "";
////        }
//        witmemoryAdapter.notifyDataSetChanged();
////        isRevive = false;
//        rv_witmemory.setVisibility(View.VISIBLE);
//        countDownTimer.start();
//    }
//
//
////    @Override
////    public void result(JsonObject result, String from) {
//////        Logger.loge(from + "   :   " + result);
////        String box_res;
////        JsonObject jsonObject;
////        String code = result.get("rst_cd").getAsString();
////        DTO_WM_Award dto_wm_award;
////        switch (code) {
////            case Const.API_SUC:
////                switch (from) {
////                    case Const.MAXNUM_START:
////                        box_res = result.get("box_res").getAsString();
////                        box_res = Security.decrypt(box_res, Const.AES_KEY);
//////                        Logger.loge("box_res   :  " + box_res);
////                        jsonObject = gson.fromJson(box_res, JsonObject.class);
////                        key_sq = jsonObject.get("key_sq").getAsString();
////
////                        break;
////                    case Const.MAXNUM_ENTER:
////                        box_res = result.get("box_res").getAsString();
////                        box_res = Security.decrypt(box_res, Const.AES_KEY);
//////                        Logger.loge("box_res   :  " + box_res);
////                        jsonObject = gson.fromJson(box_res, JsonObject.class);
////
////                        startNum = jsonObject.get("startNum").getAsInt();
//////                        startNum = 1201;
////                        maxNum = startNum;
////                        endNum = jsonObject.get("endNum").getAsInt();
//////                        endNum = 1212;
////                        addNum = jsonObject.get("addNum").getAsInt();
////                        countDown = jsonObject.get("countDown").getAsInt();
////                        plus_time = jsonObject.get("plus_time").getAsInt();
////                        difficulty = jsonObject.get("difficulty").getAsInt();
////                        continue_time = jsonObject.get("continue_time").getAsInt();
////                        revive_limit_cnt = jsonObject.get("revive_limit_cnt").getAsInt();
////                        penalty = jsonObject.get("penalty").getAsDouble();
////
//////                        if (CApp.test) {
//////                            countDown = 15100; //처음 남은 시간
//////                            plus_time = 3000; //라운드별 추가 시간
//////                        }
////
////                        dto_wm_awards_top10.clear();
////                        for (JsonElement element : jsonObject.get("list_tot10").getAsJsonArray()) {
////                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
////                            dto_wm_award.setItemViewType(0);
////                            dto_wm_awards_top10.add(dto_wm_award);
////                        }
////                        dto_wm_awards_cur10.clear();
////                        for (JsonElement element : jsonObject.get("list_cur10").getAsJsonArray()) {
////                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
////                            dto_wm_award.setItemViewType(0);
////                            dto_wm_awards_cur10.add(dto_wm_award);
////                        }
////                        dto_wm_awards_list_cash.clear();
////                        Logger.loge(jsonObject.get("list_cash").getAsJsonArray().toString());
////                        for (JsonElement element : jsonObject.get("list_cash").getAsJsonArray()) {
////                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
////                            dto_wm_award.setItemViewType(1);
////                            dto_wm_awards_list_cash.add(dto_wm_award);
////                        }
//////                        dto_wm_award = new DTO_WM_Award("3","1000","N","1000");
//////                        dto_wm_award.setItemViewType(1);
//////                        dto_wm_awards_list_cash.add(dto_wm_award);
//////                        dto_wm_award = new DTO_WM_Award("4","1000","N","1000");
//////                        dto_wm_award.setItemViewType(1);
//////                        dto_wm_awards_list_cash.add(dto_wm_award);
//////                        dto_wm_award = new DTO_WM_Award("5","1000","N","1000");
//////                        dto_wm_award.setItemViewType(1);
//////                        dto_wm_awards_list_cash.add(dto_wm_award);
//////                        dto_wm_award = new DTO_WM_Award("6","1000","N","1000");
//////                        dto_wm_award.setItemViewType(1);
//////                        dto_wm_awards_list_cash.add(dto_wm_award);
//////                        dto_wm_award = new DTO_WM_Award("7","1000","N","1000");
//////                        dto_wm_award.setItemViewType(1);
//////                        dto_wm_awards_list_cash.add(dto_wm_award);
////                        if (dto_wm_awards_cur10.size() == 0) {
////                            tv_witmemory_rank_none.setVisibility(View.VISIBLE);
////                        } else {
////                            tv_witmemory_rank_none.setVisibility(View.GONE);
////                        }
////                        isReady = true;
////                        try {
////                            usr_cash = jsonObject.get("usr_cash").getAsString();
////                            my_cur_rank = jsonObject.get("my_cur_rank").getAsString();
////                            my_tot_rank = jsonObject.get("my_tot_rank").getAsString();
////                            my_tot_step = jsonObject.get("my_tot_step").getAsString();
////                            my_cur_step = jsonObject.get("my_cur_step").getAsString();
////                            usr_nick = jsonObject.get("usr_nick").getAsString();
////                            today_get_price_flag = jsonObject.get("today_get_price_flag").getAsString();
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////
////                        String flag = (String) Util.getCacheDataForKey(Const.WITMEMORY_POP);
////                        if (today_get_price_flag.equals("Y") && (flag == null || flag.equals(""))) {
////                            if (customDialog_witmemory != null && customDialog_witmemory.isShowing())
////                                return;
////                            customDialog_witmemory = new CustomDialog_Witmemory(WitmemoryActivity.this, onClickListener, "");
////                            customDialog_witmemory.setCancelable(false);
////                            customDialog_witmemory.show();
////                        }
////                        tv_witmemory_mynick.setText(usr_nick);
////                        tv_witmemory_usr_cash.setText(Util.myFormatter(usr_cash) + "망고");
////
////                        break;
////                    case Const.MAXNUM_RST:
////                        box_res = result.get("box_res").getAsString();
////                        box_res = Security.decrypt(box_res, Const.AES_KEY);
////                        Logger.loge("box_res   :  " + box_res);
////                        jsonObject = gson.fromJson(box_res, JsonObject.class);
////
////                        String add_cash = jsonObject.get("add_cash").getAsString();
////                        String result_yn = jsonObject.get("result_yn").getAsString();
////
////                        dto_wm_awards_top10.clear();
////                        for (JsonElement element : jsonObject.get("list_tot10").getAsJsonArray()) {
////                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
////                            dto_wm_award.setItemViewType(0);
////                            dto_wm_awards_top10.add(dto_wm_award);
////                        }
////                        dto_wm_awards_cur10.clear();
////                        for (JsonElement element : jsonObject.get("list_cur10").getAsJsonArray()) {
////                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
////                            dto_wm_award.setItemViewType(0);
////                            dto_wm_awards_cur10.add(dto_wm_award);
////                        }
////                        if (dto_wm_awards_cur10.size() == 0) {
////                            tv_witmemory_rank_none.setVisibility(View.VISIBLE);
////                        } else {
////                            tv_witmemory_rank_none.setVisibility(View.GONE);
////                        }
////                        isReady = true;
////
////                        try {
////                            usr_cash = jsonObject.get("usr_cash").getAsString();
////                            my_cur_rank = jsonObject.get("my_cur_rank").getAsString();
////                            my_tot_rank = jsonObject.get("my_tot_rank").getAsString();
////                            my_tot_step = jsonObject.get("my_tot_step").getAsString();
////                            my_cur_step = jsonObject.get("my_cur_step").getAsString();
////                            usr_nick = jsonObject.get("usr_nick").getAsString();
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////
////                        tv_witmemory_mynick.setText(usr_nick);
////                        tv_witmemory_usr_cash.setText(Util.myFormatter(usr_cash) + "망고");
////
//////                        if (isExit) {
//////                            onBackPressed();
//////                        } else {
//////                            rl_witmemory_intro.setVisibility(View.VISIBLE);
//////                            rl_witmemory_rank_root.setVisibility(View.GONE);
//////                            isFail = false;
//////                            isTouch = false;
//////                            remaining = 0; //남은 시간
//////                            r_Count = 0; //라운드별 총 사라지는 갯수
//////                            isReady = false;
//////                            key_sq = "";
//////                            request_maxnum_enter();
//////                        }
////
////                        if (customDialog_witmemory != null && customDialog_witmemory.isShowing())
////                            return;
////                        customDialog_witmemory = new CustomDialog_Witmemory(WitmemoryActivity.this, onClickListener, Util.myFormatter(add_cash));
////                        customDialog_witmemory.setCancelable(false);
////                        customDialog_witmemory.show();
////                        countDownTimer = null;
////
////                        break;
////                }
////                break;
////            default:
////                Util.showToast(getApplicationContext(), result.get("rst_msg").getAsString());
////                break;
////        }
////    }
//
//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            int position = 0;
//            DTO_Witmemory dto_witmemory;
//            switch (view.getId()) {
//                case R.id.iv_custom_dialog_witmemory_can: //다시하기
//                    customDialog_witmemory.cancel();
//                    countDownTimer.cancel();
//                    rl_witmemory_intro.setVisibility(View.VISIBLE);
////                    rl_witmemory_rank_root.setVisibility(View.GONE);
//                    isFail = false;
//                    isTouch = false;
//                    remaining = 0; //남은 시간
//                    r_Count = 0; //라운드별 총 사라지는 갯수
//                    isReady = true;
//                    isAd = true;
//
//                    //받아올 데이터 start
//                    startNum = 1; //시작시 처음 수
//                    endNum = 12; //시작시 마지막 수
//                    addNum = 12; //다음 라운드 추가되는 수
//                    countDown = 15100; //처음 남은 시간
//                    plus_time = 3000; //라운드별 추가 시간
//                    difficulty = 2;  //라운드마다 사라지는 숫자 갯수 늘어나는 폭
//                    continue_time = 5100;  //라운드마다 사라지는 숫자 갯수 늘어나는 폭
//                    //받아올 데이터 end
//                    maxNum = 1; //기록 숫자
////    int revive_limit_cnt = 5;   //이어하기 숫자
//                    penalty = 500;   //패널티 숫자
////                    startGame();
//
//
////                    request_maxnum_enter();
//
//
////                    rl_witmemory_top10.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_top10.setTextColor(Color.parseColor("#f8f3d2"));
////                    rl_witmemory_cur10.setBackgroundColor(Color.parseColor("#f8f3d2"));
////                    tv_witmemory_cur10.setTextColor(Color.parseColor("#6b3a12"));
////                    rl_witmemory_rewarded.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_rewarded.setTextColor(Color.parseColor("#f8f3d2"));
////                    dto_rank_base.clear();
////                    dto_rank_base.addAll(dto_wm_awards_cur10);
////                    witmemory_rankAdapter.notifyDataSetChanged();
////                    ll_witmemory_ranking_reborn.setVisibility(View.VISIBLE);
////                    rl_witmemory_ranking_my_in.setVisibility(View.VISIBLE);
////                    ll_witmemory_ranking_rewarded.setVisibility(View.GONE);
////
////                    if (my_cur_rank.equals("")) my_cur_rank = "0";
////                    tv_witmemory_myrank.setText(my_cur_rank + "등");
////                    tv_witmemory_mymaxnum.setText(my_cur_step);
////
////                    rl_witmemory_rank_root.setVisibility(View.VISIBLE);
//
//                    break;
//                case R.id.iv_custom_dialog_witmemory_con:
//                    customDialog_witmemory.cancel();
//                    onBackPressed();
//                    break;
////                case R.id.iv_witmemory_intro_ranking:
////                    //랭킹 보기
////
////                    rl_witmemory_top10.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_top10.setTextColor(Color.parseColor("#f8f3d2"));
////                    rl_witmemory_cur10.setBackgroundColor(Color.parseColor("#f8f3d2"));
////                    tv_witmemory_cur10.setTextColor(Color.parseColor("#6b3a12"));
////                    rl_witmemory_rewarded.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_rewarded.setTextColor(Color.parseColor("#f8f3d2"));
////                    dto_rank_base.clear();
////                    dto_rank_base.addAll(dto_wm_awards_cur10);
////                    witmemory_rankAdapter.notifyDataSetChanged();
////
////                    ll_witmemory_ranking_reborn.setVisibility(View.VISIBLE);
////                    rl_witmemory_ranking_my_in.setVisibility(View.VISIBLE);
////                    ll_witmemory_ranking_rewarded.setVisibility(View.GONE);
////                    if (my_cur_rank.equals("")) my_cur_rank = "0";
////                    tv_witmemory_myrank.setText(my_cur_rank + "등");
////                    tv_witmemory_mymaxnum.setText(my_cur_step);
////
////                    rl_witmemory_rank_root.setVisibility(View.VISIBLE);
////
////                    break;
////                case R.id.iv_custom_dialog_witmemory_ad_flag_con:
////                    //TODO 광고보고 이어하기
////                    customDialog_witmemory.cancel();
////
////                    Intent intent = new Intent(WitmemoryActivity.this, Ad_Mob_GameActivity.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                    intent.putExtra("none_ad", "N");//광고 없을때 실행시키지마
////                    intent.putExtra("and_game", "G04");//메인쪽 리프레시 하지마
////                    startActivityForResult(intent, ADMOBCALLBACK);
////                    revive_ad++;
////                    Logger.loge("revive_ad  :  " + revive_ad);
////
////                    break;
////                case R.id.iv_custom_dialog_witmemory_ad_flag_can:
////                    customDialog_witmemory.cancel();
////                    request_maxnum_rst(maxNum + "");
////                    break;
////                case R.id.iv_witmemory_game_close:
////                    if (customDialog_witmemory != null) customDialog_witmemory.cancel();
////                    onBackPressed();
////                    break;
////                case R.id.iv_custom_dialog_witmemory_can_flag:
////                    customDialog_witmemory.cancel();
//////                    Util.setCacheData(Const.WITMEMORY_POP, "Y");
////                    break;
////                case R.id.iv_custom_dialog_witmemory_con_flag:
////                    customDialog_witmemory.cancel();
////                    break;
////                case R.id.rl_witmemory_top10:
////
////                    rl_witmemory_top10.setBackgroundColor(Color.parseColor("#f8f3d2"));
////                    tv_witmemory_top10.setTextColor(Color.parseColor("#6b3a12"));
////                    rl_witmemory_cur10.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_cur10.setTextColor(Color.parseColor("#f8f3d2"));
////                    rl_witmemory_rewarded.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_rewarded.setTextColor(Color.parseColor("#f8f3d2"));
////
////                    dto_rank_base.clear();
////                    dto_rank_base.addAll(dto_wm_awards_top10);
////                    witmemory_rankAdapter.notifyDataSetChanged();
////
////                    ll_witmemory_ranking_reborn.setVisibility(View.GONE);
////                    rl_witmemory_ranking_my_in.setVisibility(View.VISIBLE);
////                    ll_witmemory_ranking_rewarded.setVisibility(View.GONE);
////                    if (my_tot_rank.equals("")) my_tot_rank = "0";
////                    tv_witmemory_myrank.setText(my_tot_rank + "등");
////                    tv_witmemory_mymaxnum.setText(my_tot_step);
////
////
////                    break;
////                case R.id.rl_witmemory_cur10:
////
////                    rl_witmemory_top10.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_top10.setTextColor(Color.parseColor("#f8f3d2"));
////                    rl_witmemory_cur10.setBackgroundColor(Color.parseColor("#f8f3d2"));
////                    tv_witmemory_cur10.setTextColor(Color.parseColor("#6b3a12"));
////                    rl_witmemory_rewarded.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_rewarded.setTextColor(Color.parseColor("#f8f3d2"));
////                    dto_rank_base.clear();
////                    dto_rank_base.addAll(dto_wm_awards_cur10);
////                    witmemory_rankAdapter.notifyDataSetChanged();
////
////                    ll_witmemory_ranking_reborn.setVisibility(View.VISIBLE);
////                    rl_witmemory_ranking_my_in.setVisibility(View.VISIBLE);
////                    ll_witmemory_ranking_rewarded.setVisibility(View.GONE);
////                    if (my_cur_rank.equals("")) my_cur_rank = "0";
////                    tv_witmemory_myrank.setText(my_cur_rank + "등");
////                    tv_witmemory_mymaxnum.setText(my_cur_step);
////
////
////                    break;
////                case R.id.rl_witmemory_rewarded:
////
////                    rl_witmemory_top10.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_top10.setTextColor(Color.parseColor("#f8f3d2"));
////                    rl_witmemory_cur10.setBackgroundColor(Color.parseColor("#6b3a12"));
////                    tv_witmemory_cur10.setTextColor(Color.parseColor("#f8f3d2"));
////                    rl_witmemory_rewarded.setBackgroundColor(Color.parseColor("#f8f3d2"));
////                    tv_witmemory_rewarded.setTextColor(Color.parseColor("#6b3a12"));
////
////                    dto_rank_base.clear();
////                    dto_rank_base.addAll(dto_wm_awards_list_cash);
////
////                    witmemory_rankAdapter.notifyDataSetChanged();
////
////                    ll_witmemory_ranking_reborn.setVisibility(View.GONE);
////                    rl_witmemory_ranking_my_in.setVisibility(View.GONE);
////                    ll_witmemory_ranking_rewarded.setVisibility(View.VISIBLE);
////                    break;
//                case R.id.rl_witmemory_back:
//                    onBackPressed();
//                    break;
////                case R.id.tv_custom_dialog_witmemory_no:
////                    customDialog_witmemory.cancel();
////                    Util.setCacheData(Const.WITMEMORY_POP, "Y");
////                    break;
//                case R.id.rl_witmemory_start:
//                    rl_witmemory_start.setVisibility(View.GONE);
//                    startGame();
//                    break;
//                case R.id.iv_witmemory_intro_start:
//                    rl_witmemory_start.setVisibility(View.GONE);
//                    startGame();
//                    rl_witmemory_intro.setVisibility(View.GONE);
//                    break;
//                case R.id.rl_witmemory_root:
//                    long currentClickTime = SystemClock.uptimeMillis();
//                    long elapsedTime = currentClickTime - mLastClickTime;
//                    mLastClickTime = currentClickTime;
//                    if (elapsedTime <= MIN_CLICK_INTERVAL) {
//                        break;
//                    }
//                    position = (int) view.getTag();
//                    dto_witmemory = dto_witmemories.get(position);
//                    int finalPosition = position;
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
////                            Logger.loge("isTouch   out:  " + isTouch);
//                            if (!isTouch) {
////                                isTouch = true;
//                                if (!isFail) {
//                                    if (maxNum < endNum) {
//                                        if ((maxNum) == dto_witmemory.getNum()) { //맞게 누름
//                                            dto_witmemory.setIsSuc("Y");
////                                            dto_witmemories.set(finalPosition, dto_witmemory);
//                                            witmemoryAdapter.notifyItemChanged(finalPosition,dto_witmemory);
//                                            maxNum++;
////                                            tv_witmemory_maxnum.setText((maxNum / 10) + "망고");
////                                            isTouch = false;
//                                        } else {//TODO 잘못누름
//                                            dto_witmemory.setIsFail("Y");
////                                            dto_witmemories.set(finalPosition, dto_witmemory);
//                                            witmemoryAdapter.notifyItemChanged(finalPosition,dto_witmemory);
//                                            countDownTimer.cancel();
//                                            countDown = (long) (remaining - penalty);
//                                            countDownTimer = new CountDownTimer(countDown, 1000) {
//                                                public void onTick(long millisUntilFinished) {
//                                                    remaining = millisUntilFinished;
//                                                    tv_witmemory_title.setText("" + millisUntilFinished / 1000);
//                                                }
//
//                                                public void onFinish() {
//                                                    isFail = true;
////                                                    if (!isContinue && (revive_ad < revive_limit_cnt)) {
//                                                        if (customDialog_witmemory != null && customDialog_witmemory.isShowing())
//                                                            return;
//                                                        customDialog_witmemory = new CustomDialog_Witmemory(WitmemoryActivity_old.this, onClickListener);
//                                                        customDialog_witmemory.setCancelable(false);
//                                                        customDialog_witmemory.show();
////                                                    } else {
////                                                        request_maxnum_rst(maxNum + "");
////                                                    }
//                                                }
//                                            };
//                                            countDownTimer.start();
//                                            tv_witmemory_title_minus.setVisibility(View.VISIBLE);
//                                            tv_witmemory_title_minus.setText(" - " + (penalty / 1000) + "초");
//                                            handler.postDelayed(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    tv_witmemory_title_minus.setVisibility(View.GONE);
//                                                    dto_witmemory.setIsFail("");
////                                                    dto_witmemories.set(finalPosition, dto_witmemory);
////                                                    witmemoryAdapter.notifyItemChanged(finalPosition);
//                                                    witmemoryAdapter.notifyItemChanged(finalPosition,dto_witmemory);
//                                                }
//                                            }, (long) penalty);
////                                            isTouch = false;
//                                        }
//                                    } else { //라운드 성공
//                                        maxNum = endNum + 1;
//                                        startNum = endNum + 1;
//                                        endNum = endNum + addNum;
//                                        if (maxNum > 60) isAd = false;
//                                        countDownTimer.cancel();
//                                        countDown = remaining + plus_time;
//                                        tv_witmemory_title.setText("" + countDown / 1000);
//                                        rl_witmemory_start.setVisibility(View.VISIBLE);
//                                        rv_witmemory.setVisibility(View.GONE);
////                                        isTouch = false;
//                                    }
//                                }
//                            }
////                            isTouch = false;
//                        }
//                    });
////                    }
//                    break;
//            }
//        }
//    };
//
//
////    @Override
////    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == ADMOBCALLBACK) {
////            if (resultCode == RESULT_OK) {
//////                isContinue = true; //1회만 부활
//////                maxNum = startNum;
//////                r_Count = r_Count - difficulty;
////                isAd = true;
////                isRevive = true;
////                isFail = false;
////                countDown = continue_time;
////                if (countDownTimer != null) countDownTimer.cancel();
////                tv_witmemory_title.setText("" + countDown / 1000);
////                rl_witmemory_start.setVisibility(View.VISIBLE);
////                rv_witmemory.setVisibility(View.GONE);
////                isTouch = false;
////            } else {
////                //TODO 광고 안봐와 같아
////                request_maxnum_rst(maxNum + "");
////            }
////        }
////    }
//}
