package com.technipixl.eval04android.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseType(
    @PrimaryKey(autoGenerate = true)
    var typeId: Long = 0,
    var name: String?
)