//Janne Suotsalo 1606186
//Creates a map of the users and has methods for adding and deleting users
object User {
    var userList = mutableMapOf<String,Int>()
    var users = userList.keys

    //Updates the user list to get the correct amount of messages sent by a user
    fun update(username: String, amount : Int){
        userList.replace(username, amount)
    }

    //Checks if the username already exists in the userList, and returns true and adds it to the userList if it doesn't
    fun addUser(username: String): Boolean {
        if (!userList.contains(username)) {
            userList.put(username, 0)
            return true
        }
        return false
    }

    // Creates a string from the userList
    override fun toString(): String {
        var listOfUsers = ""

        for (i in users) {
            listOfUsers += StringBuilder().append("$i | ")
        }
        return listOfUsers
    }

    //Removes username from the userList
    fun removeUser(username: String) {
        if (userList.contains(username)) {
            userList.remove(username)
        } else {
            println("Username does not exist")
        }
    }
}