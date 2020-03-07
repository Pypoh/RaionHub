package com.example.raionhub.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.LupaPasswordActivity
import com.example.raionhub.R
import com.example.raionhub.databinding.FragmentLoginBinding
import com.example.raionhub.main.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() , View.OnClickListener{

    private lateinit var dataBinding : FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    //Edit Text
    private lateinit var Email : EditText
    private lateinit var Password : EditText

    //Button
    private lateinit var LupaPass : Button
    private lateinit var MasukTamu : Button
    private lateinit var LoginButton : Button

    //Firebase
    private lateinit var mAuth : FirebaseAuth

    //Alert Dialog
    lateinit var alertDialog: AlertDialog


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

        Email = view!!.findViewById(R.id.iet_email_login)
        Password = view!!.findViewById(R.id.iet_pass_login)
        LupaPass = view!!.findViewById(R.id.btn_lupapass_login)
        MasukTamu = view!!.findViewById(R.id.btn_masuktamu_login)
        LoginButton = view!!.findViewById(R.id.btn_login_login)

        mAuth = FirebaseAuth.getInstance()

        LoginButton.setOnClickListener(this)
        LupaPass.setOnClickListener(this)
        MasukTamu.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(view : View?) {
        when (view?.id) {
            R.id.btn_login_login -> signIn()
            R.id.btn_lupapass_login -> lupapass()
            R.id.btn_masuktamu_login -> main()
        }
    }

    private fun main() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun lupapass() {
        val intent = Intent(context, LupaPasswordActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
    private fun signIn() {
        if (InputValidate()) {
            val email = Email.getText().toString()
            val password = Password.getText().toString()
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    showProgressDialog()
                    Toast.makeText(context, "Sign In Berhasil", Toast.LENGTH_SHORT).show()
                    main()
                } else {
                    showProgressDialog()
                    val err = task.exception!!.message
                    if (err != null) {
                        if (err.contains("password")) {
                            Password.setError(err)
                        } else {
                            Toast.makeText(context, err, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

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
        val dialogBuilder = context?.let { AlertDialog.Builder(it) }
        val inflater = this.getLayoutInflater()
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        dialogBuilder!!.setView(dialogView)
        dialogBuilder.setCancelable(false)
        if (dialogBuilder != null) {
            alertDialog = dialogBuilder.create()
        }
        alertDialog.show()
    }
}
