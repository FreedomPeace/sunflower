/*
 * Copyright 2018 Google LLC
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

package com.google.samples.apps.sunflower.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.samples.apps.sunflower.data.AppDatabase
import com.google.samples.apps.sunflower.data.Login
import kotlinx.coroutines.coroutineScope

class LoginInfoSaveWorker(
    /*private var loginInfo:Login,*/
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {


                    val database = AppDatabase.getInstance(applicationContext)
                    database.loginDao().saveLoginInfo(Login("lisi","666"))

                    Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error insert loginInfo database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = LoginInfoSaveWorker::class.java.simpleName
    }
}