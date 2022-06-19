package com.moneybox.minimb.feature.products.di

import com.moneybox.minimb.feature.products.api.ProductDataService
import com.moneybox.minimb.feature.products.data.ProductDataSource
import com.moneybox.minimb.feature.products.data.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class ProductRepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideProductDataSource(
        service: ProductDataService
    ): ProductDataSource {
        return ProductDataSource(service)
    }

    @Provides
    @ViewModelScoped
    fun provideProductDataRepository(
        dataSource: ProductDataSource
    ): ProductRepository {
        return ProductRepository(dataSource)
    }
}