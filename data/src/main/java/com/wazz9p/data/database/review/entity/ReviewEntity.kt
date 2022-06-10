package com.wazz9p.data.database.review.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val userName: String,
    val phoneNumber: String?,
    val message: String,
    val adminMessage: String?
)
