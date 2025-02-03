package a26052.pdmnewsapp.di

import a26052.pdmnewsapp.data.local.AppDatabase
import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.data.repository.ArticleRepositoryImpl
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ✅ Provide the Application Context
    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context {
        return context
    }

    // ✅ Provide Room Database Instance
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "articles_db"
        ).build()
    }

    // ✅ Provide DAO Instance
    @Provides
    fun provideArticleDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }

    // ✅ Provide OkHttpClient for networking
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    // ✅ Provide Retrofit API Instance
    @Provides
    @Singleton
    fun provideArticlesAPI(okHttpClient: OkHttpClient): ArticlesAPI {
        return Retrofit.Builder()
            .baseUrl("https://newsdata.io/api/1/") // Change this if needed
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ArticlesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(
        api: ArticlesAPI,
        articleDao: ArticleDao
    ): ArticlesRepository {
        return ArticleRepositoryImpl(api, articleDao)
    }
}
