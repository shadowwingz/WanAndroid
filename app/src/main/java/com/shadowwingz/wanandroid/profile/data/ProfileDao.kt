package com.shadowwingz.wanandroid.profile.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.shadowwingz.wanandroid.profile.data.model.ProfileResponseData as ProfileModel

@Dao
interface ProfileDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertProfile(profile: ProfileModel)
}