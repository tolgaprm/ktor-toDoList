package com.prmto.repository

import com.prmto.entities.ToDo
import com.prmto.entities.ToDoDraft

interface ToDoRepository {

    fun getAllToDos(): List<ToDo>

    fun getToDo(id: Int): ToDo?

    fun addTodo(draft: ToDoDraft): ToDo

    fun removeTodo(id: Int): Boolean

    fun updateToDo(id: Int, draft: ToDoDraft): Boolean
}