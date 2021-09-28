package com.example.mvmm.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        performReplaceFragment(containerId, fragmentTransaction, fragment, addToBackStack)
    }

    private fun performReplaceFragment(containerId: Int, ft: FragmentTransaction, fragment: Fragment, addToBackStack: Boolean) {
        ft.replace(containerId, fragment, fragment.javaClass.canonicalName)
        if (addToBackStack) {
            ft.addToBackStack(fragment.javaClass.canonicalName)
        }
        ft.commit()
    }
