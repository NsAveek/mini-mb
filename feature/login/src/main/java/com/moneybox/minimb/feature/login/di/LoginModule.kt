package com.moneybox.minimb.feature.login.di

import com.moneybox.minimb.feature.login.api.LoginDataService
import com.moneybox.minimb.network.core.NoAuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@InstallIn(ActivityRetainedComponent::class)
@Module
object LoginModule {

    val clientBuilder by lazy {
        OkHttpClient.Builder()
            .addInterceptor(NoAuthenticationInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @ActivityRetainedScoped
    fun appServiceProvider(retrofit: Retrofit.Builder) : LoginDataService{
        return retrofit.client(clientBuilder).build().create(LoginDataService::class.java)
    }
}