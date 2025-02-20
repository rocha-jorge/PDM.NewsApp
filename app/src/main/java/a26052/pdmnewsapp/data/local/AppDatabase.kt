package a26052.pdmnewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
