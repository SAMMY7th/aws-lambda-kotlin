package net.nocono

import java.io.InputStream
import java.io.OutputStream
import java.net.URLDecoder
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.*

class Main {
  val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  fun handler(input: InputStream, output: OutputStream): Unit {
    val params = mapper.readValue<SlackOutgoingData>(input)
    print(params)
    val result: String? = when(params.decodedText) {
      "こんにちは" -> "こんにちは, ${params.user_name}!!"
      "Kotlin"     -> "かわいい"
      else         -> null
    }

    result?.let {
      output.write("""{ "text": "$result" }""".toByteArray())
    }
  }

}

data class SlackOutgoingData(
    val token: String,
    val team_id: String,
    val team_domain: String,
    val service_id: String,
    val channel_id: String,
    val channel_name: String,
    val timestamp: String,
    val user_id: String,
    val user_name: String,
    val text: String,
    val trigger_word: String?) {

    val decodedText = URLDecoder.decode(text, "UTF-8")
}