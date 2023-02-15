package controllers

import exceptions.PersonaNotFoundException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Persona
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.PersonaRepository
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
class PersonaControllerTest {
    @MockK
    private lateinit var repo: PersonaRepository

    @InjectMockKs
    private lateinit var controller: PersonaController

    private val personas = listOf(
        Persona("54243004B", "Emma", 21, "Estudiante"),
        Persona("49052682F", "Sergio", 22, "Estudiante"),
        Persona("69696969P", "José Luis", 253, "Suspendedor de alumnos")
    )

    @Test
    fun count() {
        every { repo.count() } returns personas.size

        assertEquals(3, controller.count())

        verify { repo.count() }
    }

    @Test
    fun delete() {
        every { repo.delete(any()) } returns personas[0]

        assertEquals(personas[0], controller.delete("54243004B"))

        verify { repo.delete(any()) }
    }

    @Test
    fun deletePersonaNotFound() {
        every { repo.delete(any()) } returns null

        val res = assertThrows<PersonaNotFoundException> {
            controller.delete("12345678A")
        }

        assertEquals("La persona con DNI 12345678A no existe", res.message)

        verify { repo.delete(any()) }
    }

    @Test
    fun findAll() {
        every { repo.findAll() } returns personas

        val res = controller.findAll()
        assertAll(
            { assertNotNull(res) },
            { assertEquals(personas, res) }
        )

        verify { repo.findAll() }
    }

    @Test
    fun findByDni() {
        every { repo.findByDni(any()) } returns personas[0]


    }

    @Test
    fun save() {
    }

    @Test
    fun saveAll() {
    }
}