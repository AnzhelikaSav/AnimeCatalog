package com.example.animecatalog

fun Double.toRating(): Float =
    (this / 2).toFloat()
