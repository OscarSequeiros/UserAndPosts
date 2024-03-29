package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.RemotePost
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.RemoteUser
import co.com.ceiba.mobile.pruebadeingreso.domain.model.Post
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class UserRemoteSource @Inject constructor() {

    private val client = HttpClient(Android) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = HOST_URL
                encodedPath = url.encodedPath
            }
        }
        engine {
            connectTimeout = TIME_OUT_MILLIS

        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun getAll(): List<RemoteUser> {
        return client.get("users")
    }

    suspend fun getPost(userId: Long): List<RemotePost> {
        return client.get("posts?userId=$userId")
    }

    companion object {
        const val HOST_URL = "jsonplaceholder.typicode.com"
        const val TIME_OUT_MILLIS = 10_000
    }
}