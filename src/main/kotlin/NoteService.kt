class NoteService : CRUD<Note> {
    var notes = mutableListOf<Note>()

    override fun add(type: Note): Int {
        notes.add(type.copy(nid = notes.size + 1))
        return notes.last().nid
    }

    override fun delete(type: Note): Boolean {
        notes.remove(searchNote(type.nid))
        return true
    }

    override fun edit(type: Note): Boolean {
        notes[searchNote(type.nid).nid - 1] = type.copy(nid = type.nid)
        return true
    }

    fun get(userIdSearch: Int): List<Note> {
        val result = notes.filter { it.fromId == userIdSearch }
        if (result.isNotEmpty()) {
            return result
        } else throw NoteNotFoundException("У пользователя не найдено заметок")
    }

    fun getById(nidSearch: Int): Note {
        return searchNote(nidSearch)
    }

    private fun searchNote(id: Int): Note {
        notes.forEach() {
            if (it.nid == id) {
                return it
            }
        }
        throw NoteNotFoundException("Заметка c ID $id не найдена")
    }
}