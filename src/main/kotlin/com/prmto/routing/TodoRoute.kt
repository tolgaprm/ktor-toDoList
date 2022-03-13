package com.prmto.routing

import com.prmto.entities.ToDo
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.registerRoute() {

    routing {
        configureTodoList()
    }

}

fun Route.configureTodoList() {

    route("/") {

        val todoList = listOf(
            ToDo(1, "Plan Content for video #2", true),
            ToDo(2, "Record Video #2", false),
            ToDo(3, "Upload video #2", false),

            )

        get {
            call.respondText("Hello Todo List")
        }

        get("todos") {
            call.respond(todoList)

        }

        get("todos/{id}") {
            val id = call.parameters["id"]

            call.respondText("TodoList Details for ToDo Item #$id")
        }

        post("todos") {

        }

        put("todos/{id}") {


        }

        delete("todos/{id}") {

        }
    }
}