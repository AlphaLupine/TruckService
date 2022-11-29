package lupinespace.com.data

import lupinespace.com.data.models.PartialUser
import lupinespace.com.data.models.UserAccount
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.*

private val client = KMongo.createClient(String.format("mongodb+srv://lupines-space:%s@socialites.fezg0.mongodb.net/?retryWrites=true&w=majority", System.getenv("MONGO_PW"))).coroutine
private val database = client.getDatabase("TruckService")

private val userAccounts = database.getCollection<UserAccount>("Users")

suspend fun getPartialUserById(id: Int): PartialUser? {
    val userAccount = userAccounts.findOneById(id) ?: return null
    return PartialUser(
        username = userAccount.username,
        role = userAccount.role
    )
}

/*suspend fun getPartialUsers(): PartialUser? {
    val userAccountCollection = userAccounts

    val partialUsers = listOf<PartialUser>()
    userAccountCollection.


    return PartialUser(
        username = userAccount.username,
        role = userAccount.role
    )
}*/