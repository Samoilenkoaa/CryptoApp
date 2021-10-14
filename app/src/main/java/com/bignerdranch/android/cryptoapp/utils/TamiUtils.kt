package com.bignerdranch.android.cryptoapp.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

// метод который преобразует секунды с 1970 года в нужной нам формат времени
fun convertTimesTampToTime(timestamp: Long?): String {

    if(timestamp == null) return ""

    // время
    val stamp = Timestamp(timestamp * 1000)

    // получение кокретной даты
    val date = Date(stamp.time)

    // формат времени в 24 часовом формате
    val pattern = "HH:mm:ss" //(HH это часы, если с большой буквы то 24 часовой формат, если с маленькой то 12)

    //получаем объект SimpleDateFormat что бы лг вернул текущее местоположение пользователю
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())

    // что бы врем было в нашей временной зоне
    sdf.timeZone = TimeZone.getDefault()

    return sdf.format(date)

}