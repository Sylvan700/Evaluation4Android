package com.technipixl.eval04android.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var expenseId: Long = 0,
    var date : String?,
    var name: String?,
    var value : Float?
)