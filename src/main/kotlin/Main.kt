import java.util.Scanner

fun main() {
    val db = DatabaseConnection()
    val auth = AuthenticationService(db)

    val scanner = Scanner(System.`in`)

    while(true) {
        println("Welcome! What do you want to do?")
        println("1. Login")
        println("2. Register")
        println("3. Exit")

        val choice = scanner.nextInt()
        scanner.nextLine()
        val app = ToDoApp(db, auth)
        when(choice) {
            1-> {
                if(app.login()){
                    app.start()
                }else{
                    println("Incorrect credentials!")
                }
            }
            2 -> {
                try {
                    if(app.register()){
                        app.start()
                    }else{
                        println("Could not register!")
                        continue
                    }
                }catch (ex: Exception){
                    println(ex.message)
                }

            }
            3-> {
                println("Thanks for using the app! Bye!")
                break
            }
            else -> {
                println("Unknown command!")
            }
        }
    }
}