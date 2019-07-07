package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = when(parts?.getOrNull(0)?.trim(' ')) {
            "" -> null
            else -> parts?.getOrNull(0)
        }

        val lastName = when(parts?.getOrNull(1)?.trim(' ')) {
            "" -> null
            else -> parts?.getOrNull(1)
        }

        return firstName to lastName //Pair(firstName, lastName)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initials: String? =
            when(firstName?.trim(' ')) { "", null -> "" else -> firstName.getOrNull(0)?.toString() } +
            when(lastName?.trim(' ')) { "", null -> "" else -> lastName.getOrNull(0)?.toString() }

        return if(initials == "") null else initials?.toUpperCase()
    }

    fun transliteration(payload:String, divider:String = " "): String {
        var text = ""

        for(char in payload) {
            text += when(char) {
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ', 'Ъ' -> ""
                'ы' -> "i"
                'ь', 'Ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"

                'А' -> "A"
                'Б' -> "B"
                'В' -> "V"
                'Г' -> "G"
                'Д' -> "D"
                'Е' -> "E"
                'Ё' -> "E"
                'Ж' -> "Zh"
                'З' -> "Z"
                'И' -> "I"
                'Й' -> "I"
                'К' -> "K"
                'Л' -> "L"
                'М' -> "M"
                'Н' -> "N"
                'О' -> "O"
                'П' -> "P"
                'Р' -> "R"
                'С' -> "S"
                'Т' -> "T"
                'У' -> "U"
                'Ф' -> "F"
                'Х' -> "H"
                'Ц' -> "C"
                'Ч' -> "Ch"
                'Ш' -> "Sh"
                'Щ' -> "Sh'"
                'Ы' -> "I"
                'Э' -> "E"
                'Ю' -> "Yu"
                'Я' -> "Ya"

                ' ' -> divider

                else -> char
            }
        }

        return text
    }
}