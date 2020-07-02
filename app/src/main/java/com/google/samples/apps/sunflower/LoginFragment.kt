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

package com.google.samples.apps.sunflower

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.samples.apps.sunflower.data.AppDatabase
import com.google.samples.apps.sunflower.data.Login
import com.google.samples.apps.sunflower.data.LoginDao
import com.google.samples.apps.sunflower.databinding.FragmentLoginBinding
import com.google.samples.apps.sunflower.viewmodels.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var root:View
    private lateinit var loginDao: LoginDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
         loginDao = AppDatabase.getInstance(context!!).loginDao()

         root = FragmentLoginBinding.inflate(inflater, container, false).apply {
            val loginViewModel = LoginViewModel(loginDao, activity!!.application)
             viewModel = loginViewModel
             lifecycleOwner = viewLifecycleOwner
            login.setOnClickListener { v ->
                val username = username.text.toString()
                val password = password.text.toString()
                if (!TextUtils.isEmpty(username) &&
                        !TextUtils.isEmpty(password)) {
                    loginViewModel.saveLoginInfo(Login(username,password))
//                    val direction =
//                            LoginFragmentDirections.actionLoginFragmentToViewPagerFragment()
//                    v.findNavController().navigate(direction)
//                    Thread {
//                        kotlin.run {
//                            val ret = loginDao.saveLoginInfo(Login(
//                                    username, password
//                            ))
//                            println(ret)
//                            val message = loginDao.getLoginInfo()
//                            println(message)
//                        }
//                    }.start()
                }
            }
        }.root
//        Thread(){
//            kotlin.run {
//                loginDao.getLoginInfo("666").apply {
//                    activity!!.runOnUiThread {
//                        root.username.setText(this.username)
//                        root.password.setText(this.password)
//                    }
//
//                }
//            }
//        }.start()
        return root
    }

}