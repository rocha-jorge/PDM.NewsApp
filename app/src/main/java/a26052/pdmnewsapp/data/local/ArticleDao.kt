package a26052.pdmnewsapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticlesFlow(): Flow<List<ArticleEntity>> // ✅ Fix name

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>) // ✅ Ensure this exists

    @Delete
    suspend fun deleteArticle(article: ArticleEntity): Int // ✅ Ensure correct type
}
