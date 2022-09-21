
import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korio.net.http.*
import kotlinx.serialization.*

@Serializable
data class Fact(
    val fact: String,
    val type: String)
suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()
    Text("lol", textSize = 20.0, color = Colors.WHITE).centerOnStage()
	sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
	override suspend fun SContainer.sceneMain() {
        Text("Loading...").position(10, 10)
		var client = HttpClient()
        var request = client.request(Http.Method.GET, "https://fsrb.tech/api/facts/random")

        var jsonString = request.readAllString()
        val factString = kotlinx.serialization.json.Json.decodeFromString<Fact>(jsonString)

        Text("lol", textSize = 20.0, color = Colors.WHITE).centerOnStage()
        println(factString.fact)
        Text(factString.fact, textSize = 20.0, color = Colors.WHITE).centerOnStage()
	}
}
