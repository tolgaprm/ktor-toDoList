package com.prmto.repository

import com.prmto.database.DatabaseManager
import com.prmto.entities.ToDo
import com.prmto.entities.ToDoDraft

class MySQLTodoRepository : ToDoRepository {

    private val database = DatabaseManager()

    override fun getAllToDos(): List<ToDo> {
        return database.getAllTodos().map { ToDo(it.id, it.title, it.done) }
    }

    override fun getToDo(id: Int): ToDo? {
        TODO("Not yet implemented")
    }

    override fun addTodo(draft: ToDoDraft): ToDo {
        TODO("Not yet implemented")
    }

    override fun removeTodo(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {
        TODO("Not yet implemented")
    }
}