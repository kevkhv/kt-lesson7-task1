class CommentService(private val notes: MutableList<Note>) : CRUD<Comment> {

    override fun add(type: Comment): Int {
        notes.forEach() {
            if (it.nid == type.noteId) {
                it.comments.add(type.copy(noteId = it.nid, cid = it.comments.size + 1))
                return it.comments.last().cid
            }
        }
        throw NoteNotFoundException("Заметка с ID ${type.noteId} не найдена")
    }

    override fun delete(type: Comment): Boolean {
        searchComment(type.noteId, type.cid).deleteFlag = true
        return true
    }

    override fun edit(type: Comment): Boolean {
        notes[searchComment(type.noteId, type.cid).noteId - 1].comments[type.cid - 1] =
            type.copy(cid = type.cid)
        return true
    }

    fun getComments(type: Note): List<Comment> {
        val result = type.comments.filter { !it.deleteFlag }
        if (result.isNotEmpty()) {
            return result
        } else throw CommentNotFoundException("У записи с ID ${type.nid} отсутствуют комментарии")
    }

    fun restoreComment(type: Comment): Boolean {
        searchComment(type.noteId, type.cid).deleteFlag = false
        return true
    }

    private fun searchComment(noteId: Int, cid: Int): Comment {
        notes.forEach() { cell ->
            if (cell.nid == noteId) {
                cell.comments.forEach() {
                    if (it.cid == cid) {
                        it.deleteFlag = true
                        return it
                    }
                }
                throw CommentNotFoundException("Комментарий с ID $cid не найден")
            }
        }
        throw NoteNotFoundException("Заметка c ID $noteId не найдена")
    }

}