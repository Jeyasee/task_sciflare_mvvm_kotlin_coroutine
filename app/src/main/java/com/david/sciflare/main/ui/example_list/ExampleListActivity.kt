package com.david.sciflare.main.ui.example_list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.asLiveData
import com.david.sciflare.R
import com.david.sciflare.databinding.ActivityExampleListBinding
import com.domain.model.example_list.ExampleApiModelItem
import com.david.sciflare.main.ui.example_list.adapter.ExampleAdapter
import com.david.support.base_class.ActionBarActivity
import com.david.support.utility.view.DialogBox.confirmationDialog
import com.david.support.utility.view.DialogBox.listDialog
import com.domain.entity.flickr.UserModelEntity
import com.domain.model.user_data.RequestUserModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast
import java.util.*

@AndroidEntryPoint
class ExampleListActivity : ActionBarActivity<ActivityExampleListBinding, ExampleListViewModel>(
    R.layout.activity_example_list,
    ExampleListViewModel::class.java
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
        viewModel.userProfile.asLiveData().observe(this) { userProfile ->
            //todo: attach user data to ui
        }
        viewModel.userModelLiveData.observe(this){userModelEntityList ->
            /*Database insert/update will be listened here*/

            childBinding.bankRecyclerView.adapter = ExampleAdapter().also {
                it.submitList(userModelEntityList)
            }
        }
        //initFetching() /*invoking network call execution at beginning and retriving data*/
    }

    private fun initFetching() {
        viewModel.retrieveList().observe(this){
            //when network call success/failure @String will be passed here
            toast(it)
        }
    }

    private fun initListener() {
        childBinding.actionFloatingActionButton.setOnClickListener {
            childBinding.extendFab = !childBinding.extendFab /*restore to another state*/
        }
        childBinding.addFloatingActionButton.setOnClickListener {
            val name = randomString
            val email = randomString+"@gmail.com"
            val mobile = "0124567890"
            val gender = if (Random().nextBoolean()) "Male" else "Female"

            viewModel.addUser(name,email,mobile,gender).observe(this){(success,message)->
                toast(message)
            }
        }
    }

    private val randomString get() = UUID.randomUUID().toString().substring((0..10).random())

    private fun initPreview() {
        setTitle("Example List")
        setNavigateUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onItemSelected(position: Int, item: UserModelEntity) {
        //todo: handle adapter listener
    }

    override fun onDestroy() {
        super.onDestroy()
        alertDialog?.dismiss()
    }

    override fun getLocale(context: Context): String? {
        return "en"
    }

    companion object {
        fun intent(activity:Activity): Intent {
            return Intent(activity,ExampleListActivity::class.java)
        }
    }
}
