package com.droi_mini.witmemory


import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.droi_mini.BaseActivity
import com.droi_mini.CustomRecyclerDecoration_Ve
import com.droi_mini.R
import com.droi_mini.databinding.ActivityConcentrationBinding
import com.droi_mini.databinding.ActivityWitmemoryBinding
import com.droi_mini.dto.DTO_Witmemory
import kotlinx.android.synthetic.main.activity_witmemory.*
import java.util.*


class WitmemoryActivity : BaseActivity() {

    //    var rv_witmemory_fake: RecyclerView? = null
    var binding: ActivityWitmemoryBinding? = null

    /*adapter*/ //    Main_Presenter main_presenter;
    var handler = Handler()
    var countDownTimer: CountDownTimer? = null
    var witmemoryAdapter: WitmemoryAdapter? = null
    var witmemoryAdapter_fake: WitmemoryAdapter? = null

    //    Witmemory_rankAdapter witmemory_rankAdapter;
    //    Gson gson = new Gson();
    var customDialog_witmemory: CustomDialog_Witmemory? = null
    var dto_witmemories = ArrayList<DTO_Witmemory?>()
    var dto_witmemories_fake = ArrayList<DTO_Witmemory>()

    //받아올 데이터 start
    var startNum = 1 //시작시 처음 수
    var endNum = 12 //시작시 마지막 수
    var addNum = 12 //다음 라운드 추가되는 수
    var countDown: Long = 15100 //처음 남은 시간
    var plus_time = 3000 //라운드별 추가 시간
    var difficulty = 2 //라운드마다 사라지는 숫자 갯수 늘어나는 폭
    var continue_time = 5100 //라운드마다 사라지는 숫자 갯수 늘어나는 폭

    //받아올 데이터 end
    var maxNum = 1 //기록 숫자

    //    int revive_limit_cnt = 5;   //이어하기 숫자
    var penalty = 500.0 //패널티 숫자
    var isAd = false //광고본 후 알려주기

    //    boolean isContinue = false; //이어하기 할래?
    //    boolean isRevive = false; //이어하기 했니?
    //    ArrayList<DTO_WM_Award> dto_wm_awards_top10 = new ArrayList<>();
    //    ArrayList<DTO_WM_Award> dto_wm_awards_cur10 = new ArrayList<>();
    //    ArrayList<DTO_WM_Award> dto_wm_awards_list_cash = new ArrayList<>();
    //    ArrayList<DTO_WM_Award> dto_rank_base = new ArrayList<>();
    var isFail = false
    var isTouch = false
    var isDema = false
    var remaining: Long = 0 //남은 시간
    var r_Count = 0 //라운드별 총 사라지는 갯수

    //    int revive_ad = 0;
    var isReady = false
    var usr_cash = ""
    var my_tot_rank = ""
    var my_cur_rank = ""
    var my_tot_step = ""
    var my_cur_step = ""
    var usr_nick = ""
    var today_get_price_flag = "N"

    //    String key_sq = "";
    var ADMOBCALLBACK = 1537
    private var mLastClickTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_witmemory)
//        setContentView(R.layout.activity_witmemory)
        //        main_presenter = new Main_Presenter(this, this);
        mContext = this
        init()
        isReady = true
        //        request_maxnum_enter();
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {

//        if (rl_witmemory_rank_root.getVisibility() == View.VISIBLE) {
//            rl_witmemory_rank_root.setVisibility(View.GONE);
//            if (isFail) {
//                if (MainActivity.mContext != null)
//                    ((MainActivity) MainActivity.mContext).ref_Gameroom();
//                super.onBackPressed();
//            }
//        } else
//            if (countDownTimer != null) {
//            isContinue = true;
//            countDownTimer.onFinish();
//            countDownTimer.cancel();
//            countDownTimer = null;
//        } else {
//            if (MainActivity.mContext != null)
//                ((MainActivity) MainActivity.mContext).ref_Gameroom();
//            super.onBackPressed();
//        }
        finish()
    }

    private fun init() {

        if (customDialog_witmemory != null && customDialog_witmemory!!.isShowing) return
        customDialog_witmemory = CustomDialog_Witmemory(this@WitmemoryActivity,onClickListener)
        customDialog_witmemory!!.setCancelable(false)

        val options = RequestOptions()
        options.centerCrop()
        Glide.with(this).load(R.drawable.game_intro_bg).apply(options).into(iv_witmemory_intro!!)


//        rl_witmemory_rank_root.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return true;
//            }
//        });
        rl_witmemory_back!!.setOnClickListener(onClickListener)
        rl_witmemory_start!!.setOnClickListener(onClickListener)
        iv_witmemory_intro_start!!.setOnClickListener(onClickListener)
        //        rl_witmemory_top10.setOnClickListener(onClickListener);
//        rl_witmemory_cur10.setOnClickListener(onClickListener);
//        iv_witmemory_game_close.setOnClickListener(onClickListener);
        iv_witmemory_intro_ranking!!.setOnClickListener(onClickListener)
        //        rl_witmemory_rewarded.setOnClickListener(onClickListener);
        val gridLayout = GridLayoutManager(this@WitmemoryActivity, 3)
        rv_witmemory!!.layoutManager = gridLayout
        //        rv_witmemory.addItemDecoration(new CustomRecyclerDecoration_Ho(7));
        rv_witmemory!!.addItemDecoration(CustomRecyclerDecoration_Ve(7))
        witmemoryAdapter = WitmemoryAdapter(this@WitmemoryActivity, dto_witmemories, onClickListener)
        rv_witmemory!!.adapter = witmemoryAdapter
        rl_witmemory_intro!!.visibility = View.VISIBLE
        //        rl_witmemory_rank_root.setVisibility(View.GONE);
        val gridLayout_fake = GridLayoutManager(this@WitmemoryActivity, 3)
        rv_witmemory_fake!!.layoutManager = gridLayout_fake
        //        rv_witmemory.addItemDecoration(new CustomRecyclerDecoration_Ho(7));
        rv_witmemory_fake!!.addItemDecoration(CustomRecyclerDecoration_Ve(7))
        for (i in 1 until 13 + 1) {
            dto_witmemories_fake.add(DTO_Witmemory(i, "N", "N", false))
        }
        witmemoryAdapter_fake = WitmemoryAdapter(this@WitmemoryActivity, dto_witmemories_fake, onClickListener)
        rv_witmemory_fake!!.adapter = witmemoryAdapter_fake

//        setGradient(WitmemoryActivity.this, rl_witmemory_rank_root_sub, R.drawable.shape_ffffff_ra38_sto);


//        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rv_witmemory_ranking.setLayoutManager(LinearLayoutManager);
////        practice_rentAdapter = new Practice_RentAdapter(this, dto_practices, onClickListener);
//        witmemory_rankAdapter = new Witmemory_rankAdapter(this, dto_rank_base, onClickListener);
//        rv_witmemory_ranking.setAdapter(witmemory_rankAdapter);
    }

    fun setGradient(context: Context, view: View, so: Int) {
        val drawable2 = context.getDrawable(so) as GradientDrawable?
        view.background = drawable2
        view.clipToOutline = true
    }

    //    private void request_maxnum_enter() {
    //        JsonObject base = Util.baseJson();
    //        JsonObject jsonObject = new JsonObject();
    ////        jsonObject.addProperty("quiz_sq", quiz_sq);
    ////        Logger.loge("jsonObject   :  " + jsonObject);
    //        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
    //        base.addProperty("box_req", box_req);
    ////        Logger.loge("base  :  " + base);
    //        main_presenter.maxnum_enter(base);
    //    }
    //    private void request_maxnum_rst(String maxNum) {
    //        JsonObject base = Util.baseJson();
    //        JsonObject jsonObject = new JsonObject();
    //        jsonObject.addProperty("maxNum", maxNum);
    //        jsonObject.addProperty("key_sq", key_sq);
    //        Logger.loge("jsonObject   :  " + jsonObject);
    //        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
    //        base.addProperty("box_req", box_req);
    ////        Logger.loge("base  :  " + base);
    //        main_presenter.maxnum_rst(base);
    //    }
    //
    //    private void request_maxnum_start() {
    //        JsonObject base = Util.baseJson();
    //        JsonObject jsonObject = new JsonObject();
    ////        jsonObject.addProperty("maxNum", maxNum);
    ////        Logger.loge("jsonObject   :  " + jsonObject);
    //        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
    //        base.addProperty("box_req", box_req);
    ////        Logger.loge("base  :  " + base);
    //        main_presenter.maxnum_start(base);
    //    }
    private fun startGame() {
//        if (key_sq.equals("")) {
////            request_maxnum_start();
//        }
        if (!isReady) return
        countDownTimer = object : CountDownTimer(countDown, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remaining = millisUntilFinished
                tv_witmemory_title!!.text = "" + millisUntilFinished / 1000
            }

            override fun onFinish() {
                isFail = true
                //                tv_witmemory_title.setText("실패!");
//                if (!isContinue && (revive_ad < revive_limit_cnt)) {
                if (customDialog_witmemory != null && customDialog_witmemory!!.isShowing) return
//                customDialog_witmemory = CustomDialog_Witmemory(this@WitmemoryActivity, onClickListener)
//                customDialog_witmemory!!.setCancelable(false)
                customDialog_witmemory!!.show()
                //                } else {
//                    request_maxnum_rst(maxNum + "");
//                }
//                countDownTimer = null;
            }
        }
        //        if (!isRevive) {
        dto_witmemories.clear()
        for (i in startNum until endNum + 1) {
            dto_witmemories.add(DTO_Witmemory(i, "N", "N", false))
        }
        Collections.shuffle(dto_witmemories)
        val random = Random()
        for (i in 0 until r_Count) {
            val randomValue = random.nextInt(10)
            val dto_witmemory = dto_witmemories[randomValue]
            dto_witmemory!!.isAlpah = true
            dto_witmemories[randomValue] = dto_witmemory
        }
        r_Count = r_Count + difficulty
        witmemoryAdapter!!.isAd = isAd
        witmemoryAdapter!!.firstNum = startNum.toString() + ""
        //        }
        witmemoryAdapter!!.notifyDataSetChanged()
        //        isRevive = false;
        rv_witmemory!!.visibility = View.VISIBLE
        countDownTimer?.start()
    }

    //    @Override
    //    public void result(JsonObject result, String from) {
    ////        Logger.loge(from + "   :   " + result);
    //        String box_res;
    //        JsonObject jsonObject;
    //        String code = result.get("rst_cd").getAsString();
    //        DTO_WM_Award dto_wm_award;
    //        switch (code) {
    //            case Const.API_SUC:
    //                switch (from) {
    //                    case Const.MAXNUM_START:
    //                        box_res = result.get("box_res").getAsString();
    //                        box_res = Security.decrypt(box_res, Const.AES_KEY);
    ////                        Logger.loge("box_res   :  " + box_res);
    //                        jsonObject = gson.fromJson(box_res, JsonObject.class);
    //                        key_sq = jsonObject.get("key_sq").getAsString();
    //
    //                        break;
    //                    case Const.MAXNUM_ENTER:
    //                        box_res = result.get("box_res").getAsString();
    //                        box_res = Security.decrypt(box_res, Const.AES_KEY);
    ////                        Logger.loge("box_res   :  " + box_res);
    //                        jsonObject = gson.fromJson(box_res, JsonObject.class);
    //
    //                        startNum = jsonObject.get("startNum").getAsInt();
    ////                        startNum = 1201;
    //                        maxNum = startNum;
    //                        endNum = jsonObject.get("endNum").getAsInt();
    ////                        endNum = 1212;
    //                        addNum = jsonObject.get("addNum").getAsInt();
    //                        countDown = jsonObject.get("countDown").getAsInt();
    //                        plus_time = jsonObject.get("plus_time").getAsInt();
    //                        difficulty = jsonObject.get("difficulty").getAsInt();
    //                        continue_time = jsonObject.get("continue_time").getAsInt();
    //                        revive_limit_cnt = jsonObject.get("revive_limit_cnt").getAsInt();
    //                        penalty = jsonObject.get("penalty").getAsDouble();
    //
    ////                        if (CApp.test) {
    ////                            countDown = 15100; //처음 남은 시간
    ////                            plus_time = 3000; //라운드별 추가 시간
    ////                        }
    //
    //                        dto_wm_awards_top10.clear();
    //                        for (JsonElement element : jsonObject.get("list_tot10").getAsJsonArray()) {
    //                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
    //                            dto_wm_award.setItemViewType(0);
    //                            dto_wm_awards_top10.add(dto_wm_award);
    //                        }
    //                        dto_wm_awards_cur10.clear();
    //                        for (JsonElement element : jsonObject.get("list_cur10").getAsJsonArray()) {
    //                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
    //                            dto_wm_award.setItemViewType(0);
    //                            dto_wm_awards_cur10.add(dto_wm_award);
    //                        }
    //                        dto_wm_awards_list_cash.clear();
    //                        Logger.loge(jsonObject.get("list_cash").getAsJsonArray().toString());
    //                        for (JsonElement element : jsonObject.get("list_cash").getAsJsonArray()) {
    //                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
    //                            dto_wm_award.setItemViewType(1);
    //                            dto_wm_awards_list_cash.add(dto_wm_award);
    //                        }
    ////                        dto_wm_award = new DTO_WM_Award("3","1000","N","1000");
    ////                        dto_wm_award.setItemViewType(1);
    ////                        dto_wm_awards_list_cash.add(dto_wm_award);
    ////                        dto_wm_award = new DTO_WM_Award("4","1000","N","1000");
    ////                        dto_wm_award.setItemViewType(1);
    ////                        dto_wm_awards_list_cash.add(dto_wm_award);
    ////                        dto_wm_award = new DTO_WM_Award("5","1000","N","1000");
    ////                        dto_wm_award.setItemViewType(1);
    ////                        dto_wm_awards_list_cash.add(dto_wm_award);
    ////                        dto_wm_award = new DTO_WM_Award("6","1000","N","1000");
    ////                        dto_wm_award.setItemViewType(1);
    ////                        dto_wm_awards_list_cash.add(dto_wm_award);
    ////                        dto_wm_award = new DTO_WM_Award("7","1000","N","1000");
    ////                        dto_wm_award.setItemViewType(1);
    ////                        dto_wm_awards_list_cash.add(dto_wm_award);
    //                        if (dto_wm_awards_cur10.size() == 0) {
    //                            tv_witmemory_rank_none.setVisibility(View.VISIBLE);
    //                        } else {
    //                            tv_witmemory_rank_none.setVisibility(View.GONE);
    //                        }
    //                        isReady = true;
    //                        try {
    //                            usr_cash = jsonObject.get("usr_cash").getAsString();
    //                            my_cur_rank = jsonObject.get("my_cur_rank").getAsString();
    //                            my_tot_rank = jsonObject.get("my_tot_rank").getAsString();
    //                            my_tot_step = jsonObject.get("my_tot_step").getAsString();
    //                            my_cur_step = jsonObject.get("my_cur_step").getAsString();
    //                            usr_nick = jsonObject.get("usr_nick").getAsString();
    //                            today_get_price_flag = jsonObject.get("today_get_price_flag").getAsString();
    //                        } catch (Exception e) {
    //                            e.printStackTrace();
    //                        }
    //
    //                        String flag = (String) Util.getCacheDataForKey(Const.WITMEMORY_POP);
    //                        if (today_get_price_flag.equals("Y") && (flag == null || flag.equals(""))) {
    //                            if (customDialog_witmemory != null && customDialog_witmemory.isShowing())
    //                                return;
    //                            customDialog_witmemory = new CustomDialog_Witmemory(WitmemoryActivity.this, onClickListener, "");
    //                            customDialog_witmemory.setCancelable(false);
    //                            customDialog_witmemory.show();
    //                        }
    //                        tv_witmemory_mynick.setText(usr_nick);
    //                        tv_witmemory_usr_cash.setText(Util.myFormatter(usr_cash) + "망고");
    //
    //                        break;
    //                    case Const.MAXNUM_RST:
    //                        box_res = result.get("box_res").getAsString();
    //                        box_res = Security.decrypt(box_res, Const.AES_KEY);
    //                        Logger.loge("box_res   :  " + box_res);
    //                        jsonObject = gson.fromJson(box_res, JsonObject.class);
    //
    //                        String add_cash = jsonObject.get("add_cash").getAsString();
    //                        String result_yn = jsonObject.get("result_yn").getAsString();
    //
    //                        dto_wm_awards_top10.clear();
    //                        for (JsonElement element : jsonObject.get("list_tot10").getAsJsonArray()) {
    //                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
    //                            dto_wm_award.setItemViewType(0);
    //                            dto_wm_awards_top10.add(dto_wm_award);
    //                        }
    //                        dto_wm_awards_cur10.clear();
    //                        for (JsonElement element : jsonObject.get("list_cur10").getAsJsonArray()) {
    //                            dto_wm_award = gson.fromJson(element, DTO_WM_Award.class);
    //                            dto_wm_award.setItemViewType(0);
    //                            dto_wm_awards_cur10.add(dto_wm_award);
    //                        }
    //                        if (dto_wm_awards_cur10.size() == 0) {
    //                            tv_witmemory_rank_none.setVisibility(View.VISIBLE);
    //                        } else {
    //                            tv_witmemory_rank_none.setVisibility(View.GONE);
    //                        }
    //                        isReady = true;
    //
    //                        try {
    //                            usr_cash = jsonObject.get("usr_cash").getAsString();
    //                            my_cur_rank = jsonObject.get("my_cur_rank").getAsString();
    //                            my_tot_rank = jsonObject.get("my_tot_rank").getAsString();
    //                            my_tot_step = jsonObject.get("my_tot_step").getAsString();
    //                            my_cur_step = jsonObject.get("my_cur_step").getAsString();
    //                            usr_nick = jsonObject.get("usr_nick").getAsString();
    //                        } catch (Exception e) {
    //                            e.printStackTrace();
    //                        }
    //
    //                        tv_witmemory_mynick.setText(usr_nick);
    //                        tv_witmemory_usr_cash.setText(Util.myFormatter(usr_cash) + "망고");
    //
    ////                        if (isExit) {
    ////                            onBackPressed();
    ////                        } else {
    ////                            rl_witmemory_intro.setVisibility(View.VISIBLE);
    ////                            rl_witmemory_rank_root.setVisibility(View.GONE);
    ////                            isFail = false;
    ////                            isTouch = false;
    ////                            remaining = 0; //남은 시간
    ////                            r_Count = 0; //라운드별 총 사라지는 갯수
    ////                            isReady = false;
    ////                            key_sq = "";
    ////                            request_maxnum_enter();
    ////                        }
    //
    //                        if (customDialog_witmemory != null && customDialog_witmemory.isShowing())
    //                            return;
    //                        customDialog_witmemory = new CustomDialog_Witmemory(WitmemoryActivity.this, onClickListener, Util.myFormatter(add_cash));
    //                        customDialog_witmemory.setCancelable(false);
    //                        customDialog_witmemory.show();
    //                        countDownTimer = null;
    //
    //                        break;
    //                }
    //                break;
    //            default:
    //                Util.showToast(getApplicationContext(), result.get("rst_msg").getAsString());
    //                break;
    //        }
    //    }
    var onClickListener = View.OnClickListener { view ->
        var position = 0
        val dto_witmemory: DTO_Witmemory?
        when (view.id) {
            R.id.iv_custom_dialog_witmemory_can -> {
                customDialog_witmemory!!.cancel()
                countDownTimer!!.cancel()
                rl_witmemory_intro!!.visibility = View.VISIBLE
                //                    rl_witmemory_rank_root.setVisibility(View.GONE);
                isFail = false
                isTouch = false
                remaining = 0 //남은 시간
                r_Count = 0 //라운드별 총 사라지는 갯수
                isReady = true
                isAd = true

                //받아올 데이터 start
                startNum = 1 //시작시 처음 수
                endNum = 12 //시작시 마지막 수
                addNum = 12 //다음 라운드 추가되는 수
                countDown = 15100 //처음 남은 시간
                plus_time = 3000 //라운드별 추가 시간
                difficulty = 2 //라운드마다 사라지는 숫자 갯수 늘어나는 폭
                continue_time = 5100 //라운드마다 사라지는 숫자 갯수 늘어나는 폭
                //받아올 데이터 end
                maxNum = 1 //기록 숫자
                //    int revive_limit_cnt = 5;   //이어하기 숫자
                penalty = 500.0 //패널티 숫자
            }
            R.id.iv_custom_dialog_witmemory_con -> {
                customDialog_witmemory!!.cancel()
                onBackPressed()
            }
            R.id.rl_witmemory_back -> onBackPressed()
            R.id.rl_witmemory_start -> {
                rl_witmemory_start!!.visibility = View.GONE
                startGame()
            }
            R.id.iv_witmemory_intro_start -> {
                rl_witmemory_start!!.visibility = View.GONE
                startGame()
                rl_witmemory_intro!!.visibility = View.GONE
            }
            R.id.rl_witmemory_root -> {
                val currentClickTime = SystemClock.uptimeMillis()
                val elapsedTime = currentClickTime - mLastClickTime
                mLastClickTime = currentClickTime
                if (elapsedTime <= MIN_CLICK_INTERVAL) {

                } else {
                    position = view.tag as Int
                    dto_witmemory = dto_witmemories[position]
                    val finalPosition = position
                    handler.post {
                        //                            Logger.loge("isTouch   out:  " + isTouch);
                        if (!isTouch) {
                            //                                isTouch = true;
                            if (!isFail) {
                                if (maxNum < endNum) {
                                    if (maxNum == dto_witmemory!!.num) { //맞게 누름
                                        dto_witmemory.isSuc = "Y"
                                        //                                            dto_witmemories.set(finalPosition, dto_witmemory);
                                        witmemoryAdapter!!.notifyItemChanged(finalPosition, dto_witmemory)
                                        maxNum++
                                        //                                            tv_witmemory_maxnum.setText((maxNum / 10) + "망고");
                                        //                                            isTouch = false;
                                    } else { //TODO 잘못누름
                                        dto_witmemory.isFail = "Y"
                                        //                                            dto_witmemories.set(finalPosition, dto_witmemory);
                                        witmemoryAdapter!!.notifyItemChanged(finalPosition, dto_witmemory)
                                        countDownTimer!!.cancel()
                                        countDown = (remaining - penalty).toLong()
                                        countDownTimer = object : CountDownTimer(countDown, 1000) {
                                            override fun onTick(millisUntilFinished: Long) {
                                                remaining = millisUntilFinished
                                                tv_witmemory_title!!.text = "" + millisUntilFinished / 1000
                                            }

                                            override fun onFinish() {
                                                isFail = true
                                                //                                                    if (!isContinue && (revive_ad < revive_limit_cnt)) {

                                                customDialog_witmemory!!.show()
                                                //                                                    } else {
                                                //                                                        request_maxnum_rst(maxNum + "");
                                                //                                                    }
                                            }
                                        }
                                        countDownTimer?.start()
                                        tv_witmemory_title_minus!!.visibility = View.VISIBLE
                                        tv_witmemory_title_minus!!.text = " - " + penalty / 1000 + "초"
                                        handler.postDelayed({
                                            tv_witmemory_title_minus!!.visibility = View.GONE
                                            dto_witmemory.isFail = ""
                                            //                                                    dto_witmemories.set(finalPosition, dto_witmemory);
                                            //                                                    witmemoryAdapter.notifyItemChanged(finalPosition);
                                            witmemoryAdapter!!.notifyItemChanged(finalPosition, dto_witmemory)
                                        }, penalty.toLong())
                                        //                                            isTouch = false;
                                    }
                                } else { //라운드 성공
                                    maxNum = endNum + 1
                                    startNum = endNum + 1
                                    endNum = endNum + addNum
                                    if (maxNum > 60) isAd = false
                                    countDownTimer!!.cancel()
                                    countDown = remaining + plus_time
                                    tv_witmemory_title!!.text = "" + countDown / 1000
                                    rl_witmemory_start!!.visibility = View.VISIBLE
                                    rv_witmemory!!.visibility = View.GONE
                                    //                                        isTouch = false;
                                }
                            }
                        }
                        //                            isTouch = false;
                    }
                }
            }
        }
    } //    @Override

    //    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //        super.onActivityResult(requestCode, resultCode, data);
    //        if (requestCode == ADMOBCALLBACK) {
    //            if (resultCode == RESULT_OK) {
    ////                isContinue = true; //1회만 부활
    ////                maxNum = startNum;
    ////                r_Count = r_Count - difficulty;
    //                isAd = true;
    //                isRevive = true;
    //                isFail = false;
    //                countDown = continue_time;
    //                if (countDownTimer != null) countDownTimer.cancel();
    //                tv_witmemory_title.setText("" + countDown / 1000);
    //                rl_witmemory_start.setVisibility(View.VISIBLE);
    //                rv_witmemory.setVisibility(View.GONE);
    //                isTouch = false;
    //            } else {
    //                //TODO 광고 안봐와 같아
    //                request_maxnum_rst(maxNum + "");
    //            }
    //        }
    //    }
    companion object {
        /*data*/
        var mContext: Context? = null
        private const val MIN_CLICK_INTERVAL: Long = 0
    }
}
