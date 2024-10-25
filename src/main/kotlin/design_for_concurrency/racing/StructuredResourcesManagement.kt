package org.example.design_for_concurrency.racing

import arrow.fx.coroutines.continuations.ResourceScope
import arrow.fx.coroutines.resourceScope
import arrow.install
import java.io.File


data class AppFile(
    val name: String = "file.txt",
    val size: Double = 2.0
) {

    fun close() {
        println("Your files size:${size}")
    }

}

@OptIn(ExperimentalStdlibApi::class)
private suspend fun ResourceScope.loadFile(): FileUploader {

    return install(
        { FileUploader().also { println("Upload files") } },
    ) { uplaoder, _ ->
        uplaoder.close()
    }
}

suspend fun uploadFile() = resourceScope {
    loadFile()
}

data class FileUploader(
    val loadedFiles: List<AppFile> = listOf()
) : AutoCloseable {
    override fun close() {

    }
}

fun main() {

}