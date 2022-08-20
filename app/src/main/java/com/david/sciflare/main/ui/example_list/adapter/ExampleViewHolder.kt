package com.david.sciflare.main.ui.example_list.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.domain.model.example_list.ExampleApiModelItem
import com.david.sciflare.R
import com.david.sciflare.databinding.ItemExampleListBinding
import com.david.support.base_class.BaseViewHolder

class ExampleViewHolder(
    private val itemBankBinding: ItemExampleListBinding,
    selectionList: List<Int>,
) : BaseViewHolder<ExampleApiModelItem, Int>(selectionList, itemBankBinding.root) {

    override fun bind(position: Int, item: ExampleApiModelItem) {
        itemBankBinding.model = item //assign data to preview

        Glide.with(context)
            .load(item.image)
            .placeholder(R.drawable.ic_round_person_24)
            .signature(ObjectKey(item.image))
            .into(itemBankBinding.imageAppCompatImageView)

    }
}