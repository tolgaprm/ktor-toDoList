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
        return database.getToDo(id)?.let {
            ToDo(it.id, it.title, it.done)
        }
    }

    override fun addTodo(draft: ToDoDraft): ToDo {
        return database.addToDo(draft)
    }

    override fun removeTodo(id: Int): Boolean {
        return database.removeTodo(id)
    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {
        return database.updateTodo(id, draft)
    }
}