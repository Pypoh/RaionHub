package com.example.raionhub.main.memberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.R

class MemberListFragment : Fragment() {

    private lateinit var dashboardViewModel: MemberListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(MemberListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_memberlist, container, false)

        return root
    }
}
