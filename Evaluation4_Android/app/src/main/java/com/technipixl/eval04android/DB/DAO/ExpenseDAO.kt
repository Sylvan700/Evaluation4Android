package com.technipixl.eval04android.DB.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.technipixl.eval04android.DB.Entities.Expense
import com.technipixl.eval04android.DB.Entities.ExpenseWithType

@Dao
interface ExpenseDAO {

    @Query("SELECT * FROM expense WHERE expenseId = :expenseId LIMIT 1")
    suspend fun findById(expenseId: Long): Expense

    @Query("SELECT * FROM expense WHERE expenseId = :expenseId LIMIT 1")
    suspend fun findExpenseWithTypeById(expenseId: Long): ExpenseWithType

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense): Long

    @Query("SELECT * FROM expense")
    suspend fun getAll(): List<Expense>

    @Query("DELETE FROM expense WHERE expenseId = :expenseId")
    suspend fun deleteExpense(expenseId: Long)
}