package com.flyavis.firstkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.flyavis.firstkotlin.R
import com.flyavis.firstkotlin.databinding.ContentFragmentBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.content_fragment, container, false)
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
        viewModel.getAnimals(i).observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
            Timber.d("observed")
        })

    }

}
