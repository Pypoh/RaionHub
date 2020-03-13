package com.example.raionhub.ui.main.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.R
import com.example.raionhub.ui.auth.AuthActivity
import com.example.raionhub.ui.auth.forgotpassword.LupaPasswordActivity
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var notificationsViewModel: ProfileViewModel
    private lateinit var profilePhoto: CircleImageView
    private lateinit var KeluarBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        profilePhoto = root.findViewById(R.id.civ_image_profile)
        KeluarBtn = root.findViewById(R.id.btn_logout_profile)

        return root
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_logout_profile -> showdialog()
            R.id.btn_lupapass_login -> changePicture()
        }
    }

    // ganti circle picturenya
    private fun changePicture() {
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, 3000)
    }

    // ngeset option menu di profile
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_edit_profile) {
            val editProfileIntent = Intent(activity, EditProfileFragment::class.java)
            activity!!.startActivity(editProfileIntent)
            return true
        } else if (id == R.id.menu_setting) {
            // val Setting = Intent(activity, SettingActivity::class.java)
            // activity!!.startActivity(Setting)
            return true
        } else if (id == R.id.menu_lupapass) {
            val LupapassIntent = Intent(activity, LupaPasswordActivity::class.java)
            activity!!.startActivity(LupapassIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // kalo logout diliatin dialognya
    private fun showdialog() {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setMessage("Apakah Anda yakin untuk keluar dari aplikasi?")
            .setPositiveButton("Keluar") { _, _ ->
                val intent = Intent(context, AuthActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                activity?.finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = alertBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        }
        alertDialog.show()
    }
}
