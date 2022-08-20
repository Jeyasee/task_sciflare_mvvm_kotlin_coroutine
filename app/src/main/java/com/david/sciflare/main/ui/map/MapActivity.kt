package com.david.sciflare.main.ui.map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.david.sciflare.R
import com.david.sciflare.databinding.ActivityMapBinding
import com.david.sciflare.main.ui.example_list.adapter.ExampleAdapter
import com.david.support.base_class.ActionBarActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapActivity : ActionBarActivity<ActivityMapBinding, MapViewModel>(
    R.layout.activity_map,
    MapViewModel::class.java
), ExampleAdapter.ItemListener {

    private var alertDialog: AlertDialog? = null
    private lateinit var exampleAdapter: ExampleAdapter

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initData()
        initListener()
        initPreview()
    }

    private fun initData() {
        //todo:
    }

    private fun initListener() {
    }

    private fun initPreview() {
        setTitle("Map")
        setNavigateUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        fun intent(activity: Activity): Intent {
            return Intent(activity, MapActivity::class.java)
        }
    }
}
