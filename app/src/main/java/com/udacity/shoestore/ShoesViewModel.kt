package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoesViewModel : ViewModel() {
    private val _shoes = MutableLiveData<List<Shoe>>()

    val shoes: LiveData<List<Shoe>>
        get() = _shoes
    val shoeSizePosition = MutableLiveData<Int>()
    val shoe = MutableLiveData<Shoe>()

    init {
        shoe.value = Shoe("", 6.0, "", "", ArrayList())
    }

    fun addShoe() {
        if (shoe.value!!.name == "") {
            Timber.w("Tried to add shoe before assigning its' values")
            return
        }
        val list: ArrayList<Shoe> = if (_shoes.value != null) {
            _shoes.value as ArrayList<Shoe>
        } else {
            ArrayList()
        }
        shoe.value!!.size = shoeSizePosition.value?.div(2.0)?.plus(6.0) ?: 6.0
        list.add(shoe.value!!)
        _shoes.value = list
        shoe.value = Shoe("", 6.0, "", "", ArrayList())
    }
}