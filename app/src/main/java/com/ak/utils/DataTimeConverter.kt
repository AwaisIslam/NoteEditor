package com.ak.utils

import androidx.room.TypeConverter
import java.time.Instant

object DataTimeConverter {

    @TypeConverter
    fun fromDate(date: Instant?): Long? = date?.toEpochMilli()

    @TypeConverter
    fun fromDate(millisSinceEpoch: Long?): Instant? = millisSinceEpoch?.let {
        Instant.ofEpochMilli(it)
    }
}