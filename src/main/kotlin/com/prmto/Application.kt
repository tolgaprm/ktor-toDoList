package com.prmto

import io.ktor.application.*
import com.prmto.plugins.*
import com.prmto.routing.configureRoutingToDoList
import io.ktor.features.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module(testing: Boolean = false) {

    install(CallLogging)
    configureSerialization()
    configureRoutingToDoList()

}
