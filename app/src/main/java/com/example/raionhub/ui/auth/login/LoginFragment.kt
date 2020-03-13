package com.example.raionhub.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.R
import com.example.raionhub.databinding.FragmentLoginBinding
import com.example.raionhub.repository.datasource.remote.auth.login.LoginRepoImpl
import com.example.raionhub.ui.auth.login.domain.LoginImpl
import com.example.raionhub.ui.main.MainActivity
import com.example.raionhub.utils.toast
import com.example.raionhub.utils.viewobject.Resource
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {

    // Utils
    private lateinit var alertDialog: AlertDialog

    // Data Binding
    private lateinit var loginDataBinding: FragmentLoginBinding

    // View Model
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            LoginVMFactory(LoginImpl(LoginRepoImpl()))
        ).get(LoginViewModel::class.java)
    }

    // Views
    private lateinit var loginButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Setup Data Binding
        loginDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginDataBinding.loginViewModel = loginViewModel

        // View Binding
        setupViewBinding(loginDataBinding.root)

        // Init Progress Dialog
        initProgressDialog()

        // Setup Login Button onClick Listener
        setupButtonListener()

        return loginDataBinding.root
    }

    private fun setupViewBinding(view: View) {
        loginButton = view.findViewById(R.id.btn_login_login)
    }

    private fun setupButtonListener() {
        loginButton.setOnClickListener {
            loginViewModel.loginWithEmailAndPassword()
            loginViewModel.result.observe(viewLifecycleOwner, Observer { task ->
                when (task) {
                    is Resource.Loading -> {
                        if (!alertDialog.isShowing) alertDialog.show()
                    }

                    is Resource.Success -> {
                        // TODO: Intent ke main
                        context!!.toast("Success")
                        if (alertDialog.isShowing) alertDialog.dismiss()
                        intentToMain()
                    }

                    is Resource.Failure -> {
                        if (alertDialog.isShowing) alertDialog.dismiss()
                        context!!.toast(task.throwable.message.toString())
                    }

                    else -> {
                        // do nothing
                        context!!.toast(task.toString())
                    }
                }
            })
        }
    }

    private fun intentToMain() {
        val intent = Intent(this.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity!!.finish()
    }

    private fun initProgressDialog() {
        val dialogBuilder = AlertDialog.Builder(context!!)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
    }
}
