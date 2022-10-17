package com.technipixl.eval04android.DB

import android.content.Context
import com.technipixl.eval04android.DB.Entities.Expense
import com.technipixl.eval04android.DB.Entities.ExpenseType
import com.technipixl.eval04android.DB.Entities.ExpenseXExpenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseDBRepo {
    companion object {
        var expenseDB: ExpenseDB? = null

        fun initializeDB(context: Context): ExpenseDB {
            val db = ExpenseDB.getDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val types = expenseDB?.typeDao()?.getAll()
                if (types?.isEmpty() == true) {
                    expenseDB!!.typeDao().insert(ExpenseType(name = "Tax"))
                    expenseDB!!.typeDao().insert(ExpenseType(name = "Bil"))
                    expenseDB!!.typeDao().insert(ExpenseType(name = "Subscription"))
                    expenseDB!!.typeDao().insert(ExpenseType(name = "Online Payment"))
                }
            }
            return db
        }

        fun insertExpense(
            context: Context,
            date: String,
            name: String,
            value: Float,
            selectedType: ExpenseType? = null
        ) {
            expenseDB = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val expense = Expense(date = date, name = name, value = value)
                val expenseId = expenseDB!!.expenseDao().insert(expense)
                if (selectedType != null) {
                    expenseDB!!.expenseXTypeDao().insert(
                        ExpenseXExpenseType(
                            expenseId = expenseId,
                            typeId = selectedType.typeId
                        )
                    )
                }
            }
        }

    }
}