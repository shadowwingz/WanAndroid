package com.shadowwingz.wanandroid.profile.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileResponseData(
  @PrimaryKey val userId: Int,
  @ColumnInfo(name = "coin_count") val coinCount: Int,
  val level: Int,
  val nickname: String,
  val rank: String,
  val username: String
)
