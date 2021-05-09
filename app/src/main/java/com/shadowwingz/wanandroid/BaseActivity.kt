package com.shadowwingz.wanandroid

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected var root: ViewGroup? = null
    protected abstract fun setViewBinding(): ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBindingImpl(setViewBinding())
    }

    private fun setViewBindingImpl(root: ViewGroup) {
        this.root = root
        setContentView(root)
    }
}