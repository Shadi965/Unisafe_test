package ru.unisafe.room.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import ru.unisafe.room.shopping_lists.ShoppingListDbEntity

@Entity(
    tableName = "products",
    primaryKeys = ["shopping_list_id", "id"],
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["shopping_list_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductDbEntity(
    @ColumnInfo(name = "shopping_list_id")
    val shoppingListId: Int,
    val id: Int,
    val name: String,
    val count: Int,
    @ColumnInfo(name = "is_crossed")
    val isCrossed: Boolean
)
