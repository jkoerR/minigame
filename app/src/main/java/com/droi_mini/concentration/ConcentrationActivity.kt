package com.droi_mini.concentration

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.droi_mini.BaseActivity
import com.droi_mini.R
import com.droi_mini.databinding.ActivityConcentrationBinding
import com.droi_mini.dto.DTO_Concentration
import com.droi_mini.witmemory.CustomDialog_Witmemory
import kotlinx.android.synthetic.main.activity_concentration.*
import java.util.*

class ConcentrationActivity : BaseActivity() {
    var binding: ActivityConcentrationBinding? = null

    /*adapter*/ //    Main_Presenter main_presenter;
    var handler = Handler(Looper.getMainLooper())
    var countDownTimer: CountDownTimer? = null

    //    Concentration_rankAdapter concentration_rankAdapter;
    //    Gson gson = new Gson();
    var customDialog_concentration: CustomDialog_Witmemory? = null

    //받아올 데이터 start
    var countDown = 3000 //주어지는 시간
    var minus_sec = 100 //게임이 진행 될 수록 줄어드는 시간 간격
    var touch_count = 7 //터치해야하는 횟수
    var limit_count = 800 //줄어드는 초 한계
    var limit_round = 4 //초가 줄어들기 시작하는 단계
    var shuffle_round = 3 //섞기가 시작되는 단계
    var fake_back = 5 //배경 랜덤이 시작되는 단계

    //    int revive_limit_cnt = 5;   //이어하기 숫자
    //받아올 데이터 end
    //보낼데이터
    var maxNum = 0 //기록 숫자
    var isFail = false
    var isAd = false
    var isContinue = false
    var isOpen = false
    var isReady = false
    var isStart = false
    var ran_count = -1
    var select_ran = -1
    var fake_ran = -1
    var revive_ad = 0
    var oriArrayList = ArrayList<DTO_Concentration?>()
    var fakeArrayList = ArrayList<DTO_Concentration?>()
    var key_sq = ""
    var reGame = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_concentration);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_concentration)
        mContext = this
        init()
        isReady = true

        //        request_cococo_enter();
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
//        if (rl_concentration_rank_root.getVisibility() == View.VISIBLE) {
//            rl_concentration_rank_root.setVisibility(View.GONE);
//            if (isFail) {
//                if (MainActivity.mContext != null)
//                    ((MainActivity) MainActivity.mContext).ref_Gameroom();
//                super.onBackPressed();
//            }
//        } else if (countDownTimer != null) {
//            isContinue = true;
//            countDownTimer.onFinish();
//            countDownTimer.cancel();
//            countDownTimer = null;
//        } else {
//            if (MainActivity.mContext != null)
//                ((MainActivity) MainActivity.mContext).ref_Gameroom();
        super.onBackPressed()
        finish()
        //        }

//        if (customDialog_tbtn != null && customDialog_tbtn.isShowing()) return;
//        customDialog_tbtn = new CustomDialog_tbtn(concentrationActivity.this, onClickListener, 0, "공짜시대", "종료하시겠습니까?", "취소", "확인", false);
//        customDialog_tbtn.setCancelable(false);
//        customDialog_tbtn.show();
    }

    private fun init() {
        rl_concentration_back.setOnClickListener(onClickListener)
        rl_concentration_touch.setOnClickListener(onClickListener)
        rl_concentration_1.setOnClickListener(onClickListener)
        rl_concentration_2.setOnClickListener(onClickListener)
        rl_concentration_3.setOnClickListener(onClickListener)
        rl_concentration_4.setOnClickListener(onClickListener)
        rl_concentration_5.setOnClickListener(onClickListener)
        rl_concentration_6.setOnClickListener(onClickListener)
        rl_concentration_7.setOnClickListener(onClickListener)
        rl_concentration_8.setOnClickListener(onClickListener)
        rl_concentration_9.setOnClickListener(onClickListener)
        rl_concentration_10.setOnClickListener(onClickListener)
        rl_concentration_11.setOnClickListener(onClickListener)
        iv_concentration_intro_start.setOnClickListener(onClickListener)
        iv_concentration_intro_ranking.setOnClickListener(onClickListener)
        iv_concentration_btn_explicate.setOnClickListener(onClickListener)
        iv_concentration_game_explicate.setOnClickListener(onClickListener)
        iv_concentration_close_2.setOnClickListener(onClickListener)
        iv_concentration_intro_start.setOnClickListener(onClickListener)
        //        rl_concentration_top10.setOnClickListener(onClickListener);
//        rl_concentration_cur10.setOnClickListener(onClickListener);
        iv_concentration_intro_ranking.setOnClickListener(onClickListener)
        //        rl_concentration_rewarded.setOnClickListener(onClickListener);
//        iv_concentration_game_close.setOnClickListener(onClickListener);
        rl_concentration_intro.setVisibility(View.VISIBLE)
        ll_concentration_top.setVisibility(View.GONE)
        ll_concentration_gamebody.setVisibility(View.GONE)
        rl_concentration_answer.setVisibility(View.GONE)


//        rl_concentration_rank_root.setVisibility(View.GONE);

//        setGradient(ConcentrationActivity.this, rl_concentration_rank_root_sub, R.drawable.shape_ffffff_ra38_sto);

//        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rv_concentration_ranking.setLayoutManager(LinearLayoutManager);
//        concentration_rankAdapter = new Concentration_rankAdapter(this, dto_rank_base, onClickListener);
//        rv_concentration_ranking.setAdapter(concentration_rankAdapter);
        oriArrayList.add(DTO_Concentration(R.drawable.a, "흰여우"))
        oriArrayList.add(DTO_Concentration(R.drawable.b, "통나무여우"))
        oriArrayList.add(DTO_Concentration(R.drawable.c, "거북이"))
        oriArrayList.add(DTO_Concentration(R.drawable.d, "흰고양이"))
        oriArrayList.add(DTO_Concentration(R.drawable.e, "올빼미"))
        oriArrayList.add(DTO_Concentration(R.drawable.f, "갈색여우"))
        oriArrayList.add(DTO_Concentration(R.drawable.g, "고슴도치"))
        oriArrayList.add(DTO_Concentration(R.drawable.h, "갈색고양이"))
        oriArrayList.add(DTO_Concentration(R.drawable.i, "물고기"))
        oriArrayList.add(DTO_Concentration(R.drawable.j, "강아지"))
        oriArrayList.add(DTO_Concentration(R.drawable.k, "검은고양이"))
    }

    //    private void request_cococo_enter() {
    //        JsonObject base = baseJson();
    //        JsonObject jsonObject = new JsonObject();
    ////        jsonObject.addProperty("quiz_sq", quiz_sq);
    ////        Logger.loge("jsonObject   :  " + jsonObject);
    //        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
    //        base.addProperty("box_req", box_req);
    ////        Logger.loge("base  :  " + base);
    //        main_presenter.cococo_enter(base);
    //    }
    //
    //    private void request_cococo_rst(String maxNum) {
    //        JsonObject base = baseJson();
    //        JsonObject jsonObject = new JsonObject();
    //        jsonObject.addProperty("maxNum", maxNum);
    //        jsonObject.addProperty("key_sq", key_sq);
    //        Logger.loge("jsonObject   :  " + jsonObject);
    //        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
    //        base.addProperty("box_req", box_req);
    //        Logger.loge("base  :  " + base);
    //        main_presenter.cococo_rst(base);
    //    }
    //
    //    private void request_cococo_start() {
    //        JsonObject base = baseJson();
    //        JsonObject jsonObject = new JsonObject();
    //        jsonObject.addProperty("maxNum", maxNum);
    ////        Logger.loge("jsonObject   :  " + jsonObject);
    //        String box_req = Security.encrypt(jsonObject.toString(), Const.AES_KEY);
    //        base.addProperty("box_req", box_req);
    ////        Logger.loge("base  :  " + base);
    //        main_presenter.cococo_start(base);
    //    }
    //게임 세팅
    private fun startGame() {
        fakeArrayList.clear()
        fakeArrayList.addAll(oriArrayList)
        tv_concentration_answer_text.setText(getString(R.string.concentration))
        //        if (key_sq.equals("")) {
//            request_cococo_start();
//        }
        rl_concentration_1.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_2.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_3.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_4.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_5.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_6.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_7.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_8.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_9.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_10.setBackgroundResource(R.drawable.card_bg_white)
        rl_concentration_11.setBackgroundResource(R.drawable.card_bg_white)
        tv_concentration_question.visibility = View.GONE


        for (i in fakeArrayList.indices) {
            when (i) {
                0 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_1)
                1 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_2)
                2 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_3)
                3 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_4)
                4 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_5)
                5 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_6)
                6 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_7)
                7 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_8)
                8 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_9)
                9 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_10)
                10 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_11)
            }
        }

        if (maxNum >= limit_round) {
            if (countDown > limit_count) {
                countDown = countDown - minus_sec
            }
        }
        countDownTimer = object : CountDownTimer(countDown.toLong(), 10) {
            override fun onTick(millisUntilFinished: Long) {
                tv_concentration_title.setText(myFormatter2("" + millisUntilFinished / 10) + "초")
            }

            override fun onFinish() {
                endgame(true)
            }
        }
        tv_concentration_title.setText(myFormatter2("" + countDown / 10) + "초")
        tv_concentration_grade.setText((maxNum + 1).toString() + "단계")
        if (reGame) {
            reGame = false
            countDownTimer?.cancel()
            countDownTimer = null
            return
        }
        val random = Random()
        ran_count = random.nextInt(touch_count)
        rl_concentration_touch.setEnabled(true)
        select_ran = random.nextInt(11) + 1
        fake_ran = random.nextInt(11) + 1
    }

    private fun endgame(isFinish: Boolean) {
        if (isFail) return
        isFail = true
        if (isFinish) {
            tv_concentration_title.setText("타임오버")
        } else {
            tv_concentration_title.setText("실패!")
        }
        //TODO 광고보고 다시하기
//        if (!isContinue && (revive_ad < revive_limit_cnt)) {
        if (customDialog_concentration != null && customDialog_concentration!!.isShowing) return
        customDialog_concentration = CustomDialog_Witmemory(this@ConcentrationActivity, onClickListener)
        customDialog_concentration!!.setCancelable(false)
        customDialog_concentration!!.show()
        //        } else {
//            request_cococo_rst(maxNum + "");
//        }
    }

    private fun card_shuffle() {
        if (maxNum >= shuffle_round) {
//            fakeArrayList.clear();
//            fakeArrayList.addAll(oriArrayList);
            fakeArrayList.shuffle()
            val options = RequestOptions()
            options.centerCrop()
            try {
                for (i in fakeArrayList.indices) {
                    Log.e("card_shuffle","card_shuffle     ${fakeArrayList[i]!!.img_url}")
                    when (i) {
                        0 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_1)
                        1 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).centerCrop().into(iv_concentration_2)
                        2 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_3)
                        3 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_4)
                        4 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_5)
                        5 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_6)
                        6 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_7)
                        7 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_8)
                        8 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_9)
                        9 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_10)
                        10 -> Glide.with(this).load(fakeArrayList[i]!!.img_url).into(iv_concentration_11)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
    //                    case Const.COCOCO_START:
    //                        box_res = result.get("box_res").getAsString();
    //                        box_res = Security.decrypt(box_res, Const.AES_KEY);
    ////                        Logger.loge("box_res   :  " + box_res);
    //                        jsonObject = gson.fromJson(box_res, JsonObject.class);
    //                        key_sq = jsonObject.get("key_sq").getAsString();
    //
    //                        break;
    //                    case Const.COCOCO_ENTER:
    //                        box_res = result.get("box_res").getAsString();
    //                        box_res = Security.decrypt(box_res, Const.AES_KEY);
    //                        Logger.loge("box_res   :  " + box_res);
    //                        jsonObject = gson.fromJson(box_res, JsonObject.class);
    //
    //                        countDown = jsonObject.get("countDown").getAsInt();
    //                        minus_sec = jsonObject.get("minus_sec").getAsInt();
    //                        touch_count = jsonObject.get("touch_count").getAsInt();
    //                        limit_count = jsonObject.get("limit_count").getAsInt();
    //                        limit_round = jsonObject.get("limit_round").getAsInt();
    //                        shuffle_round = jsonObject.get("shuffle_round").getAsInt();
    //                        fake_back = jsonObject.get("fake_back").getAsInt();
    //                        revive_limit_cnt = jsonObject.get("revive_limit_cnt").getAsInt();
    //
    //                        oriArrayList.clear();
    //                        for (JsonElement element : jsonObject.get("answer_list").getAsJsonArray()) {
    //                            DTO_Concentration dto_concentration = gson.fromJson(element, DTO_Concentration.class);
    //                            oriArrayList.add(dto_concentration);
    //                        }
    //                        fakeArrayList.clear();
    //                        fakeArrayList.addAll(oriArrayList);
    //                        RequestOptions options = new RequestOptions();
    ////                        options.centerCrop();
    //                        try {
    //                            for (int i = 0; i < fakeArrayList.size(); i++) {
    //                                switch (i) {
    //                                    case 0:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_1);
    //                                        break;
    //                                    case 1:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_2);
    //                                        break;
    //                                    case 2:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_3);
    //                                        break;
    //                                    case 3:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_4);
    //                                        break;
    //                                    case 4:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_5);
    //                                        break;
    //                                    case 5:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_6);
    //                                        break;
    //                                    case 6:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_7);
    //                                        break;
    //                                    case 7:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_8);
    //                                        break;
    //                                    case 8:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_9);
    //                                        break;
    //                                    case 9:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_10);
    //                                        break;
    //                                    case 10:
    //                                        Glide.with(this).load(fakeArrayList.get(i).getImg_url())).into(iv_concentration_11);
    //                                        break;
    //                                }
    //                            }
    //                        } catch (Exception e) {
    //                            e.printStackTrace();
    //                        }
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
    ////                        dto_wm_award = new DTO_WM_Award("3","1000","","");
    ////                        dto_wm_award.setItemViewType(1);
    ////                        dto_wm_awards_list_cash.add(dto_wm_award);
    //
    //
    //                        if (dto_wm_awards_cur10.size() == 0) {
    //                            tv_concentration_rank_none.setVisibility(View.VISIBLE);
    //                        } else {
    //                            tv_concentration_rank_none.setVisibility(View.GONE);
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
    //                            today_get_price_flag = jsonObject.get("today_get_price_flag").getAsString();
    //                        } catch (Exception e) {
    //                            e.printStackTrace();
    //                        }
    //
    //                        String flag = (String) getCacheDataForKey(Const.CONCENTRATION_POP);
    //                        if (today_get_price_flag.equals("Y") && (flag == null || flag.equals(""))) { //보상관련 안내
    //                            if (customDialog_concentration != null && customDialog_concentration.isShowing())
    //                                return;
    //                            customDialog_concentration = new CustomDialog_Witmemory(ConcentrationActivity.this, onClickListener, "");
    //                            customDialog_concentration.setCancelable(false);
    //                            customDialog_concentration.show();
    //                        }
    //                        tv_concentration_mynick.setText(usr_nick);
    //                        tv_concentration_title.setText(myFormatter2("" + countDown / 10) + "초");
    ////                        tv_concentration_usr_cash.setText(usr_cash + "망고");
    //                        if (reGame) startGame();
    //
    //                        break;
    //                    case Const.COCOCO_RST:
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
    //                            tv_concentration_rank_none.setVisibility(View.VISIBLE);
    //                        } else {
    //                            tv_concentration_rank_none.setVisibility(View.GONE);
    //                        }
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
    //                        tv_concentration_mynick.setText(usr_nick);
    //                        if (customDialog_concentration != null && customDialog_concentration.isShowing())
    //                            return;
    //                        customDialog_concentration = new CustomDialog_Witmemory(ConcentrationActivity.this, onClickListener, myFormatter(add_cash));
    //                        customDialog_concentration.setCancelable(false);
    //                        customDialog_concentration.show();
    //
    //                        break;
    //                    default:
    //                        showToast(ConcentrationActivity.this, result.get("rst_msg").getAsString());
    //                        break;
    //                }
    //                break;
    //            default:
    //                showToast(getApplicationContext(), result.get("rst_msg").getAsString());
    //                break;
    //        }
    //    }
    var onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.iv_custom_dialog_witmemory_can -> {
                customDialog_concentration!!.cancel()
                rl_concentration_intro.setVisibility(View.VISIBLE)
                ll_concentration_top.setVisibility(View.GONE)
                ll_concentration_gamebody.setVisibility(View.GONE)
                rl_concentration_answer.setVisibility(View.GONE)
                //                    rl_concentration_rank_root.setVisibility(View.GONE);
                maxNum = 0 //기록 숫자
                isFail = false
                isOpen = false
                isReady = false
                isStart = false
                ran_count = -1
                select_ran = -1
                fake_ran = -1
                reGame = true
                key_sq = ""
                revive_ad = 0
                isContinue = false
                countDown = 3000 //주어지는 시간
                minus_sec = 100 //게임이 진행 될 수록 줄어드는 시간 간격
                touch_count = 7 //터치해야하는 횟수
                limit_count = 800 //줄어드는 초 한계
                limit_round = 4 //초가 줄어들기 시작하는 단계
                shuffle_round = 9 //섞기가 시작되는 단계
                fake_back = 5 //배경 랜덤이 시작되는 단계
            }
            R.id.iv_custom_dialog_witmemory_con -> {
                customDialog_concentration!!.cancel()
                countDownTimer = null
                onBackPressed()
            }
            R.id.rl_concentration_back -> onBackPressed()
            R.id.iv_concentration_btn_explicate -> {
                iv_concentration_game_explicate.setVisibility(View.VISIBLE)
                iv_concentration_close_2.setVisibility(View.VISIBLE)
            }
            R.id.iv_concentration_game_explicate, R.id.iv_concentration_close_2 -> {
                iv_concentration_game_explicate.setVisibility(View.GONE)
                iv_concentration_close_2.setVisibility(View.GONE)
            }
            R.id.iv_concentration_intro_start -> {
                rl_concentration_intro.setVisibility(View.GONE)
                ll_concentration_top.setVisibility(View.VISIBLE)
                ll_concentration_gamebody.setVisibility(View.VISIBLE)
                rl_concentration_answer.setVisibility(View.VISIBLE)
            }
            R.id.rl_concentration_touch -> {
                if (isFail) return@OnClickListener
                if (ran_count == -1) {
                    startGame()
                    return@OnClickListener
                }
                if (ran_count > 0) {
                    isOpen = false
                    card_shuffle()
                    tv_concentration_question.setVisibility(View.VISIBLE)
                    tv_concentration_question.setText("코")
                    val animation = AnimationUtils.loadAnimation(this@ConcentrationActivity, R.anim.scale)
                    tv_concentration_question.startAnimation(animation)
                    animation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {
                            if (isOpen) {
                                tv_concentration_question.setVisibility(View.VISIBLE)
                            } else {
                                tv_concentration_question.setVisibility(View.GONE)
                            }
                        }

                        override fun onAnimationRepeat(animation: Animation) {}
                    })
                    ran_count = ran_count - 1
                } else {
                    //                        Logger.loge("isStart   :  " + isStart);
                    if (isStart) return@OnClickListener
                    isStart = true
                    isOpen = true
                    //                        Logger.loge("select_ran   :  " + select_ran);
                    tv_concentration_question.clearAnimation()
                    //                        tv_concentration_question.setText(oriArrayList.get(select_ran - 1));
                    tv_concentration_answer_text.setText(fakeArrayList[select_ran - 1]!!.text)
                    tv_concentration_question.setText("")
                    tv_concentration_question.setVisibility(View.GONE)
                    countDownTimer!!.start()
                    var ran = -1
                    ran = if (maxNum >= fake_back) {
                        fake_ran
                    } else {
                        select_ran
                    }
                    when (ran) {
                        1 -> rl_concentration_1.setBackgroundResource(R.drawable.card_bg_color)
                        2 -> rl_concentration_2.setBackgroundResource(R.drawable.card_bg_color)
                        3 -> rl_concentration_3.setBackgroundResource(R.drawable.card_bg_color)
                        4 -> rl_concentration_4.setBackgroundResource(R.drawable.card_bg_color)
                        5 -> rl_concentration_5.setBackgroundResource(R.drawable.card_bg_color)
                        6 -> rl_concentration_6.setBackgroundResource(R.drawable.card_bg_color)
                        7 -> rl_concentration_7.setBackgroundResource(R.drawable.card_bg_color)
                        8 -> rl_concentration_8.setBackgroundResource(R.drawable.card_bg_color)
                        9 -> rl_concentration_9.setBackgroundResource(R.drawable.card_bg_color)
                        10 -> rl_concentration_10.setBackgroundResource(R.drawable.card_bg_color)
                        11 -> rl_concentration_11.setBackgroundResource(R.drawable.card_bg_color)
                    }
                }
            }
            R.id.rl_concentration_1 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 1 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_2 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 2 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_3 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 3 && !isFail && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_4 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 4 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_5 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 5 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_6 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 6 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_7 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 7 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_8 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 8 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_9 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 9 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_10 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 10 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
            R.id.rl_concentration_11 -> {
                if (!isOpen) return@OnClickListener
                countDownTimer!!.cancel()
                handler.post {
                    if (select_ran == 11 && !isFail) {
                        tv_concentration_question.setVisibility(View.VISIBLE)
                        tv_concentration_question.setText("정답")
                        isStart = false
                        maxNum++
                        ran_count = -1
                    } else {
                        endgame(false)
                    }
                    isOpen = false
                }
            }
        }
    }

    companion object {
        /*data*/
        var mContext: Context? = null
        fun myFormatter2(num: String): String {
//        if (num == null || num.equals("")) return "0";
//        DecimalFormat myFormatter = new DecimalFormat("#,##");
//        return myFormatter.format(Long.parseLong(num));
            var num = num
            num = if (num.length > 2) {
                num.substring(0, 1) + "." + num.substring(1, num.length)
            } else {
                "0.$num"
            }
            return num
        }
    }
}
