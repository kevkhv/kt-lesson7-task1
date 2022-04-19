data class Note(
    val nid: Int = 0,                                   // Идентификатор созданной заметки
    val title: String? = null,                          // Заголовок заметки
    val text: String,                                   // Текст заметки
    val fromId: Int,                                    // Идентификатор владельца заметки
    val dateCreated: Int? = null,                       // Дата создания заметки
    var comments:MutableList<Comment> = mutableListOf() // Список с комментариями к заметке
)





