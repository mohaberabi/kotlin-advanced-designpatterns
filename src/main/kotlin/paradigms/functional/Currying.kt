package org.example.paradigms.functional


enum class LogLevel {
    Error,
    Info,
    Debug
}


fun log(level: LogLevel, message: String) = println("${level}-${message}")


val errorLog = fun(message: String) {
    log(LogLevel.Error, message)
}

fun createLogger(level: LogLevel): (String) -> Unit {
    return { message: String ->
        log(level, message)
    }
}

fun main() {
    errorLog("Error happened ")
    val infoLogger = createLogger(LogLevel.Info)
    infoLogger("Info info info")
}