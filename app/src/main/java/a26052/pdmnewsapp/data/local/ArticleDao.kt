package a26052.pdmnewsapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticlesFlow(): Flow<List<ArticleEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity): Long // Return type should be Long (ID of inserted row)

    @Delete
    suspend fun deleteArticle(article: ArticleEntity): Int // Return type should be Int (number of rows deleted)


}
