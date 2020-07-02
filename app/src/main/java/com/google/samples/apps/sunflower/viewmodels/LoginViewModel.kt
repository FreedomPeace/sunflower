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

package com.google.samples.apps.sunflower.viewmodels
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.samples.apps.sunflower.data.Login
import com.google.samples.apps.sunflower.data.LoginDao
import kotlinx.coroutines.launch

class LoginViewModel(
        private var loginDao: LoginDao, application: Application
) : AndroidViewModel(application) {
//    var username: MutableLiveData<String> = MutableLiveData()
//    var password: MutableLiveData<String> = MutableLiveData()

    fun loginInfo666() = loginDao.getLoginInfo666("666")
//    {
//          val loginInfo666 = loginDao.getLoginInfo666("666")
//          username.value = loginInfo666.value?.username;
//          password.value = loginInfo666.value?.password;
//    }

    fun saveLoginInfo(loginInfo: Login) {
        viewModelScope.launch {
            val saveLoginInfo = loginDao.saveLoginInfo(loginInfo)
            println(saveLoginInfo)
        }

    }

}