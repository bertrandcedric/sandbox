package fr.bertrand.cedric;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import fr.bertrand.cedric.server.HttpServer;

public class WebTesterHelper extends WebTester {

	private static final int DEFAULT_PORT = 8183;
	private static HttpServer server;

	@BeforeClass
	public static void startService() {
		server = new HttpServer(DEFAULT_PORT);
		server.startAndWait();
	}

	@Before
	public void before() {
		setBaseUrl("http://localhost:" + DEFAULT_PORT);
	}

	@AfterClass
	public static void stopService() {
		if (null != server) {
			server.getChannel().close();
		}
	}

}