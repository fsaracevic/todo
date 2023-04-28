class Util {

    companion object {
        fun checkPassword(password: String, passwordCheck: String): Boolean {
            return password == passwordCheck
        }
    }
}