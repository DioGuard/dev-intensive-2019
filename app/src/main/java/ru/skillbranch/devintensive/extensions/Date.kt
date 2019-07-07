package ru.skillbranch.devintensive.extensions

import java.util.*
import java.text.SimpleDateFormat

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd:MM:yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, unit: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(unit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String {

    return when(val diff = date.time - this.time) {
        in -360 * DAY   .. -26 * HOUR -> "через ${-diff / DAY} ${
            if(-diff / DAY / 10 == 1L || -diff / DAY % 10 !in 1..4) "дней" else {
                if(-diff / DAY % 10 == 1L) "день" else "дня"
            }}"
        in -26 * HOUR   .. -22 * HOUR -> "через день"
        in -75 * SECOND .. -45 * SECOND -> "через минуту"
        in -22 * HOUR   .. -75 * MINUTE -> "через ${-diff / HOUR} ${
            if(-diff / HOUR / 10 == 1L || -diff / HOUR % 10 !in 1..4) "часов" else {
                if(-diff / HOUR % 10 == 1L) "час" else "часа"
            }}"
        in -75 * MINUTE .. -45 * MINUTE -> "через час"
        in -45 * MINUTE .. -75 * SECOND -> "через ${-diff / MINUTE} ${
            if(-diff / MINUTE / 10 == 1L || -diff / MINUTE % 10 !in 1..4) "минут" else {
                if(-diff / MINUTE % 10 == 1L) "минуту" else "минуты"
            }}"
        in -45 * SECOND ..  -1 * SECOND -> "через несколько секунд"

        in  0 * SECOND ..   1 * SECOND -> "только что"
        in  1 * SECOND ..  45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND ..  75 * SECOND -> "минуту назад"
        in 75 * SECOND ..  45 * MINUTE -> "${diff / MINUTE} ${
            if(diff / MINUTE / 10 == 1L || diff / MINUTE % 10 !in 1..4) "минуты" else {
                if(diff / MINUTE % 10 == 1L) "минуту" else "минут"
            }} назад"
        in 45 * MINUTE ..  75 * MINUTE -> "час назад"
        in 75 * MINUTE ..  22 * HOUR   -> "${diff / HOUR} ${
            if(diff / HOUR / 10 == 1L || diff / HOUR % 10 !in 1..4) "часов" else {
                if(diff / HOUR % 10 == 1L) "час" else "часа"
            }} назад"
        in 22 * HOUR   ..  26 * HOUR   -> "день назад"
        in 26 * HOUR   .. 360 * DAY    -> "${diff / DAY} ${
            if(diff / DAY / 10 == 1L || diff / DAY % 10 !in 1..4) "дней" else {
                if(diff / DAY % 10 == 1L) "день" else "дня"
            }} назад"

        else -> if(diff > 0) "более года назад" else "более чем через год"
    }

}