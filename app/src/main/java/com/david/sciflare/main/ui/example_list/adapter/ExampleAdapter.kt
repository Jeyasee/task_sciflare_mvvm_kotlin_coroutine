package com.david.sciflare.main.ui.example_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.david.sciflare.databinding.ItemExampleListBinding
import com.david.support.base_class.BaseAdapter2
import com.david.support.base_class.BaseViewHolder
import com.domain.entity.flickr.UserModelEntity

class ExampleAdapter(private val itemListener: ItemListener? = null) :
    BaseAdapter2<UserModelEntity, Int>() {
    override fun getList(): List<UserModelEntity> {
        return currentList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<UserModelEntity, Int> {
        return ExampleViewHolder(
            ItemExampleListBinding.inflate(LayoutInflater.from(parent.context)),
            getSelections()
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<UserModelEntity, Int>, position: Int) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            itemListener?.onItemSelected(position, currentList[position])
        }
    }

    interface ItemListener {
        fun onItemSelection(position: Int, item: UserModelEntity) {}
        fun onItemSelected(position: Int, item: UserModelEntity) {}
        fun onOptionSelected(
            view: View? = null,
            position: Int,
            item: UserModelEntity,
        ) {
        }
    }

    override fun isSameItem(oldItem: UserModelEntity, newItem: UserModelEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun isSameContent(
        oldItem: UserModelEntity,
        newItem: UserModelEntity,
    ): Boolean {
        return oldItem.name == newItem.name
    }
}