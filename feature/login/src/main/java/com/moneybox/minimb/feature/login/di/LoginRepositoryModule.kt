package com.moneybox.minimb.feature.login.di

import com.moneybox.minimb.feature.login.api.LoginDataService
import com.moneybox.minimb.feature.login.data.LoginDataSource
import com.moneybox.minimb.feature.login.data.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class LoginRepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideLoginDataSource(
        service: LoginDataService
    ): LoginDataSource {
        return LoginDataSource(service)
    }

    @Provides
    @ViewModelScoped
    fun provideLoginDataRepository(
        dataSource: LoginDataSource
    ): LoginRepository {
        return LoginRepository(dataSource)
    }
}