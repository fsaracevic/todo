import java.util.Scanner

class ToDoApp(private val db: DatabaseConnection, private val auth: AuthenticationService) {

    private val scanner = Scanner(System.`in`)

    fun start() {
        if(auth.getAuthenticatedUserId() > 0){
            println("Welcome")
            while(true) {
                println("Current tasks: ")
                val currentTasks = db.getUserTasksList(auth.getAuthenticatedUserId())
                for (task in currentTasks) {
                    println("${task.id}: ${task.name}")
                }

                println("What do you want to do next?")
                println("1. Create task")
                println("2. Delete task")
                println("3. Logout")
                val userSelection = scanner.nextInt()
                scanner.nextLine()

                when (userSelection){
                    1 -> {
                        println("Enter task:")
                        val newTask = scanner.nextLine()
                        if(db.addTask(newTask, auth.getAuthenticatedUserId())){
                            println("Task $newTask added successfully!")
                        }else{
                            println("Unable to add task!")
                        }
                    }
                    2 -> {
                        println("Enter task to delete:")
                        val taskId = scanner.nextInt()
                        scanner.nextLine()
                        val success = db.deleteTask(taskId)

                        if(success){
                            println("Task deleted successfully!")
                        }else {
                            println("Unable to delete requested task!")
                        }

                    }
                    3 -> {
                        auth.logout()
                        println("Bye!")
                        return
                    }
                    else -> {
                        println("Unknown command!")
                    }
                }
            }
        }else{
            println("Not logged in!")
            return
        }
    }

    fun login(): Boolean {
        println("Enter name:")
        val userName = scanner.next()

        println("Enter password:")
        val password = scanner.next()

        return auth.authenticate(userName, password)
    }

    fun register(): Boolean {
        println("Enter username:")
        val username = scanner.nextLine()

        println("Enter password:")
        val password = scanner.nextLine()

        println("Enter password again:")
        val passwordCheck = scanner.nextLine()

        if(!Util.checkPassword(password, passwordCheck)) {
            throw Exception("Passwords not same")
//            println("Passwords not same")
//            return false
        }

        return if(db.addNewUser(username, password)) {
            println("New user created!")
            auth.authenticate(username, password)
            true
        }else {
            throw Exception("Couldn't add new user")
        }
    }

}