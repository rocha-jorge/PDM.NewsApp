package a26052.pdmnewsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val article_id: String, // ✅ Ensure this is properly mapped
    val title: String,
    val description: String,
    val image_url: String?,
    val link: String
)
