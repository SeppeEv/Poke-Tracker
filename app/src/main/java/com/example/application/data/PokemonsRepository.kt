import com.example.application.data.FavoritePokemon
import com.example.application.data.FavoritePokemonDao
import com.example.application.model.PokemonResponse

class PokemonsRepository(private val favoritePokemonDao: FavoritePokemonDao) {

    suspend fun addFavorite(pokemon: PokemonResponse) {
        val favoritePokemon = FavoritePokemon(
            id = pokemon.id,
            name = pokemon.name ?: "",
        )
        favoritePokemonDao.addFavorite(favoritePokemon)
    }

    suspend fun removeFavorite(pokemon: PokemonResponse) {
        val favoritePokemon = FavoritePokemon(
            id = pokemon.id,
            name = pokemon.name ?: "",
        )
        favoritePokemonDao.removeFavorite(favoritePokemon)
    }

    suspend fun getFavorites(): List<FavoritePokemon> {
        return favoritePokemonDao.getFavorites()
    }

    suspend fun isFavorite(name: String): Boolean {
        return favoritePokemonDao.isFavorite(name)
    }
}
