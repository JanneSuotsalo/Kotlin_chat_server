//Janne Suotsalo 1606186
//Maintains the server and accepts new connections
import java.net.InetAddress
import java.net.ServerSocket

class ChatServer() {
    fun serve(){
        try {
            while (true) {
                val serverSocket = ServerSocket(30001,7, InetAddress.getByName("127.0.0.1"))
                println("We have port " + serverSocket.localPort)

                while (true) {
                    val s = serverSocket.accept()
                    println("new connection " + s.inetAddress.hostAddress + " " + s.port)
                    val t = Thread(CommandInterpreter(s.getInputStream(), s.getOutputStream()))
                    t.start()
                }
            }
        }catch (e: Exception){
            println("Got exception: ${e.message}")
        }finally {
            println("All serving done")
        }
    }
}
