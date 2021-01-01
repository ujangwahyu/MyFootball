package com.wahyu.myfootball.utils

import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.util.*

fun ViewGroup.sheetBehavior(): BottomSheetBehavior<*> {
    return BottomSheetBehavior.from(this)
}

fun getLastDate() : String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
    return Converter.dateFormat(calendar.time, "yyyy-MM-dd")
}