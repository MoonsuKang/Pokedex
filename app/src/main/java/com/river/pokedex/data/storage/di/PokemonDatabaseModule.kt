package com.river.pokedex.data.storage.di

import android.content.Context
import androidx.room.Room
import com.river.pokedex.data.storage.database.PokemonDao
import com.river.pokedex.data.storage.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonDatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(
        @ApplicationContext context: Context,
    ): PokemonDatabase = Room.databaseBuilder(
        context,
        PokemonDatabase::class.java,
        "favorite_pokemon.db",
    ).build()

    @Provides
    @Singleton
    fun provideFavoritePokemonDao(
        db: PokemonDatabase,
    ): PokemonDao = db.favoritePokemonDao()
}
