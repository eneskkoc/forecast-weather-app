package com.example.mvmm.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun MutableLiveData<Unit>.execute() {
    this.value = Unit
}

/**
 * Short for of observer(owner, Observer { func })
 */
fun <T: Any?> LiveData<T>.obs(owner: LifecycleOwner, func: (t: T) -> Unit) {
    observe(owner, Observer { func(it) })
}