package com.technipixl.eval04android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.eval04android.DB.Entities.Expense
import com.technipixl.eval04android.databinding.CellBinding

class ExpenseAdapter(private var expensesModel: List<Expense>, private val clickListener: OnItemClickListener) : RecyclerView.Adapter<ExpenseAdapter.ExpenseRowHolder>() {

    private lateinit var binding: CellBinding

    interface OnItemClickListener{
        fun onItemClicked(expense: Expense)
    }

    class ExpenseRowHolder(private var viewBinding : CellBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(expense: Expense, clickListener:OnItemClickListener){
            viewBinding.textViewDate.text = expense.date
            viewBinding.textViewExpenseName.text = expense.name
            viewBinding.textViewPrice.text = expense.value.toString()
            itemView.setOnClickListener{
                clickListener.onItemClicked(expense)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseRowHolder {
       binding = CellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseRowHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseRowHolder, position: Int) {
        holder.bind(expensesModel[position], clickListener)
    }

    override fun getItemCount() = expensesModel.size


}
