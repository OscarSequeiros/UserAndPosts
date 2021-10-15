package co.com.ceiba.mobile.pruebadeingreso.data.mapper

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.RemoteUser
import co.com.ceiba.mobile.pruebadeingreso.domain.User

class UserDataMapper {

    fun toRoom(users: List<RemoteUser>): List<RoomUser> {
        return users.map { user -> user.toRoom() }
    }

    private fun RemoteUser.toRoom(): RoomUser {
        return RoomUser(
                id = id,
                name = name,
                email = email,
                phoneNumber = phone
        )
    }

    fun toDomain(users: List<RoomUser>): List<User> {
        return users.map { user -> user.toDomain() }
    }

    private fun RoomUser.toDomain(): User {
        return User(
                id = id,
                name = name,
                email = email,
                phoneNumber = phoneNumber
        )
    }
}