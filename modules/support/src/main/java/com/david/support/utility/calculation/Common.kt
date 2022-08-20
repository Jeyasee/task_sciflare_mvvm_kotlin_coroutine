package com.david.support.utility.calculation

import java.util.*

fun getRandomNumber(maxNumber: Int, isFromZero: Boolean): Int {
    return if (isFromZero) {
        Random().nextInt(maxNumber)
    } else {
        Random().nextInt(maxNumber) + 1
    }
}