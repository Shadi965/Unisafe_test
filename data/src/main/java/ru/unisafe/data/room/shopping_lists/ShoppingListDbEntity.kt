package ru.unisafe.data.room.shopping_lists

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_lists",
    indices = [
        Index("key")
    ]
)
data class ShoppingListDbEntity(
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val key: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String
)