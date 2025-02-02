import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import a26052.pdmnewsapp.data.local.AppDatabase
import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.data.repository.ArticlesRepositoryImpl // ✅ Corrected package path
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import a26052.pdmnewsapp.domain.usecase.GetArticlesUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticlesAPI(): ArticlesAPI {
        return ArticlesAPI.create() // Ensure this matches how you initialize API service
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesAPI, dao: ArticleDao): ArticlesRepository {
        return ArticlesRepositoryImpl(api, dao) // ✅ Pass API instance
    }

    @Provides
    @Singleton
    fun provideGetArticlesUseCase(repository: ArticlesRepository): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }
}
