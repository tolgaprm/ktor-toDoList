package com.prmto.database

import com.prmto.entities.ToDo
import com.prmto.entities.ToDoDraft
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    private val hostname = "root@"
    private val databaseName = "ktor_todo"
    private val userName = "root"
    private val password = "123456"

    private lateinit var ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?username=$userName&password=$password&useSSL=true"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllTodos(): List<DBTodoEntity> {
        return ktormDatabase.sequenceOf(DbTodoTable).toList()
    }

    fun getToDo(id: Int): DBTodoEntity? {

        return ktormDatabase.sequenceOf(DbTodoTable).firstOrNull { it.id eq id }
    }

    fun addToDo(draft: ToDoDraft): ToDo {

        val insertedId = ktormDatabase.insertAndGenerateKey(DbTodoTable) {
            set(DbTodoTable.title, draft.title)
            set(DbTodoTable.done, draft.done)
        } as Int

        return ToDo(insertedId, draft.title, draft.done)

    }

    fun updateTodo(id: Int, draft: ToDoDraft): Boolean {
        val updatedRows = ktormDatabase.update(DbTodoTable) {
            set(DbTodoTable.title, draft.title)
            set(DbTodoTable.done, draft.done)
            where {
                it.id eq id
            }
        }

        return updatedRows > 0
    }

    fun removeTodo(id: Int): Boolean {

        val deletedRows = ktormDatabase.delete(DbTodoTable) {
            it.id eq id
        }

        return deletedRows > 0
    }

}