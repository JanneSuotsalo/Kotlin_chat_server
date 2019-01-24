//Janne Suotsalo 1606186
//Handles all the user input
import sun.security.util.PolicyUtil.getInputStream
import java.io.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CommandInterpreter(input: InputStream,  output: OutputStream) : ChatHistoryObserver, Runnable{
    val input = Scanner(input)
    val out = PrintStream(output, true)

    override fun newMessage(message : ChatMessage){
        out.println(message)
    }

    override fun run(){
        var messageAmount: Int = 0
        ChatHistory.registerObserver(this)
        var chatting: Boolean = true
        var hasUserName: Boolean = false
        var name: String = ""
       // var time = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        var current = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyy-HH:mm:ss")
        var time = current.format(formatter)
        var consoleMsg = "Console"
        out.println("$consoleMsg $time Welcome to the chat!")

        while(chatting) {
            var messages = input.nextLine()
            if(messages != null) {
                //Check's if the input starts with : , if it does, it's a command
                if (messages[0] == ':') {
                    //Splits the input inorder to separate the :user command from the username
                    val arguments = messages.split(' ')

                    //Setting the username
                    if(arguments[0] == ":user" && !hasUserName){
                        //Checks that the username is not empty
                        if(messages == ":user" || arguments[1].isEmpty() || arguments[1]== "Console" || arguments[1]== "console"){
                            out.println("$consoleMsg $time Please set a valid username.")
                        } else {
                            name = arguments[1]
                            // Checks if the username has already been taken and if not, it's added to the username list
                            if(User.addUser(name)) {
                                User.addUser(name)
                                out.println("$consoleMsg $time Welcome $name")
                                hasUserName = true
                            } else {
                                out.println("$consoleMsg $time Username has already been taken, choose another username.")
                            }
                        }
                        //If the username has already been set, you can not change it
                        } else if(arguments[0] == ":user" && hasUserName) {
                        out.println("$consoleMsg $time You have already set an username.")
                    }

                    //Makes sure that the command that is called is not :user
                    if(arguments[0] != ":user") {
                        when (messages) {

                            //Exists the program, removes the user from the user list and deregisters the observer
                            ":exit" -> {
                                User.removeUser(name)
                                ChatHistory.deregisterObserver(this)
                                chatting = false
                            }
                            //Prints out a list of commands
                            ":help" -> out.println("$consoleMsg $time Commands are: :user, :online, :help, :history, :exit")
                            //Prints out the list of people who are in the chat
                            ":online" -> if (hasUserName) {
                                out.println("$consoleMsg $time Online users: | " + User.toString())
                            } else {
                                out.println("$consoleMsg $time You have to set an username first inorder to use this command.")
                            }
                            //Prints out the history
                            ":history" -> if (hasUserName) {
                                out.println("$consoleMsg $time History: | " + ChatHistory.toString())
                            } else {
                                out.println("$consoleMsg $time You have to set an username first, inorder to use this command.")
                            }
                            //Prints out that the command was invalid if the command is spelled incorrectly
                            else -> out.println("$consoleMsg $time invalid command, type :help to see the list of commands.")
                        }
                    }
                } else {
                    // Checks if the username has been set and if not, you are unable chat
                    if(hasUserName) {
                        messageAmount++
                        User.update(name, messageAmount)
                        ChatHistory.insert(ChatMessage(messages, time, name))
                    } else {
                        out.println("$consoleMsg $time You have to set an username first with the :user command.")
                    }
                }
            } else {
                //If message is null, removes the user from the user list, deregisters the observer and exits the program
                out.println("$consoleMsg $time Message was null.")
                User.removeUser(name)
                ChatHistory.deregisterObserver(this)
                chatting = false
            }
        }
        input.close()
    }
}

