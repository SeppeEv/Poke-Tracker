package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.application.data.Favorite
import com.example.application.data.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class FavoriteEntryViewModel(private val favoritesRepository: FavoritesRepository) : ViewModel() {
    var favoriteUiState by mutableStateOf(FavoriteUiState())
        private set

    fun updateUiState(favoriteDetails: FavoriteDetails) {
        favoriteUiState = FavoriteUiState(
            favoriteDetails = favoriteDetails,
            isEntryValid = validateInput(favoriteDetails)
        )
    }

    suspend fun saveFavorite() {
        if (validateInput()) {
            favoritesRepository.insertFavorite(favoriteUiState.favoriteDetails.toFavorite())
        }
    }

    suspend fun removeFavorite() {
        favoritesRepository.deleteFavorite(favoriteUiState.favoriteDetails.toFavorite())
    }

    suspend fun isFavorite(name: String): Boolean {
        val favoriteFlow: Flow<Favorite?> = favoritesRepository.getFavoriteStreamByName(name)

        // Check if the flow emits any non-null value
        val firstFavorite = favoriteFlow.firstOrNull()
        return firstFavorite != null
    }

    suspend fun getFavorite(name: String): Favorite? {
        return favoritesRepository.getFavoriteStreamByName(name).firstOrNull()
    }

    private fun validateInput(uiState: FavoriteDetails = favoriteUiState.favoriteDetails): Boolean {
        return uiState.name.isNotBlank()
    }
}data class FavoriteUiState(
    val favoriteDetails: FavoriteDetails = FavoriteDetails(),
    val isEntryValid: Boolean = true
)
data class FavoriteDetails(
    val id: Int = 0,
    val name: String = "",
)
fun FavoriteDetails.toFavorite(): Favorite = Favorite(id = id, name = name)
fun Favorite.toFavoriteUiState(isEntryValid: Boolean = false): FavoriteUiState = FavoriteUiState(
    favoriteDetails = this.toFavoriteDetails(),
    isEntryValid = isEntryValid
)

fun Favorite.toFavoriteDetails(): FavoriteDetails = FavoriteDetails(
    id = id,
    name = name
)