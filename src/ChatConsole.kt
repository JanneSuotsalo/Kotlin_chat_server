//Janne Suotsalo 1606186
//Prints the message in the console
class ChatConsole: ChatHistoryObserver{
    override fun newMessage(message: ChatMessage){
        println(message)
    }
}