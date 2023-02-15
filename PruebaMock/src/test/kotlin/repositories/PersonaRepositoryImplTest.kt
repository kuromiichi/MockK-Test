package repositories

import models.Persona
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonaRepositoryImplTest {

    private lateinit var repoVacio: PersonaRepositoryImpl
    private lateinit var repoTest: PersonaRepositoryImpl
    private val personas = listOf(
        Persona("54243004B", "Emma", 21, "Estudiante"),
        Persona("49052682F", "Sergio", 22, "Estudiante"),
        Persona("69696969P", "José Luis", 253, "Suspendedor de alumnos")
    )

    @BeforeEach
    fun setUp() {
        repoVacio = PersonaRepositoryImpl()
        repoTest = PersonaRepositoryImpl()
        repoTest.saveAll(personas)
    }

    @Test
    fun count() {
        assertAll(
            { assertEquals(3, repoTest.count()) },
            { assertEquals(0, repoVacio.count()) }
        )
    }

    @Test
    fun delete() {
        assertAll(
            { assertEquals(null, repoVacio.delete("54243004B")) },
            { assertEquals(listOf<Persona>(), repoVacio.findAll()) },
            { assertEquals(personas[0], repoTest.delete("54243004B")) },
            { assertEquals(listOf(personas[1], personas[2]), repoTest.findAll()) },
            { assertEquals(null, repoTest.delete("12345678A")) },
            { assertEquals(listOf(personas[1], personas[2]), repoTest.findAll()) },
        )
    }

    @Test
    fun findAll() {
        assertAll (
            { assertEquals(listOf<Persona>(), repoVacio.findAll())},
            { assertEquals(personas, repoTest.findAll()) }
        )
    }

    @Test
    fun findByDni() {
        assertAll(
            { assertEquals(null, repoVacio.findByDni("54243004B")) },
            { assertEquals(personas[0], repoTest.findByDni("54243004B")) },
            { assertEquals(null, repoTest.findByDni("12345678A")) }
        )
    }


    @Test
    fun save() {
        assertAll(
            { assertEquals(personas[0], repoVacio.save(personas[0])) },
            { assertEquals(listOf(personas[0]), repoVacio.findAll()) },
            { assertEquals(personas[0], repoTest.save(personas[0])) },
            { assertEquals(listOf(personas[1], personas[2], personas[0]), repoTest.findAll()) }
        )
    }

    @Test
    fun saveAll() {
        val nuevaPersona = Persona("12345678A", "test", 1, "test")
        assertAll(
            { assertEquals(listOf<Persona>(), repoVacio.saveAll(listOf<Persona>())) },
            { assertEquals(listOf(personas[0], personas[1]), repoVacio.saveAll(listOf(personas[0], personas[1]))) },
            { assertEquals(listOf(personas[0], personas[1]), repoVacio.findAll()) },
            { assertEquals(listOf(personas[0], nuevaPersona), repoTest.saveAll(listOf(personas[0], nuevaPersona))) },
            { assertEquals(listOf(personas[1], personas[2], personas[0], nuevaPersona), repoTest.findAll())}
        )
    }
}