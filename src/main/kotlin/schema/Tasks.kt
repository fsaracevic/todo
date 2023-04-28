package schema

import model.Task
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

val Database.tasks get() = this.sequenceOf(Tasks)

object Tasks: Table<Task>("tasks") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val userId = int("user_id").references(Users) { it.user }
}