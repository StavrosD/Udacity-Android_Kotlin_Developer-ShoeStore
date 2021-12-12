package com.udacity.shoestore

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeView : LinearLayout {

    private var _shoe: Shoe = Shoe("", 6.0, "", "", ArrayList())

    private var _binding: ShoeListItemBinding? = null
    private val binding get() = _binding!!


    constructor(context: Context?) : super(context)


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setShoe(shoe: Shoe) {
        _shoe = shoe
        init()
    }

    private fun init() {
        val binding = ShoeListItemBinding.inflate(LayoutInflater.from(context), this, true)
        binding.shoeName.text = _shoe.name
        binding.shoeCompany.text = _shoe.company
        binding.shoeDescription.text = _shoe.description
        binding.shoeSize.text = _shoe.size.toString()
        invalidate()
    }


}