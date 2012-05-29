package fr.bertrand.cedric.controller;

import org.junit.Test;

import fr.bertrand.cedric.WebTesterHelper;

public class ErrorControllerTest extends WebTesterHelper {

	@Test
	public void showHome() {
		beginAt("/t");

		assertTextPresent("Unable to find controller for action '/t'.");
	}
}