package a26052.pdmnewsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String
)
