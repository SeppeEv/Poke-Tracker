package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.application.data.Favorite
import com.example.application.data.FavoritesRepository
class FavoriteEntryViewModel(private val favoritesRepository: FavoritesRepository) : ViewModel() {
    var favoriteUiState by mutableStateOf(FavoriteUiState())
        private set
    fun updateUiState(favoriteDetails: FavoriteDetails) {
        favoriteUiState = FavoriteUiState(favoriteDetails = favoriteDetails, isEntryValid = validateInput(favoriteDetails))
    }
    suspend fun saveFavorite() {
        if (validateInput()) {
            favoritesRepository.insertFavorite(favoriteUiState.favoriteDetails.toFavorite())
        }
    }
    private fun validateInput(uiState: FavoriteDetails = favoriteUiState.favoriteDetails): Boolean {
        return with(uiState) {
            name.isNotBlank()
        }
    }
}
data class FavoriteUiState(
    val favoriteDetails: FavoriteDetails = FavoriteDetails(),
    val isEntryValid: Boolean = false
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