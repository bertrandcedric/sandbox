import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._

class SimulationStatification extends Simulation {

	def apply = {
		//val urlBase = "http://localhost:8080"
		val urlBase = "http://localhost:8080/statification"

		val httpConf = httpConfig
						.baseURL(urlBase)


		val headers_0 = Map(
			"Accept" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8""",
			"Accept-Charset" -> """ISO-8859-1,utf-8;q=0.7,*;q=0.3""",
			"Accept-Encoding" -> """gzip,deflate,sdch""",
			"Accept-Language" -> """fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4""",
			"Cache-Control" -> """no-cache""",
			"Host" -> """localhost:8080""",
			"Pragma" -> """no-cache""",
			"User-Agent" -> """Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.4 (KHTML, like Gecko) Chrome/16.0.889.0 Safari/535.4""")


		val scn = scenario("Scenario name")
				.loop(
					chain
					.exec(
						(session: Session) => session.setAttribute("data", math.round(math.random * 100000))
					)
					.pause(100, 300, MILLISECONDS)
					.exec(http("request_1")
						.get("/rest/offre/codeVillage/${data}/codeOccupation/1/codeVilleDepartDemande/ABCDEF")
						.headers(headers_0)
					)
				)
				.during(300, SECONDS)

		List(
			scn.configure.users(1000).ramp(10).protocolConfig(httpConf)
		)
	}
}