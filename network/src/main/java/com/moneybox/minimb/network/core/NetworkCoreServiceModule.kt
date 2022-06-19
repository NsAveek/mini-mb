package com.moneybox.minimb.network.core


import com.google.gson.GsonBuilder
import com.moneybox.minimb.network.BuildConfig
import com.moneybox.minimb.network.ILogger
import com.moneybox.minimb.network.LoggerNetworkModule
import com.moneybox.minimb.network.NetworkConstants
import com.moneybox.minimb.network.core.INetworkCoreDataService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkCoreServiceModule {

    @Provides
    @Singleton
    fun providesLogger(): ILogger = LoggerNetworkModule()

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit.Builder {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(NoAuthenticationInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .client(clientBuilder)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    }
}