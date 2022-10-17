package com.technipixl.eval04android.Fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.technipixl.eval04android.DB.ExpenseDBRepo
import com.technipixl.eval04android.databinding.FragmentDetailsBinding


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
        binding.textInputType.setOnClickListener {
            seletectType()
        }
        binding.textInputDate.setOnClickListener {
            datePicker()
        }

        return binding.root
    }

    private fun datePicker() {
        val datePickerDialog = DatePickerDialog(requireContext())
        val calendar = Calendar.getInstance()
        datePickerDialog.show()

            binding.textInputDate.setText("${calendar.get(Calendar.DAY_OF_MONTH)}/" +
                    "${calendar.get(Calendar.MONTH)}/${calendar.get(Calendar.YEAR)}")

    }

    private fun seletectType() {

        val message = "Y a rien lol"

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose a Type.")
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, which ->
           binding.textInputType.setText(message)
        }
        builder.setNegativeButton("cancel") { dialog, which ->
            binding.textInputType.setText("")
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun saveNewTax() {

        if(binding.textInputAmount.text.isNullOrEmpty() || binding.textInputDate.text.isNullOrEmpty() ||
            binding.textInputName.text.isNullOrEmpty() || binding.textInputType.text.isNullOrEmpty()){
        }
        else{
            insertNewExpense()
        }

        findNavController().navigateUp()

    }

    private fun insertNewExpense(){

            ExpenseDBRepo.insertExpense(context = requireContext(),date = binding.textInputDate.text.toString(),
                name = binding.textInputName.text.toString(),value = binding.textInputAmount.text.toString().toFloat())
    }

}