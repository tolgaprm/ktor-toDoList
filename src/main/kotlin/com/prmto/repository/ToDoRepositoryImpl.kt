package com.prmto.repository

import com.prmto.entities.ToDo
import com.prmto.entities.ToDoDraft

class ToDoRepositoryImpl : ToDoRepository {

    val todoList = mutableListOf<ToDo>()

    override fun getAllToDos(): List<ToDo> {
        return todoList
    }

    override fun getToDo(id: Int): ToDo? {
        return todoList.firstOrNull { it.id == id }
    }

    override fun addTodo(draft: ToDoDraft): ToDo {

        val todo = ToDo(
            id = todoList.size + 1,
            title = draft.title,
            done = draft.done
        )

        todoList.add(todo)

        return todo
    }

    override fun removeTodo(id: Int): Boolean {
        return todoList.removeIf { it.id == id }
    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {

        val todo = todoList.firstOrNull { it.id == id } ?: return false

        todo.title = draft.title
        todo.done = draft.done

        return true
    }
}