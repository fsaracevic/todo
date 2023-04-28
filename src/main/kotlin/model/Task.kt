package model

import org.ktorm.entity.Entity

interface Task: Entity<Task> {
    companion object: Entity.Factory<Task>()
    val id: Int
    var name: String
    var user: User
}