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
import com.example.raionhub.repository.datasource.remote.auth.LoginRepoImpl
import com.example.raionhub.ui.auth.login.domain.LoginImpl
import com.example.raionhub.ui.auth.splash.LupaPasswordActivity
import com.example.raionhub.ui.main.MainActivity
import com.example.raionhub.utils.viewobject.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var Email: EditText
    private lateinit var Password: EditText
    private lateinit var LupaPass: Button
    private lateinit var MasukTamu: Button
    private lateinit var LoginButton: Button
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

    /* NOTE: Semua logic harusnya di viewModel */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        loginDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Ini gausah
//        Email = findViewById(R.id.iet_email_login)
//        Password = findViewById(R.id.iet_pass_login)
//        LupaPass = findViewById(R.id.btn_lupapass_login)
//        MasukTamu = findViewById(R.id.btn_masuktamu_login)
//        LoginButton = findViewById(R.id.btn_login_login)

        // Ini harusnya di splash screen
        mAuth = FirebaseAuth.getInstance()

        // Ini gapapa
        LoginButton.setOnClickListener(this)
        LupaPass.setOnClickListener(this)
        MasukTamu.setOnClickListener(this)

        // New Code from View Model Revision
//        loginViewModel.fetchLoginAuth.observe(this, Observer {task ->
//            when(task) {
//                is Resource.Loading -> {
//
//                }
//
//                is Resource.Success -> {
//
//                }
//
//                is Resource.Failure -> {
//
//                }
//            }
//        })
    }

    // OnStart nya gausah
    override fun onStart() {
        super.onStart()
//        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onClick(view: View?) {
//        when (view?.id) {
//            R.id.btn_login_login -> signIn()
//            R.id.btn_lupapass_login -> lupapass()
//            R.id.btn_masuktamu_login -> main()
//        }
}

    private fun main() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun lupapass() {
        val intent = Intent(this@LoginActivity, LupaPasswordActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun signIn() {
        if (InputValidate()) {
            val email = Email.getText().toString()
            val password = Password.getText().toString()
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        showProgressDialog()
                        Toast.makeText(applicationContext, "Sign In Berhasil", Toast.LENGTH_SHORT)
                            .show()
                        main()
                    } else {
                        showProgressDialog()
                        val err = task.exception!!.message
                        if (err != null) {
                            if (err.contains("password")) {
                                Password.setError(err)
                            } else {
                                Toast.makeText(applicationContext, err, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
        }
    }

    // Ini dipindah ke viewmodel
    private fun InputValidate(): Boolean {
        var res = true
        if (Email.getText().toString().isEmpty()) {
            res = false
            Email.setError("This is required")
        }
        if (Password.getText().toString().isEmpty()) {
            res = false
            Password.setError("This is required")
        }
        return res
    }

    fun showProgressDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.getLayoutInflater()
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}
