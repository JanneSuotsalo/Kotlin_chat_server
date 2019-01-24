//Janne Suotsalo 1606186
//Returns the string, which contains name, time and the message
class ChatMessage(var message: String?, var timestamp: String, var name: String){
    override fun toString(): String {
        return "$name $timestamp $message"
    }
}