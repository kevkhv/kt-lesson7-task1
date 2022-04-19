data class Comment(
    val noteId:Int,                  // Идентификатор заметки, к которой создан комментарий
    var cid: Int = 0,                // Идентификатор комментария
    val ownerId: Int? = null,        // Идентификатор владельца комментария
    val message: String,             // Текст комментария
    var deleteFlag:Boolean = false   // Информация о том, что комментарий удален (true — удален, false — не удален)
)
