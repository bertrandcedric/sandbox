package fr.bertrand.cedric.controller;

import static org.junit.Assert.assertTrue;
import net.sourceforge.jwebunit.exception.TestingEngineResponseException;

import org.junit.Test;

import fr.bertrand.cedric.WebTesterHelper;

public class ControllerTest extends WebTesterHelper {

	@Test
	public void error() {
		beginAt("/t");

		assertTextPresent("Unable to find controller for action '/t'.");
	}

	@Test
	public void index() {
		beginAt("/");

		assertTextPresent("Test");
	}

	@Test
	public void list() {
		beginAt("/list");

		assertTextPresent("[\"toto1\",\"toto2\",\"toto3\"]");
	}

	@Test
	public void resourceFavIcon() {
		try {
			beginAt("/favicon.ico");
			assertTrue(true);
		} catch (TestingEngineResponseException e) {
			assertTrue(false);
		}
	}

	@Test
	public void resource() {
		beginAt("/resources/js/main.js");

		assertTextPresent("mon javascript");
	}
}