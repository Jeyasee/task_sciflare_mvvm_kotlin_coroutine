package com.david.sciflare.main.ui.navigation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.david.sciflare.R
import com.david.sciflare.databinding.ActivityNavigationBinding
import com.david.sciflare.main.ui.example_list.ExampleListActivity
import com.david.sciflare.main.ui.example_list.adapter.ExampleAdapter
import com.david.support.base_class.ActionBarActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : ActionBarActivity<ActivityNavigationBinding, NavigationViewModel>(
    R.layout.activity_navigation,
    NavigationViewModel::class.java
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
        childBinding.listAppCompatButton.setOnClickListener {
            startActivity(ExampleListActivity.intent(this@NavigationActivity))
        }
        childBinding.mapAppCompatButton.setOnClickListener {
            //todo: invoke map activity
        }
    }

    private fun initPreview() {
        setTitle("Example List")
        setNavigateUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        alertDialog?.dismiss()
    }

    override fun getLocale(context: Context): String? {
        return "en"
    }
}
