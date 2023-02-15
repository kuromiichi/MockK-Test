package repositories

import models.Persona

class PersonaRepositoryImpl : PersonaRepository {
    private val repo = mutableListOf<Persona>()

    override fun count(): Int {
        return repo.size
    }

    override fun delete(dni: String): Persona? {
        val persona = findByDni(dni)
        if (persona != null) repo.remove(persona)
        return persona
    }

    override fun findAll(): List<Persona> {
        return repo.toList()
    }

    override fun findByDni(dni: String): Persona? {
        var persona: Persona? = null
        repo.forEach { if (it.dni == dni) persona = it }
        return persona
    }

    override fun save(persona: Persona): Persona {
        delete(persona.dni)
        repo.add(persona)
        return persona
    }

    override fun saveAll(personas: List<Persona>): List<Persona> {
        personas.forEach { save(it) }
        return personas
    }
}