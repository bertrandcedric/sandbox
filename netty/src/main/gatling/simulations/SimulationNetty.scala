import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._

class SimulationNetty extends Simulation {

	def apply = {
		val urlBase = "http://localhost:8080"

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

		val scn1 = scenario("Scenario by code village")
				.loop(
					chain
					.exec(
						(session: Session) => {
							session.setAttributes(
								Map("test" -> 1)
							)
						}
					)
					.pause(100, 500, MILLISECONDS)
					.exec(http("request by code village")
						.get("/rest/test?${test}")
						.headers(headers_0)
					)
				)
				.during(3, MINUTES)
				
		List(
			scn1.configure.users(100).ramp(60).protocolConfig(httpConf),
			scn1.configure.users(200).ramp(60).delay(60).protocolConfig(httpConf)
		)
	}
}