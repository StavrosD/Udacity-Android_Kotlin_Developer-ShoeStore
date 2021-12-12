package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoesBinding

class ShoesFragment : Fragment() {
    private var _binding: FragmentShoesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = FragmentShoesBinding.inflate(inflater, container, false)

        // Setup the floating action button
        binding.fab.setOnClickListener { view ->
            view?.findNavController()
                ?.navigate(ShoesFragmentDirections.actionShoesFragmentToDetailFragment())
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.shoes.observe(this, { shoes ->
            if (shoes.isNotEmpty()) {
                shoes.forEach {
                    val newView = ShoeView(this.context)
                    newView.setShoe(it)
                    binding.shoeList.addView(newView)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                findNavController().navigate(R.id.loginFragment)
                return true
            }
            else -> {
                return true
            }
        }
        //    return item.onNavDestinationSelected( findNavController()) || super.onOptionsItemSelected(item)
    }
}