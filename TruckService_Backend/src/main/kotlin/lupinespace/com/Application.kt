package lupinespace.com

import com.mongodb.MongoClient
import com.mongodb.MongoException
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import lupinespace.com.plugins.*

fun main() {
    var mongoClient: MongoClient? = null
    try {
        mongoClient = MongoClient(String.format("mongodb+srv://lupines-space:%s@socialites.fezg0.mongodb.net/?retryWrites=true&w=majority", System.getenv("MONGO_PW")))
        println("Application connected to MongoDB")
    } catch (e: MongoException) {
        e.printStackTrace()
    } finally {
        mongoClient!!.close()
    }
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    //configureSecurity() TODO: Add authentication
    configureRouting()
}
