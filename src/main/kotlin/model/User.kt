package model

import org.ktorm.entity.Entity

interface User: Entity<User> {
    companion object: Entity.Factory<User>()
    val id: Int
    var name: String
    var password: String
}