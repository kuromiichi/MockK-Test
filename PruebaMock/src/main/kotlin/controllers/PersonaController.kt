package controllers

import exceptions.DniNoValidoException
import exceptions.PersonaNotFoundException
import models.Persona
import repositories.PersonaRepository

class PersonaController(private val repo: PersonaRepository) {

    fun count(): Int {
        return repo.count()
    }

    fun delete(dni: String): Persona {
        val dniComprobado = comprobarDni(dni)
        val res = repo.delete(dniComprobado)
        if (res != null) return res
        throw PersonaNotFoundException("La persona con DNI $dniComprobado no existe")
    }

    fun findAll(): List<Persona> {
        return repo.findAll()
    }

    fun findByDni(dni: String): Persona {
        val dniComprobado = comprobarDni(dni)
        val res = repo.findByDni(dniComprobado)
        if (res != null) return res
        throw PersonaNotFoundException("La persona con DNI $dniComprobado no existe")
    }

    fun save(persona: Persona): Persona {
        val dniComprobado = comprobarDni(persona.dni)
        persona.dni = dniComprobado
        return repo.save(persona)
    }
    fun saveAll(personas: List<Persona>): List<Persona>{
        val dnisComprobados = mutableListOf<String>()
        personas.forEach { dnisComprobados.add(comprobarDni(it.dni)) }
        for (i in personas.indices) personas[i].dni = dnisComprobados[i]
        return repo.saveAll(personas)
    }

    private fun comprobarDni(dni: String): String {
        if (!dni.uppercase().matches("""\d{8}[A-Z]""".toRegex())) throw DniNoValidoException("El DNI $dni no es válido")
        return dni.uppercase()
    }
}