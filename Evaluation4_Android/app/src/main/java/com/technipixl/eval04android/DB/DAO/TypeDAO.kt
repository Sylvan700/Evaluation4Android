package com.technipixl.eval04android.DB.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.technipixl.eval04android.DB.Entities.ExpenseType
import com.technipixl.eval04android.DB.Entities.TypeWithExpense

@Dao
interface TypeDAO {

    @Query("SELECT * FROM expenseType WHERE typeId = :typeId LIMIT 1")
    suspend fun findById(typeId: Long): ExpenseType

    @Query("SELECT * FROM expenseType WHERE typeId = :typeId LIMIT 1")
    suspend fun findTypeWithExpenseById(typeId: Long): TypeWithExpense

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: ExpenseType): Long

    @Query("SELECT * FROM expenseType")
    suspend fun getAll(): List<ExpenseType>

    @Query("DELETE FROM expenseType WHERE typeId = :typeId")
    suspend fun deleteType(typeId: Long)
}