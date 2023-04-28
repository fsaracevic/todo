package schema

import model.User
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

val Database.users get() = this.sequenceOf(Users)

object Users: Table<User>("users") {
    val id = int("id").primaryKey().bindTo { it.id }
    var name = varchar("name").bindTo { it.name }
    var password = varchar("password").bindTo { it.password }
}