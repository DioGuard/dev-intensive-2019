package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
) {

    init {
        println("My name is $firstName $lastName.")
    }

    constructor(id: String): this(id, "Unknown", "Unknown №$id", null)

    constructor(id: String, firstName: String?, lastName: String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(builder: Builder) : this(
            builder.id,
            builder.firstName,
            builder.lastName,
            builder.avatar,
            builder.rating,
            builder.respect,
            builder.lastVisit,
            builder.isOnline
        )

    class Builder {
        var id: String = ""
        private set
        var firstName: String? = ""
        private set
        var lastName: String? = ""
        private set
        var avatar: String? = ""
        private set
        var rating: Int  = 0
        private set
        var respect: Int  = 0
        private set
        var lastVisit: Date? = Date()
        private set
        var isOnline: Boolean  = false
        private set

        fun id(id: String) = apply {this.id = id }
        fun firstName(firstName: String?) = apply {this.firstName = firstName }
        fun lastName(lastName: String?) = apply {this.lastName = lastName }
        fun avatar(avatar: String?) = apply {this.avatar = avatar }
        fun rating(rating: Int) = apply {this.rating = rating }
        fun respect(respect: Int) = apply {this.respect = respect }
        fun lastVisit(lastVisit: Date?) = apply {this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply {this.isOnline = isOnline }

        fun build() = User(this)
    }

    companion object Factory {
        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id="$lastId", firstName = firstName, lastName = lastName)
        }
    }

    fun printFullName() = println("$firstName $lastName")

    fun printMe() =  println("""
            id        = $id
            firstName = $firstName
            lastName  = $lastName
            avatar    = $avatar
            rating    = $rating
            respect   = $respect
            lastVisit = ${lastVisit?.format()}
            isOnline  = $isOnline
        """.trimIndent())

}
