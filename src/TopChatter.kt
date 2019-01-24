//Janne Suotsalo 1606186
//Keeps track of how many messages each user has sent and prints top 4 chatters in the console
class TopChatter : ChatHistoryObserver {
    override fun newMessage(message: ChatMessage){
        var i: Int = 0
        val sorted = User.userList.toList().sortedByDescending { (_, value) -> value }

        //If the the user list contains under 4 users, it will list the all the users instead of just 4
        if(User.userList.size >= 4) {
            while (i < 4) {
                println(sorted[i])
                i++
            }
        } else {
            println(sorted)
        }
    }
}