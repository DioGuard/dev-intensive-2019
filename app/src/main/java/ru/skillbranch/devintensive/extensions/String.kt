package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String{
    val str = this.dropLast(this.length - length).trimEnd()
    return if(str == this.trimEnd()) str else "$str..."

}

fun String.stripHtml(): String {
    var isHtml = false
    var str = ""

    for (i in 0 until this.length) {
        if(this[i] in "<&") isHtml = true

        if(!isHtml && this[i] !in "&\'\"") {
            str += this[i]
        }

        if(this[i] in ">;") isHtml = false
    }

    while(str.contains("  "))
        str = str.replace("  ", " ")

    return str
}