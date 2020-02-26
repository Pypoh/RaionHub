package com.example.raionhub.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.R
import com.example.raionhub.databinding.FragmentLoginBinding
import com.example.raionhub.main.MainActivity

class LoginFragment : Fragment() {

    private lateinit var dataBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        context ?: return dataBinding.root

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        dataBinding.loginViewModel = loginViewModel
        dataBinding.lifecycleOwner = this
    }

    private fun toMain() {
        startActivity(Intent(context, MainActivity::class.java))
    }
}
