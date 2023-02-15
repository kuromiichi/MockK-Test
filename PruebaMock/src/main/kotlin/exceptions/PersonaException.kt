package exceptions

sealed class PersonaException(message: String) : Exception(message)
class PersonaNotFoundException(message: String) : PersonaException(message)
class DniNoValidoException(message: String): PersonaException(message)
class EdadNoValidaException(message: String): PersonaException(message)