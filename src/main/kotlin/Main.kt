fun main() {
    val noteService = NoteService()
    val commentService = CommentService(noteService.notes)
}