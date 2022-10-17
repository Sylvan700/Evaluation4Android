package com.technipixl.eval04android.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.technipixl.eval04android.R
import com.technipixl.eval04android.databinding.FragmentDetailsBinding
import com.technipixl.eval04android.databinding.FragmentMainBinding

class DetailsFragment : Fragment() {

    private  lateinit var binding : FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.imageButton.setOnClickListener{
            saveNewTax()
        }

        return binding.root
    }

    private fun saveNewTax() {
        findNavController().navigateUp()

    }

}