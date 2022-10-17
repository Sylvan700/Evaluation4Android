package com.technipixl.eval04android.DB.Entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = [ "expenseId" ,
    "typeId"])
data class ExpenseXExpenseType(
    var expenseId: Long,
    var typeId: Long
)

data class ExpenseWithType(
    @Embedded
    val expense: Expense,
    @Relation(
        parentColumn = "expenseId",
        entityColumn = "typeId",
        associateBy = Junction(ExpenseXExpenseType::class)
    )
    val types: List<ExpenseType>
)

data class TypeWithExpense(
    @Embedded
    val genre: ExpenseType,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "expenseId",
        associateBy = Junction(ExpenseXExpenseType::class)
    )
    val expenses: List<Expense>
)