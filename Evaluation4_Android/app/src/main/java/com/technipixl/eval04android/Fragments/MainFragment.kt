package com.technipixl.eval04android.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.eval04android.DB.Entities.Expense
import com.technipixl.eval04android.DB.Entities.ExpenseType
import com.technipixl.eval04android.DB.Entities.ExpenseXExpenseType
import com.technipixl.eval04android.DB.ExpenseDB
import com.technipixl.eval04android.DB.ExpenseDBRepo
import com.technipixl.eval04android.ExpenseAdapter
import com.technipixl.eval04android.R
import com.technipixl.eval04android.databinding.FragmentMainBinding
import kotlinx.coroutines.*
import java.util.*

class MainFragment : Fragment(), ExpenseAdapter.OnItemClickListener {

    private lateinit var binding : FragmentMainBinding
    private var typeList : List<ExpenseType>? = null
    private var expensesModel : List<Expense>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        recyclerViewConfig()
        insertNewExpense()

        binding.imageButton.setOnClickListener{
            addNewTax()
        }

        return binding.root

    }

    private fun addNewTax(){
        findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
    }

    fun recyclerViewConfig(){
        val recyclerView = binding.recyclerView1

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = expensesModel?.let { ExpenseAdapter(it, this) }

    }

    fun insertNewExpense(){
        val db = ExpenseDBRepo.initializeDB(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            expensesModel = db.expenseDao().getAll()
            binding.recyclerView1.adapter?.notifyDataSetChanged()
        }
    }


    override fun onItemClicked(expense: Expense) {
        //Nothing
    }

}