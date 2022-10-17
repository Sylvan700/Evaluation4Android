package com.technipixl.eval04android.DB.Entities

import androidx.room.TypeConverter
import java.util.*

class Convertes {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}