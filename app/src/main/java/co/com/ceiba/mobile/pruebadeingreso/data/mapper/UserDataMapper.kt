package co.com.ceiba.mobile.pruebadeingreso.data.mapper

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.RemoteUser
import co.com.ceiba.mobile.pruebadeingreso.domain.User

class UserDataMapper {

    fun toRoom(user: RemoteUser): RoomUser {
        return RoomUser(
                id = user.id,
                name = user.name,
                email = user.email,
                phoneNumber = user.phone
        )
    }

    fun toDomain(user: RoomUser): User {
        return User(
                id = user.id,
                name = user.name,
                email = user.email,
                phoneNumber = user.phoneNumber
        )
    }
}