package lupinespace.com.data

import lupinespace.com.data.models.PartialUser
import lupinespace.com.data.models.UserAccount
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.*

private val client = KMongo.createClient(String.format("mongodb+srv://lupines-space:%s@socialites.fezg0.mongodb.net/?retryWrites=true&w=majority", System.getenv("MONGO_PW"))).coroutine
private val database = client.getDatabase("TruckService")

private val userAccounts = database.getCollection<UserAccount>("Users")

suspend fun getPartialUserById(id: String): PartialUser? {
    val userAccount = userAccounts.findOneById(id) ?: return null
    return PartialUser(
        username = userAccount.username,
        role = userAccount.role
    )
}

suspend fun createOrUpdateUserById(user: UserAccount): Boolean {
    val doesExist = userAccounts.findOneById(user.id) != null
    return if (doesExist) {
        userAccounts.updateOneById(user.id, user).wasAcknowledged()
    } else {
        user.id = ObjectId().toString()
        userAccounts.insertOne(user).wasAcknowledged()
    }
}
