package com.prmto.routing

import com.prmto.entities.ToDoDraft
import com.prmto.repository.ToDoRepository
import com.prmto.repository.ToDoRepositoryImpl
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.HttpStatusCode.Companion as HttpStatusCode

fun Application.configureRoutingToDoList() {

    routing {
        configureTodoList()
    }

}

fun Route.configureTodoList() {

    val repository: ToDoRepository = ToDoRepositoryImpl()

    route("/") {
        get {
            call.respondText("Hello Todo List")
        }

        get("todos") {
            val todoList = repository.getAllToDos()
            if (todoList.isNotEmpty()) {
                call.respond(todoList)
            } else {
                call.respondText("Not Found ToDo List", status = HttpStatusCode.NotFound)
            }
        }

        get("todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()


            if (id == null) {
                call.respondText("Missing format", status = HttpStatusCode.BadRequest)
            } else {


                val todo = repository.getToDo(id.toInt())

                if (todo == null) {
                    call.respondText("No todo with id #$id", status = HttpStatusCode.NotFound)
                } else {
                    call.respond(todo)
                }

            }


        }

        post("todos") {
            val todoDraft = call.receive<ToDoDraft>()

            val todo = repository.addTodo(todoDraft)

            call.respond(todo)

        }

        put("todos/{id}") {

            val todoId = call.parameters["id"]?.toIntOrNull()
            val todoDraft = call.receive<ToDoDraft>()

            if (todoId == null) {
                call.respondText("is parameter has to be a number", status = HttpStatusCode.BadRequest)
                return@put
            }

            val updated = repository.updateToDo(todoId, todoDraft)

            if (updated) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "Found no todo with id #$todoId")
            }

        }

        delete("todos/{id}") {
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null) {
                call.respondText("is parameter has to be a number", status = HttpStatusCode.BadRequest)
                return@delete
            }

            val isDeleted = repository.removeTodo(todoId)

            if (isDeleted) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "Found no todo with id #$todoId")
            }


        }
    }
}