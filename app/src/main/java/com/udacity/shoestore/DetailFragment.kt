package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val viewModel: ShoesViewModel by activityViewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var shoeSizesList = ArrayList<Double>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val spinner = binding.shoeSizesSpinner

        // fill the spinner with shoe sizes
        for (shoeSize in 12..30) {  // US shoe sizes, from 6 to 15 (12/2 to 30/2).
            shoeSizesList.add(shoeSize / 2.0)
        }
        val sizes = shoeSizesList.toList()
        val shoeSizeAdapter = ArrayAdapter(
            this.context!!,
            android.R.layout.simple_spinner_dropdown_item,
            sizes
        ) as SpinnerAdapter
        spinner.adapter = shoeSizeAdapter

        // Validate input data. The "SAVE" button is disabled if any of the editTexts is blank.
        binding.editName.doAfterTextChanged { validate() }
        binding.editCompany.doAfterTextChanged { validate() }
        binding.editDescription.doAfterTextChanged { validate() }

        binding.buttonCancel.setOnClickListener {
             findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoesFragment())
        }
        binding.buttonSave.setOnClickListener {
            viewModel.addShoe()
            findNavController().navigateUp()
        }
        binding.shoeVM = viewModel
        return binding.root
    }

    private fun validate(): Boolean {
        binding.buttonSave.isEnabled = false
        if (binding.editName.text.isBlank()) {
            return false
        }
        if (binding.editCompany.text.isBlank()) {
            return false
        }
        if (binding.editDescription.text.isBlank()) {
            return false
        }
        binding.buttonSave.isEnabled = true
        return true
    }
}