package com.example.raionhub.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        view.findViewById<View>(R.id.button_home).setOnClickListener {
//            val action = HomeFragmentDirections
//                    .actionHomeFragmentToHomeSecondFragment("From HomeFragment")
//            NavHostFragment.findNavController(this@HomeFragment)
//                    .navigate(action)
//        }
//    }
}
