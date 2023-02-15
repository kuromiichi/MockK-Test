package repositories

import models.Persona

interface PersonaRepository {
    fun count(): Int
    fun delete(dni: String): Persona?
    fun findAll(): List<Persona>
    fun findByDni(dni: String): Persona?
    fun save(persona: Persona): Persona
    fun saveAll(personas: List<Persona>): List<Persona>
}