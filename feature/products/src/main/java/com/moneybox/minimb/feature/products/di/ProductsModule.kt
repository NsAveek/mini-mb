package com.moneybox.minimb.feature.products.di

import android.content.SharedPreferences
import com.moneybox.minimb.feature.products.api.ProductDataService
import com.moneybox.minimb.network.core.NoAuthenticationInterceptor
import com.moneybox.minimb.network.core.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InstallIn(ActivityRetainedComponent::class)
@Module
object ProductsModule {


    @Provides
    @ActivityRetainedScoped
    fun clientBuilder( sharedPreferences: SharedPreferences) : OkHttpClient{
        return OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor(sharedPreferences))
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }


    @Provides
    @ActivityRetainedScoped
    fun appServiceProvider(retrofit: Retrofit.Builder, clientBuilder : OkHttpClient ) : ProductDataService{
        return retrofit.client(clientBuilder).build().create(ProductDataService::class.java)
    }
}