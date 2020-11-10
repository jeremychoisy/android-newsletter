package com.mbds.newsletter.helpers

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

fun formatDate(fullDate: String): String {
    val dateWithoutTime = fullDate.substring(0,10)
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    return LocalDate.parse(dateWithoutTime, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)).format(formatter)
}