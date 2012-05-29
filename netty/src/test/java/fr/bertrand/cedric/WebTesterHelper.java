package fr.bertrand.cedric;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.After;
import org.junit.Before;

import fr.bertrand.cedric.server.HttpServer;

public class WebTesterHelper extends WebTester {

	private static final int DEFAULT_PORT = 8183;
	private HttpServer server;

	@Before
	public void startService() {
		server = new HttpServer(DEFAULT_PORT);
		server.startAndWait();

		setBaseUrl("http://localhost:" + DEFAULT_PORT);
	}

	@After
	public void stopService() {
		if (null != server) {
			server.getChannel().close();
		}
	}

}