package com.flyavis.firstkotlin.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.flyavis.firstkotlin.databinding.ContentFragmentBinding
import com.yy.mobile.rollingtextview.CharOrder
import com.yy.mobile.rollingtextview.strategy.Strategy
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject


class ContentFragment : DaggerFragment() {

    companion object {
        fun newInstance() = ContentFragment()
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: ContentViewModel
    private lateinit var binding: ContentFragmentBinding
    private lateinit var controller: EpoxyController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, com.flyavis.firstkotlin.R.layout.content_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ContentViewModel::class.java)

        controller = EpoxyController()
        binding.epoxyRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.epoxyRecyclerView.setController(controller)
        var i = 1
        if (arguments?.get("key") != null) {
            i = arguments?.get("key") as Int
        }
        viewModel.setKey(i)
        viewModel.getAnimals().observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
            Timber.d("observed")
            Timber.d((it.size.toString()))
        })

        viewModel.getDataSize().observe(viewLifecycleOwner, Observer {
            onScaleAnimation(binding.profileImage, 0f, 1.0f)
            onScaleAnimation(binding.textView8, 0f, 1.0f)
            binding.textView8.animationDuration = 2000L
            binding.textView8.charStrategy = Strategy.CarryBitAnimation()
            binding.textView8.addCharOrder(CharOrder.Number)

            binding.textView8.addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    onScaleAnimation(binding.profileImage, 1.0f, 0f)
                    onScaleAnimation(binding.textView8, 1.0f, 0f)
                }
            })
            binding.textView8.setText(it.toString())
        })
    }

    private fun onScaleAnimation(view: View, f: Float, f2: Float) {
        val animatorX = ObjectAnimator.ofFloat(view, "scaleX", f, f2)
        val animatorY = ObjectAnimator.ofFloat(view, "scaleY", f, f2)
        val set = AnimatorSet()
        set.duration = 1000
        set.playTogether(animatorX, animatorY)
        set.start()
    }
}
