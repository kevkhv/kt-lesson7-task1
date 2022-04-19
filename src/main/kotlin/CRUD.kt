interface CRUD<A> {
    fun add(type: A): Int
    fun delete(type: A): Boolean
    fun edit(type: A): Boolean
}