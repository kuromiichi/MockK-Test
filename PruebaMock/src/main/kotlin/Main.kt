import controllers.PersonaController
import repositories.PersonaRepositoryImpl

fun main() {
    val controller = PersonaController(PersonaRepositoryImpl())
}