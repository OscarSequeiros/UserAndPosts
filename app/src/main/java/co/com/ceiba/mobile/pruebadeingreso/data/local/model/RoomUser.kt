package co.com.ceiba.mobile.pruebadeingreso.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser(
        @PrimaryKey val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "email") val email: String,
        @ColumnInfo(name = "phone_number") val phoneNumber: String,
)