package org.example.design_for_concurrency.pipeline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Document
import javax.print.Doc


private fun CoroutineScope.producePages() = produce {


    fun getPages(): List<String> {
        return listOf(
            "<html><body><h1>Cool Stuff</h1></body></html>",
            "<html><body><h1>Cool Stuff</h1></body></html>"
        )
    }

    val pages = getPages()
    while (this.isActive) {
        for (p in pages) {
            send(p)
        }
    }
}

data class ParsedDoc(val page: String)

private fun CoroutineScope.produceParsed(
    pages: ReceiveChannel<String>,
) = produce {


    fun parse(page: String): ParsedDoc {
        return ParsedDoc(page)
    }

    for (p in pages) {
        send(parse(p))
    }
}


private fun CoroutineScope.produceTitles(
    parsed: ReceiveChannel<ParsedDoc>,
) = produce {


    fun getTitle(doc: ParsedDoc): List<String> {

        return listOf(doc.page)
    }

    for (p in parsed) {
        send(getTitle(p))
    }
}


fun main() {
    runBlocking {
        val pageProducer = producePages()
        val parsedProducer = produceParsed(pageProducer)
        val titleProducer = produceTitles(parsedProducer)
        for (t in titleProducer) {
            println(t)
        }
    }
}