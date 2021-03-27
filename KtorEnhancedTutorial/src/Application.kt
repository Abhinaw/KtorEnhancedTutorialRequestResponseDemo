package com.example

import com.example.entities.LoginRequest
import com.example.entities.LoginResponse
import com.example.entities.ToDo
import com.example.entities.ToDoDraft
import com.example.repository.InMemoryToDoRepository
import com.example.repository.ToDoRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.gson.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(CallLogging)
    install(ContentNegotiation)
    {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        val repository: ToDoRepository = InMemoryToDoRepository()

        get("/") {
            call.respondText("HELLO Ktor!")
        }
        post("/login")
        {
            val loginRequest = call.receive<LoginRequest>()
            if (loginRequest.username == "admin" && loginRequest.password == "adminpwd") {
                call.respond(LoginResponse(true, "Login Successfull!!"))
            } else {
                call.respond(LoginResponse(false, "Credentials are invalid!!"))
            }
        }

        get("/todos")
        {
            call.respond(repository.getAllToDos())
        }
        get("/todos/{id}")
        {
            val id = call.parameters["id"]
            call.respondText("Todolist Details for todo items #$id")
        }
        post("/todos")
        {
            val todoDraft = call.receive<ToDoDraft>()
            val todo = repository.addToDo(todoDraft)
            call.respond(todo)
        }
        put("/todos/{id}")
        {
            val todoDraft = call.receive<ToDoDraft>()
            val todoId = call.parameters["id"]?.toIntOrNull()
            if (todoId == null) {
                call.respond(HttpStatusCode.BadRequest, "id  parameter has to be a number!")
                return@put
            }
            val update = repository.updatetodo(todoId, todoDraft)
            if (update) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "found no todo with the id $todoId")
            }
        }
        delete("/todos/{id}")
        {
            val todoDraft = call.receive<ToDoDraft>()
            val todoId = call.parameters["id"]?.toIntOrNull()
            if (todoId == null) {
                call.respond(HttpStatusCode.BadRequest, "id  parameter has to be a number!")
                return@delete
            }
            val removed = repository.removeTodo(todoId)
            if (removed) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "removed no todo with the id $todoId")
            }
        }
    }
}

