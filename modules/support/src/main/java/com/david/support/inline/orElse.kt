package com.david.support.inline

//?.let{}.orElse{}
inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}