package co.com.ceiba.mobile.pruebadeingreso.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        foreignKeys = [ForeignKey(
                entity = RoomUser::class,
                parentColumns = ["id"],
                childColumns = ["user_id"],
                onDelete = ForeignKey.CASCADE
        )]
)
data class RoomPost(
        @PrimaryKey val id: Long,
        @ColumnInfo(name = "user_id", index = true) val userId: Long,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "body") val body: String,
)