package com.example.application.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)
    @Update
    suspend fun update(favorite: Favorite)
    @Delete
    suspend fun delete(favorite: Favorite)
    @Query("SELECT * from favorites WHERE id = :id")
    fun getFavorite(id: Int): Flow<Favorite>
    @Query("SELECT * from favorites WHERE name = :name")
    fun getFavoriteByName(name: String): Flow<Favorite>
    @Query("SELECT * from favorites ORDER BY name ASC")
    fun getAllFavorites(): Flow<List<Favorite>>
}
