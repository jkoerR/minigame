package com.droi_mini

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.droi_mini.concentration.ConcentrationActivity

import com.droi_mini.databinding.ActivityIntroBinding
import com.droi_mini.witmemory.WitmemoryActivity

import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        var intent : Intent? = null
        game1.setOnClickListener {
            intent = Intent(this, WitmemoryActivity::class.java)
            startActivity(intent)
        }
        game2.setOnClickListener {
            intent = Intent(this, ConcentrationActivity::class.java)
            startActivity(intent)
        }

    }
}