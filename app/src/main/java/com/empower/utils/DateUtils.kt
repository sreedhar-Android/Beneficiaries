package com.empower.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {
    fun formatDate(date: String): String {
        val parser = SimpleDateFormat(Constants.DATE_FORMAT_INPUT, Locale.US)
        val formatter = SimpleDateFormat(Constants.DATE_FORMAT_OUTPUT, Locale.US)
        return formatter.format(parser.parse(date)!!)
    }
}