package com.example.raionhub.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.R
import com.example.raionhub.databinding.ActivityLoginBinding
import com.example.raionhub.repository.datasource.remote.auth.login.LoginRepoImpl
import com.example.raionhub.ui.auth.login.domain.LoginImpl
import com.example.raionhub.ui.auth.forgotpassword.LupaPasswordActivity
import com.example.raionhub.ui.main.MainActivity
import com.example.raionhub.utils.toast
import com.example.raionhub.utils.viewobject.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var alertDialog: AlertDialog

    // Data Binding
    private lateinit var loginDataBinding: ActivityLoginBinding

    // View Model
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            LoginVMFactory(LoginImpl(LoginRepoImpl()))
        ).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data Binding
        loginDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginDataBinding.lifecycleOwner = this
        loginDataBinding.loginViewModel = loginViewModel

        // Init Progress Dialog ONCE!
        initProgressDialog()

        // Login button on click listener
        btn_login_login.setOnClickListener(View.OnClickListener {
            loginViewModel.loginWithEmailAndPassword()
            loginViewModel.result.observe(this, Observer { task ->
                when (task) {
                    is Resource.Loading -> {
                        alertDialog.show()
                    }

                    is Resource.Success -> {
                        toast("Sukses")
                        // TODO: Intent ke main
                        alertDialog.dismiss()
                        intentToMain()
                    }

                    is Resource.Failure -> {
                        toast(task.throwable.message.toString())
                        alertDialog.dismiss()
                    }

                    else -> {
                        // do nothing
                    }
                }
            })
        })
    }

    private fun intentToMain() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun initProgressDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.getLayoutInflater()
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
    }
}
