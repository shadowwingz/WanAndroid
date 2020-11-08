package com.shadowwingz.wanandroid.ui.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.shadowwingz.wanandroid.R

class SystemFragment : Fragment() {

    companion object {
        fun newInstance() = SystemFragment()
    }

    private lateinit var viewModel: SystemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.system_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SystemViewModel::class.java)
        // TODO: Use the ViewModel
    }

}