//Janne Suotsalo 1606186
//Defines what methods chat history will implement
interface ChatHistoryObservable {
    fun registerObserver(observer:ChatHistoryObserver)
    fun deregisterObserver(observer:ChatHistoryObserver)
    fun notifyObserver(message: ChatMessage)
}