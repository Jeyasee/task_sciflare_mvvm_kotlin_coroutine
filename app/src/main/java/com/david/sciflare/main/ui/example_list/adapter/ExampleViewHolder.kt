package com.david.sciflare.main.ui.example_list.adapter

import com.david.sciflare.databinding.ItemExampleListBinding
import com.david.support.base_class.BaseViewHolder
import com.domain.entity.flickr.UserModelEntity

class ExampleViewHolder(
    private val binding: ItemExampleListBinding,
    selectionList: List<Int>,
) : BaseViewHolder<UserModelEntity, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: UserModelEntity) {
        binding.model = item //assign data to preview
    }
}