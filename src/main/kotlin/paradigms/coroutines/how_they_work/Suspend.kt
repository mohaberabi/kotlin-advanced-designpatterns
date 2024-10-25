package org.example.paradigms.coroutines.how_they_work


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis


class Suspend {


    companion object {
        private val scope = CoroutineScope(Dispatchers.Default)
        suspend fun profile(id: String): UserProfile {
            val bio = fetchBioHttp(id)
            val friends = fetchFriendsFromDb(id)
            val pics = fetchPicsFromDb(id)
            return UserProfile(
                bio = bio,
                pics = pics,
                friends = friends,
                id = id,
            )
        }

        private suspend fun fetchFriendsFromDb(
            id: String,
        ): List<String> {
            delay(100)
            return emptyList<String>()
        }

        private suspend fun fetchPicsFromDb(
            id: String,
        ): List<String> {
            delay(100)
            return emptyList<String>()
        }


        private suspend fun fetchBioHttp(
            id: String,
        ): String {
            delay(1000)
            return "nio"
        }

    }


}

private suspend fun main() {
    val time = measureTimeMillis {
        Suspend.profile("id")
    }
    println(time)

}