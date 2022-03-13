package com.prmto.database

import org.ktorm.database.Database
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

}