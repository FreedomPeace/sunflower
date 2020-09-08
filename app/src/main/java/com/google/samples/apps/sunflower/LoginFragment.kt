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

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.samples.apps.sunflower.data.AppDatabase
import com.google.samples.apps.sunflower.data.LoginDao
import com.google.samples.apps.sunflower.databinding.FragmentLoginBinding
import com.google.samples.apps.sunflower.viewmodels.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var root: View
    private lateinit var loginDao: LoginDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        loginDao = AppDatabase.getInstance(context!!).loginDao()
        val loginViewModel = LoginViewModel(loginDao, activity!!.application)
        root = FragmentLoginBinding.inflate(inflater, container, false).apply {

            viewModel = loginViewModel
            lifecycleOwner = viewLifecycleOwner
            val direction =
                    LoginFragmentDirections.actionLoginFragmentToViewPagerFragment()
            val sp = activity!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            root.post {
                username.setText(sp.getString(USERNAME, ""))
                password.setText(sp.getString(PASSWORD, ""))
            }
            login.setOnClickListener {
                if (!TextUtils.isEmpty(loginViewModel.username.value) &&
                        !TextUtils.isEmpty(loginViewModel.password.value)) {
                    if (sp.getString(USERNAME, "-1") == loginViewModel.username.value &&
                            sp.getString(PASSWORD, "-1") == loginViewModel.password.value) {
                        root.findNavController().navigate(direction)
                    }
//                loginViewModel.saveLoginInfo(Login(username, password))
                }
            }
            register.setOnClickListener{
                if (!TextUtils.isEmpty(loginViewModel.username.value) &&
                        !TextUtils.isEmpty(loginViewModel.password.value)) {
                   sp.edit().putString(USERNAME, loginViewModel.username.value)
                            .putString(PASSWORD, loginViewModel.password.value)
                            .apply()
                }
            }
            loginViewModel.username.observe(viewLifecycleOwner, Observer<String?> {
                if (sp.getString(USERNAME, "") == loginViewModel.username.value &&
                        sp.getString(PASSWORD, "") == loginViewModel.password.value) {
                    root.findNavController().navigate(direction)
                }
            })
//            loginViewModel.loginInfo666().observe(viewLifecycleOwner, Observer<Login?> {
//                val a = viewLifecycleOwner.lifecycle.currentState
//                if (it == null) {
//                    Toast.makeText(context, "当前账户密码不为666", Toast.LENGTH_LONG).show()
//                } else {
//                    Toast.makeText(context, it.username, Toast.LENGTH_LONG).show()
//                    root.findNavController().navigate(direction)
//                }
//            })
        }.root

        return root
    }
    companion object {
        private const val USERNAME: String = "username"
        private const val PASSWORD: String = "password"

    }
}