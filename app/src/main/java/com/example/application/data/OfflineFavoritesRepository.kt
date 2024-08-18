package com.example.application.data

import kotlinx.coroutines.flow.Flow

class OfflineFavoritesRepository(private val favoriteDao: FavoriteDao) : FavoritesRepository {
    override fun getAllFavoritesStream(): Flow<List<Favorite>> = favoriteDao.getAllFavorites()
    override fun getFavoriteStream(id: Int): Flow<Favorite?> = favoriteDao.getFavorite(id)
    override fun getFavoriteStreamByName(name: String): Flow<Favorite?> = favoriteDao.getFavoriteByName(name)
    override suspend fun insertFavorite(item: Favorite) = favoriteDao.insert(item)
    override suspend fun deleteFavorite(item: Favorite) = favoriteDao.delete(item)
    override suspend fun updateFavorite(item: Favorite) = favoriteDao.update(item)
}