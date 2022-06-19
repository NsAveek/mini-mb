package com.moneybox.minimb.feature.login.di

import com.moneybox.minimb.feature.login.api.LoginDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@InstallIn(ActivityRetainedComponent::class)
@Module
object LoginModule {

    @Provides
    @ActivityRetainedScoped
    fun appServiceProvider(retrofit: Retrofit) : LoginDataService{
        return retrofit.create(LoginDataService::class.java)
    }
}