package com.favourites.impl.di

import com.favourites.api.domain.FavoritesRepository
import com.favourites.api.domain.LikeInteractor
import com.favourites.impl.data.LikeApi
import com.favourites.impl.data.interactors.LikeInteractorImpl
import com.favourites.impl.data.repositories.FavouritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface FavouritesAppBindsModule {

    @Binds
    fun likeInteractor(impl: LikeInteractorImpl): LikeInteractor

    @Binds
    fun favoritesRepository(impl: FavouritesRepositoryImpl): FavoritesRepository

    companion object {
        @Provides
        fun provideLikeApi(retrofit: Retrofit): LikeApi {
            return retrofit.create(LikeApi::class.java)
        }
    }
}