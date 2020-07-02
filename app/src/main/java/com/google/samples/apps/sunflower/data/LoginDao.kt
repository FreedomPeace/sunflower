/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface LoginDao {
    @Query("SELECT * FROM login where id = :id limit 1")
    fun getLoginInfo(id:String): Login
    @Query("SELECT * FROM login where id = :id limit 1")
    fun getLoginInfo666(id:String): LiveData<Login>

    @Query("SELECT * FROM login ")
    fun getLoginInfo(): List<Login>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun saveLoginInfo(loginInfo: Login):Long
}
