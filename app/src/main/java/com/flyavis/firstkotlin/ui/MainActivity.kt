package com.flyavis.firstkotlin.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.flyavis.firstkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.flyavis.firstkotlin.R.layout.activity_main)
        fragmentAdapter = FragmentAdapter(supportFragmentManager)
        binding.viewPager.adapter = fragmentAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.title = ("狗")
        binding.viewPager.addOnPageChangeListener(
            object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    if (position == 0) {
                        binding.title = ("狗")
//                        val view = fragmentAdapter.getItem(0).view
//                        onScaleAnimation(view!!.findViewById(R.id.textView8))
//                        onScaleAnimation(view.findViewById(R.id.profile_image))
//                        view.findViewById<TextView>(R.id.textView8).animate()
                    } else {
                        binding.title = ("貓")
                    }
                }
            })
    }

    private fun onScaleAnimation(view: View) {
        val animatorX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1.8f)
        val animatorY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1.8f)
        val set = AnimatorSet()
        set.duration = 1000
        set.playTogether(animatorX, animatorY)
        set.start()
    }
}
