package com.example.mvmm.extensions

import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.onItemSelected(function: (Int) -> Unit) {
    setOnNavigationItemSelectedListener { item ->
        for (i in 0 until menu.size()) {
            if (menu.getItem(i).itemId == item.itemId) {
                function(i)
            }
        }
        return@setOnNavigationItemSelectedListener true
    }
}

fun BottomNavigationView.onItemReselected(function: (Int) -> Unit) {
    setOnNavigationItemReselectedListener { item ->
        for (i in 0 until menu.size()) {
            if (menu.getItem(i).itemId == item.itemId) {
                function(i)
            }
        }
    }
}