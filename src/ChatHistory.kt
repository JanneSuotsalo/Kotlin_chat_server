//Janne Suotsalo 1606186
//Creates chat history, handles registering and deregistering the obsertvers and notifying the observers of the new messages
import java.util.*

object ChatHistory : ChatHistoryObservable {
    val observers = mutableSetOf<ChatHistoryObserver>()
    var chatHistory= mutableListOf<ChatMessage>()
    var chatConsole = ChatConsole()
    var topChatter = TopChatter()

    //Adds new message to the history and calls the notifyObserver() method to notify observers of the new message
    fun insert(message: ChatMessage) {
        notifyObserver(message)
        chatHistory.add(message)
    }

    //Creates a string of the history
    override fun toString() :String{
        var history = ""

        for(i in chatHistory){
            history += StringBuilder().append("$i | ")
        }
        return history
    }

    //Registers the observers
    override fun registerObserver(observer:ChatHistoryObserver){
        observers.add(observer)
        observers.add(chatConsole)
        observers.add(topChatter)
    }

    //Deregisters the observer
    override fun deregisterObserver(observer:ChatHistoryObserver){
        observers.remove(observer)
    }

    //Notifies the observers of new messages
    override fun notifyObserver(message: ChatMessage){
        for(i in observers){
            i.newMessage(message)
        }
    }
}