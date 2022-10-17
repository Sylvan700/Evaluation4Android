package com.technipixl.eval04android.DB

import android.content.Context
import androidx.room.*
import com.technipixl.eval04android.DB.DAO.ExpenseDAO
import com.technipixl.eval04android.DB.DAO.ExpenseXTypeDAO
import com.technipixl.eval04android.DB.DAO.TypeDAO
import com.technipixl.eval04android.DB.Entities.Convertes
import com.technipixl.eval04android.DB.Entities.Expense
import com.technipixl.eval04android.DB.Entities.ExpenseType
import com.technipixl.eval04android.DB.Entities.ExpenseXExpenseType

@Database(entities = arrayOf(Expense::class, ExpenseXExpenseType::class, ExpenseType::class), version = 1)
@TypeConverters(Convertes::class)
abstract class ExpenseDB : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDAO
    abstract fun typeDao(): TypeDAO
    abstract fun expenseXTypeDao(): ExpenseXTypeDAO

    companion object {

        @Volatile
        private var sharedInstance: ExpenseDB? = null

        fun getDB(context: Context) : ExpenseDB {
            if (sharedInstance != null) return sharedInstance!!
            synchronized(this) {
                sharedInstance = Room
                    .databaseBuilder(context, ExpenseDB::class.java, "expenses.db")
                    .fallbackToDestructiveMigration()
                    .build()
                return sharedInstance!!
            }
        }
    }

}