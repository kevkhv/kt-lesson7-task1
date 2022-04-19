package ru.netology

import Comment
import CommentNotFoundException
import Note
import NoteService
import CommentService
import NoteNotFoundException
import org.junit.Assert.*
import org.junit.Test


class ServiceTest {
    private val noteService = NoteService()
    private val commentService = CommentService(noteService.notes)
    private val note1 = Note(32, "Заголовок", "Первая заметка", 44)
    private val note2 = Note(22, "Заголовок2", "Вторая заметка", 55)
    private val note3 = Note(1, "Заголовок", "Первая заметка", 44)
    private val note4 = Note(3, "Заголовок", "Первая заметка", 44)
    private val comment1 = Comment(1, 22, 345, "Первый комментарий")
    private val comment2 = Comment(2, 22, 232, "Второй комментарий")
    private val comment3 = Comment(1, 1, 44, "Третий комментарий")
    private val note5 = Note(1, "Заголовок", "Первая заметка", 44, comments = mutableListOf(comment1))

    @Test(expected = NoteNotFoundException::class)
    fun delete_returnNoteNotFoundException() {
        //arrange
        noteService.add(note1)
        //act
        val actualException = noteService.delete(note2)
    }

    @Test
    fun delete_returnTrue() {
        //arrange
        noteService.add(note1)
        //act
        val result = noteService.delete(note3)
        //assert
        assertTrue(result)
    }

    @Test
    fun edit_returnTrue() {
        //arrange
        noteService.add(note1)
        //act
        val result = noteService.edit(note3)
        //assert
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun edit_returnNoteNotFoundException() {
        //arrange
        noteService.add(note1)
        //act
        val actualException = noteService.edit(note2)
    }

    @Test
    fun get_returnListNote() {
        //arrange
        noteService.add(note1)
        noteService.add(note2)
        noteService.add(note3)
        val expected: List<Note> = listOf(note3, note4)
        val actual = noteService.get(44)
        //assert
        assertEquals(expected, actual)
    }

    @Test(expected = NoteNotFoundException::class)
    fun get_returnException() {
        //arrange
        noteService.add(note1)
        //act
        val actual = noteService.get(222)
    }

    @Test
    fun getById_returnNote() {
        //arrange
        noteService.add(note1)
        val expected = note3
        val actual = noteService.getById(1)
        //assert
        assertEquals(expected, actual)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getById_returnException() {
        //arrange
        noteService.add(note1)
        //act
        val actual = noteService.getById(222)
    }

    @Test
    fun addComment_returnCid() {
        //arrange
        noteService.add(note1)
        val expectedCid = 1
        //act
        val actualCid = commentService.add(comment1)
        //assert
        assertEquals(expectedCid, actualCid)




    }

    @Test(expected = NoteNotFoundException::class)
    fun addComment_returnException(){
        //arrange
        noteService.add(note1)
        //act
        commentService.add(comment2)
    }

    @Test
    fun editComment_returnTrue(){
        //arrange
        noteService.add(note1)
        commentService.add((comment1))
        //act
        val result = commentService.edit(comment3)
        //assert
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editComment_returnCommentNotFoundException() {
        //arrange
        noteService.add(note1)
        commentService.add((comment1))
        //act
        commentService.edit(comment1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editComment_returnNoteNotFoundException() {
        //arrange
        noteService.add(note1)
        commentService.add((comment1))
        //act
        commentService.edit(comment2)
    }

    @Test
    fun deleteComment_returnTrue(){
        //arrange
        noteService.add(note1)
        commentService.add((comment1))
        //act
        val result = commentService.delete(comment3)
        //assert
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteComment_returnCommentNotFoundException() {
        //arrange
        noteService.add(note1)
        commentService.add((comment1))
        //act
        commentService.delete(comment1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteComment_returnNoteNotFoundException() {
        //arrange
        noteService.add(note1)
        commentService.add((comment1))
        //act
        commentService.delete(comment2)
    }

    @Test
    fun getComments_returnListComment() {
        //arrange
        noteService.add(note5)
        val expected: List<Comment> = listOf(comment1)
        val actual = commentService.getComments(note5)
        //assert
        assertEquals(expected, actual)
    }

    @Test(expected = CommentNotFoundException::class)
    fun getComments_returnException() {
        //arrange
        noteService.add(note5)
        //act
        val actual = commentService.getComments(note2)
    }

    @Test
    fun restoreComment_returnTrue(){
        //arrange
        noteService.add(note5)
        commentService.delete(comment1)
        //act
        val result = commentService.restoreComment(comment1)
        //assert
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreComment_returnCommentNotFoundException() {
        //arrange
        noteService.add(note5)
        commentService.delete(comment1)
        //act
        val result = commentService.restoreComment(comment3)
    }

    @Test(expected = NoteNotFoundException::class)
    fun restoreComment_returnNoteNotFoundException() {
        //arrange
        noteService.add(note5)
        commentService.delete(comment1)
        //act
        val result = commentService.restoreComment(comment2)
    }
}
