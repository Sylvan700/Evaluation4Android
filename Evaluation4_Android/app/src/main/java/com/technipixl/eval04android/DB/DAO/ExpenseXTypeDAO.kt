package com.technipixl.eval04android.DB.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.technipixl.eval04android.DB.Entities.ExpenseXExpenseType

@Dao
interface ExpenseXTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: ExpenseXExpenseType)

    @Query("SELECT * FROM expenseXExpenseType")
    suspend fun getAll(): List<ExpenseXExpenseType>

}

