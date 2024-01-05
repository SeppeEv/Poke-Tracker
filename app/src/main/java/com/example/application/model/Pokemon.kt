import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val abilities: List<String>,
    val moves: List<String>,
    val stats: List<String>,
    val sprites: List<String>,
    val species: String,
    val heldItems: List<String>,
    val locationAreaEncounters: String,
    val isDefault: Boolean,
    val order: Int,
    val gameIndices: List<String>,
    val forms: List<String>,
    val pastTypes: List<String>,
    val isFavorite: Boolean,
)