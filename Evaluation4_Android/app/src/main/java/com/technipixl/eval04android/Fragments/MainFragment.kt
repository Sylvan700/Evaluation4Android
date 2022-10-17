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
import com.technipixl.eval04android.DB.ExpenseDB
import com.technipixl.eval04android.DB.ExpenseDBRepo
import com.technipixl.eval04android.ExpenseAdapter
import com.technipixl.eval04android.R
import com.technipixl.eval04android.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

class MainFragment : Fragment(), ExpenseAdapter.OnItemClickListener {

    private lateinit var binding : FragmentMainBinding
    private val db = ExpenseDBRepo.initializeDB(requireContext())
    private var expensesModel : List<Expense>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        CoroutineScope(Dispatchers.IO).launch {
            recyclerViewConfig()
        }
        // val allTypes = ExpenseDBRepo.getAllTypes(requireContext())

        //ExpenseDBRepo.insertExpense(requireContext(), Date("fefe"),"Cookies", 2.0F, allTypes.value!![1])

        binding.imageButton.setOnClickListener{
            addNewTax()
        }

        return binding.root

    }

    private fun addNewTax(){
        findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
    }

    suspend fun recyclerViewConfig(){
        val recyclerView = binding.recyclerView1

        expensesModel = db.expenseDao().getAll()

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter =  expensesModel?.let { ExpenseAdapter(it, this) }

    }

    override fun onItemClicked(expense: Expense) {
        //Nothing
    }

}